package escolaAPI.dto;

import java.util.Date;

public class EmprestimoFormularioDTO {

	private Integer idAluno;
	private Integer idLivro;
	private Date dataAluguel;
	private Date dataDevolucao;

	public Integer getIdAluno() {
		return idAluno;
	}
	public Integer getIdLivro() {
		return idLivro;
	}
}
