package com.mbalem.cursos.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mbalem.cursos.boot.domain.TiposVeiculos;
import com.mbalem.cursos.boot.service.TiposVeiculosService;

@Component
public class StringToTiposVeiculosConverter implements Converter<String, TiposVeiculos> {

	@Autowired
	private TiposVeiculosService tiposVeiculosService;

	@Override
	public TiposVeiculos convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return tiposVeiculosService.buscarPorId(id);
	}

}
