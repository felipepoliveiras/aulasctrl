package io.senai.aulasctrl.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "matricula_instrutor",
uniqueConstraints = @UniqueConstraint(columnNames = {"turma_id", "instrutor_id"}))
public class MatriculaInstrutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "turma_id", nullable = false)
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name = "instrutor_id", nullable = false)
	private Instrutor instrutor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}
	
	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}
}
