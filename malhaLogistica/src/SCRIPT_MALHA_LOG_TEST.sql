
 CREATE DATABASE DB_WALMART_LOGISTICA;

 USE DB_WALMART_LOGISTICA;

 CREATE TABLE TB_MALHA_LOGISTICA(
	ID_MALHA_LOG BIGINT NOT NULL AUTO_INCREMENT,
    NOME_MALHA VARCHAR(60) NOT NULL,
    ORIGEM VARCHAR(60) NOT NULL,
    DESTINO VARCHAR(60) NOT NULL,
    DISTANCIA INT NOT NULL,
    
    CONSTRAINT PK_ID_MALHA_LOG PRIMARY KEY(ID_MALHA_LOG)
 );

  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','A','B',10);
  
  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','B','D',15);
  
  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','A','C',20);
  
  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','C','D',30);
  
  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','B','E',50);
  
  INSERT INTO TB_MALHA_LOGISTICA(NOME_MALHA,ORIGEM,DESTINO,DISTANCIA)
  VALUES('SP','D','E',30);
  


 