package com.money.service;

import com.money.model.PlanoGasto;
import com.money.model.dto.PlanoGastoDTO;
import com.money.model.dto.PlanoGastoDetalheDTO;
import com.money.repository.PlanoDeGastoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PlanoGastoService
{
	@Autowired
	private PlanoDeGastoRepository repository;

//	public List<TypeSpentDTO> listTypeSpent()
//	{
//		return this.typeSpentRepository.listTypeSpent();
//	}

	public PlanoGastoDTO create(PlanoGastoDetalheDTO dto)
	{
		PlanoGasto tp = this.findById(dto.getId());

		if (tp == null)
		{
			Long sequencial = this.gerarSequencial();
			this.repository.savePlanSpent(sequencial, dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
				dto.getDataInicio(), dto.getDataFim());

			return new PlanoGastoDTO(sequencial, dto.getTitulo(), dto.getQuantia());
		}

		return null;

	}

	public PlanoGasto findById(Long id)
	{
		Optional<PlanoGasto> pl = this.repository.findById(id);

		return pl.orElse(null);
	}

	public PlanoGastoDetalheDTO search(Long id)
	{
		PlanoGasto plan = this.findById(id);

		return new PlanoGastoDetalheDTO(plan.getId(), plan.getTitulo(), plan.getQuantia(),
			plan.getDescricao(), plan.getDataInicio(), plan.getDataFim());
	}

	public boolean delete(Long id)
	{
		PlanoGasto to = this.findById(id);

		if (to != null)
		{
			this.repository.delete(id);
			return true;
		}

		return false;
	}

	public PlanoGastoDTO update(PlanoGastoDetalheDTO dto)
	{
		PlanoGasto plan = this.findById(dto.getId());

		if (plan != null)
		{

			if (dto.getTitulo() != null)
			{
				plan.setTitulo(dto.getTitulo());
			}

			if (dto.getQuantia() != null)
			{
				plan.setQuantia(dto.getQuantia());
			}

			if (dto.getDescricao() != null)
			{
				plan.setDescricao(dto.getDescricao());
			}

			if (dto.getDataInicio() != null && dto.getDataInicio().isAfter(plan.getDataInicio()))
			{
				plan.setDataInicio(dto.getDataInicio());
			}

			if (dto.getDataFim() != null)
			{
				plan.setDataFim(dto.getDataFim());
			}

			this.repository.update(plan.getId(), plan.getDataFim(), plan.getDescricao(), plan.getQuantia(), plan.getTitulo());

			return new PlanoGastoDTO(plan.getId(), plan.getTitulo(), plan.getQuantia());
		}

		return null;
	}

	public Long gerarSequencial(){
		boolean idExiste = true;
		Long sequencial = null;
		Random random = new Random();
		while (idExiste){
			int numero = random.nextInt(100000);

			if(this.findById((long) numero) == null){
				idExiste = false;
				sequencial = (long) numero;
			};
		}
		return sequencial;
	}

	public List<PlanoGasto> listPlanosDeGasto()
	{
		return this.repository.listPlanoDeGasto();
	}
}
