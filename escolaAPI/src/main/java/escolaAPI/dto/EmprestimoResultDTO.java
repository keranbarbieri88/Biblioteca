package escolaAPI.dto;

public class EmprestimoResultDTO {
	
	private String nomeLivro;
	private String nomeAluno;
	private Integer codigoEmprestimo;
	
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public Integer getCodigoEmprestimo() {
		return codigoEmprestimo;
	}
	public void setCodigoEmprestimo(Integer codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}
}
