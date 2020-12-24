# MySQL + JSP 연결 가ㅏ즈아

### 테이블 생성
``` sql
use ssar;
drop table users;
create table users(
	id int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    email varchar(50)
);

insert into users(username, password, email) values ('ssar', '1234', 'ssar@nate.com');
insert into users(username, password, email) values ('cos', '1234', 'cos@nate.com');
commit;

```
