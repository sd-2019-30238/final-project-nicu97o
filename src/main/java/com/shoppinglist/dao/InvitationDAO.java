package com.shoppinglist.dao;

import com.shoppinglist.model.database.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationDAO extends JpaRepository<Invitation, Long> {
    List<Invitation> findAllByReceiverUsernameAndUsedFalse(String username);
}
