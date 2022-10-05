package com.money.service;

import com.money.model.PlanoGasto;
import com.money.model.dto.PlanoGastoDTO;
import com.money.model.dto.PlanoGastoDetalheDTO;
import com.money.repository.PlanoDeGastoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		if (tp != null)
		{

			this.repository.savePlanSpent(dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
				dto.getDataInicio(), dto.getDataFim(), dto.getId());

			return new PlanoGastoDTO(dto.getId(), dto.getTitulo(), dto.getQuantia());
		}

		return null;

	}

	public PlanoGasto findById(Long id)
	{
		return this.repository.findById(id).get();
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

			if (dto.getDataInicio() != null)
			{
				plan.setDataInicio(dto.getDataInicio());
			}

			if (dto.getDataFim() != null)
			{
				plan.setDataFim(dto.getDataFim());
			}

			this.repository.update(plan.getTitulo(), plan.getQuantia(), plan.getDescricao(),
				plan.getDataInicio(), plan.getDataFim(), plan.getId());

			return new PlanoGastoDTO(plan.getId(), plan.getTitulo(), plan.getQuantia());
		}

		return null;
	}
}
