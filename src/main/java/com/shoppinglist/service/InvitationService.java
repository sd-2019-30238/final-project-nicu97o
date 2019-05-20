package com.shoppinglist.service;

import com.shoppinglist.model.database.Invitation;

import java.util.List;

public interface InvitationService {
    List<Invitation> getAllUnconfirmedInvitationsForAnUser(String username);

    Invitation getInvitationById(long id);

    void useInvitation(long invitationId);

    void sendInvitationByUsername(long clubId, String senderUsername, String receiverUsername);

    void sendInvitationByMail(long clubId, String senderUsername, String receiverMail);
}
