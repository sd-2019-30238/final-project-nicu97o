package com.shoppinglist.service;

import com.shoppinglist.configuration.DatabaseConfiguration;
import com.shoppinglist.exception.NoUserFoundException;
import com.shoppinglist.model.database.User;
import com.shoppinglist.model.database.UserType;
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

import static com.shoppinglist.service.DatabaseInsertedData.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddUser_shouldBeSuccessful() {
        userService.addUser(UNISERTED_USER_1);
        assertThat(NR_OF_INITIAL_USERS, is(JdbcTestUtils.countRowsInTable(jdbcTemplate, USER_TABLE) - 1));
    }

    @Test
    public void testGetUserById_shouldBeSuccessful() {
        User user = userService.getUserById(INSERTED_USER_1.getId());
        assertThat(user, is(INSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByIdWhenUserIsNotInDatabase_shouldThrowException() {
        userService.getUserById(0L);
    }

    @Test
    public void testGetUserByUsername_shouldBeSuccessful() {
        User user = userService.getUserByUsername(INSERTED_USER_1.getUsername());
        assertThat(user, is(INSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByUsernameWhenNoUserFound_shouldThrowException() {
        userService.getUserByUsername("");
    }

    @Test
    public void testGetUserByMail_shouldBeSuccessful() {
        User user = userService.getUserByMail(INSERTED_USER_1.getMail());
        assertThat(user, is(INSERTED_USER_1));
    }

    @Test(expected = NoUserFoundException.class)
    public void testGetUserByMailWhenNoUserFound_shouldThrowException() {
        userService.getUserByMail("");
    }

    @Test
    public void testGetAllUsers_shouldBeSuccessful() {
        List<User> users = userService.getAllUsers();
        assertThat(NR_OF_INITIAL_USERS, is(users.size()));
    }

    @Test
    public void testUpdateUser_shouldBeSuccessful() {
        User user = new User(INSERTED_USER_1.getId(), INSERTED_USER_1.getUsername(), "newMail@yahoo.com", "newPassword", INSERTED_USER_1.getUserType(), INSERTED_USER_1.isConfirmed());
        userService.updateUser(user);
        assertThat(user, is(userService.getUserById(user.getId())));
    }

    @Test
    public void testDeleteUser_shouldBeSuccessful() {
        userService.deleteUser(INSERTED_USER_1.getUsername());
        List<User> users = userService.getAllUsers();
        assertTrue(!users.contains(INSERTED_USER_1));
    }

    @Test
    public void testChangeMail_shouldBeSuccessful() {
        String newMail = "newMail@yahoo.com";
        userService.changeMail(INSERTED_USER_1.getUsername(), newMail);
        User user = userService.getUserById(INSERTED_USER_1.getId());
        assertThat(user.getMail(), is(newMail));
    }

    @Test
    public void testGetUsersOfAClubByClubsId_shouldBeSuccessful() {
        List<User> users = userService.getUsersOfAClubByClubsId(ID_OF_CLUB_WHICH_USER_1_IS_MEMBER);
        assertTrue(users.contains(INSERTED_USER_1));
    }
}
