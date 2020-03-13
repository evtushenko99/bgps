create table exam_type
(
    id   int,
    type text,
    primary key (id)

);

create table mark
(
    id    int,
    name  text,
    value text,
    primary key (id)

);

create table subject
(
    id         int,
    name       text,
    short_name text,
    primary key (id)

);

create table study_group
(
    id   int,
    name text,
    primary key (id)

);

create table student
(
    id             int,
    surname        text,
    name           text,
    second_name    text,
    study_group_id int,
    primary key (id)

);

create table study_plan
(
    id           int,
    subject_id   int,
    exam_type_id int,
    primary key (id)

);

create table journal
(
    id            int,
    student_id    int,
    study_plan_id int,
    in_time       bit,
    count         int,
    mark_id       int,
    primary key (id)

);

alter table journal
    add constraint r1 foreign key (mark_id) references mark (id);

alter table journal
    add constraint r2 foreign key (student_id) references student (id);

alter table journal
    add constraint r3 foreign key (study_plan_id) references study_plan (id);

alter table student
    add constraint r4 foreign key (study_group_id) references study_group (id);

alter table study_plan
    add constraint r5 foreign key (subject_id) references subject (id);

alter table study_plan
    add constraint r6 foreign key (exam_type_id) references exam_type (id);