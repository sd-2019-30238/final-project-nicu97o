package com.shoppinglist.service;

import com.shoppinglist.model.database.*;

public abstract class DatabaseInsertedData {
    static final String USER_TABLE = "user";
    static final User INSERTED_USER_1 = new User(1L, "nicu", "nicu.tuturuga@yahoo.com", "123", UserType.NORMAL, true);
    static final User INSERTED_USER_2 = new User(2L, "tuturuga", "tuturuganicu@gmail.com", "123", UserType.NORMAL, true);
    static final User UNISERTED_USER_1 = new User(null, "newUser", "newMail@gmail.com", "123", UserType.NORMAL, true);
    static final int NR_OF_INITIAL_USERS = 2;
    static final long ID_OF_CLUB_WHICH_USER_1_IS_MEMBER = 1l;

    static final String CLUB_TABLE = "club";
    static final Club INSERTED_CLUB_1 = new Club(1L, "club1", "12345");
    static final int NR_OF_INITIAL_CLUBS = 1;

    static final String WANTED_PRODUCT_TABLE = "WantedProduct";
    static final WantedProduct INSERTED_WANTED_PRODUCT_1 = new WantedProduct(1L, "product1", "desc for product1", Category.FOOD, false, false);
    static final WantedProduct INSERTED_WANTED_PRODUCT_2 = new WantedProduct(2L, "product2", "desc for product2", Category.FOOD, true, true);
    static final int NR_OF_INITIAL_WANTED_PRODUCTS = 2;

    static final String PRODUCT_CONSTRAINT_TABLE = "ProductConstraint";
    static final ProductConstraint INSERTED_PRODUCT_CONSTRAINT_1 = new ProductConstraint(1L, "constr1", "constr message", ConstraintsType.BASED_ON_DESCRIPTION);
    static final int NR_OF_INITIAL_PRODUCT_CONSTRAINTS = 1;

    static final String INVITATION_TABLE = "Invitation";
    static final Invitation INSERTED_INVITATION_1 = new Invitation(1L, "12345", false);
    static final int NR_OF_INITIAL_INVITATION = 1;
}
