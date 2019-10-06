Create table PLACES (
     `ID` int NOT NULL AUTO_INCREMENT ,
     `NAME` varchar(120) NOT NULL ,
     `URL_SLUG` varchar(120) NOT NULL ,
     `CITY` varchar(120) NOT NULL ,
     `STATE` char(2) NOT NULL ,
     `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
     `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
     PRIMARY KEY (`ID`)
   )