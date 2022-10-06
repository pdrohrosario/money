package com.money.controller;

import com.money.model.dto.FormaPagamentoDTO;
import com.money.service.FormaPagamentoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/forma-pagamento/")
public class FormaPagamentoController
{
	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@GetMapping()
	public List<FormaPagamentoDTO> list()
	{
		return this.formaPagamentoService.listPaymentWay();
	}

	@PostMapping()
	@Transactional
	public ResponseEntity<FormaPagamentoDTO> create( @RequestBody @Valid FormaPagamentoDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		FormaPagamentoDTO dto = this.formaPagamentoService.create(form);
		if(dto == null){
			return ResponseEntity.badRequest().build();
		}
		URI uri = uriBuilder.path("/forma-pagamento/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public FormaPagamentoDTO search(@PathVariable Long id)
	{
		return this.formaPagamentoService.search(id);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		if (this.formaPagamentoService.delete(id))
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
	public ResponseEntity<FormaPagamentoDTO> update(@PathVariable Long id, @RequestBody @Valid FormaPagamentoDTO form)
		throws Exception
	{
		FormaPagamentoDTO dto = this.formaPagamentoService.update(form);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
