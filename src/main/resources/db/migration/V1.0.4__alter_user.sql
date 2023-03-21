ALTER TABLE user DROP COLUMN email ;
ALTER TABLE user DROP COLUMN password ;
ALTER TABLE user ADD COLUMN created datetime NOT NULL;
