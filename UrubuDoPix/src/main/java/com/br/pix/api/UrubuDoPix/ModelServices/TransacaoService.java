package com.br.pix.api.UrubuDoPix.ModelServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pix.api.UrubuDoPix.ModelEntitys.Transacao;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToCreate;
import com.br.pix.api.UrubuDoPix.ModelExceptions.FailedToDelete;
import com.br.pix.api.UrubuDoPix.ModelExceptions.NotFound;
import com.br.pix.api.UrubuDoPix.ModelRepositoryes.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public List<Transacao> findAll(){
		try {
			return transacaoRepository.findAll();
			
		}catch(NotFound err) {throw new NotFound("Não existem objetos cadastrados no banco de dados");}
	
	}

	public Optional<Transacao> findById(Integer id){
		try {
			return transacaoRepository.findById(id);
			
		}catch(NotFound err) {throw new NotFound("Não foi Possível encontrar o objeto pelo Id");}
		
		
		
	}

	public Transacao save(Transacao transacao) {
		try {
			return transacaoRepository.save(transacao);
		}catch(FailedToCreate err) {throw new FailedToCreate("Não foi possível criar o objeto");}
		
	}

	public void deleteById(Integer id) {

		try {
			transacaoRepository.deleteById(id);
		}catch(
				FailedToDelete err)
		{throw new FailedToDelete("Não foi possível deletar pelo Id, nao encontrado ou não existe");}

	}


	
}
