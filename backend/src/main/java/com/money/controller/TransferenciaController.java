package com.money.controller;

import com.money.model.Transferencia;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.service.TransferenciaService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TransferenciaController
{
	@Autowired
	private TransferenciaService transferenciaService;

	@GetMapping("/transferencias/{userId}")
	public List<TransferDTO> list(@PathVariable("userId") Long userId){

		return this.transferenciaService.findAllTransferByUserId(userId);
	}

	@GetMapping("/transferencia/{id}")
	public TransferDetalheDTO search(@PathVariable Long id){
		return this.transferenciaService.search(id);
	}
//
//
//	@GetMapping("/transferencia/poupanca/{userName}")
//	public List<TransferDTO>listaTransferenciasPoupancaAtual(@PathVariable String userName){
//		return  this.transferenciaService.listaTransferenciasPoupancaAtual(userName);
//	}
//
//	@GetMapping("/transferencia/poupanca-vinculada/{id}")
//	public List<TransferDetalheDTO> findTransferenciasVinculasPoupanca(@PathVariable("id") Long poupancaId){
//		if(poupancaId != null)
//		{
//			return this.transferenciaService.findTransferenciasVinculasPoupanca(poupancaId);
//		}
//
//		return null;
//	}
//
	@PostMapping("/transferencia")
	@Transactional
	public ResponseEntity<TransferDTO> create( @RequestBody @Valid TransferDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TransferDTO dto = this.transferenciaService.create(form);
		if(dto == null){
			return ResponseEntity.badRequest().build();
		}
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}



	@DeleteMapping("/transferencia/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){

		if (this.transferenciaService.delete(id))
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/transferencia/{id}")
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
