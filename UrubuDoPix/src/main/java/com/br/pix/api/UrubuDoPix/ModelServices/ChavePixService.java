package com.br.pix.api.UrubuDoPix.ModelServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pix.api.UrubuDoPix.ModelEntitys.ChavePix;
import com.br.pix.api.UrubuDoPix.ModelRepositoryes.ChavePixRepository;

@Service
public class ChavePixService {
	
	@Autowired
	ChavePixRepository chavePixRepository;
	
	public List<ChavePix> findAll(){
		return chavePixRepository.findAll();
		
	}
	
	public Optional<ChavePix> findById(Integer id){
		return chavePixRepository.findById(id);

	}

	public ChavePix save(ChavePix chavePix) {
		return chavePixRepository.save(chavePix);
	}

	public void deleteById(Integer id) {
		chavePixRepository.deleteById(id);
	}
}
