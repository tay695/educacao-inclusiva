package com.ifbaino.educacaoinclusiva.utils.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ValidadorExeceptio extends Exception {
	private static final long serialVersionUID = 1L;
	private final ListErrors erro;

	public ValidadorExeceptio(ListErrors erro) {
		super("Validação Falhou. Total de Campos Incorretos: " + erro.getErros());
		this.erro = erro;

	}

	public ListErrors getErroCampos() {
		return erro;
	}

	public String getMessage() {
		StringBuilder mensagemCompleta = new StringBuilder("Erros de validação:\n");
		for (ErroCampo erro : getErroCampos().getErros()) {
			mensagemCompleta.append(erro.toString()).append("\n");
		}
		return mensagemCompleta.toString();
	}

	public String getJson() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		return gson.toJson(erro);

	}
}