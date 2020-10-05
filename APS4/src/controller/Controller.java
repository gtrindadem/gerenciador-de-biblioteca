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
}
