package com.shoppinglist.service;

import com.shoppinglist.configuration.DatabaseConfiguration;
import com.shoppinglist.exception.NoClubFoundException;
import com.shoppinglist.exception.UserAlreadyAMemberException;
import com.shoppinglist.model.database.Club;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.shoppinglist.service.DatabaseInsertedData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class ClubServiceTest {
    @Autowired
    private ClubService clubService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetClubById_shouldBeSuccessful() {
        Club club = clubService.getClubById(INSERTED_CLUB_1.getId());
        assertThat(club, is(INSERTED_CLUB_1));
    }

    @Test(expected = NoClubFoundException.class)
    public void testGetClubByIdWhenNoClubWithWantedIdFound_shouldThrowException() {
        clubService.getClubById(0L);
    }

    @Test
    public void testAddClub_shouldBeSuccessful() {
        clubService.addClub(new Club(null, "newClub", UUID.randomUUID().toString()), INSERTED_USER_1.getUsername());
        assertThat(NR_OF_INITIAL_CLUBS + 1, is(JdbcTestUtils.countRowsInTable(jdbcTemplate, CLUB_TABLE)));
    }

    @Test
    public void testJoinClub_shouldBeSuccessful() {
        clubService.joinClub(INSERTED_CLUB_1.getInviteCode(), INSERTED_USER_2.getUsername());
        List<Club> clubs = clubService.getClubsByUsersUsername(INSERTED_USER_2.getUsername());
        assertTrue(clubs.contains(INSERTED_CLUB_1));
    }

    @Test(expected = UserAlreadyAMemberException.class)
    public void testJoinClubWhenUserIsAlreadyAMember_shouldThrowException() {
        clubService.joinClub(INSERTED_CLUB_1.getInviteCode(), INSERTED_USER_1.getUsername());
    }

    @Test
    public void testLeaveClub_shouldBeSuccessful() {
        clubService.leaveClub(INSERTED_USER_1.getId(), INSERTED_USER_1.getUsername());
        List<Club> clubs = clubService.getClubsByUsersUsername(INSERTED_USER_1.getUsername());
        assertTrue(!clubs.contains(INSERTED_CLUB_1));
    }

    @Test
    public void testGetClubsByUsersUsername_shouldBeSuccessful() {
        List<Club> clubs = clubService.getClubsByUsersUsername(INSERTED_USER_1.getUsername());
        assertTrue(clubs.contains(INSERTED_CLUB_1));
    }
}
