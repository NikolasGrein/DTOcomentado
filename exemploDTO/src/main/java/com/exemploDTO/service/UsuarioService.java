package com.exemploDTO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemploDTO.dto.UsuarioDTO;
import com.exemploDTO.entity.Usuario;
import com.exemploDTO.repository.UsuarioRepository;

@Service
// Determina que esta classe será a camada de Serviço
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository= usuarioRepository;
	}
	// Conecta a camada Service com a camada Repository
	
	//Método modificado para usar o DTO
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		// Convert DTO to entity
		Usuario usuario = new Usuario (usuarioDTO.nome(),usuarioDTO.senha());
		Usuario salvarUsuario = usuarioRepository.save(usuario);	
		return new UsuarioDTO(salvarUsuario.getId(), salvarUsuario.getNome(), salvarUsuario.getSenha());
	}
	// Da utilidade a função "POST" - Salvar

	// Método modificado para utilizar o DTO
	public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
		Usuario existeUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		existeUsuario.setNome(usuarioDTO.nome());
		existeUsuario.setSenha(usuarioDTO.senha());
		
		Usuario updateUsuario = usuarioRepository.save(existeUsuario);
		return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha());
	}
	// Da utilidade a função "PUT" - Atualizar
	
	public boolean deletar(Long id) {
		Optional <Usuario> existeUsuario = usuarioRepository.findById(id);
		if (existeUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
	// Da utilidade a função "DELETE" - Deletar
	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
	// Da utilidade a função "GET" - Buscar
	
	public Usuario buscarPorId(Long id) {
		Optional <Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElse(null);
	}
	// Permite que a função GET busque itens das tabela por seu Id
}
