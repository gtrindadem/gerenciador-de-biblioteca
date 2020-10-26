package model;

import java.util.Collection;
import java.util.Map;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public interface Dao {
	
	//Busca
	public Collection<Livro> buscaLivro(String titulo);
	public void cadastraLivro(String titulo, String isbn, int idEditora, float preco);
	
	public Map<Integer, Editora> buscaEditora(String nome);
	
	public Collection<Autor> buscaAutor(String nome);
	
}
