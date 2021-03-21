drop database if exists demo;
create database demo;
use demo;

/**用户表**/
create table user(
    id        int(12) not null auto_increment,
    username  varchar(60) not null,
    password  varchar(100) not null,
    status int(1) default 1 check(status in (0, 1)),
    primary key (id),
    unique (username)
);
/**角色表**/
create table role(
    id        int(12) not null auto_increment,
    role_name varchar(60) not null,
    note      varchar(256),
    primary key (id)
);
insert into role value (null, 'teacher', '老师权限说明');
insert into role value (null, 'student', '学生权限说明');
insert into role value (null, 'guest', '访客权限说明');
/**权限表**/
create table permission(
    id    int(12) not null auto_increment,
    name  varchar(60) not null,
    value varchar(100) not null,
    primary key (id)
);
insert into permission value (null, '读权限', 'pms:read');
insert into permission value (null, '写权限', 'pms:write');
/**用户角色表**/
create table user_role (
    id      int(12) not null auto_increment,
    role_id int(12) not null,
    user_id int(12) not null,
    primary key (id),
    unique (role_id, user_id)
);
/**角色权限表**/
create table role_permission(
    id            int(12) not null auto_increment,
    permission_id int(12) not null,
    role_id       int(12) not null,
    primary key (id),
    unique (permission_id, role_id)
);
insert into role_permission value (null, 1, 1);
insert into role_permission value (null, 2, 1);
insert into role_permission value (null, 1, 2);