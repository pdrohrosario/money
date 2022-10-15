package com.money.controller;

import com.money.model.PlanoGasto;
import com.money.model.dto.PlanoGastoDTO;
import com.money.model.dto.PlanoGastoDetalheDTO;
import com.money.model.dto.TipoGastoDTO;
import com.money.service.PlanoGastoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/plano-gasto/")
public class PlanoGastoController
{
	@Autowired
	private PlanoGastoService planoGastoService;

	@PostMapping("")
	@Transactional
	public ResponseEntity<PlanoGastoDTO> create( @RequestBody @Valid PlanoGastoDetalheDTO dto, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		PlanoGastoDTO plan = this.planoGastoService.create(dto);
		if(plan != null)
		{
			URI uri = uriBuilder.path("/plano-gasto/{id}").buildAndExpand(plan.getId()).toUri();
			return ResponseEntity.created(uri).body(plan);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping()
	public List<PlanoGasto> list()
	{
		return this.planoGastoService.listPlanosDeGasto();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanoGastoDetalheDTO> search(@PathVariable Long id){
		PlanoGastoDetalheDTO dto = this.planoGastoService.search(id);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PlanoGastoDTO> update(@PathVariable Long id,
		@RequestBody @Valid PlanoGastoDetalheDTO dto) throws Exception
	{
		PlanoGastoDTO plan = this.planoGastoService.update(dto);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(plan);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		if (!this.planoGastoService.delete(id)){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
