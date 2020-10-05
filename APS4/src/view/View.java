package view;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import entity.Editora;
import entity.Livro;

public interface View {

	public void setActionListenerBuscaLivro(ActionListener e);
	public void setActionListenerBuscaEditora(ActionListener e);
	public String getTituloBuscaLivro();
	public String getNomeBuscaEditora();
	public void listaLivros(Collection<Livro> livros);
	public void listaEditoras(Map<Integer, Editora> editoras);
	
}
