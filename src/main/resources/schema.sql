drop table if exists Run;

create table if not exists Run(
    id int not null,
    title varchar(250) not null,
    started_on timestamp not null,
    completed_on timestamp not null,
    miles int not null,
    location varchar(10) not null,
    version int,
    primary key (id)
);