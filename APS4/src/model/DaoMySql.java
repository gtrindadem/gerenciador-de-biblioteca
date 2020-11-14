package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public class DaoMySql implements Dao {
	
	Connection connection = MySqlConnector.getConnection();
	
	@Override
	public Collection<Livro> buscaLivro(String titulo) {
		Collection<Livro> livros = new ArrayList<>();
		
		String qBusca = "SELECT * FROM books WHERE title LIKE ?;";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qBusca);
			pstm.setString(1, "%" + titulo + "%");
			ResultSet rs = pstm.executeQuery();
			
			Livro livro;
			while(rs.next()) {
				livro = new Livro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
				livros.add(livro);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return livros;
	}
	
	@Override
	public Map<Integer, Editora> buscaEditora(String nome) {
		Map<Integer, Editora> editoras = new TreeMap<>();
		
		String qBusca = "SELECT * FROM publishers WHERE name LIKE ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qBusca);
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();
			
			Editora editora;
			while(rs.next()) {
				editora = new Editora(rs.getInt(1), rs.getString(2), rs.getString(3));
				editoras.put(editora.getId(), editora);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return editoras;
	}

	@Override
	public Collection<Autor> buscaAutor(String nome) {
		Collection<Autor> autores = new ArrayList<>();
		
		String qBusca = "SELECT * FROM authors WHERE fname LIKE ? OR name LIKE ? ORDER BY fname";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qBusca);
			pstm.setString(1, "%" + nome + "%");
			pstm.setString(2, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();
			
			Autor autor;
			while(rs.next()) {
				autor = new Autor(rs.getInt(1), rs.getString(2), rs.getString(3));
				autores.add(autor);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return autores;
	}

	@Override
	public void cadastraLivro(Livro livro, Collection<Integer> idAutores) throws Exception{
		String qInsert = "INSERT books VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, livro.getTitle());
			pstm.setString(2, livro.getIsbn());
			pstm.setInt(3, livro.getIdEditora());
			pstm.setDouble(4, livro.getPreco());
			pstm.executeUpdate();
		}catch(SQLException e) {
			System.out.println("DAO Cadastra Livro: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		qInsert = "INSERT booksauthors VALUES(?, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, livro.getIsbn());
			int i = 1;
			for(int idAutor: idAutores) {
				pstm.setInt(2, idAutor);
				pstm.setInt(3, i);
				i++;
				pstm.executeUpdate();
			}
		}catch(SQLException e) {
			System.out.println("DAO Cadastra Relação Livro-Autor: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void cadastraRelacaoLivrosAutores(String isbn, Collection<Integer> idAutores) throws Exception {
		String qInsert = "INSERT booksauthors VALUES(?, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, isbn);
			int i = 1;
			for(int idAutor: idAutores) {
				pstm.setInt(2, idAutor);
				pstm.setInt(3, i);
				i++;
				pstm.executeUpdate();
			}
		}catch(SQLException e) {
			System.out.println("DAO Cadastra Relação Livro-Autor: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void cadastraEditora(String nome, String site) throws Exception{
		String qInsert = "INSERT publishers VALUES(0, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, nome);
			pstm.setString(2, site);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DAO Cadastra Editora: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void cadastraAutor(String nome, String sobrenome) throws Exception{
		String qInsert = "INSERT authors VALUES(0, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, sobrenome);
			pstm.setString(2, nome);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DAO Cadastra Autor: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void excluirLivro(String isbn) {
		String qDelete = "DELETE FROM books WHERE isbn = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qDelete);
			pstm.setString(1, isbn);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void excluirEditora(int id) {
		String qDelete = "DELETE FROM publishers WHERE publisher_id = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qDelete);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void excluirAutor(int id) {
		String qDelete = "DELETE FROM authors WHERE author_id = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qDelete);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void alterarLivro(String isbn, float preco) {
		String qUpdate = "UPDATE books SET price = ? WHERE isbn = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qUpdate);
			pstm.setDouble(1, preco);
			pstm.setString(2, isbn);
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void alterarEditora(Editora novaEditora) {
		String qUpdate = "UPDATE publishers SET name = ?, url = ? WHERE publisher_id = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qUpdate);
			pstm.setString(1, novaEditora.getName());
			pstm.setString(2, novaEditora.getUrl());
			pstm.setInt(3, novaEditora.getId());
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void alterarAutor(Autor novoAutor) {
		String qUpdate = "UPDATE authors SET fname = ?, name = ? WHERE author_id = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qUpdate);
			pstm.setString(1, novoAutor.getNome());
			pstm.setString(2, novoAutor.getSobrenome());
			pstm.setInt(3, novoAutor.getId());
			pstm.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Collection<Livro> detalharEditora(int idEditora) {
		Collection<Livro> livros = new ArrayList<>();
		
		String qBusca = "SELECT * FROM books WHERE publisher_id = ?";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qBusca);
			pstm.setInt(1, idEditora);
			ResultSet rs = pstm.executeQuery();
			
			Livro livro;
			while(rs.next()) {
				livro = new Livro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
				livros.add(livro);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return livros;
	}
	
	@Override
	public Collection<Livro> detalharAutor(int idAutor) {
		Collection<Livro> livros = new ArrayList<>();
		
		String qBusca = "SELECT * FROM books WHERE isbn IN (SELECT isbn FROM booksauthors WHERE author_id = ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qBusca);
			pstm.setInt(1, idAutor);
			ResultSet rs = pstm.executeQuery();
			
			Livro livro;
			while(rs.next()) {
				livro = new Livro(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
				livros.add(livro);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return livros;
	}
	
}
