package com.br.pix.api.UrubuDoPix.ModelExceptions;

public class FailedToCreate extends RuntimeException {
	public FailedToCreate(String erro) {
		super(erro);
	}
	
	public FailedToCreate(String erro, Throwable causaDoErro) {
		super(erro, causaDoErro);
	}
}
