package io.senai.aulasctrl.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.DiaDeAulaDAO;
import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.exceptions.NaoImplementadoException;
import io.senai.aulasctrl.exceptions.ValidacaoException;
import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.Turma;
import io.senai.aulasctrl.utils.CalendarUtils;

@Service
public class DiaDeAulaService extends CrudService<DiaDeAula>{
	
	@Autowired
	@Qualifier("diaDeAulaJPA")
	private DiaDeAulaDAO diaDeAulaDAO;
	
	@Autowired
	private TurmaService turmaService;

	@Override
	public DAO<DiaDeAula> getDAO() {
		return diaDeAulaDAO;
	}
	
	@Override
	public DiaDeAula alterar(Long id, DiaDeAula source, BindingResult bindingResult) {
		throw new NaoImplementadoException();
	}
	
	public DiaDeAula buscarAulaDeHojeDaTurma(Turma turma) {
		return buscarPorTurmaEData(turma, CalendarUtils.resetHourTo0());
	}
	
	public DiaDeAula buscarPorTurmaEData(Turma turma, Date data) {
		DiaDeAula diaDeAula = diaDeAulaDAO.buscarPorDataETurma(data, turma);
		if (diaDeAula == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return diaDeAula;
	}
	
	@Override
	public DiaDeAula persistir(DiaDeAula obj, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
		//Validando o id da turma
		if (obj.getTurma().getId() == null) {
			bindingResult.addError(new FieldError("diaDeAula", "turma", "O turma deve ser informada"));
			throw new ValidacaoException(bindingResult);
		}
		
		//Busca a turma pra ver se ela existe
		Turma turma = turmaService.buscar(obj.getTurma().getId());
		
		//Verifica se o dia de aula ja foi cadastrado
		if (diaDeAulaDAO.buscarPorDataETurma(obj.getData(), turma) != null) {
			bindingResult.addError(new FieldError("diaDeAula", "turma", "Este dia de aula ja foi registrado"));
		}
		
		diaDeAulaDAO.persistir(obj);
		
		return obj;
	}

}
