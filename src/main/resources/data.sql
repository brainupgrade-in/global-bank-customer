-- Columns are named explicitly: Hibernate 6 does not order generated columns the
-- same way Hibernate 5 did, so a positional INSERT silently lands values in the
-- wrong columns, and Hibernate 6 maps @Column(name="dateOfBirth") to
-- date_of_birth via CamelCaseToUnderscoresNamingStrategy (it failed with: Cannot parse "DATE" constant "eric").
insert into customer_entity (userid, address, date_of_birth, pan, password, username) values ('eric','Canada','1970-01-10','ADKIM3241D','unigps','Eric');
insert into customer_entity (userid, address, date_of_birth, pan, password, username) values ('john','USA','2001-02-04','AFNIG8472A','unigps','John');
insert into customer_entity (userid, address, date_of_birth, pan, password, username) values ('ratan','Bangalore','1985-12-23','FRMAI8204U','unigps','Ratan');
