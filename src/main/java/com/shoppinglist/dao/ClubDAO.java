package com.shoppinglist.dao;

import com.shoppinglist.model.database.Club;
import com.shoppinglist.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClubDAO extends JpaRepository<Club, Long> {
    List<Club> findClubsByUsers(User user);

    Optional<Club> findClubByInviteCode(String inviteCode);
}
