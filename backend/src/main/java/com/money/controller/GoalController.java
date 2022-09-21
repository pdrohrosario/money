package com.money.controller;

import com.money.controller.form.GoalForm;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.UserDTO;
import com.money.service.GoalService;
import java.net.URI;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class GoalController
{
	@Autowired
	private GoalService goalService;

//	@GetMapping
//	public Page<GoalDTO> list(@RequestParam(required = true) Long userId, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
//		Page<Goal> goals;
//
//		if(userId == null){
//			return null;
//		}
//
//		Optional<User> user = this.userRepository.findById(userId);
//		goals =  this.goalRepository.findGoalByUser(user, pageable);
//
//		return GoalDTO.coverter(goals);
//	}
	@PostMapping("/{id}/goals")
	@Transactional
	public ResponseEntity<GoalDTO> create ( @RequestBody @Valid GoalForm form, @PathVariable Long id,UriComponentsBuilder uriBuilder)
		throws Exception
	{
		GoalDTO dto = this.goalService.create(form, id);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
//	@GetMapping("/{id}")
//	public ResponseEntity<GoalDTO> search(@PathVariable Long id){
//		List<UserDTO> dto = this.goalService.findGoalByUser(id);
//		if(dto == null){
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(dto);
//	}
}
