function mascaraMoeda(valorDigitado) {
	var valorFormatado = valorDigitado.value;
	valorFormatado = valorFormatado.replace(/\D/g, "");
	valorFormatado = valorFormatado.replace(/(\d)(\d{8})$/,"$1.$2");
	valorFormatado = valorFormatado.replace(/(\d)(\d{5})$/,"$1.$2");
	valorFormatado = valorFormatado.replace(/(\d)(\d{2})$/,"$1,$2");
	valorDigitado.value = valorFormatado;
}