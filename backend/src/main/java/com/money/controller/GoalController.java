package com.money.controller;

import com.money.model.dto.*;
import com.money.service.GoalService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class GoalController
{
	@Autowired
	private GoalService goalService;

	@GetMapping("/{userName}/goals")
	public List<GoalDTO> list(@PathVariable("userName") String userName)
	{

		if (userName == null && userName.isEmpty())
		{
			return null;
		}

		return this.goalService.listGoalsByUserName(userName);
	}

	@PostMapping("/goals")
	@Transactional
	public ResponseEntity<GoalDTO> create( @RequestBody @Valid GoalDetalheDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		GoalDTO dto = this.goalService.create(form);
		URI uri = uriBuilder.path("/goals/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/goals/status/{id}")
	public GoalStatusDTO goalStatus(@PathVariable Long id)
	{
		return this.goalService.verificateGoalStatus(id);
	}

	@GetMapping("/{userName}/goals/olden")

	public List<GoalOldenDTO> listGoalsBeforeActualDate(@PathVariable("userName") String userName){

		if (userName == null && userName.isEmpty())
		{
			return null;
		}

		return this.goalService.listOldenGoalsByUserName(userName);
	}

	@GetMapping("/goals/{id}")
	public GoalDetalheDTO search(@PathVariable Long id)
	{
		return this.goalService.search(id);
	}

	@DeleteMapping("/{id}/goals")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{

		boolean deleteGoal = this.goalService.delete(id);
		if (deleteGoal)
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/goals/{id}")
	@Transactional
	public ResponseEntity<GoalDTO> update(@PathVariable Long id, @RequestBody @Valid GoalDetalheDTO form)
		throws Exception
	{
		GoalDTO dto = this.goalService.update(form, id);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
