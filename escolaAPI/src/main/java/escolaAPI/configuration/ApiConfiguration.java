package escolaAPI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import escolaAPI.handler.AlunoHandler;
import escolaAPI.handler.EmprestimoHandler;
import escolaAPI.handler.LivroHandler;

@Configuration
public class ApiConfiguration {
	  @Bean
	  public EmprestimoHandler emprestimoHandler() {
		  EmprestimoHandler nuvem = new EmprestimoHandler();
	    return nuvem;
	  }
	  
	  @Bean
	  public AlunoHandler alunoHandler() {
		  AlunoHandler nuvem = new AlunoHandler();
	    return nuvem;
	  }
	  @Bean
	  public LivroHandler livroHandler() {
		  LivroHandler nuvem = new LivroHandler();
	    return nuvem;
	  }
}
