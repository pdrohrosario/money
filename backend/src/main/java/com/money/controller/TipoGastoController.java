package com.money.controller;

import com.money.model.dto.TipoGastoDTO;
import com.money.service.TipoGastoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/type-spent/")
public class TipoGastoController
{

	@Autowired
	private TipoGastoService typeSpentService;

	@GetMapping()
	public List<TipoGastoDTO> list()
	{
		return this.typeSpentService.listTypeSpent();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<TipoGastoDTO> create( @RequestBody @Valid TipoGastoDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TipoGastoDTO dto = this.typeSpentService.create(form);
		URI uri = uriBuilder.path("/type-spent/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public TipoGastoDTO search(@PathVariable Long id)
	{
		return this.typeSpentService.search(id);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{

		boolean deleteGoal = this.typeSpentService.delete(id);
		if (deleteGoal)
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TipoGastoDTO> update(@PathVariable Long id, @RequestBody @Valid TipoGastoDTO form)
		throws Exception
	{
		TipoGastoDTO dto = this.typeSpentService.update(form);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
