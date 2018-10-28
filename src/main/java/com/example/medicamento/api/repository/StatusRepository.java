package com.example.medicamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicamento.api.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}