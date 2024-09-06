package com.br.pix.api.UrubuDoPix.UsuarioModel.modelControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pix.api.UrubuDoPix.ModelEntitys.Usuario;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToCreate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToDelete;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToUpdate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.NotFound;
import com.br.pix.api.UrubuDoPix.ModelServices.UsuarioService;

@RestController
@RequestMapping("/pix/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> getAllUsuario() {
		try {
			return usuarioService.findAll();
		} catch (NotFound err) {
			throw new NotFound("Objeto não cadastrado no banco de Dados");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByIdUsuario(@PathVariable Integer id) {
		try {
			return usuarioService.findById(id).map(Usuario -> ResponseEntity.ok().body(Usuario))
					.orElse(ResponseEntity.notFound().build());
		} catch (NotFound err) {
			throw new NotFound(
					"Não foi possível encontrar o id: Objeto não cadastrado no banco de Dados ou não existe");
		}
	}

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		try {
			if (usuario == null) {
				return ResponseEntity.badRequest().build();
			}
			Usuario usuarioSalvo = usuarioService.save(usuario);
			return ResponseEntity.ok().body(usuarioSalvo);
		} catch (FailedToCreate err) {
			throw new FailedToCreate("Não foi Possível criar o objeto");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
		try {
			return usuarioService.findById(id).map(Usuario -> {
				Usuario.setEmail(usuarioDetails.getEmail());
				Usuario.setNome(usuarioDetails.getNome());
				Usuario.setSenha_hash(usuarioDetails.getSenha_hash());
				Usuario updt = usuarioService.save(usuarioDetails);
				return ResponseEntity.ok().body(updt);
			}).orElse(ResponseEntity.notFound().build());
		} catch (FailedToUpdate err) {
			throw new FailedToUpdate(
					"Não foi possível atualizar o Objeto, Id não encontrado ou não existe no banco de Dados");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuarioById(@PathVariable Integer id) {
		try {
			if (usuarioService.findById(id).isPresent()) {

				usuarioService.deleteById(id);
				return ResponseEntity.noContent().build();
			} else {

				return ResponseEntity.notFound().build();

			}

		} catch (FailedToDelete err) {
			throw new FailedToDelete("Não foi possível deletar pelo Id");
		}
	}
}
