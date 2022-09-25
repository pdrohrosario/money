package com.money.controller;

import com.money.controller.form.GoalForm;
import com.money.model.Goal;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import com.money.model.dto.UserDTO;
import com.money.service.GoalService;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class GoalController
{
	@Autowired
	private GoalService goalService;

	@GetMapping("/goals")
	public Page<GoalDetalheDTO> list(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return this.goalService.findAllGoals(pageable);
	}

	@GetMapping("/{id}/goals")
	public Page<GoalDetalheDTO> search(@PathVariable Long id,@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return this.goalService.findGoalsByUserId(id,pageable);
	}
//	@PostMapping("/{id}/goals")
//	@Transactional
//	public ResponseEntity<GoalDTO> create ( @RequestBody @Valid GoalForm form, @PathVariable Long id,UriComponentsBuilder uriBuilder)
//		throws Exception
//	{
//		GoalDTO dto = this.goalService.create(form, id);
//		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.getId()).toUri();
//		return ResponseEntity.created(uri).body(dto);
//	}

	@DeleteMapping("/{id}/goals")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){

		boolean deleteGoal = this.goalService.delete(id);
		if (!deleteGoal)
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/goals")
	@Transactional
	public ResponseEntity<GoalDTO> update(@PathVariable Long id, @RequestBody @Valid GoalForm form) throws Exception
	{
		GoalDTO dto = this.goalService.update(form, id);
		if(dto == null){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
