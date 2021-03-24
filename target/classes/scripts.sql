create schema "shop";
create table shop.product
(
    id int not null,
    title text not null,
    price int not null
);

create unique index product_id_uindex
	on shop.product (id);

alter table shop.product
    add constraint product_pk
        primary key (id);

create table shop.customer
(
    id int not null,
    name text not null
);

create unique index customer_id_uindex
	on shop.customer (id);

alter table shop.customer
    add constraint customer_pk
        primary key (id);

INSERT INTO shop.customer VALUES ('1', 'Иван');
INSERT INTO shop.customer VALUES ('2', 'Василий');
INSERT INTO shop.customer VALUES ('3', 'Николай');
INSERT INTO shop.product VALUES ('1','Картошка', '100');
INSERT INTO shop.product VALUES ('2', 'Огурцы', '200');
INSERT INTO shop.product VALUES ('3', 'Бананы', '300');

create table shop.cart
(
    id int not null,
    customer_id int not null,
    product_id int not null,
);

create unique index cart_id_uindex
	on shop.cart (id);

alter table shop.cart
    add constraint cart_pk
        primary key (id);

INSERT INTO shop.cart VALUES ('1', '1', '1');
INSERT INTO shop.cart VALUES ('2', '2', '2');
INSERT INTO shop.cart VALUES ('3', '3', '3');

alter table shop.cart
    add constraint cart_customer_id_fk
        foreign key (customer_id) references shop.customer;

alter table shop.cart
    add constraint cart_product_id_fk
        foreign key (product_id) references shop.product;

alter table shop.cart
    add constraint cart_product_price_fk
        foreign key (price) references shop.product (price);