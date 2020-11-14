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
	public Livro getLivroCadastrarLivro();
	public Collection<Integer> getIdAutoresCadastraLivro();
	
	public void setActionListenerExcluirLivro(ActionListener e);
	public String getIsbnAlterarExcluirLivro();
	
	public void setActionListenerAlterarLivro(ActionListener e);
	public float getPrecoAlterarLivro();
	
	//Editora
	public void setActionListenerBuscaEditora(ActionListener e);
	public String getNomeBuscaEditora();
	public void listaEditoras(Map<Integer, Editora> editoras);
	
	public void setActionListenerCadastraEditora(ActionListener e);
	public String getNomeCadastraEditora();
	public String getSiteCadastraEditora();
	
	public void setActionListenerExcluirEditora(ActionListener e);
	public int getIdExcluirEditora();
	
	public void setActionListenerAlterarEditora(ActionListener e);
	public Editora getEditoraAlterarEditora();
	
	public void setActionListenerDetalharEditora(ActionListener e);
	public int getIdDetalharEditora();
	public void listarLivrosDetalharEditora(Collection<Livro> livros);
	
	//Autor
	public void setActionListenerBuscaAutor(ActionListener e);
	public String getNomeAutor();
	public void listaAutores(Collection<Autor> autores);
	
	public void setActionListenerCadastraAutor(ActionListener e);
	public String getNomeCadastraAutor();
	public String getSobrenomeCadastraAutor();
	
	public void setActionListenerExcluirAutor(ActionListener e);
	public int getIdExcluirAutor();
	
	public void setActionListenerAlterarAutor(ActionListener e);
	public Autor getAutorAlterarAutor();
	
	public void setActionListenerDetalharAutor(ActionListener e);
	public int getIdDetalharAutor();
	public void listarLivrosDetalharAutor(Collection<Livro> livros);
	
	public void msg(String msg);
}
