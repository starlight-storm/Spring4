drop table if exists T_USER_ROLE ;
drop table if exists T_USER;
drop table if exists T_ROLE;

create table T_ROLE
(
  ID BIGINT primary key,
  ROLE_NAME varchar(100) not null,
  DESCRIPTION varchar(100) not null
)
;

create table T_USER
(
  ID BIGINT primary key,
  LOGIN_ID VARCHAR(20) not null,
  PASSWORD VARCHAR(200) not null,
  FULL_NAME VARCHAR(100) not null,
  DEPT_NAME VARCHAR(100)
)
;

create table T_USER_ROLE
(
  USER_ID BIGINT,
  ROLE_ID BIGINT,
  primary key(USER_ID, ROLE_ID)
)
;