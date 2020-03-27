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
CREATE TABLE student_local
(   id             int,
    surname        text,
    name           text,
    second_name    text,
    study_group_id int,
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

alter table student_local
    add constraint r7 foreign key (study_group_id) references study_group (id);

alter table study_plan
    add constraint r5 foreign key (subject_id) references subject (id);

alter table study_plan
    add constraint r6 foreign key (exam_type_id) references exam_type (id);

insert into subject(id, name, short_name)
values (1, 'Проектирование информационных систем', 'ПрИС'),
       (2, 'Системы искусственного интеллекта', 'СИИ'),
       (3, 'Программная инженерия', 'ПИ'),
       (4, 'Национальная система информационной безопасности', 'НСИБ'),
       (5, 'Системный анализ', 'СисАнал'),
       (6, 'Распределенные базы данных', 'РБД'),
       (7, 'Системное программное обеспечение', 'СПО');

insert into exam_type (id, type)
values (1, 'Экзамен'),
       (2, 'Зачет'),
       (3, 'Зачет с оценкой'),
       (4, 'Курсовая');

insert into study_plan (id, subject_id, exam_type_id)
values (1, 1, 1),
       (2, 1, 4),
       (3, 2, 1),
       (4, 3, 1),
       (5, 4, 2),
       (6, 5, 1),
       (7, 6, 2),
       (8, 7, 1);

insert into mark (id, name, value)
values (1, 'Отлично', 5),
       (2, 'Хорошо', 4),
       (3, 'Удовлетворительно', 3),
       (4, 'Неудовлетворительно', 2),
       (5, 'Зачет', 'з'),
       (6, 'Незачет', 'н'),
       (7, 'Неявка', '');
INSERT INTO study_group (id, name)
VALUES (4, 'ИКБО-03-16');
insert into student_local(id, surname, name, second_name, study_group_id)
values (30550, 'Евтушенко', 'Максим', 'Евгеньевич', 4);
