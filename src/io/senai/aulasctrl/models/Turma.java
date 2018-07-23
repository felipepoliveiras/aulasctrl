package io.senai.aulasctrl.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 16, nullable = false, unique = true)
	@Size(min = 1, max = 16)
	@NotNull
	private String codigoTurma;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Size(min = 1, max = 5)
	@JoinTable(name = "instrutores_turma",
	uniqueConstraints = @UniqueConstraint(columnNames = {"turma_id", "instrutor_id"}),
	joinColumns = @JoinColumn(name = "turma_id", nullable = false),
	inverseJoinColumns = @JoinColumn(name = "instrutor_id", nullable = false))
	private List<Instrutor> instrutores;
	
	@ManyToOne
	@JoinColumn(name = "curso_id", nullable = false)
	@NotNull
	private Curso curso;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataInicio;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataConclusao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public List<Instrutor> getInstrutores() {
		return instrutores;
	}
	
	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}
	
	public Date getDataConclusao() {
		return dataConclusao;
	}
	
	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
}
