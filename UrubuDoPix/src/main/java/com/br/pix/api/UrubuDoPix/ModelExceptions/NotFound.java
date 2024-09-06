package com.br.pix.api.UrubuDoPix.ModelExceptions;

public class NotFound extends RuntimeException {
	public NotFound(String erro) {
		super(erro);

	}
	
	public NotFound(String erro, Throwable causaDoErro) {
		super(erro, causaDoErro);
		
	}

}
