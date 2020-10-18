package com.mbalem.cursos.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.mbalem.cursos.boot.domain.Departamento;
import com.mbalem.cursos.boot.service.DepatamentoServiceImp;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	private DepatamentoServiceImp DepatamentoServiceImp;
	
	@Override
	public Departamento convert(String text) {
		
		if (text.isEmpty() ){
			return null;
		}
		
		Long id = Long.valueOf(text);
		return DepatamentoServiceImp.buscarPorId(id);
	}

}
