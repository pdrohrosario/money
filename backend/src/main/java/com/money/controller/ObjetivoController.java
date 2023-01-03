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

	@GetMapping("/objetivos/{userId}")
	public List<ObjetivoDTO> listObjetivosByUserId(@PathVariable("userId") Long userId)
	{

		if(userId == null){
			return null;
		}

		return this.objetivoService.listObjetivosByUserId(userId);
	}
//
//	@PostMapping("/objetivo")
//	@Transactional
//	public ResponseEntity<ObjetivoDTO> create( @RequestBody @Valid ObjetivoDetalheDTO form, UriComponentsBuilder uriBuilder)
//		throws Exception
//	{
//		ObjetivoDTO dto = this.objetivoService.create(form);
//		URI uri = uriBuilder.path("/objetivos/{id}").buildAndExpand(dto.getId()).toUri();
//		return ResponseEntity.created(uri).body(dto);
//	}
//
//	@GetMapping("/objetivos/status/{id}")
//	public ObjetivoStatusDTO goalStatus(@PathVariable Long id)
//	{
//		ObjetivoStatusDTO list = this.objetivoService.verificateGoalStatus(id);
//
//		if(list == null){
//			return new ObjetivoStatusDTO();
//		}
//
//		return list;
//	}
//
//	@GetMapping("/objetivos/finalizados/{userName}")
//
//	public List<ObjetivosFinalizadosDTO> listGoalsBeforeActualDate(@PathVariable("userName") String userName){
//
//		if (userName == null && userName.isEmpty())
//		{
//			return null;
//		}
//
//		List<ObjetivosFinalizadosDTO> list = this.objetivoService.listOldenGoalsByUserName(userName);
//
//		return list;
//	}
//
	@GetMapping("/objetivo/{id}")
	public ObjetivoDetalheDTO search(@PathVariable Long id)
	{
		return this.objetivoService.search(id);
	}
//
//	@DeleteMapping("/objetivo/{id}")
//	@Transactional
//	public ResponseEntity<?> delete(@PathVariable Long id)
//	{
//
//		if (this.objetivoService.delete(id))
//		{
//			return ResponseEntity.ok().build();
//		}
//		else
//		{
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/objetivo/{id}")
//	@Transactional
//	public ResponseEntity<ObjetivoDTO> update(@PathVariable Long id, @RequestBody @Valid ObjetivoDetalheDTO form)
//		throws Exception
//	{
//		ObjetivoDTO dto = this.objetivoService.update(form, id);
//		if (dto == null)
//		{
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().build();
//	}
}
