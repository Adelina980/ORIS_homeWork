insert into profession(id, name) values
    ((select nextval('profession_seq')), 'teacher');
