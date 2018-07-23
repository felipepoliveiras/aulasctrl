package io.senai.aulasctrl.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.senai.aulasctrl.utils.HashUtils;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Authentication{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 80, nullable = false)
	@NotNull
	@Size(min = 6, max = 64)
	private String nome;
	
	@Column(length = 120, nullable = false, unique = true)
	@NotNull
	@Email
	private String email;
	
	@Column(length = 64, nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull
	@Size(min = 6, max = 64) 
	private String senha;
	
	@Column(nullable = false)
	@NotNull
	private Boolean ativo = false;
	
	public void hashearSenha() {
		if (this.senha != null) {
			this.senha = HashUtils.saltedSha256(senha);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	@JsonIgnore
	public String getName() {
		return nome;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>(3);
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
		
		if (this instanceof Administrador || this instanceof Instrutor) {
			authorities.add(new SimpleGrantedAuthority("ROLE_TURMA_ADM"));
		}
		
		if (this instanceof Administrador) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
		} else if (this instanceof Instrutor) {
			authorities.add(new SimpleGrantedAuthority("ROLE_INSTRUTOR"));
		} else if (this instanceof Aluno) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ALUNO"));
		}
		
		return authorities;
	}

	@Override
	@JsonIgnore
	public Object getCredentials() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getDetails() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {
		return this;
	}

	@Override
	@JsonIgnore
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	@JsonIgnore
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}	
}
