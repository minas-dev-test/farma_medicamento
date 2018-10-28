CREATE TABLE tipo_status_medicamento (
  id_tipo_status_medicamento int(11) NOT NULL,
  descricao varchar(200) DEFAULT NULL,
  PRIMARY KEY (id_tipo_status_medicamento)
);

INSERT INTO tipo_status_medicamento (id_tipo_status_medicamento, descricao) values (1, 'Disponível');
INSERT INTO tipo_status_medicamento (id_tipo_status_medicamento, descricao) values (2, 'Reservado');
INSERT INTO tipo_status_medicamento (id_tipo_status_medicamento, descricao) values (3, 'Doado');
INSERT INTO tipo_status_medicamento (id_tipo_status_medicamento, descricao) values (4, 'Vencido');
INSERT INTO tipo_status_medicamento (id_tipo_status_medicamento, descricao) values (5, 'Excluído');