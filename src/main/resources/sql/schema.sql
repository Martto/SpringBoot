CREATE TABLE IF NOT EXISTS patients
(
    id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL,
    diseases VARCHAR
);

CREATE TABLE IF NOT EXISTS results_table
(
    doctor_id VARCHAR(60) DEFAULT RANDOM_UUID() PRIMARY KEY,
    execution_time INT NOT NULL,
    error VARCHAR,
    doc_source VARCHAR NOT NULL
);