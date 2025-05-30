 	function atualizarFormulario() {
    const tipo = document.querySelector("input[name='tipoUsuario']:checked").value;
    const form = document.getElementById("cadastroForm");

    if (tipo === "tutor") {
        form.action = contextPath + "/CadastroTutor";
        document.getElementById("campoEspecializacao").style.display = "block";
    } else {
        form.action = contextPath + "/CadastroAluno";
        document.getElementById("campoEspecializacao").style.display = "none";
    }
}

window.addEventListener("DOMContentLoaded", atualizarFormulario);
 	