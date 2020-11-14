package model;

import java.util.Collection;
import java.util.Map;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public interface Dao {
	
	//Livro
	public Collection<Livro> buscaLivro(String titulo);
	public void cadastraLivro(Livro livro, Collection<Integer> idAutores) throws Exception;
	public void cadastraRelacaoLivrosAutores(String isbn, Collection<Integer> idAutores) throws Exception;
	public void excluirLivro(String isbn);
	public void alterarLivro(String isbn, float preco);
	
	//Autor
	public Collection<Autor> buscaAutor(String nome);
	public void cadastraAutor(String nome, String sobrenome) throws Exception;
	public void excluirAutor(int id);
	public void alterarAutor(Autor novoAutor);
	public Collection<Livro> detalharAutor(int idAutor);
	
	//Editora
	public Map<Integer, Editora> buscaEditora(String nome);
	public void cadastraEditora(String nome, String site) throws Exception;
	public void excluirEditora(int id);
	public void alterarEditora(Editora novaEditora);
	public Collection<Livro> detalharEditora(int idEditora);

}
