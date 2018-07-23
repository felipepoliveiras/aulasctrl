package io.senai.aulasctrl.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "presenca_aula",
uniqueConstraints = @UniqueConstraint(columnNames = {"aluno_id", "aula_id"}))
public class PresencaAula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaEntrada;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIME)
	private Date horaSaida;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id", nullable = false)
	@NotNull
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "aula_id", nullable = false)
	private DiaDeAula diaDeAula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public DiaDeAula getDiaDeAula() {
		return diaDeAula;
	}

	public void setDiaDeAula(DiaDeAula diaDeAula) {
		this.diaDeAula = diaDeAula;
	}
	
	
}
