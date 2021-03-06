INSERT INTO dealership(DEALERSHIP_ID,name,brand,phoneNumber,street,city,state,zip) VALUES (1,"Ya Ao","Chevrolet","13915594891","Ma Yun Road","Suzhou","Jiang Su","215000");
INSERT INTO dealership(DEALERSHIP_ID,name,brand,phoneNumber,street,city,state,zip) VALUES (2,"Hua Tian","Chevrolet","13912594891","Kai Ma Moto Town","Suzhou","Jiang Su","215002");
INSERT INTO dealership(DEALERSHIP_ID,name,brand,phoneNumber,street,city,state,zip) VALUES (3,"Dong Chang","Chevrolet","17915594891","Xing hu Street","Suzhou","Jiang Su","215001");
INSERT INTO dealership(DEALERSHIP_ID,name,brand,phoneNumber,street,city,state,zip) VALUES (4,"Yuan Qu","Chevrolet","13815594891","Xing hu Street","Suzhou","Jiang Su","215004");
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (1,'Robbie','Gu','1983-12-13',30,4,'ROLE_USER','user1','pwd1');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (2,'Nick','Gu','1982-11-13',31,1,'ROLE_USER','user2','pwd2');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (3,'Tom','Chen','1983-10-11',32,2,'ROLE_USER','user3','pwd3');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (4,'Terry','Gao','1985-09-08',33,4,'ROLE_USER','user4','pwd4');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (5,'Lynda','Wang','1990-04-01',34,3,'ROLE_USER','gwj41','pwd5');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (6,'Sam','Smith','1991-04-01',34,4,'ROLE_USER','user6','pwd6');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (7,'Robbie','Williams','1992-04-01',69,1,'ROLE_USER','user7','pwd7');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (8,'King','Lan','1993-04-01',55,2,'ROLE_USER','gwj42','pwd8');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (9,'Li jia','Wang','1994-04-01',90,4,'ROLE_USER','user9','pwd9');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (10,'Xue ming','Gu','1995-04-01',48,1,'ROLE_USER','user10','pwd10');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (11,'Bin','Chen','1996-04-01',65,3,'ROLE_USER','user11','pwd11');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (12,'Yu ming','Guo','2001-04-01',53,3,'ROLE_USER','gwj43','pwd12');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (13,'Will','Young','2002-04-01',25,1,'ROLE_USER','user13','pwd13');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (14,'Linkin','Park','1950-04-01',14,2,'ROLE_USER','user14','pwd14');
INSERT INTO user(USER_ID,firstName,lastName,birthday,age,DEALERSHIP_ID,role,username,password) VALUES (15,'Maroon','V','1990-04-01',24,3,'ROLE_USER','user15','pwd15');

INSERT INTO acl_sid (id,principal,sid) VALUES (1,TRUE ,'gwj41');
INSERT INTO acl_sid (id,principal,sid) VALUES (2,TRUE ,'gwj42');
INSERT INTO acl_sid (id,principal,sid) VALUES (3,TRUE ,'gwj43');
INSERT INTO acl_sid (id,principal,sid) VALUES (4,FALSE ,'ROLE_USER');
INSERT INTO acl_sid (id,principal,sid) VALUES (5,FALSE ,'ROLE_ADMIN');

INSERT INTO acl_class (id,class) VALUES (1,'com.robbie.mvc.entity.User');
INSERT INTO acl_class (id,class) VALUES (2,'com.robbie.mvc.entity.Dealership');

INSERT INTO acl_object_identity (id,object_id_class,object_id_identity,parent_object,owner_sid,entries_inheriting)
VALUES (1,2,3,null,1,false);
INSERT INTO acl_object_identity (id,object_id_class,object_id_identity,parent_object,owner_sid,entries_inheriting)
VALUES (2,2,2,null,2,false);

INSERT INTO acl_entry (id,acl_object_identity,ace_order,sid,mask,granting,audit_success,audit_failure)
VALUES (1,1,1,1,1,TRUE ,TRUE ,TRUE );
INSERT INTO acl_entry (id,acl_object_identity,ace_order,sid,mask,granting,audit_success,audit_failure)
VALUES (2,2,1,2,1,TRUE ,TRUE ,TRUE );
INSERT INTO acl_entry (id,acl_object_identity,ace_order,sid,mask,granting,audit_success,audit_failure)
VALUES (3,2,2,3,1,TRUE ,TRUE ,TRUE );
INSERT INTO acl_entry (id,acl_object_identity,ace_order,sid,mask,granting,audit_success,audit_failure)
VALUES (4,1,2,3,1,TRUE ,TRUE ,TRUE );