package com.example.medicamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicamento.api.model.TipoMedicamento;

public interface TipoRepository extends JpaRepository<TipoMedicamento, Long>{

}