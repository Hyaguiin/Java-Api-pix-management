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

import com.br.pix.api.UrubuDoPix.ModelEntitys.Transacao;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToCreate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToDelete;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToUpdate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.NotFound;
import com.br.pix.api.UrubuDoPix.ModelServices.TransacaoService;

@RestController
@RequestMapping("/pix/usuario/transacao")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@GetMapping
	public List<Transacao> getAllTransacao() {
		try {
			return transacaoService.findAll();

		} catch (NotFound err) {
			throw new NotFound("Não existem objetos cadastrados no banco de dados");
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Transacao> getTransacaoById(@PathVariable Integer id) {
		try {
			return transacaoService.findById(id).map(Transacao -> ResponseEntity.ok().body(Transacao))
					.orElse(ResponseEntity.notFound().build());

		} catch (NotFound err) {
			throw new NotFound("Não foi Possível encontrar o objeto pelo Id ou não existe no banco de dados");
		}

	}

	@PostMapping
	public ResponseEntity<Transacao> saveTransacao(@RequestBody Transacao transacao) {
		try {
			if (transacao == null) {

				ResponseEntity.badRequest().build();
			}

			Transacao createTransacao = transacaoService.save(transacao);
			return ResponseEntity.ok().body(createTransacao);

		} catch (FailedToCreate err) {
			throw new FailedToCreate("Não foi possível criar o objeto");
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Transacao> updateTransacao(@PathVariable Integer id, @RequestBody Transacao transacaoDetails){
		try{
			return transacaoService.findById(id)
					.map(Transacao -> {
						Transacao.setChaveDestinatario(transacaoDetails.getChaveDestinatario());
						Transacao.setChaveRemetente(transacaoDetails.getChaveRemetente());
						Transacao.setValor(transacaoDetails.getValor());
						Transacao update = transacaoService.save(transacaoDetails);
						return ResponseEntity.ok().body(update);
					})
					.orElse(ResponseEntity.notFound().build());
			
			
			}catch(FailedToUpdate err) {
				throw new FailedToUpdate("Não foi possível atualizar o objeto");}
			}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransacaoById(@PathVariable Integer id){
		try {
		if(transacaoService.findById(id).isPresent()) {
			transacaoService.deleteById(id);
			 return ResponseEntity.noContent().build();
			}
		else {
			return ResponseEntity.notFound().build();
		}
		
		
	}catch(FailedToDelete err) {
		throw new FailedToDelete("Não foi possiível deletar pelo id, Objeto não encontrado ou não existe");
	}
}
	}
				
				


