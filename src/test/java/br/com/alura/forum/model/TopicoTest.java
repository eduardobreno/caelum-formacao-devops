package br.com.alura.forum.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TopicoTest {

	Usuario autor = new Usuario("usu01", "usu01@usu01.com", "123");
	Usuario autorResposta = new Usuario("usu02", "usu02@usu02.com", "123");
	Categoria categoria = new Categoria("Teste01");
	Curso curso = new Curso("teste01", categoria);
	Topico topico = new Topico("Teste01", "descricao01", autor, curso);
	
	@Test
	public void add_resposta() {
		Resposta resposta = new Resposta("resposta01", topico, autorResposta);
		topico.addResposta(resposta);
		int ultimaResposta = topico.getRespostas().size() - 1;
		assertEquals(topico.getRespostas().get(ultimaResposta).getDescricao(),
				resposta.getDescricao());
	}
	
	@Test
	public void add_nova_resposta() {
		Resposta resposta = new Resposta("resposta02", topico, autorResposta);
		topico.registrarNovaResposta(resposta);
		int ultimaResposta = topico.getRespostas().size() - 1;
		assertEquals(topico.getRespostas().get(ultimaResposta).getDescricao(),
				resposta.getDescricao());
	}
	
	@Test
	public void marcar_como_solucionado_e_fechar() {
		topico.marcarComoSolucionado();
		assertEquals(topico.isSolucionado(),true);
		topico.fechar();
		assertEquals(topico.isFechado(),true);
	}

	
}
