package com.br.pix.api.UrubuDoPix.ModelRepositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.pix.api.UrubuDoPix.ModelEntitys.ChavePix;
import com.br.pix.api.UrubuDoPix.ModelEntitys.Usuario;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, Integer> {
	Usuario usuario = new Usuario();
}
