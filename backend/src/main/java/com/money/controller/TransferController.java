package com.money.controller;

import com.money.controller.form.TransferForm;
import com.money.model.Transfer;
import com.money.model.User;
import com.money.model.dto.TransferDTO;
import com.money.repository.TransferRepository;
import com.money.repository.UserRepository;
import java.net.URI;
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
@RequestMapping("/transfer")
public class TransferController
{
	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	@Cacheable(value="listTransfers")
	public Page<TransferDTO> list(@RequestParam(required = true) Long userId, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		Page<Transfer> transfers;

		if(userId == null){
			return null;
		}

		Optional<User> user = this.userRepository.findById(userId);
		transfers =  this.transferRepository.findTransferByUser(user, pageable);

		return TransferDTO.coverter(transfers);
	}

//	@PostMapping
//	@Transactional
//	@CacheEvict(value="listTransfers", allEntries = true)
//	public ResponseEntity<TransferDTO> create(@RequestBody @Valid TransferForm form, UriComponentsBuilder uriBuilder){
//		Transfer transfer = form.converter();
//		this.transferRepository.save(transfer);
//		URI uri = uriBuilder.path("/transfer/{id}").buildAndExpand(transfer.getId()).toUri();
//		return ResponseEntity.created(uri).body(new TransferDTO(transfer));
//	}
}
