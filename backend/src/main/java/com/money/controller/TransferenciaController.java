package com.money.controller;

import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.service.TransferenciaService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/transferencia")
@RestController
public class TransferenciaController
{
	@Autowired
	private TransferenciaService transferenciaService;

	@GetMapping("/{userName}")
	public List<TransferDTO> list(@PathVariable("userName") String userName){

		if(userName == null){
			return null;
		}

		return this.transferenciaService.findAllTransferByUserName(userName);
	}

	@GetMapping("/{id}")
	public TransferDetalheDTO search(@PathVariable Long id){
		return this.transferenciaService.search(id);
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<TransferDTO> create( @RequestBody @Valid TransferDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TransferDTO dto = this.transferenciaService.create(form);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){

		boolean deleteGoal = this.transferenciaService.delete(id);
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
	public ResponseEntity<TransferDTO> update(@PathVariable Long id, @RequestBody TransferDetalheDTO form) throws Exception
	{
		TransferDTO dto = this.transferenciaService.update(form);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
