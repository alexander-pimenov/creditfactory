drop table if exists CAR;

create table if not exists CAR (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  year_of_issue YEAR,
  --assessed_value DEC(20)
);

drop table if exists AIRPLANE;

create table if not exists AIRPLANE (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuel_capacity INT,
  seats INT
);

drop table if exists assessed_value_car;

create table if not exists assessed_value_car (
  id IDENTITY primary key,
  car_id INT,
  assessment_date DATE DEFAULT now() NOT NULL,
  assessed_value DEC(20),
  CONSTRAINT assessed_value_car_fk FOREIGN KEY (car_id)
    REFERENCES CAR(id)
);

drop table if exists assessed_value_airplane;

create table if not exists assessed_value_airplane (
  id IDENTITY primary key,
  airplane_id INT,
  assessment_date DATE DEFAULT now() NOT NULL,
  assessed_value DEC(20),
  CONSTRAINT assessed_value_airplane_fk FOREIGN KEY (airplane_id)
    REFERENCES AIRPLANE(id)
);

insert into CAR (brand, model, power, year_of_issue)
values ('bmw', 'X5', 300.0, 2010),
       ('audi', 'Q3', 150.0, 2015),
       ('chevrolet', 'tahoe', 420.0, 2017);

insert into assessed_value_car (car_id, assessment_date, assessed_value)
values (1, '2016-03-03', 6000000),
       (1, '2018-04-04', 5500000),
       (2, '2019-03-03', 3500000),
       (2, '2020-04-04', 3000000),
       (3, '2019-02-04', 6500000),
       (3, '2020-05-04', 600000);

insert into AIRPLANE (brand, model, manufacturer, year_of_issue, fuel_capacity, seats)
values ('Boeing', '777', 'USA', 1992, 20000, 200),
       ('TU', '204', 'Russia', 1995, 15000, 200),
       ('Boeing', '747', 'USA', 1998, 20000, 300);

insert into assessed_value_airplane (airplane_id, assessment_date, assessed_value)
values (1, '2017-03-03', 700000000),
       (1, '2020-04-04', 500000000),
       (2, '2016-03-03', 400000000),
       (2, '2020-04-04', 250000000),
       (3, '2018-04-04', 750000000),
       (3, '2020-04-04', 720000000);