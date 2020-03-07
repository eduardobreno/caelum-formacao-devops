package br.com.alura.forum.aceitacao.cadastro;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.github.javafaker.Faker;

public class FluxoDeCadastroTest {
	
	ChromeDriver browser;
	
	@Before
	public void antes() {
		System.setProperty("webdriver.chrome.driver","./chromedriver");
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors"); 
		browser = new ChromeDriver();
	}
	
	@After
	public void depois() {
		browser.close();
	}
	
	@Ignore
	@Test
	public void deve_ser_capaz_de_criar_uma_conta() throws IOException, InterruptedException {
		Faker faker = new Faker();
		
		browser.get("http://localhost:8080/alura-forum/");
		
		TopicosPage paginaDeTopicos = new TopicosPage(browser);
		CadastroPage paginaDeCadastro = paginaDeTopicos.clicarNoLinkDeCadastro();
		
		String nome = faker.cat().name();
		String email = faker.internet().emailAddress();
		String senha = faker.internet().password();
		
		paginaDeCadastro.preencheFormulario(nome, email, senha);
		PaginaLogadaPage paginaLogadaPage = paginaDeCadastro.submeteCadastro();

		Assert.assertTrue(paginaLogadaPage.contem(nome));
	}
	
}