package com.money.controller;

import com.money.model.dto.PaymentWayDTO;
import com.money.service.PaymentService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/payment-way/")
public class PaymentWayController
{
	@Autowired
	private PaymentService paymentService;

	@GetMapping()
	public List<PaymentWayDTO> list()
	{
		return this.paymentService.listPaymentWay();
	}

	@PostMapping("")
	@Transactional
	public ResponseEntity<PaymentWayDTO> create( @RequestBody @Valid PaymentWayDTO form, UriComponentsBuilder uriBuilder)
		throws Exception
	{
		PaymentWayDTO dto = this.paymentService.create(form);
		URI uri = uriBuilder.path("/payment-way/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping("/{id}")
	public PaymentWayDTO search(@PathVariable Long id)
	{
		return this.paymentService.search(id);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id)
	{

		boolean deleteGoal = this.paymentService.delete(id);
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
	public ResponseEntity<PaymentWayDTO> update(@PathVariable Long id, @RequestBody @Valid PaymentWayDTO form)
		throws Exception
	{
		PaymentWayDTO dto = this.paymentService.update(form);
		if (dto == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
