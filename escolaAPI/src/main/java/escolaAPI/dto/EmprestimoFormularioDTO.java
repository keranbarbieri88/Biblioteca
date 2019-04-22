package escolaAPI.dto;

import java.util.Date;

public class EmprestimoFormularioDTO {

	private Integer idAluno;
	private Integer idLivro;
	private Date dataEntrega;


	public Integer getIdAluno() {
		return idAluno;
	}
	public Integer getIdLivro() {
		return idLivro;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
}
