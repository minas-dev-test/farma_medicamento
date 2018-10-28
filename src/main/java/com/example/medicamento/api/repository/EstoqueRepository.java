package com.example.medicamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicamento.api.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {

}