package view;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public interface View {
	
	//Livro
	public void setActionListenerBuscaLivro(ActionListener e);
	public String getTituloBuscaLivro();
	public void listaLivros(Collection<Livro> livros);
	
	//Editora
	public String getNomeBuscaEditora();
	public void setActionListenerBuscaEditora(ActionListener e);
	public void listaEditoras(Map<Integer, Editora> editoras);
	
	//Autor
	public void setActionListenerBuscaAutor(ActionListener e);
	public String getNomeAutor();
	public void listaAutores(Collection<Autor> autores);
	public int getTipoBuscaAutor();
	
}
