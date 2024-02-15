package com.exemploDTO.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Todos os imports necessários para fazer o código funcionar

@Data
// Demarca que a classe irá guardar dados
@NoArgsConstructor 
// Ajuda a encurtar a criação dos métodos construtores
@AllArgsConstructor
@Entity 
// Demarca que a classe será uma Entidade/Tabela
@Table(name = "usuario")
// Dá o nome a tabela
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// O item "id" atribui valor único aos itens da tabela permitindo que possam existir dados parecidos sem interferência
	
	@NotNull
	@NotBlank
	// Impede que o item seja adicionado a tabela com informações faltando
	private String nome;
	// Priva o acesso ao item, limita o preenchimento do item para somente texto e classifica seu nome como "nome"
	
	@NotNull
	@NotBlank
	private String senha;
	
	private String permissao;

	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
}
