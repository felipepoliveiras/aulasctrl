package io.senai.aulasctrl.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.TurmaDAO;
import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.exceptions.ValidacaoException;
import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.models.Curso;
import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.PresencaAula;
import io.senai.aulasctrl.models.Turma;

@Service
public class TurmaService extends CrudService<Turma>{

	@Autowired
	@Qualifier("turmaJPA")
	private TurmaDAO turmaDAO;
	
	@Autowired
	private MatriculaAlunoService matriculaAlunoService;
	
	@Autowired
	private DiaDeAulaService diaDeAulaService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private PresencaAulaService presencaAulaService;

	@Override
	public DAO<Turma> getDAO() {
		return turmaDAO;
	}
	
	@Override
	public String[] getIgnoredUpdateFields() {
		return new String[] {"id"};
	}
	
	@Override
	public String[] getUniqueFields() {
		return new String[] {"codigoTurma"};
	}
	
	@Override
	public Turma persistir(Turma obj, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
		checkAndTriggerUniqueValidationErrors(obj, bindingResult);
		
		//Verificando se curso existe
		Curso curso = cursoService.buscar(obj.getCurso().getId());
		obj.setCurso(curso);
		
		//Validando data
		if (obj.getDataInicio().getTime() > obj.getDataConclusao().getTime()) { 
			bindingResult.addError(new FieldError("turma", "dataInicio", "A data de início não pode ser maior que a data de conclusão"));
			throw new ValidacaoException(bindingResult);
		}
		
		this.turmaDAO.persistir(obj);
		
		return obj;
	}
	
	public PresencaAula aplicarPresenca(Aluno solicitante, Long turmaId, BindingResult br) {
		PresencaAula presenca = new PresencaAula();
		
		//Turma
		Turma turma = null;
		try {
			turma = buscar(turmaId);
		} catch (EntidadeNaoEncontradaException e) {
			br.addError(new FieldError("presencaAula", "turma", "A turma informada não existe"));
			throw new ValidacaoException(br);
		} 
		
		//Verifica se o aluno esta matriculado na turma
		if (!matriculaAlunoService.alunoEstaMatriculadoNaTurma(solicitante, turma)) {
			br.addError(new FieldError("presencaAula", "turma", "O aluno não está matriculado na turma solicitada"));
			throw new ValidacaoException(br);
		}
		
		//Dia de aula
		try {
			presenca.setDiaDeAula(diaDeAulaService.buscarAulaDeHojeDaTurma(turma));
		} catch (Exception e) {
			br.addError(new FieldError("presencaAula", "diaDeAula", "Não existe um dia de aula lançado no sistema para a data de hoje"));
			throw new ValidacaoException(br);
		}
		
		//Criando o objeto da presenca
		
		presenca.setAluno(solicitante);
		presenca.setHoraEntrada(new Date());
		
		presencaAulaService.persistir(presenca);
		
		return presenca;
	}

}
