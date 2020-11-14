package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Dao;
import view.View;

public class Controller {
	private Dao dao;
	private View view;
	
	public Controller(Dao dao, View view) {
		this.dao = dao;
		this.view = view;
		init();
	}
	
	private void init() {
		view.setActionListenerBuscaLivro(new ActionBuscaLivro());
		view.setActionListenerBuscaEditora(new ActionBuscaEditora());
		view.setActionListenerBuscaAutor(new ActionBuscaAutor());
		view.setActionListenerCadastraLivro(new ActionCadastraLivro());
		view.setActionListenerCadastraEditora(new ActionCadastraEditora());
		view.setActionListenerCadastraAutor(new ActionCadastraAutor());
		view.setActionListenerExcluirLivro(new ActionExcluirLivro());
		view.setActionListenerExcluirEditora(new ActionExcluirEditora());
		view.setActionListenerExcluirAutor(new ActionExcluirAutor());
		view.setActionListenerAlterarLivro(new ActionAlterarLivro());
		view.setActionListenerAlterarEditora(new ActionAlterarEditora());
		view.setActionListenerAlterarAutor(new ActionAlterarAutor());
		
		view.setActionListenerDetalharEditora(new ActionDetalharEditora());
		view.setActionListenerDetalharAutor(new ActionDetalharAutor());
	}

	class ActionBuscaLivro implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.listaLivros(dao.buscaLivro(view.getTituloBuscaLivro()));
		}
		
	}
	
	class ActionBuscaEditora implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.listaEditoras(dao.buscaEditora(view.getNomeBuscaEditora()));
		}
		
	}
	
	class ActionBuscaAutor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.listaAutores(dao.buscaAutor(view.getNomeAutor()));
		}
		
	}
	
	class ActionCadastraLivro implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				dao.cadastraLivro(view.getLivroCadastrarLivro(), view.getIdAutoresCadastraLivro());
				view.msg("Livro cadastrado com sucesso!");
			}catch(Exception ex) {
				view.msg("Erro ao cadastrar livro: " + ex.getMessage());
			}
			
		}
		
	}
	
	class ActionCadastraEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomeEditora = view.getNomeCadastraEditora();
			String siteEditora = view.getSiteCadastraEditora();
			
			try {
				dao.cadastraEditora(nomeEditora, siteEditora);
				view.msg("Editora cadastrada com sucesso!");
			}catch(Exception ex) {
				view.msg(ex.getMessage());
			}
		}
		
	}
	
	class ActionCadastraAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomeAutor = view.getNomeCadastraAutor();
			String sobrenomeAutor = view.getSobrenomeCadastraAutor();
			
			try {
				dao.cadastraAutor(nomeAutor, sobrenomeAutor);
				view.msg("Autor cadastrado com sucesso!");
			}catch(Exception ex) {
				view.msg(ex.getMessage());
			}
		}
		
	}
	
	class ActionExcluirLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.excluirLivro(view.getIsbnAlterarExcluirLivro());
		}
		
	}
	
	class ActionExcluirEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.excluirEditora(view.getIdExcluirEditora());
		}
		
	}
	
	class ActionExcluirAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.excluirAutor(view.getIdExcluirAutor());
		}
		
	}
	
	class ActionAlterarLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.alterarLivro(view.getIsbnAlterarExcluirLivro(), view.getPrecoAlterarLivro());
		}
		
	}
	
	class ActionAlterarEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.alterarEditora(view.getEditoraAlterarEditora());
		}
		
	}
	
	class ActionAlterarAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dao.alterarAutor(view.getAutorAlterarAutor());
		}
		
	}
	
	class ActionDetalharEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.listarLivrosDetalharEditora(dao.detalharEditora(view.getIdDetalharEditora()));
		}
		
	}
	
	class ActionDetalharAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.listarLivrosDetalharAutor(dao.detalharAutor(view.getIdDetalharAutor()));
		}
		
	}
	
}
