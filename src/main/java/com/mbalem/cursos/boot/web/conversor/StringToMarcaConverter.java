package com.mbalem.cursos.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mbalem.cursos.boot.domain.Marca;
import com.mbalem.cursos.boot.service.MarcaService;

@Component
public class StringToMarcaConverter implements Converter<String, Marca> {

	@Autowired
	private MarcaService marcaService;

	@Override
	public Marca convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return marcaService.buscarPorId(id);
	}

}
