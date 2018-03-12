DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(id bigint , username varchar(40), name varchar(20), age integer , balance decimal(10,2),primary key (id));

/*
 * DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (
  id bigint NOT NULL ,
  rolename varchar(32) DEFAULT NULL,
 PRIMARY KEY  (id)
) ;

INSERT INTO t_role(id,rolename) VALUES(1,'admin'),(2,'operator'),(3,'manager'),(4,'guest');

DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission (
  id bigint NOT NULL,
  permissions varchar(255) DEFAULT NULL ,
  role_id bigint NOT NULL ,
  PRIMARY KEY (id)
);

INSERT INTO t_permission(id,permissions,role_id) VALUES(1,'add',2),(2,'delete',1),(3,'search',4),(4,'update',3);

DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
  user_id bigint DEFAULT NULL,
  role_id bigint DEFAULT NULL
);
INSERT INTO t_user_role(user_id,role_id) VALUES(1,2),(2,1),(3,4),(4,3);
 * 
 */*/