package com.tinnova.veiculos.resources;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinnova.veiculos.repository.VeiculoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.tinnova.veiculos.models.Veiculo;


@RestController
@RequestMapping(value="/")
@Api(value="Api Rest Veiculos")
@CrossOrigin(origins="*")
public class VeiculoResources {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@GetMapping("/veiculos")
	@ApiOperation(value="Retorna todos os veículos")
	public List<Veiculo> listaVeiculos(){
		return veiculoRepository.findAll();
	}
	
	@GetMapping("/veiculos/find")
	@ApiOperation(value="Retorna os veículos de acordo com o termo passado parâmetro q")
	public Veiculo listaVeiculoPorParametro(@QueryParam("veiculo") String veiculo,
											@QueryParam("marca") String marca,
											@QueryParam("ano") String ano,
											@QueryParam("descricao") String descricao,
											@QueryParam("vendido") String vendido){

	return null;		
	}

	@GetMapping("/veiculos/{id}")
	@ApiOperation(value="Retorna os detalhes do veículo")
	public Veiculo listaVeiculo(@PathVariable(value="id") long id){
		return veiculoRepository.findById(id);
	}
	
	@PostMapping("/veiculos")
	@ApiOperation(value="Adiciona um novo veículo")
	public Veiculo salvaVeiculo(@RequestBody Veiculo veiculo) {
		Date data = new Date();
		veiculo.setCreated(data);
		return veiculoRepository.save(veiculo);
	}
	
	@PutMapping("/veiculos/{id}")
	@ApiOperation(value="Atualiza os dados de um veículo")
	public Veiculo atualizaVeiculo(@PathVariable(value="id") long id, @RequestBody Veiculo veiculo) {
		Date data = new Date();
		veiculo.setId(id);
		veiculo.setUpdated(data);
		veiculoRepository.save(veiculo);
		return veiculo;
	}
	
	@PatchMapping("/veiculos/{id}")
	@ApiOperation(value="Atualiza alguns dados de um veículo")
	public Veiculo alteraVeiculo(@PathVariable(value="id") long id, @RequestBody Map<Object, Object> campos) {
		Date data = new Date();
		Veiculo veiculo = veiculoRepository.findById(id);
		veiculo.setUpdated(data);

		
		campos.forEach((k,v) -> {
			Field field = ReflectionUtils.findRequiredField(Veiculo.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field,  veiculo, v);
		});
		
		veiculoRepository.save(veiculo);	
		return veiculo;

	}
	
	@DeleteMapping("/veiculos/{id}")
	@ApiOperation(value="Deleta um veículo")
	public void apagaVeiculo(@PathVariable(value="id") long id) {
		veiculoRepository.deleteById(id);
	}
	
}