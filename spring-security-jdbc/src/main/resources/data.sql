INSERT into users(username, password, enabled)
values ('ayush', 'ayush1234', true);
INSERT into users(username, password, enabled)
values ('piyush', 'piyush1234', true);

INSERT into authorities(username, authority)
values ('ayush', 'ROLE_ADMIN');
INSERT into authorities(username, authority)
values ('piyush', 'ROLE_USER');