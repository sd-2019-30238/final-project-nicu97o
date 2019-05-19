package com.shoppinglist.service;

import com.shoppinglist.model.database.Token;
import com.shoppinglist.model.database.TokenType;

public interface TokenService {
    Token createToken(String username, TokenType tokenType);

    Token getToken(String tokenValue);

    Token generateNewVerificationToken(String username);
}
