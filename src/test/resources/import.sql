insert into user(username, mail, password, userType, confirmed) values('nicu', 'nicu.tuturuga@yahoo.com', '123', 'NORMAL', true)
insert into user(username, mail, password, userType, confirmed) values('tuturuga', 'tuturuganicu@gmail.com', '123', 'NORMAL', true)

insert into club(name, inviteCode) values('club1', '12345');

insert into UsersClubs(userId, clubId) values(1, 1);

insert into WantedProduct(name, description, category, bought, constrained, clubId) values('product1', 'desc for product1', 'FOOD', false, false, 1)
insert into WantedProduct(name, description, category, bought, constrained, clubId) values('product2', 'desc for product2', 'FOOD', true, true, 1)

insert into ProductConstraint(name, message, constraintsType) values ('constr1', 'constr message', 'BASED_ON_DESCRIPTION')

insert into Invitation(inviteCode, used, senderId, receiverId) values('12345', false, 1, 2)