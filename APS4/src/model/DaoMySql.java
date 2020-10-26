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
		
		String qBusca = "SELECT * FROM authors WHERE fname LIKE ? OR name LIKE ?";
		
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
	public void cadastraLivro(String titulo, String isbn, int idEditora, float preco) {
		String qInsert = "INSERT books VALUES(?, ?, ?, ?)";
		
		try {
			PreparedStatement pstm = connection.prepareStatement(qInsert);
			pstm.setString(1, titulo);
			pstm.setString(2, isbn);
			pstm.setInt(3, idEditora);
			pstm.setDouble(4, preco);
			pstm.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
