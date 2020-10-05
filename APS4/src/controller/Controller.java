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
			switch(view.getTipoBuscaAutor()) {
				case 0:
					view.listaAutores(dao.buscaAutorPorNome(view.getNomeAutor()));
					break;
				case 1:
					view.listaAutores(dao.buscaAutorPorSobrenome(view.getNomeAutor()));
					break;
			}
		}
		
	}
	
}
