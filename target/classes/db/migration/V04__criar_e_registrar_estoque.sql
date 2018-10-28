CREATE TABLE medicamento_estoque (
  id_estoque int(11) NOT NULL AUTO_INCREMENT,
  lote varchar(200) NOT NULL,
  quantidade int(11) DEFAULT NULL,
  PRIMARY KEY (id_estoque),
  CONSTRAINT FK_Medicamento_estoque_1 FOREIGN KEY (lote) REFERENCES medicamento (lote)
);

INSERT INTO medicamento_estoque (lote, quantidade) values ('1130000580310', 1);
INSERT INTO medicamento_estoque (lote, quantidade) values ('123000067090', 10);
INSERT INTO medicamento_estoque (lote, quantidade) values ('1233456062244', 10);
INSERT INTO medicamento_estoque (lote, quantidade) values ('222222222', 1);