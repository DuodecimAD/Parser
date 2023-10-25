 ---------------------------------------------------------------
 --        Script Oracle.  
 ---------------------------------------------------------------


------------------------------------------------------------
-- Table: City
------------------------------------------------------------
CREATE TABLE City(
	city_id    NUMBER NOT NULL ,
	city_name  VARCHAR2 (50) NOT NULL  ,
	city_lat   FLOAT  NOT NULL  ,
	city_long  FLOAT  NOT NULL  ,
	CONSTRAINT City_PK PRIMARY KEY (city_id)
);

------------------------------------------------------------
-- Table: Driver
------------------------------------------------------------
CREATE TABLE Driver(
	driver_id         NUMBER NOT NULL ,
	driver_firstName  VARCHAR2 (50) NOT NULL  ,
	driver_lastName   VARCHAR2 (50) NOT NULL  ,
	driver_age        NUMBER(10,0)  NOT NULL  ,
	driver_address    VARCHAR2 (100) NOT NULL  ,
	CONSTRAINT Driver_PK PRIMARY KEY (driver_id)
);

------------------------------------------------------------
-- Table: Client
------------------------------------------------------------
CREATE TABLE Client(
	client_id         NUMBER NOT NULL ,
	client_firstName  VARCHAR2 (50) NOT NULL  ,
	client_lastName   VARCHAR2 (50) NOT NULL  ,
	CONSTRAINT Client_PK PRIMARY KEY (client_id)
);

------------------------------------------------------------
-- Table: trailer
------------------------------------------------------------
CREATE TABLE trailer(
	trailer_id           NUMBER NOT NULL ,
	trailer_name         VARCHAR2 (50) NOT NULL  ,
	trailer_marchandise  VARCHAR2 (50) NOT NULL  ,
	trailer_weight       NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT trailer_PK PRIMARY KEY (trailer_id)
);

------------------------------------------------------------
-- Table: Status
------------------------------------------------------------
CREATE TABLE Status(
	status_id    NUMBER NOT NULL ,
	status_name  VARCHAR2 (50) NOT NULL  ,
	CONSTRAINT Status_PK PRIMARY KEY (status_id)
);

------------------------------------------------------------
-- Table: Brand
------------------------------------------------------------
CREATE TABLE Brand(
	brand_id    NUMBER NOT NULL ,
	brand_name  VARCHAR2 (50) NOT NULL  ,
	CONSTRAINT Brand_PK PRIMARY KEY (brand_id)
);

