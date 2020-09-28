package entity;

public class Livro {
	String title;
	String isbn;
	int idEditora;
	float preco;
	
	public Livro(String title, String isbn, int idEditora, float preco) {
		this.title = title;
		this.isbn = isbn;
		this.idEditora = idEditora;
		this.preco = preco;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getIdEditora() {
		return idEditora;
	}
	
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
}
