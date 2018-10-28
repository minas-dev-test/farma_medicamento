package com.example.medicamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.repository.medicamento.MedicamentoRepositoryQuery;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String>, MedicamentoRepositoryQuery{

}