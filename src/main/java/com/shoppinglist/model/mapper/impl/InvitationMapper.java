package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.Invitation;
import com.shoppinglist.model.database.User;
import com.shoppinglist.model.dto.InvitationDTO;
import com.shoppinglist.model.dto.UserDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvitationMapper implements Mapper<Invitation, InvitationDTO> {
    private Mapper<User, UserDTO> userMapper;

    @Autowired
    public InvitationMapper(Mapper<User, UserDTO> userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Invitation convertToEntity(InvitationDTO invitationDTO) {
        if (invitationDTO == null) {
            return null;
        }
        Invitation invitation = new Invitation();
        invitation.setId(invitationDTO.getId());
        invitation.setInviteCode(invitationDTO.getInviteCode());
        invitation.setUsed(invitationDTO.isUsed());
        invitation.setReceiver(userMapper.convertToEntity(invitationDTO.getReceiver()));
        invitation.setSender(userMapper.convertToEntity(invitationDTO.getSender()));
        return invitation;
    }

    @Override
    public InvitationDTO convertToDTO(Invitation invitation) {
        if (invitation == null) {
            return null;
        }
        InvitationDTO invitationDTO = new InvitationDTO();
        invitationDTO.setId(invitation.getId());
        invitationDTO.setInviteCode(invitation.getInviteCode());
        invitationDTO.setUsed(invitation.isUsed());
        invitationDTO.setReceiver(userMapper.convertToDTO(invitation.getReceiver()));
        invitationDTO.setSender(userMapper.convertToDTO(invitation.getSender()));
        return invitationDTO;
    }
}
