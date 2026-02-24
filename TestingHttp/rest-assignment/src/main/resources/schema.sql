CREATE TABLE IF NOT EXISTS students (
    reg_no       BIGINT PRIMARY KEY,
    roll_no      INTEGER NOT NULL,
    name         VARCHAR(100) NOT NULL,
    standard     INTEGER NOT NULL,
    school       VARCHAR(100) NOT NULL,
    gender       VARCHAR(10) CHECK (gender IN ('Male', 'Female')),
    percentage   NUMERIC(5,2) CHECK (percentage BETWEEN 0 AND 100)
);
