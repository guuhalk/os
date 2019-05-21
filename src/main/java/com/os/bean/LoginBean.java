package com.os.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.os.dao.UsuarioDao;
import com.os.generico.GenericBean;
import com.os.model.Usuario;

@RequestScoped
@ManagedBean
public class LoginBean extends GenericBean {

	private String login;
	private String senha;
	private Usuario usuario;

	
	public void logar() {

		validaUsuario(login, senha);
		usuario = new UsuarioDao().buscarUsuarioParaLogin(login, senha);

		if (usuario != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			request.getSession().setAttribute("user", usuario);
			redirecionarPagina("pages/index.xhtml");

		}

		menssagemErro("Favor verificar login/password");
	}

	
	public void sair() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.invalidate();
		redirecionarPagina("login.xhtml");
	}

	
	public void validaUsuario(String login, String senha) {

		if ((login == null || login.isEmpty()) || (senha == null || senha.isEmpty())) {
			menssagemErro("Favor preencher os campos");
		}

		if (login == null || login.isEmpty()) {
			menssagemErro("Favor preencher o login");
		}

		if (senha == null || senha.isEmpty()) {
			menssagemErro("Favor preencher a senha");
		}

	}

	// GATTERS AND SETTERS

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
