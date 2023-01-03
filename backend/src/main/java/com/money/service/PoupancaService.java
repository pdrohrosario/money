package com.money.service;

import com.money.repository.PoupancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoupancaService
{
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PlanoGastoService planoGastoService;
	@Autowired
	private PoupancaRepository repository;

//	public PoupancaDTO create(PoupancaDetalheDTO dto)
//	{
//
//		User user = this.usuarioService.findUserByUserName(dto.getUserName());
//
//		if (user != null && this.validaPoupancaAtiva(LocalDate.now(), dto.getUserName()))
//		{
//
//			if(dto.getDataFim().isBefore(LocalDate.now())){
//				return null;
//			}
//
//			Integer quantidadeMeses = (dto.getDataFim().getMonth().getValue() - LocalDate.now().getMonth().getValue());
//
//			Double quantiaMes = dto.getQuantia()/quantidadeMeses;
//
//			PlanoGastoDTO plan = this.planoGastoService.create(
//				new PlanoGastoDetalheDTO(dto.getTitulo(), dto.getQuantia(), dto.getDescricao(),
//					LocalDate.now(), dto.getDataFim()));
//
//			Long sequencial = this.gerarSequencial();
//
//			this.repository.saveKeepMoney(sequencial, quantiaMes, quantidadeMeses, dto.getQuantidadeMinimaTransferencias(), plan.getId(), user.getId());
//
//			return new PoupancaDTO(sequencial, dto.getTitulo(), dto.getQuantia());
//		}
//		return null;
//	}
//
//	private boolean validaPoupancaAtiva(LocalDate dataInicio, String userName)
//	{
//		if (dataInicio == null){
//			return false;
//		}
//
//		List<Poupanca> poupancas = this.repository.buscaPoupancaAtiva(dataInicio, userName);
//
//		return poupancas.isEmpty();
//	}
//
//	public List<PoupancaDTO> listKeepMoneyByUserName(String userName)
//	{
//		List<PoupancaDTO> lista = this.repository.findPoupancaByUserName(userName);
//
//		return lista;
//	}
//
//	public PoupancaDetalheDTO search(Long id)
//	{
//		Poupanca poupanca = this.findPoupancaById(id);
//		if(poupanca != null)
//		{
//			return this.repository.findPoupancaDetalheById(id);
//		}
//		return new PoupancaDetalheDTO();
//	}
//
//	public Poupanca findPoupancaById(Long goalId)
//	{
//		Optional<Poupanca> p = this.repository.findPoupancaById(goalId);
//
//		return p.orElse(null);
//	}
//
//	public boolean delete(Long id)
//	{
//		Poupanca poupanca = this.findPoupancaById(id);
//		if (poupanca != null)
//		{
//			this.repository.deleteKeepMoney(id);
//			return true;
//		}
//
//		return false;
//	}
//
//	public PoupancaDTO update(PoupancaDetalheDTO dto)
//	{
//		Poupanca poupanca = this.findPoupancaById(dto.getId());
//		if (poupanca != null)
//		{
//			Integer quantidadeMeses = 0;
//
//			Double quantiaMes = 0.0;
//
//			if(dto.getDataFim().isBefore(LocalDate.now())){
//				return null;
//			}
//
//			if(dto.getDataFim().isBefore(poupanca.getPlanSpent().getDataInicio())){
//				return null;
//			}
//
//			if(dto.getQuantia() != null && !Objects.equals(dto.getQuantia(),
//				poupanca.getPlanSpent().getQuantia()))
//			{
//				quantidadeMeses = (dto.getDataFim().getMonth().getValue() - poupanca.getPlanSpent().getDataInicio().getMonth().getValue());
//
//				quantiaMes = dto.getQuantia() / quantidadeMeses;
//
//				poupanca.setQuantiaMesEsperada(quantiaMes);
//
//				poupanca.setQuantidadeMeses(quantidadeMeses);
//			}
//
//			PlanoGastoDTO plan = this.planoGastoService.update(
//				new PlanoGastoDetalheDTO(poupanca.getPlanSpent().getId(), dto.getTitulo(),
//					dto.getQuantia(), dto.getDescricao(), LocalDate.now(), dto.getDataFim()));
//
//			this.repository.update(poupanca.getQuantidadeMeses(),poupanca.getQuantiaMesEsperada());
//
//			return new PoupancaDTO(poupanca.getId(), plan.getTitulo(), plan.getQuantia());
//		}
//
//		return null;
//	}
//
//	public Poupanca findByUsernameAndDate(String username, LocalDate transferDate)
//	{
//		return this.repository.findByUsernameAndDate(username, transferDate);
//	}
//
//	public Long gerarSequencial(){
//		boolean idExiste = true;
//		Long sequencial = null;
//		Random random = new Random();
//		while (idExiste){
//			int numero = random.nextInt(100000);
//
//			if(this.findPoupancaById((long) numero) == null){
//				idExiste = false;
//				sequencial = (long) numero;
//			};
//		}
//		return sequencial;
//	}
}
