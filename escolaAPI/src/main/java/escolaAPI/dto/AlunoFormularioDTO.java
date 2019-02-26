package escolaAPI.dto;

import javax.validation.constraints.NotNull;

public class AlunoFormularioDTO {

	@NotNull
	private String ra;
	@NotNull
	private String nome;
	private String champson;

	public String getRa() {return ra;}
	public String getNome() {
		return nome;
	}
}