------------------------------------------------------------
-- Table: Truck
------------------------------------------------------------
CREATE TABLE Truck(
	truck_id    NUMBER NOT NULL ,
	truck_km    NUMBER(10,0)  NOT NULL  ,
	truck_fuel  NUMBER(10,0)  NOT NULL  ,
	brand_id    NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT Truck_PK PRIMARY KEY (truck_id)

	,CONSTRAINT Truck_Brand_FK FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

------------------------------------------------------------
-- Table: Contract
------------------------------------------------------------
CREATE TABLE Contract(
	contract_id             NUMBER NOT NULL ,
	contract_code           VARCHAR2 (50) NOT NULL  ,
	contract_price          NUMBER(10,0)  NOT NULL  ,
	contract_distance       NUMBER(10,0)  NOT NULL  ,
	contract_time           NUMBER(10,0)  NOT NULL  ,
	contract_date           DATE  NOT NULL  ,
	truck_id                NUMBER(10,0)  NOT NULL  ,
	driver_id               NUMBER(10,0)  NOT NULL  ,
	client_id               NUMBER(10,0)  NOT NULL  ,
	city_id                 NUMBER(10,0)  NOT NULL  ,
	city_id_trailer_pickup  NUMBER(10,0)  NOT NULL  ,
	city_id_get_truck       NUMBER(10,0)  NOT NULL  ,
	city_id_park_truck      NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT Contract_PK PRIMARY KEY (contract_id)

	,CONSTRAINT Contract_Truck_FK FOREIGN KEY (truck_id) REFERENCES Truck(truck_id)
	,CONSTRAINT Contract_Driver0_FK FOREIGN KEY (driver_id) REFERENCES Driver(driver_id)
	,CONSTRAINT Contract_Client1_FK FOREIGN KEY (client_id) REFERENCES Client(client_id)
	,CONSTRAINT Contract_City2_FK FOREIGN KEY (city_id) REFERENCES City(city_id)
	,CONSTRAINT Contract_City3_FK FOREIGN KEY (city_id_trailer_pickup) REFERENCES City(city_id)
	,CONSTRAINT Contract_City4_FK FOREIGN KEY (city_id_get_truck) REFERENCES City(city_id)
	,CONSTRAINT Contract_City5_FK FOREIGN KEY (city_id_park_truck) REFERENCES City(city_id)
);

------------------------------------------------------------
-- Table: trajet
------------------------------------------------------------
CREATE TABLE trajet(
	trajet_id  NUMBER NOT NULL ,
	Date_t     DATE  NOT NULL  ,
	driver_id  NUMBER(10,0)  NOT NULL  ,
	truck_id   NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT trajet_PK PRIMARY KEY (trajet_id)

	,CONSTRAINT trajet_Driver_FK FOREIGN KEY (driver_id) REFERENCES Driver(driver_id)
	,CONSTRAINT trajet_Truck0_FK FOREIGN KEY (truck_id) REFERENCES Truck(truck_id)
);

------------------------------------------------------------
-- Table: transporter
------------------------------------------------------------
CREATE TABLE transporter(
	trailer_id   NUMBER(10,0)  NOT NULL  ,
	contract_id  NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT transporter_PK PRIMARY KEY (trailer_id,contract_id)

	,CONSTRAINT transporter_trailer_FK FOREIGN KEY (trailer_id) REFERENCES trailer(trailer_id)
	,CONSTRAINT transporter_Contract0_FK FOREIGN KEY (contract_id) REFERENCES Contract(contract_id)
);

------------------------------------------------------------
-- Table: etat
------------------------------------------------------------
CREATE TABLE etat(
	status_id    NUMBER(10,0)  NOT NULL  ,
	contract_id  NUMBER(10,0)  NOT NULL  ,
	CONSTRAINT etat_PK PRIMARY KEY (status_id,contract_id)

	,CONSTRAINT etat_Status_FK FOREIGN KEY (status_id) REFERENCES Status(status_id)
	,CONSTRAINT etat_Contract0_FK FOREIGN KEY (contract_id) REFERENCES Contract(contract_id)
);





CREATE SEQUENCE Seq_City_city_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Driver_driver_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Client_client_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_trailer_trailer_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Status_status_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Brand_brand_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Truck_truck_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_Contract_contract_id START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE Seq_trajet_trajet_id START WITH 1 INCREMENT BY 1 NOCYCLE;


CREATE OR REPLACE TRIGGER City_city_id
	BEFORE INSERT ON City 
  FOR EACH ROW 
	WHEN (NEW.city_id IS NULL) 
	BEGIN
		 select Seq_City_city_id.NEXTVAL INTO :NEW.city_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Driver_driver_id
	BEFORE INSERT ON Driver 
  FOR EACH ROW 
	WHEN (NEW.driver_id IS NULL) 
	BEGIN
		 select Seq_Driver_driver_id.NEXTVAL INTO :NEW.driver_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Client_client_id
	BEFORE INSERT ON Client 
  FOR EACH ROW 
	WHEN (NEW.client_id IS NULL) 
	BEGIN
		 select Seq_Client_client_id.NEXTVAL INTO :NEW.client_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER trailer_trailer_id
	BEFORE INSERT ON trailer 
  FOR EACH ROW 
	WHEN (NEW.trailer_id IS NULL) 
	BEGIN
		 select Seq_trailer_trailer_id.NEXTVAL INTO :NEW.trailer_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Status_status_id
	BEFORE INSERT ON Status 
  FOR EACH ROW 
	WHEN (NEW.status_id IS NULL) 
	BEGIN
		 select Seq_Status_status_id.NEXTVAL INTO :NEW.status_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Brand_brand_id
	BEFORE INSERT ON Brand 
  FOR EACH ROW 
	WHEN (NEW.brand_id IS NULL) 
	BEGIN
		 select Seq_Brand_brand_id.NEXTVAL INTO :NEW.brand_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Truck_truck_id
	BEFORE INSERT ON Truck 
  FOR EACH ROW 
	WHEN (NEW.truck_id IS NULL) 
	BEGIN
		 select Seq_Truck_truck_id.NEXTVAL INTO :NEW.truck_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER Contract_contract_id
	BEFORE INSERT ON Contract 
  FOR EACH ROW 
	WHEN (NEW.contract_id IS NULL) 
	BEGIN
		 select Seq_Contract_contract_id.NEXTVAL INTO :NEW.contract_id from DUAL; 
	END;
/
CREATE OR REPLACE TRIGGER trajet_trajet_id
	BEFORE INSERT ON trajet 
  FOR EACH ROW 
	WHEN (NEW.trajet_id IS NULL) 
	BEGIN
		 select Seq_trajet_trajet_id.NEXTVAL INTO :NEW.trajet_id from DUAL; 
	END;

