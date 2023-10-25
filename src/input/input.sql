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