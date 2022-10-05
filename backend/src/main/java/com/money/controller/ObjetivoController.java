package com.money.controller;

import com.money.model.dto.*;
import com.money.service.ObjetivoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ObjetivoController
{
	@Autowired
	private ObjetivoService objetivoService;

	@GetMapping("/{userName}")
	public List<ObjetivoDTO> list(@PathVariable("userName") String userName)
	{

		if (userName == null && userName.isEmpty())
		{
			return null;
		}

		return this.objetivoService.listGoalsByUserName(userName);
	}

	@PostMapping("/objetivo")
	@Transactional
	public ResponseEntity<ObjetivoDTO> create( @RequestBody @Valid ObjetivoDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		ObjetivoDTO dto = this.objetivoService.create(form);
		URI uri = uriBuilder.path("/objetivos/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/objetivos/status/{id}")
	public ObjetivoStatusDTO goalStatus(@PathVariable Long id)
	{
		return this.objetivoService.verificateGoalStatus(id);
	}

	@GetMapping("/{userName}/objetivos/finalizados")

	public List<ObjetivosFinalizadosDTO> listGoalsBeforeActualDate(@PathVariable("userName") String userName){

		if (userName == null && userName.isEmpty())
		{
			return null;
		}

		return this.objetivoService.listOldenGoalsByUserName(userName);
	}

	@GetMapping("/objetivos/{id}")
	public ObjetivoDetalheDTO search(@PathVariable Long id)
	{
		return this.objetivoService.search(id);
	}

	@DeleteMapping("/objetivos/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{

		if (this.objetivoService.delete(id))
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/objetivos/{id}")
	@Transactional
	public ResponseEntity<ObjetivoDTO> update(@PathVariable Long id, @RequestBody @Valid ObjetivoDetalheDTO form)
		throws Exception
	{
		ObjetivoDTO dto = this.objetivoService.update(form, id);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
