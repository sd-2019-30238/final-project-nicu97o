package com.shoppinglist.service.impl;

import com.shoppinglist.dao.ClubDAO;
import com.shoppinglist.exception.NoClubFoundException;
import com.shoppinglist.exception.UserAlreadyAMemberException;
import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.database.User;
import com.shoppinglist.service.ClubService;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClubServiceImpl implements ClubService {
    private ClubDAO clubDAO;
    private UserService userService;

    @Autowired
    public ClubServiceImpl(ClubDAO clubDAO, UserService userService) {
        this.clubDAO = clubDAO;
        this.userService = userService;
    }

    @Override
    public Club getClubById(long id) {
        return clubDAO.findById(id).orElseThrow(() -> new NoClubFoundException("No club with id " + id + " found!"));
    }

    @Override
    public void addClub(Club club, String username) {
        club.setInviteCode(UUID.randomUUID().toString());
        User user = userService.getUserByUsername(username);
        club.setId(null);
        club.getUsers().add(user);
        user.getClubs().add(club);
        clubDAO.save(club);
    }

    @Override
    public void joinClub(String code, String username) {
        Club club = clubDAO.findClubByInviteCode(code).orElseThrow(() -> new NoClubFoundException("No club with invite code " + code + " found!"));
        User user = userService.getUserByUsername(username);
        for (User member : club.getUsers()) {
            if (member.equals(user)) {
                throw new UserAlreadyAMemberException(username + " is already a member of " + club.getName());
            }
        }
        club.getUsers().add(user);
        user.getClubs().add(club);
        clubDAO.save(club);
    }

    @Override
    public List<Club> getClubsByUsersUsername(String username) {
        return clubDAO.findClubsByUsers(userService.getUserByUsername(username));
    }
}
