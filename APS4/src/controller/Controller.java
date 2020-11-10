package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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
			String titulo = view.getTituloCadastraLivro();
			String isbn = view.getIsbnCadastraLivro();
			int idEditora = view.getIdEditoraCadastraLivro();
			float preco = view.getPrecoCadastraLivro();
			Collection<Integer> idAutores = view.getIdAutoresCadastraLivro();
			
			try{
				dao.cadastraLivro(titulo, isbn, idEditora, preco);
				try {
					dao.cadastraRelacaoLivrosAutores(isbn, idAutores);
				}catch(Exception ex) {
					throw new Exception(ex.getMessage());
				}
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
	
}
