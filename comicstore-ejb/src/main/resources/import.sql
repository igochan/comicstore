insert into FORMAT(id, version, name) values (1, 0, 'Comic book');
insert into FORMAT(id, version, name) values (2, 0, 'Graphic novel');
insert into FORMAT(id, version, name) values (3, 0, 'Comic strip');

insert into GENRE(id, version, name) values (100, 0, 'Super heroes');
insert into GENRE(id, version, name) values (101, 0, 'Adult');
insert into GENRE(id, version, name) values (102, 0, 'Underground');
insert into GENRE(id, version, name) values (103, 0, 'Fantasy');
insert into GENRE(id, version, name) values (104, 0, 'Manga');

insert into SCHEDULE(id, version, name) values (200, 0, 'Monthly');
insert into SCHEDULE(id, version, name) values (201, 0, 'Dialy');
insert into SCHEDULE(id, version, name) values (202, 0, 'Annual');
insert into SCHEDULE(id, version, name) values (203, 0, 'One shoot');

--insert into CONDITION(id, version, name) values (401, 0, 'Mint');
--insert into CONDITION(id, version, name) values (402, 0, 'Fine');
--insert into CONDITION(id, version, name) values (403, 0, 'Good');
--insert into CONDITION(id, version, name) values (404, 0, 'Fair');
--insert into CONDITION(id, version, name) values (405, 0, 'Poor');

insert into AUTHOR(id, version, name, surename, user, modified, deleted) values (500, 0, 'Peter', 'David', 'Abel', DATE '2015-06-23', false);
insert into AUTHOR(id, version, name, surename, user, modified, deleted) values (501, 0, 'Larry', 'Stroman', 'Abel', DATE '2015-06-23', false);
insert into AUTHOR(id, version, name, surename, user, modified, deleted) values (502, 0, 'Louise', 'Simonson', 'Abel', DATE '2015-06-23', false);
insert into AUTHOR(id, version, name, surename, user, modified, deleted) values (503, 0, 'Walter', 'Simonson', 'Abel', DATE '2015-06-23', false);
insert into AUTHOR(id, version, name, surename, user, modified, deleted) values (504, 0, 'Scott', 'McCloud', 'Abel', DATE '2015-06-23', false);

insert into PUBLISHER(id, version, name, user, modified, deleted) values (550, 0, 'Comics Forum', 'Abel', DATE '2015-06-23', false);
insert into PUBLISHER(id, version, name, user, modified, deleted) values (551, 0, 'Planeta Comic', 'Abel', DATE '2015-06-23', false);

insert into COMIC(id, version, name, publisherId, volume, user, modified, deleted) values (600, 0, 'Factor-X', 550, 1, 'Abel', DATE '2015-06-23', false);
insert into COMIC(id, version, name, publisherId, volume, user, modified, deleted) values (601, 0, 'El Escultor', 551, 1, 'Abel', DATE '2015-06-23', false);

insert into ISSUE(id, version, comicid, number, coverImageFileName, user, modified, deleted) values (700, 0, 601, 1, '1.jpg', 'Abel', DATE '2015-06-23', false);
insert into ISSUE(id, version, comicid, number, coverImageFileName, user, modified, deleted) values (701, 0, 600, 15, '2.jpg', 'Abel', DATE '2015-06-23', false);
insert into ISSUE(id, version, comicid, number, coverImageFileName, user, modified, deleted) values (702, 0, 600, 25, '3.jpg', 'Abel', DATE '2015-06-23', false);

ALTER SEQUENCE COMICSTORE_SEQUENCE RESTART with 800;