package com.ifbaino.educacaoinclusiva.utils.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

	public class ValidadorExeceptio extends RuntimeException {
		private static final long serialVersionUID = 1L;

	private final List<ErroCampo> erroCampos;

	public ValidadorExeceptio(List<ErroCampo> erroCampos) {
		super("Validação Falhou. Total de Campos Incorretos: " + erroCampos.size());
		this.erroCampos = erroCampos;

	}

	public List<ErroCampo> getErroCampos() {
		return erroCampos;
	}

	public String getMessage() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(erroCampos);
	}
}