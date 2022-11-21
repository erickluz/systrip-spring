CREATE TABLE Driver
  (
     id         	SERIAL PRIMARY KEY,
     name       	VARCHAR(255),
     cpf         	VARCHAR(255),
     phone_number       VARCHAR(255),
     user_name    	VARCHAR(255),
     password	        VARCHAR(255),
     email		VARCHAR(255)
  );

CREATE TABLE Vehicle
  (
     id				SERIAL PRIMARY KEY,
     alias      		VARCHAR(255),
     license_plate       	VARCHAR(255),
     vehicle_manufacturer	VARCHAR(255),
     model_name			VARCHAR(255),
     color			VARCHAR(255),
     id_user			integer
  );

ALTER TABLE Vehicle
  ADD CONSTRAINT fk_driver FOREIGN KEY (id_user) REFERENCES Driver;
