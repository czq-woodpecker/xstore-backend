create table if not exists product(
        id bigint auto_increment,
        imageUrl varchar(128) not null,
        name varchar(128) not null,
        price double not null,
        unit varchar(10) not null,
        primary key (id)
)engine=innodb;