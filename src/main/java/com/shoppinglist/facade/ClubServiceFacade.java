package com.shoppinglist.facade;

import com.shoppinglist.model.dto.ClubDTO;

import java.util.List;

public interface ClubServiceFacade {
    ClubDTO getClubById(long id);

    void addClub(ClubDTO clubDTO, String username);

    List<ClubDTO> getClubsByUsersUsername(String username);

    void joinClub(String code, String username);

    void leaveClub(long clubId, String username);
}
