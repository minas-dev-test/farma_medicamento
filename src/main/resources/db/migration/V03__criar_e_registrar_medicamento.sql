CREATE TABLE medicamento (
  principio_ativo varchar(200) DEFAULT NULL,
  dosagem varchar(200) DEFAULT NULL,
  data_vencimento date DEFAULT NULL,
  nome_comercial varchar(200) DEFAULT NULL,
  outras_especificacoes varchar(600) DEFAULT NULL,
  lote varchar(200) NOT NULL,
  id_tipo_medicamento int(11) DEFAULT NULL,
  laboratorio varchar(200) DEFAULT NULL,
  id_tipo_status_medicamento int(11) DEFAULT NULL,
  ob_exclusao varchar(500) DEFAULT NULL,
  ic_uso_veterinario varchar(1) DEFAULT NULL,
  PRIMARY KEY (lote),
  KEY FK_Medicamento_1 (id_tipo_medicamento),
  KEY FK_Medicamento_2 (id_tipo_status_medicamento),
  CONSTRAINT FK_Medicamento_1 FOREIGN KEY (id_tipo_medicamento) REFERENCES tipo_medicamento (id_tipo_medicamento),
  CONSTRAINT FK_Medicamento_2 FOREIGN KEY (id_tipo_status_medicamento) REFERENCES tipo_status_medicamento (id_tipo_status_medicamento)
);
INSERT INTO medicamento (principio_ativo, dosagem, data_vencimento, nome_comercial, outras_especificacoes, lote, id_tipo_medicamento, laboratorio, id_tipo_status_medicamento, ob_exclusao, ic_uso_veterinario) values ('Dipirona Sódica Monoidratada', '500 mg/mL', '2019-06-10', 'Novalgina', null, '222222222', 1, 'Sanofi', 1, null, 'N');
INSERT INTO medicamento (principio_ativo, dosagem, data_vencimento, nome_comercial, outras_especificacoes, lote, id_tipo_medicamento, laboratorio, id_tipo_status_medicamento, ob_exclusao, ic_uso_veterinario) values ('Dipirona Sódica Monoidratada', '500 mg/mL', '2019-06-10', 'Novalgina', null, '1130000580310', 1, 'Sanofi', 1, null, 'N');
INSERT INTO medicamento (principio_ativo, dosagem, data_vencimento, nome_comercial, outras_especificacoes, lote, id_tipo_medicamento, laboratorio, id_tipo_status_medicamento, ob_exclusao, ic_uso_veterinario) values ('Dipirona Sódica Monoidratada', '1 g', '2018-12-10', 'Novalgina', null, '123000067090', 2, 'Sanofi', 1, null, 'N');
INSERT INTO medicamento (principio_ativo, dosagem, data_vencimento, nome_comercial, outras_especificacoes, lote, id_tipo_medicamento, laboratorio, id_tipo_status_medicamento, ob_exclusao, ic_uso_veterinario) values ('Paracetamol', '500 mg', '2018-10-23', 'Tylenol', null, '1233456062244', 2, 'Johnson & Johnson', 1, null, 'N');