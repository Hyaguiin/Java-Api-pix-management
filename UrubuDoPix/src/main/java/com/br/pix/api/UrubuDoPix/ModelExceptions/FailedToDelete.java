package com.br.pix.api.UrubuDoPix.ModelExceptions;

public class FailedToDelete extends RuntimeException {

	public FailedToDelete(String err) {
		super(err);
	
	
	}
	

	public FailedToDelete(String err, Throwable causaErro) {
		super(err, causaErro);

	}

}
