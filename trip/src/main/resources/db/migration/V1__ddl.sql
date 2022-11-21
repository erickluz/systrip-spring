CREATE TABLE Travel
  (
     id         	SERIAL PRIMARY KEY,
     id_passenger      	integer,
     id_driver         	integer
  );


CREATE TABLE Travel_Status_History
  (
     id         	SERIAL PRIMARY KEY,
     id_travel      	integer,
     status		integer,
     datetime_status	timestamp,
     observation	VARCHAR(255)
  );

ALTER TABLE Travel_Status_History
  ADD CONSTRAINT fk_travel FOREIGN KEY (id_travel) REFERENCES Travel;
