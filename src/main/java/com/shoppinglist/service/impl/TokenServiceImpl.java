package com.shoppinglist.service.impl;

import com.shoppinglist.dao.TokenDAO;
import com.shoppinglist.exception.TokenNotFoundException;
import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.TokenType;
import com.shoppinglist.model.database.User;
import com.shoppinglist.service.TokenService;
import com.shoppinglist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    private static final int EXPIRATION_TIME_IN_HOURS = 24;
    private TokenDAO tokenDAO;
    private UserService userService;

    @Autowired
    public TokenServiceImpl(TokenDAO tokenDAO, UserService userService) {
        this.tokenDAO = tokenDAO;
        this.userService = userService;
    }

    @Override
    public Token createToken(String username, TokenType tokenType) {
        String tokenValue = UUID.randomUUID().toString();
        User user = userService.getUserByUsername(username);
        Token token = new Token(null, tokenValue, computeExpiryDateOfAToken(), tokenType, false, user);
        tokenDAO.save(token);
        return token;
    }

    @Override
    public Token getToken(String tokenValue) {
        return tokenDAO.findTokenByTokenValue(tokenValue).orElseThrow(() -> new TokenNotFoundException());
    }

    @Override
    public Token generateNewVerificationToken(String username) {
        Token token = tokenDAO.findTokenByUserUsernameAndTokenType(username, TokenType.ACTIVATION).orElseThrow(() -> new TokenNotFoundException());
        token.setExpireDateTime(computeExpiryDateOfAToken());
        token.setTokenValue(UUID.randomUUID().toString());
        tokenDAO.save(token);
        return token;
    }

    private LocalDateTime computeExpiryDateOfAToken() {
        return LocalDateTime.now().plusHours(EXPIRATION_TIME_IN_HOURS);
    }
}
