create sequence user_seq;

create table users(
    id    bigint primary key default nextval('user_seq'),
    email character varying(255),
    password character varying(255)
);