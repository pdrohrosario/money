package com.money.service;

import com.money.model.TipoGasto;
import com.money.model.dto.TipoGastoDTO;
import com.money.repository.TipoGastoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoGastoService
{
	@Autowired
	private TipoGastoRepository tipoGastoRepository;

	public TipoGasto findTypeSpentByName(String typeSpent){
		return this.tipoGastoRepository.findTypeSpentByName(typeSpent);
	}

	public List<TipoGastoDTO> listTypeSpent()
	{
		return this.tipoGastoRepository.listTypeSpent();
	}

	public TipoGastoDTO create(TipoGastoDTO form)
	{
		if(form.getNome() == null){
			return null;
		}

		this.tipoGastoRepository.saveTypeSpent(form.getNome());
		TipoGasto tp = this.findTypeSpentByName(form.getNome());

		return new TipoGastoDTO(tp.getId(), tp.getNome());
	}

	public TipoGasto findById(Long id){
		return this.tipoGastoRepository.findById(id).get();
	}

	public TipoGastoDTO search(Long id)
	{
		TipoGasto tp =  this.findById(id);

		return new TipoGastoDTO(tp.getId(), tp.getNome());
	}

	public boolean delete(Long id)
	{
		TipoGasto to = this.findById(id);

		if(to != null){
			this.tipoGastoRepository.delete(id);
			return true;
		}

		return false;
	}

	public TipoGastoDTO update(TipoGastoDTO form)
	{
		TipoGasto to = this.findById(form.getId());

		if(to != null){
			if(form.getNome() == null)
			{
				return null;
			}

			this.tipoGastoRepository.update(form.getId(), form.getNome());

			return form;
		}

		return null;
	}
}
