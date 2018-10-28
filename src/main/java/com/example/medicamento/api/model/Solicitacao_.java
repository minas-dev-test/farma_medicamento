package com.example.medicamento.api.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Solicitacao.class)
public abstract class Solicitacao_ {

	public static volatile SingularAttribute<Solicitacao, Long> idPessoa;
	public static volatile SingularAttribute<Solicitacao, Boolean> excluida;
	public static volatile SingularAttribute<Solicitacao, Boolean> confirmada;
	public static volatile SingularAttribute<Solicitacao, Medicamento> medicamento;
	public static volatile SingularAttribute<Solicitacao, Long> id;
	public static volatile SingularAttribute<Solicitacao, Integer> quantidade;
	public static volatile SingularAttribute<Solicitacao, Date> dataFinal;

}

