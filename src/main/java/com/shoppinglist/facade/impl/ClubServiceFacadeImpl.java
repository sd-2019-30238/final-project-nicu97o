package com.shoppinglist.facade.impl;

import com.shoppinglist.facade.ClubServiceFacade;
import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.dto.ClubDTO;
import com.shoppinglist.model.mapper.Mapper;
import com.shoppinglist.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubServiceFacadeImpl implements ClubServiceFacade {
    private Mapper<Club, ClubDTO> mapper;
    private ClubService clubService;

    @Autowired
    public ClubServiceFacadeImpl(Mapper<Club, ClubDTO> mapper, ClubService clubService) {
        this.mapper = mapper;
        this.clubService = clubService;
    }

    @Override
    public ClubDTO getClubById(long id) {
        return mapper.convertToDTO(clubService.getClubById(id));
    }

    @Override
    public void addClub(ClubDTO clubDTO, String username) {
        clubService.addClub(mapper.convertToEntity(clubDTO), username);
    }

    @Override
    public List<ClubDTO> getClubsByUsersUsername(String username) {
        return clubService.getClubsByUsersUsername(username).stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void joinClub(String code, String username) {
        clubService.joinClub(code, username);
    }
}
