package com.money.controller;

import com.money.model.dto.KeepMoneyDTO;
import com.money.model.dto.KeepMoneyDetalheDTO;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.service.KeepMoneyService;
import com.money.service.TransferService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class KeepMoneyController
{
	@Autowired
	private KeepMoneyService service;

	@GetMapping("/{userName}/keep-money")
	public List<KeepMoneyDTO> list(@PathVariable("userName") String userName){

		if(userName == null){
			return null;
		}

		return this.service.listKeepMoneyByUserName(userName);
	}

	@GetMapping("/keep-money/{id}")
	public KeepMoneyDetalheDTO search(@PathVariable Long id){
		return this.service.search(id);
	}

	@PostMapping("/keep-money")
	@Transactional
	public ResponseEntity<KeepMoneyDTO> create( @RequestBody @Valid KeepMoneyDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		KeepMoneyDTO dto = this.service.create(form);
		URI uri = uriBuilder.path("/keep-money/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping("/keep-money/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){

		boolean deleteGoal = this.service.delete(id);
		if (deleteGoal)
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/keep-money/{id}")
	@Transactional
	public ResponseEntity<KeepMoneyDTO> update(@RequestBody KeepMoneyDetalheDTO form) throws Exception
	{
		KeepMoneyDTO dto = this.service.update(form);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
