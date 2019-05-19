package com.shoppinglist.model.mapper.impl;

import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.dto.ClubDTO;
import com.shoppinglist.model.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ClubMapper implements Mapper<Club, ClubDTO> {
    @Override
    public Club convertToEntity(ClubDTO clubDTO) {
        Club club = new Club();
        club.setId(clubDTO.getId());
        club.setName(clubDTO.getName());
        club.setInviteCode(clubDTO.getInviteCode());
        return club;
    }

    @Override
    public ClubDTO convertToDTO(Club club) {
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(club.getId());
        clubDTO.setName(club.getName());
        clubDTO.setInviteCode(club.getInviteCode());
        return clubDTO;
    }
}
