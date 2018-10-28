package com.example.medicamento.api.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medicamento.class)
public abstract class Medicamento_ {

	public static volatile SingularAttribute<Medicamento, Estoque> estoque;
	public static volatile SingularAttribute<Medicamento, TipoMedicamento> tipo;
	public static volatile SingularAttribute<Medicamento, String> obsExclusao;
	public static volatile SingularAttribute<Medicamento, String> outrasEspecificacoes;
	public static volatile SingularAttribute<Medicamento, String> lote;
	public static volatile SingularAttribute<Medicamento, String> principioAtivo;
	public static volatile SingularAttribute<Medicamento, LocalDate> dataVencimento;
	public static volatile SingularAttribute<Medicamento, String> nomeComercial;
	public static volatile SingularAttribute<Medicamento, String> usoVeterinario;
	public static volatile SingularAttribute<Medicamento, String> laboratorio;
	public static volatile SingularAttribute<Medicamento, String> dosagem;
	public static volatile SingularAttribute<Medicamento, Status> status;

}

