package com.money.controller;

import com.money.controller.form.GoalForm;
import com.money.controller.form.TransferForm;
import com.money.controller.form.UserForm;
import com.money.model.Transfer;
import com.money.model.User;
import com.money.model.dto.GoalDetalheDTO;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import com.money.model.dto.UserDTO;
import com.money.repository.TransferRepository;
import com.money.repository.UserRepository;
import com.money.service.TransferService;
import com.money.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TransferController
{
	@Autowired
	private TransferService transferService;

	@Autowired
	private UserService userService;

	@GetMapping("/{userName}/transfers")
	public List<TransferDTO> list(@RequestParam(required = true) String userName){

		if(userName == null){
			return null;
		}

		User user = this.userService.findUserByUserName(userName);

		return this.transferService.findAllTransferByUserId(user.getId());
	}

	@GetMapping("/transfers/{id}")
	public TransferDetalheDTO search(@PathVariable Long id){
		return this.transferService.findTransferById(id);
	}

	@PostMapping("/transfers")
	@Transactional
	public ResponseEntity<TransferDTO> create( @RequestBody @Valid TransferForm form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		TransferDTO dto = this.transferService.create(form);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping("/transfers/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){

		boolean deleteGoal = this.transferService.delete(id);
		if (!deleteGoal)
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

//	@PutMapping("/transfer/{id}")
//	@Transactional
//	public ResponseEntity<TransferDTO> update(@PathVariable Long id, @RequestBody @Valid TransferForm form) throws Exception
//	{
//		TransferDTO dto = this.transferService.update(form, id);
//		if(dto == null){
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().build();
//	}
}
