package com.money.controller;

import com.money.model.dto.PlanSpentDTO;
import com.money.model.dto.PlanSpentDetalheDTO;
import com.money.model.dto.UserDTO;
import com.money.model.dto.UserDetalheDTO;
import com.money.service.PlanSpentService;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/plan-spent/")
public class PlanSpentController
{
	@Autowired
	private PlanSpentService planSpentService;

	@PostMapping("")
	@Transactional
	public ResponseEntity<PlanSpentDTO> create( @RequestBody @Valid PlanSpentDetalheDTO dto, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		PlanSpentDTO plan = this.planSpentService.create(dto);
		if(plan != null)
		{
			URI uri = uriBuilder.path("/plan-spent/{id}").buildAndExpand(plan.getId()).toUri();
			return ResponseEntity.created(uri).body(plan);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanSpentDetalheDTO> search(@PathVariable Long id){
		PlanSpentDetalheDTO dto = this.planSpentService.search(id);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PlanSpentDTO> update(@PathVariable Long id,
		@RequestBody @Valid PlanSpentDetalheDTO dto) throws Exception
	{
		PlanSpentDTO plan = this.planSpentService.update(dto);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(plan);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		boolean deleteUser = this.planSpentService.delete(id);
		if (!deleteUser){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
