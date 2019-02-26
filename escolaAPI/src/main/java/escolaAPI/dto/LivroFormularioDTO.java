package escolaAPI.dto;

import javax.validation.constraints.NotNull;

import escolaAPI.entity.Status;

public class LivroFormularioDTO {

	@NotNull
	private String titulo;
	@NotNull
	private String autor;
	private Status status;
	
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}

	
}
