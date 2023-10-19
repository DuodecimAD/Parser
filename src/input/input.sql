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