package com.shoppinglist.service;

import com.shoppinglist.model.database.Club;

import java.util.List;

public interface ClubService {
    Club getClubById(long id);

    void addClub(Club club, String username);

    void joinClub(String code, String username);

    List<Club> getClubsByUsersUsername(String username);
}
