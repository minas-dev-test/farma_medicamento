CREATE TABLE tipo_medicamento (
  id_tipo_medicamento int(11) NOT NULL,
  descricao varchar(200) DEFAULT NULL,
  PRIMARY KEY (id_tipo_medicamento)
);

INSERT INTO tipo_medicamento (id_tipo_medicamento, descricao) values (1, 'Gotas');
INSERT INTO tipo_medicamento (id_tipo_medicamento, descricao) values (2, 'Comprimidos');