package com.mbalem.cursos.boot.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mbalem.cursos.boot.domain.Veiculo;
import com.mbalem.cursos.boot.service.VeiculoService;
import com.mbalem.cursos.boot.service.VeiculoServiceImp;

public class VeiculoValidator implements Validator {

	private VeiculoService service = new VeiculoServiceImp();

	@Override
	public boolean supports(Class<?> clazz) {
		return Veiculo.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		Veiculo veiculo = (Veiculo) object;

		if (!veiculo.getChassi().isEmpty()) {

			if (service.chassiCadastrado(veiculo.getChassi())) {
				errors.rejectValue("chassi", "Chassi já cadastrado no sistema");
			}
		}

	}

}
