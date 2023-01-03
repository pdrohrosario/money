package com.money.controller;

import com.money.model.dto.PoupancaDTO;
import com.money.model.dto.PoupancaDetalheDTO;
import com.money.service.PoupancaService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PoupancaController
{
	@Autowired
	private PoupancaService service;

//	@GetMapping("/poupancas/{userName}")
//	public List<PoupancaDTO> list(@PathVariable("userName") String userName){
//
//		if(userName == null){
//			return null;
//		}
//
//		return this.service.listKeepMoneyByUserName(userName);
//	}
//
//	@GetMapping("/poupanca/{id}")
//	public PoupancaDetalheDTO search(@PathVariable Long id){
//		return this.service.search(id);
//	}
//
//	@PostMapping("/poupanca")
//	@Transactional
//	public ResponseEntity<PoupancaDTO> create( @RequestBody @Valid PoupancaDetalheDTO form, UriComponentsBuilder uriBuilder)
//		throws Exception
//	{
//		PoupancaDTO dto = this.service.create(form);
//		if(dto == null){
//			return ResponseEntity.notFound().build();
//		}
//		URI uri = uriBuilder.path("/poupanca/{id}").buildAndExpand(dto.getId()).toUri();
//		return ResponseEntity.created(uri).body(dto);
//	}
//
//	@DeleteMapping("/poupanca/{id}")
//	@Transactional
//	public ResponseEntity<?> delete(@PathVariable Long id){
//
//		if (this.service.delete(id))
//		{
//			return ResponseEntity.ok().build();
//		}
//		else
//		{
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/poupanca/{id}")
//	@Transactional
//	public ResponseEntity<PoupancaDTO> update(@RequestBody PoupancaDetalheDTO form) throws Exception
//	{
//		PoupancaDTO dto = this.service.update(form);
//		if(dto == null){
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().build();
//	}
}
