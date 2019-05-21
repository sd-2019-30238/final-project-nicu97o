package com.shoppinglist.service;

import com.shoppinglist.configuration.DatabaseConfiguration;
import com.shoppinglist.exception.NoInvitationFoundException;
import com.shoppinglist.model.database.Invitation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

import static com.shoppinglist.service.DatabaseInsertedData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class InvitationServiceTest {
    @MockBean
    private EmailService emailService;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetAllUnconfirmedInvitationsForAnUser_shouldBeSuccessful() {
        List<Invitation> invitations = invitationService.getAllUnconfirmedInvitationsForAnUser(INSERTED_USER_2.getUsername());
        System.out.println(invitations.get(0).getId());
        assertTrue(invitations.contains(INSERTED_INVITATION_1));
    }

    @Test
    public void testGetInvitationById_shouldBeSuccessful() {
        Invitation invitation = invitationService.getInvitationById(INSERTED_INVITATION_1.getId());
        assertThat(invitation, is(INSERTED_INVITATION_1));
    }

    @Test(expected = NoInvitationFoundException.class)
    public void testGetInvitationByIdWhenNoInvitationWithWantedIdFound_shouldThrowException() {
        invitationService.getInvitationById(0L);
    }

    @Test
    public void testUseInvitation_shouldBeSuccessful() {
        invitationService.useInvitation(INSERTED_INVITATION_1.getId());
        Invitation invitation = invitationService.getInvitationById(INSERTED_INVITATION_1.getId());
        assertTrue(invitation.isUsed());
    }

    @Test
    public void testSendInvitationByUsername_shouldBeSuccessful() {
        invitationService.sendInvitationByUsername(ID_OF_CLUB_WHICH_USER_1_IS_MEMBER, INSERTED_USER_1.getUsername(), INSERTED_USER_2.getUsername());
        assertThat(NR_OF_INITIAL_INVITATION + 1, is(JdbcTestUtils.countRowsInTable(jdbcTemplate, INVITATION_TABLE)));
    }

    @Test
    public void testSendInvitationByMail_shouldBeSuccessful() throws MessagingException {
        doNothing().when(emailService).sendMail(anyString(), anyString(), anyString());
        invitationService.sendInvitationByMail(ID_OF_CLUB_WHICH_USER_1_IS_MEMBER, INSERTED_USER_1.getUsername(), INSERTED_USER_2.getMail());
        assertThat(NR_OF_INITIAL_INVITATION + 1, is(JdbcTestUtils.countRowsInTable(jdbcTemplate, INVITATION_TABLE)));
    }
}
