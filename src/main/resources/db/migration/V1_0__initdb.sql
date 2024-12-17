create sequence profession_seq;

create table profession(id bigint primary key default nextval('profession_seq'),
                      name character varying(100)
);