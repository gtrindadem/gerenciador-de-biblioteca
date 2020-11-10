package model;

import java.util.Collection;
import java.util.Map;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public interface Dao {
	
	//Livro
	public Collection<Livro> buscaLivro(String titulo);
	public void cadastraLivro(String titulo, String isbn, int idEditora, float preco) throws Exception;
	public void cadastraRelacaoLivrosAutores(String isbn, Collection<Integer> idAutores) throws Exception;
	
	//Autor
	public Collection<Autor> buscaAutor(String nome);
	
	//Editora
	public Map<Integer, Editora> buscaEditora(String nome);
	public void cadastraEditora(String nome, String site) throws Exception;

}
