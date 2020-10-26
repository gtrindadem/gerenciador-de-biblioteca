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
	
	public void setActionListenerCadastraLivro(ActionListener e);
	public String getTituloCadastraLivro();
	public String getIsbnCadastraLivro();
	public int getIdEditoraCadastraLivro();
	public float getPrecoCadastraLivro();
	
	//Editora
	public void setActionListenerBuscaEditora(ActionListener e);
	public String getNomeBuscaEditora();
	public void listaEditoras(Map<Integer, Editora> editoras);
	
	//Autor
	public void setActionListenerBuscaAutor(ActionListener e);
	public String getNomeAutor();
	public void listaAutores(Collection<Autor> autores);
	
}
