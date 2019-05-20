package com.shoppinglist.facade;

import com.shoppinglist.model.dto.InvitationDTO;

import java.util.List;

public interface InvitationServiceFacade {
    List<InvitationDTO> getAllUnconfirmedInvitationsForAnUser(String username);

    void sendInvitationByUsername(long clubId, String senderUsername, String receiverUsername);

    void sendInvitationByMail(long clubId, String senderUsername, String receiverMail);

    InvitationDTO getInvitationById(long id);

    void useInvitation(long invitationId);
}
