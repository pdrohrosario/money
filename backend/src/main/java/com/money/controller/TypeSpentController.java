package com.money.controller;

import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import com.money.model.dto.GoalStatusDTO;
import com.money.model.dto.TypeSpentDTO;
import com.money.service.TypeSpentService;
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
public class TypeSpentController
{

	@Autowired
	private TypeSpentService typeSpentService;

	@GetMapping()
	public List<TypeSpentDTO> list()
	{
		return this.typeSpentService.listTypeSpent();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<TypeSpentDTO> create( @RequestBody @Valid TypeSpentDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TypeSpentDTO dto = this.typeSpentService.create(form);
		URI uri = uriBuilder.path("/type-spent/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public TypeSpentDTO search(@PathVariable Long id)
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
	public ResponseEntity<TypeSpentDTO> update(@PathVariable Long id, @RequestBody @Valid TypeSpentDTO form)
		throws Exception
	{
		TypeSpentDTO dto = this.typeSpentService.update(form);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
