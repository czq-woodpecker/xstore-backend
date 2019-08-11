create table if not exists orders(
    id bigint auto_increment,
    amount int,
    product_id bigint,
    primary key (id)
)engine=innodb;

alter table orders
add foreign key fk_orders_product(product_id)
references product (id)
on delete cascade
on update restrict;


