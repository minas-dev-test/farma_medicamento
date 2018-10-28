CREATE TABLE solicitacao_medicamento (
  id_pessoa int(11) DEFAULT NULL,
  lote varchar(200) NOT NULL,
  quantidade int(11) DEFAULT NULL,
  id_solicitacao_medicamento int(11) NOT NULL AUTO_INCREMENT,
  data_final date NOT NULL,
  confirmada boolean NOT NULL,
  excluida boolean NOT NULL,
  PRIMARY KEY (id_solicitacao_medicamento),
  CONSTRAINT FK_Solicitacao_Medicamento_1 FOREIGN KEY (lote) REFERENCES medicamento (lote)
);
