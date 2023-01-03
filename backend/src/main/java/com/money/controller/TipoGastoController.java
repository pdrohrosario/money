package com.money.controller;

import com.money.model.dto.TipoGastoDTO;
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
@RequestMapping("/tipo-gasto/")
public class TipoGastoController
{

	@Autowired
	private TipoGastoService tipoGastoService;

	@GetMapping()
	public List<TipoGastoDTO> list()
	{
		return this.tipoGastoService.listTipoGasto();
	}

	@PostMapping()
	@Transactional
	public ResponseEntity<TipoGastoDTO> create( @RequestBody @Valid TipoGastoDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TipoGastoDTO dto = this.tipoGastoService.create(form);
		if(dto == null){
			return ResponseEntity.badRequest().build();
		}
		URI uri = uriBuilder.path("/forma-pagamento/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public TipoGastoDTO search(@PathVariable Long id)
	{
		return this.tipoGastoService.search(id);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		if (this.tipoGastoService.delete(id))
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
		TipoGastoDTO dto = this.tipoGastoService.update(form);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
