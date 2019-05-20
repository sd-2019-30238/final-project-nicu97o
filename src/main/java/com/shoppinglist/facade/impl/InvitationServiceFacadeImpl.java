package com.shoppinglist.facade.impl;

import com.shoppinglist.facade.InvitationServiceFacade;
import com.shoppinglist.model.database.Invitation;
import com.shoppinglist.model.dto.InvitationDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvitationServiceFacadeImpl implements InvitationServiceFacade {
    private InvitationService invitationService;
    private Mapper<Invitation, InvitationDTO> mapper;

    @Autowired
    public InvitationServiceFacadeImpl(InvitationService invitationService, Mapper<Invitation, InvitationDTO> mapper) {
        this.invitationService = invitationService;
        this.mapper = mapper;
    }

    @Override
    public List<InvitationDTO> getAllUnconfirmedInvitationsForAnUser(String username) {
        return invitationService.getAllUnconfirmedInvitationsForAnUser(username).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void sendInvitationByUsername(long clubId, String senderUsername, String receiverUsername) {
        invitationService.sendInvitationByUsername(clubId, senderUsername, receiverUsername);
    }

    @Override
    public void sendInvitationByMail(long clubId, String senderUsername, String receiverMail) {
        invitationService.sendInvitationByMail(clubId, senderUsername, receiverMail);
    }

    @Override
    public InvitationDTO getInvitationById(long id) {
        return mapper.convertToDTO(invitationService.getInvitationById(id));
    }

    @Override
    public void useInvitation(long invitationId) {
        invitationService.useInvitation(invitationId);
    }
}
