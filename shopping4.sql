create table t_admin(
i_admin number not null PRIMARY key,
mid nvarchar2(30) not null,
mpw nvarchar2(50) not null,
nm nvarchar2(10) not null
);

insert into t_admin (i_admin,mid,mpw,nm)
values (1,'admin',1212,'������');

create table t_member(
i_member number not null PRIMARY key,
mid nvarchar2(30) not null,
mpw nvarchar2(50) not null,
nm NVARCHAR2(10) not null,
sex number (1) not null,
r_date date default sysdate
);

create table t_product(
i_product number not null PRIMARY key,
nm nvarchar2(100) not null,
price number default 0,
pic nvarchar2(255),
qty number(4) default 0,
yn_sale number(1) default 0,
info nvarchar2(255) default''
);

create table t_basket(
i_basket number not null PRIMARY key,
i_member number,
i_product number,
qty number(4) not null,
price number not null,
r_dt date default sysdate,
CONSTRAINT fk_i_member foreign key(i_member) REFERENCES t_member(i_member),
constraint fk_i_product foreign key(i_product) references t_product(i_product) 
);

create table t_product_import(
i_pi number PRIMARY key,
i_product number,
qty number(4) not null,
r_dt date default sysdate,
foreign key(i_product) references t_product(i_product)
);

create table t_purchase(
i_purchase number PRIMARY key,
i_member number,
i_product number,
qty number(4) not null,
price number not null,
r_dt date default sysdate,
foreign key (i_member) REFERENCES t_member(i_member),
foreign key (i_product) REFERENCES t_product(i_product)
);

select * from t_member;

--���� ����(ȸ������)--
insert into t_member(i_member,mid,mpw,nm,sex)
VALUES ((select nvl(max(i_member),0)+1 from t_member),'12','12','12',1);

commit;
--���� �α���--
select i_member,mpw
from t_member
where mid = '12';

--������ �α���--
select mpw
from t_admin
where mid = 'admin';

--��ǳ ��� ������--
insert into t_product(i_product,nm,price,pic,info)
VALUES ((select nvl(max(i_product),0)+1 from t_product),'��ǰ',1000,'text','text2');

select * from t_product;

--��ǰ ����Ʈ ����--
select i_product,nm,price,pic,qty,yn_sale,info
from t_product;

--��ǰ ����--
select i_product,nm,price,pic,qty,yn_sale,info
from t_product
where i_product = 1;

--��ٱ��� ����--
select a.i_basket,a.i_member,a.i_product,b.nm,b.pic,a.price,(a.price*a.qty)as saleprice,b.qty,a.qty as saleqty,a.r_dt,b.yn_sale
from t_basket a
INNER join t_product b on a.i_product = b.i_product
where i_member = 1 and yn_sale = 1;

--��ٱ��� ��� ����--
insert into t_basket(i_basket,i_product,i_member,qty,price)
values ((select nvl(max(i_basket),0)+1 from t_basket),1,1,10,2000);


select * from t_basket;

--��ǰ����Ʈ ������--
select i_product,pic,nm,price,qty,yn_sale
from t_product;

--��ǰ���� ������--
update t_product
set nm = ? , price = ?, pic = ? , yn_sale = ?;

select * from t_product;

--��ǰ�԰� ������--
insert into t_product_import (i_pi,i_product,qty)
VALUES ((select nvl(max(i_pi),0)+1 from t_product_import),?,?);


--�԰������ ��ǰ����Ʈ ������ �����ǰ���--
UPDATE t_product
set qty = qty+1
where i_product = 1;

--������ �԰� ����Ʈ--
select a.i_pi, b.nm,(b.price*a.qty) as sum,b.price,a.qty
from t_product_import A
inner join t_product B on a.i_product = b.i_product
order by i_pi desc;