document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector("form");
	const nascimentoInput = document.querySelector("input[name='nascimento']");
	const cpfInput = document.querySelector("input[name='cpf']");
	const emailInput = document.querySelector("input[name='email']");
	const senhaInput = document.querySelector("input[name='senha']");
	const tipoUsuarioInput = document.querySelector("select[name='tipoUsuario']");
	const areaInput = document.querySelector("input[name='areaEspecializacao']");
	const errorMessages = {};

	function showError(input, message) {
		removeError(input);
		const error = document.createElement("div");
		error.className = "text-danger mt-1 small error-message";
		error.innerText = message;
		input.classList.add("is-invalid");
		input.parentElement.appendChild(error);
		errorMessages[input.name] = message;
	}

	function removeError(input) {
		const existing = input.parentElement.querySelector(".error-message");
		if (existing) existing.remove();
		input.classList.remove("is-invalid");
		delete errorMessages[input.name];
	}

	function validarDatas() {
		const hoje = new Date();
		const nascimento = new Date(nascimentoInput.value);
		removeError(nascimentoInput);
		if (nascimentoInput.value) {
			const idade = hoje.getFullYear() - nascimento.getFullYear();
			if (nascimento > hoje) {
				showError(nascimentoInput, "Não tem como a data ser depois do dia de hoje");
			} else if (idade > 120) {
				showError(nascimentoInput, "Vai viver, acabou o tempo de estudar já");
			}
		}
	}

	function validarCPF(cpf) {
		cpf = cpf.replace(/[^\d]+/g, '');
		if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;

		let soma = 0;
		for (let i = 0; i < 9; i++) soma += parseInt(cpf.charAt(i)) * (10 - i);
		let resto = (soma * 10) % 11;
		if (resto === 10 || resto === 11) resto = 0;
		if (resto !== parseInt(cpf.charAt(9))) return false;

		soma = 0;
		for (let i = 0; i < 10; i++) soma += parseInt(cpf.charAt(i)) * (11 - i);
		resto = (soma * 10) % 11;
		if (resto === 10 || resto === 11) resto = 0;
		return resto === parseInt(cpf.charAt(10));
	}

	cpfInput.addEventListener("blur", () => {
		removeError(cpfInput);
		if (!validarCPF(cpfInput.value)) {
			showError(cpfInput, "CPF inválido.");
		}
	});

	emailInput.addEventListener("blur", () => {
		removeError(emailInput);
		const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!regex.test(emailInput.value)) {
			showError(emailInput, "E-mail inválido.");
		}
	});



	senhaInput.addEventListener("blur", () => {
		removeError(senhaInput);
		if (senhaInput.value.length < 6) {
			showError(senhaInput, "A senha deve ter pelo menos 6 caracteres.");
		}
	});

	tipoUsuarioInput.addEventListener("blur", () => {
		removeError(tipoUsuarioInput);
		if (!["aluno", "tutor"].includes(tipoUsuarioInput.value)) {
			showError(tipoUsuarioInput, "Selecione um tipo de usuário válido.");
		}
	});

	tipoUsuarioInput.addEventListener("change", () => {
		if (tipoUsuarioInput.value === "tutor") {
			areaInput.required = true;
		} else {
			areaInput.required = false;
			removeError(areaInput);
		}
	});

	aplicarMascara(cpfInput, "###.###.###-##");
	function aplicarMascara(input, mascara) {
		input.addEventListener("input", () => {
			let i = 0;
			const v = input.value.replace(/\D/g, "");
			input.value = mascara.replace(/#/g, _ => v[i++] || "");
		});
	}

	nascimentoInput.addEventListener("blur", validarDatas);
	inicioCursoInput?.addEventListener("blur", validarDatas);
	fimCursoInput?.addEventListener("blur", validarDatas);

	form.addEventListener("submit", function(e) {
		validarDatas();
		if (Object.keys(errorMessages).length > 0) {
			e.preventDefault();
		}
	});
});
