insert into users (user_name, password, enabled, first_name, last_name, email) values ('admin', '$2a$10$f6jAZ6RgqR/nR.lbas1I7uBBdgJl/TVj7Du/CN6J2mW4pjr0IPS3K', true, 'Damiano', 'Coombe', 'dcoombe0@google.com');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('cstoneley1', '$2a$10$7s2Ly9Meg/7ey7DvxTCXleQobZFIxkIhCVVJbQh/eCffGbQiSTZ2W', false, 'Candis', 'Stoneley', 'cstoneley1@xrea.com');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('tibbotson2', '$2a$10$qFAQWcliuWB024d7s4YsiONqvL7b9XQ9pzZSjL8oBSI5BvwpnqY.m', false, 'Tarra', 'Ibbotson', 'tibbotson2@sakura.ne.jp');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('gbrundale3', '$2a$10$PP.xIae6i4WIuZ/Vc1z0/ezMG8r32J7vHJfSzuvlbRwvLdecp5S3C', true, 'Gabey', 'Brundale', 'gbrundale3@salon.com');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('kpostlethwaite4', '$2a$10$JsYv8rgGFvbSeMAU2WbDeugQeADeirIWj925SWhbVq22skPG2nDDa', true, 'Katlin', 'Postlethwaite', 'kpostlethwaite4@ucla.edu');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('agiannazzi5', '$2a$10$lhWx1Hc0Dy2jEmvukl1SbuZXrXzq7EWmwf5d8wAliOppAtccYuPM2', false, 'Any', 'Giannazzi', 'agiannazzi5@ning.com');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('gromanini6', '$2a$10$.OfgyjjmOKZa2/Ueev5eeOyqxQYVJZ12yww0CkBKNDx043T9gFcMC', true, 'Gerrie', 'Romanini', 'gromanini6@upenn.edu');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('tchattaway7', '$2a$10$dg1QfTD7ygBgTNrCWkt.suGOTphSxW8tLT.gjQaiSSZ36pPfsH.Eu', false, 'Tressa', 'Chattaway', 'tchattaway7@narod.ru');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('ldarlaston8', '$2a$10$JalKJcpJlK/s6VuwrU2RJOQR2aykpNLFc.V9ftZ2lP4oYt4ZLVlge', true, 'Lodovico', 'Darlaston', 'ldarlaston8@bandcamp.com');
insert into users (user_name, password, enabled, first_name, last_name, email) values ('pobbard9', '$2a$10$yOCRaZKH2y/9UrOCjB.BDeaj9zeMDwSDgx1EhITrvIDoR7VuuBeW.', true, 'Port', 'Obbard', 'pobbard9@xrea.com');

insert into roles (role_name) values ('ROLE_ADMIN');
insert into roles (role_name) values ('ROLE_USER');

insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (1,2);
insert into user_role (user_id, role_id) values (2,2);
insert into user_role (user_id, role_id) values (3,2);
insert into user_role (user_id, role_id) values (4,2);
insert into user_role (user_id, role_id) values (5,2);
insert into user_role (user_id, role_id) values (6,2);
insert into user_role (user_id, role_id) values (7,2);
insert into user_role (user_id, role_id) values (8,2);
insert into user_role (user_id, role_id) values (9,2);
insert into user_role (user_id, role_id) values (10,2);