package com.money.service;

import com.money.model.TipoGasto;
import com.money.model.TipoGasto;
import com.money.model.dto.TipoGastoDTO;
import com.money.model.dto.TipoGastoDTO;
import com.money.repository.TipoGastoRepository;
import io.jsonwebtoken.lang.Assert;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoGastoService
{
	@Autowired
	private TipoGastoRepository tipoGastoRepository;

	public TipoGasto findTipoGastoByName(String PaymentWay){
		return this.tipoGastoRepository.findTipoGastoByNome(PaymentWay).orElse(null);
	}

	public List<TipoGastoDTO> listTipoGasto()
	{
		return this.tipoGastoRepository.findAll().stream().map(TipoGastoDTO::new).collect(
			Collectors.toList());
	}

	public TipoGastoDTO create(TipoGastoDTO form)
	{
		Assert.isNull(this.findTipoGastoByName(form.getNome()), "Tipo de gasto já registrada!");
		TipoGasto TipoGasto = new TipoGasto();
		TipoGasto.setId(this.gerarSequencial());
		TipoGasto.setNome(form.getNome());

		this.tipoGastoRepository.save(TipoGasto);

		return new TipoGastoDTO(TipoGasto);
	}

	public TipoGasto findById(Long id){
		return this.tipoGastoRepository.findTipoGastoById(id).orElse(null);
	}

	public TipoGastoDTO search(Long id)
	{
		TipoGasto tp = this.findById(id);

		return new TipoGastoDTO(tp);
	}

	public boolean delete(Long id)
	{
		TipoGasto to = this.findById(id);
		Assert.notNull(to, "Tipo de gasto não encontrado!");
			this.tipoGastoRepository.delete(to);
			return true;
	}

	public TipoGastoDTO update(TipoGastoDTO form)
	{
		TipoGasto to = this.findById(form.getId());

		Assert.isNull(this.findTipoGastoByName(form.getNome()), "Tipo de gasto já registrada!");

		to.setNome(form.getNome());
		to.setId(form.getId());

		this.tipoGastoRepository.save(to);

		return form;


	}

	public Long gerarSequencial()
	{
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste)
		{
			int numero = random.nextInt(100000);

			if (this.findById((long) numero) == null)
			{
				idExiste = false;
				sequencial = (long) numero;
			}
			;
		}
		return sequencial;
	}
}
