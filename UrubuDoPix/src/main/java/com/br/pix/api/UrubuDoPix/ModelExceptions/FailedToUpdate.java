package com.br.pix.api.UrubuDoPix.ModelExceptions;

public class FailedToUpdate extends RuntimeException {
	
	public FailedToUpdate(String erro) {
		super(erro);
	}

	public FailedToUpdate(String erro, Throwable causa) {
		super(erro, causa);
	}

}
