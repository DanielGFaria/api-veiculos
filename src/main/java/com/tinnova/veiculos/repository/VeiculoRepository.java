package com.tinnova.veiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnova.veiculos.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
		Veiculo findById(long id);		

}
