
create sequence users_seq;

create table users (
                       id bigint primary key default nextval('users_seq'),
                       name character varying(50),
                       password_hash character varying(255)
);