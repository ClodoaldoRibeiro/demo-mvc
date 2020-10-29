package com.mbalem.cursos.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mbalem.cursos.boot.domain.Combustivel;
import com.mbalem.cursos.boot.service.CombustivelService;

@Component
public class StringToCombustivelConverter implements Converter<String, Combustivel> {

	@Autowired
	private CombustivelService combustivelService;

	@Override
	public Combustivel convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return combustivelService.buscarPorId(id);
	}

}
