package com.br.pix.api.UrubuDoPix.UsuarioModel.modelControllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pix.api.UrubuDoPix.ModelEntitys.ChavePix;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToCreate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToDelete;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToUpdate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.NotFound;
import com.br.pix.api.UrubuDoPix.ModelServices.ChavePixService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/pix")
public class ChavePixController {

	@Autowired
	private ChavePixService chavePixService;

	@GetMapping
	public List<ChavePix> getAllPix() {
		try {
		return chavePixService.findAll();
		}catch(NotFound err){
			throw new NotFound("Não existem objetos cadastros no Banco de Dados");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ChavePix> getPixById(@PathVariable Integer id) {
		try {
		return chavePixService.findById(id).map(ChavePix -> ResponseEntity.ok().body(ChavePix))
				.orElse(ResponseEntity.notFound().build());
	}catch(NotFound err) {
		throw new NotFound("Não existe Objeto com ess iD");
	}
		}

	@PostMapping
	public ResponseEntity<ChavePix> createPix(@RequestBody ChavePix pix) throws FailedToCreate {
		try {
			if (pix != null) {

				chavePixService.save(pix);
				return ResponseEntity.created(URI.create("/pix/" + pix.getId())).body(pix);
			} else {
				return ResponseEntity.badRequest().build();
			}

		} catch (FailedToCreate err) {
			throw new FailedToCreate("Não foi possível criar o objeto");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChavePix> updatePix(@PathVariable Integer id, @RequestBody ChavePix chavePixDetails) {
		try {

			if (chavePixService.findById(id).isPresent()) {

				return chavePixService.findById(id).map(chavePix -> {
					chavePix.setTipo(chavePixDetails.getTipo());
					chavePix.setValor(chavePixDetails.getValor());
					ChavePix updated = chavePixService.save(chavePix);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
			} else {

				return ResponseEntity.notFound().build();
			}

		} catch (FailedToUpdate err) {

			throw new FailedToUpdate("Não foi possível atualizar o Objeto ou ID nao encontrado");
		}

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
	    try {
	        
	        if (chavePixService.findById(id).isPresent()) {
	            
	            chavePixService.deleteById(id);
	            return ResponseEntity.noContent().build(); 
	        } else {
	          
	            return ResponseEntity.notFound().build();
	        }
	    } catch (FailedToDelete err) {
	        
	    	throw new FailedToDelete("Não foi Possível Deletar");
	    }
	}
	
}
