package com.br.pix.api.UrubuDoPix.ModelRepositoryes;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.pix.api.UrubuDoPix.ModelEntitys.Transacao;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

}
