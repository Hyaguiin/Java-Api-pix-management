package com.br.pix.api.UrubuDoPix.ModelServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pix.api.UrubuDoPix.ModelEntitys.Usuario;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToCreate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToDelete;
import com.br.pix.api.UrubuDoPix.ModelExceptions.NotFound;
import com.br.pix.api.UrubuDoPix.ModelRepositoryes.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		try {

			return usuarioRepository.findAll();

		} catch (NotFound e) {
			throw new NotFound("Nenhum Dado existente no Banco de Dados!");
		}
	}

	public Optional<Usuario> findById(Integer id) {
		try {
			return usuarioRepository.findById(id);
		} catch (NotFound err) {

			throw new NotFound("Não foi possível achar pelo Id ou não existe");
		}
	}

	public Usuario save(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		} catch (FailedToCreate err) {
			throw new FailedToCreate("Não foi possível criar o objeto");
		}
	}

	public void deleteById(Integer id) {
		try {

			usuarioRepository.deleteById(id);
		} catch (FailedToDelete err) {

			throw new FailedToCreate("Não foi possível criar o objeto");

		}
	}

}
