package com.example.medicamento.api.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date adicionarDiasUteis(Date data, Integer qtdeDiasAcrescentados) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(data);
		while(qtdeDiasAcrescentados > 0){
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
			int diaDaSemana = dataInicial.get(Calendar.DAY_OF_WEEK);
			if (diaDaSemana != Calendar.SATURDAY && diaDaSemana != Calendar.SUNDAY) {
				--qtdeDiasAcrescentados;
			}
		}
		return dataInicial.getTime();
	}
}
