package com.shoppinglist.dao;

import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenDAO extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByUserUsernameAndTokenType(String username, TokenType tokenType);

    Optional<Token> findTokenByTokenValue(String tokenValue);
}
