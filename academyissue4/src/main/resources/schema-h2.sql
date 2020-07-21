begin;

create SEQUENCE IF NOT EXISTS sequence_personId START 1 INCREMENT 50;

create TABLE IF NOT EXISTS person (
    personId INT PRIMARY KEY,
    givenName VARCHAR(50),
    surname VARCHAR(50),
    EGN VARCHAR(10) UNIQUE
);

create TABLE IF NOT EXISTS grade (
  personId INT PRIMARY KEY REFERENCES person(personId) ,
  grade_a VARCHAR(3),
  grade_b VARCHAR(3),
  grade_c VARCHAR(3)
);

-- WARNING - this file doesn't contain Oracle-specific triggers


COMMIT;
