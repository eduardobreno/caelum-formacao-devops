package br.com.alura.forum.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.alura.forum.controller.form.CadastrarUsuarioForm;
import br.com.alura.forum.dao.PerfilDao;
import br.com.alura.forum.dao.UsuarioDao;
import br.com.alura.forum.model.Usuario;

public class CadastroUsuarioServiceTest {

	UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);
	PerfilDao perfilDao = Mockito.mock(PerfilDao.class);
	Md5Service md5 = Mockito.mock(Md5Service.class);

	@Test(expected = IllegalArgumentException.class)
	public void deve_lancar_excecao_ao_add_usuario_ja_cadastrado() {
		Usuario usuario = new Usuario("Teste01", "teste@teste.com", "123");
		CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService(usuarioDao, perfilDao, md5);
		Mockito.when(usuarioDao.buscarPorEmail("teste@teste.com")).thenReturn(usuario);
		cadastroUsuarioService.cadastrarNovoUsuario(usuario);
	}
	
	@Test
	public void deve_salvar_usuario_novo() {
		Usuario usuario = new Usuario("Teste01", "teste@teste.com", "123");
		CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService(usuarioDao, perfilDao, md5);
		cadastroUsuarioService.cadastrarNovoUsuario(usuario);
		
		Mockito.verify(usuarioDao,Mockito.times(1)).salvar(usuario);
	}

}
