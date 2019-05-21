package com.shoppinglist.service.impl;

import com.shoppinglist.dao.InvitationDAO;
import com.shoppinglist.exception.NoInvitationFoundException;
import com.shoppinglist.exception.UserAlreadyAMemberException;
import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.database.Invitation;
import com.shoppinglist.model.database.User;
import com.shoppinglist.service.ClubService;
import com.shoppinglist.service.EmailService;
import com.shoppinglist.service.InvitationService;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {
    private static final String LINK = "http://localhost:8080";
    private UserService userService;
    private ClubService clubService;
    private InvitationDAO invitationDAO;
    private EmailService emailService;

    @Autowired
    public InvitationServiceImpl(UserService userService, ClubService clubService, InvitationDAO invitationDAO, EmailService emailService) {
        this.userService = userService;
        this.clubService = clubService;
        this.invitationDAO = invitationDAO;
        this.emailService = emailService;
    }

    @Override
    public List<Invitation> getAllUnconfirmedInvitationsForAnUser(String username) {
        userService.getUserByUsername(username);
        return invitationDAO.findAllByReceiverUsernameAndUsedFalse(username);
    }

    @Override
    public Invitation getInvitationById(long id) {
        return invitationDAO.findById(id).orElseThrow(() -> new NoInvitationFoundException());
    }

    @Override
    public void useInvitation(long invitationId) {
        Invitation invitation = getInvitationById(invitationId);
        clubService.joinClub(invitation.getInviteCode(), invitation.getReceiver().getUsername());
        invitation.setUsed(true);
        invitationDAO.save(invitation);
    }

    @Override
    public void sendInvitationByUsername(long clubId, String senderUsername, String receiverUsername) {
        User receiver = userService.getUserByUsername(receiverUsername);
        createInvitation(senderUsername, clubId, receiver);
    }

    @Override
    public void sendInvitationByMail(long clubId, String senderUsername, String receiverMail) {
        User receiver = userService.getUserByMail(receiverMail);
        Invitation invitation = createInvitation(senderUsername, clubId, receiver);
        String activationLink = LINK + "/invitations/use/" + invitation.getId();
        try {
            emailService.sendMail(receiverMail, "Invitation to group " + senderUsername, activationLink);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Invitation createInvitation(String senderUsername, long clubId, User receiver) {
        User sender = userService.getUserByUsername(senderUsername);
        Club club = clubService.getClubById(clubId);
        if (receiver.getClubs().contains(club)) {
            throw new UserAlreadyAMemberException(receiver.getUsername() + " is already a user of club with id " + clubId + "!");
        }
        Invitation invitation = new Invitation(null, club.getInviteCode(), false, sender, receiver);
        invitationDAO.save(invitation);
        return invitation;
    }
}
