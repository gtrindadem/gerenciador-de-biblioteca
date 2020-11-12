package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public class JanelaHome extends JFrame implements View {
	JanelaLivro janelaLivro;
	JanelaCadastraLivro janelaCadastraLivro;
	JanelaAlterarExcluirLivro janelaAlteraLivro;
	
	JanelaEditora janelaEditora;
	JanelaCadastraEditora janelaCadastraEditora;
	
	JanelaAutor janelaAutor;
	JanelaCadastraAutor janelaCadastraAutor;
	
	ActionListener actionBuscarLivro;
	ActionListener actionCadastrarLivro;
	
	JButton btnLivros;
	JButton btnEditoras;
	JButton btnAutores;
	
	public JanelaHome() {
		janelaLivro = new JanelaLivro();
		janelaAutor = new JanelaAutor();
		janelaEditora = new JanelaEditora();
		
		janelaAlteraLivro = new JanelaAlterarExcluirLivro();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Biblioteca");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(400, 150);
		setLocationRelativeTo(null);
		
		JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelLogo.add(new JLabel("BIBLIOTECA AMAZÔNIA"));
		add(panelLogo);
		
		JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnLivros = new JButton("LIVROS");
		btnLivros.setPreferredSize(new Dimension(95, 60));
		btnLivros.addActionListener(new AbrirLivros());
		
		btnEditoras = new JButton("EDITORAS");
		btnEditoras.setPreferredSize(new Dimension(95, 60));
		btnEditoras.addActionListener(new AbrirEditoras());
		
		btnAutores = new JButton("AUTORES");
		btnAutores.setPreferredSize(new Dimension(95, 60));
		btnAutores.addActionListener(new AbrirAutores());
		
		panelBotoes.add(btnLivros);
		panelBotoes.add(btnEditoras);
		panelBotoes.add(btnAutores);
		
		add(panelBotoes);
		
		setVisible(true);
	}
	
	//Livro - Busca
	class AbrirLivros implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaLivro.dispose();
			janelaLivro = new JanelaLivro();
			janelaLivro.setActionListenerBuscarLivro(actionBuscarLivro);
			janelaLivro.setActionListenerCadastrarLivro(actionCadastrarLivro);
			actionBuscarLivro.actionPerformed(e);

			janelaLivro.setVisible(true);
			janelaLivro.janelaCadastraLivro.janelaBuscaEditora = janelaEditora;
			janelaLivro.janelaCadastraLivro.janelaBuscaAutor = janelaAutor;
		}
		
	}
	
	@Override
	public void setActionListenerBuscaLivro(ActionListener e) {
		actionBuscarLivro = e;
		this.janelaLivro.setActionListenerBuscarLivro(e);
	}

	@Override
	public String getTituloBuscaLivro() {
		return this.janelaLivro.getTitulo();
	}

	@Override
	public void listaLivros(Collection<Livro> livros) {
		janelaLivro.mostraLivros(livros);
	}
	
	//Livro - Cadastro
	@Override
	public void setActionListenerCadastraLivro(ActionListener e) {
		actionCadastrarLivro = e;
	}
	
	public Livro getLivroCadastrarLivro() {
		String titulo = janelaLivro.janelaCadastraLivro.getTitulo();
		String isbn = janelaLivro.janelaCadastraLivro.getIsbn();
		int idEditora = janelaLivro.janelaCadastraLivro.getIdEditora();
		float preco = janelaLivro.janelaCadastraLivro.getPreco();
		
		return new Livro(titulo, isbn, idEditora, preco);
	}
	
	@Override
	public Collection<Integer> getIdAutoresCadastraLivro() {
		return janelaLivro.janelaCadastraLivro.getIdAutores();
	}
	
	//Livro - Exclusão
	public void setActionListenerExcluirLivro(ActionListener e) {
		//TODO
	}
	
	public int getIsbnExcluirLivro() {
		//TODO
		return 0;
	}
	
	//Livro - Alterar
	public void setActionListenerAlterarLivro(ActionListener e) {
		//TODO
	}
	
	public float getPrecoAlterarLivro() {
		//TODO
		return 0;
	}
	
	
	//Editora - Busca
	class AbrirEditoras implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaEditora.LimparDados();
			janelaEditora.actionBuscarEditora.actionPerformed(e);
			janelaEditora.setVisible(true);
		}
		
	}
	
	public void setActionListenerBuscaEditora(ActionListener e) {
		janelaEditora.setActionListenerBuscaEditora(e);
	}
	
	@Override
	public String getNomeBuscaEditora() {
		return janelaEditora.getNome();
	}
	
	@Override
	public void listaEditoras(Map<Integer, Editora> editoras) {
		janelaEditora.mostraEditoras(editoras);
	}

	//Editora - Cadastro
	public void setActionListenerCadastraEditora(ActionListener e) {
		janelaEditora.janelaCadastraEditora.setActionListenerCadastrarEditora(e);
	}
	
	public String getNomeCadastraEditora() {
		return janelaEditora.janelaCadastraEditora.getNomeEditora();
	}
	
	public String getSiteCadastraEditora() {
		return janelaEditora.janelaCadastraEditora.getSiteEditora();
	}
	
	//Editora - Exclusão
	public void setActionListenerExcluirEditora(ActionListener e) {
		janelaEditora.janelaAlterarExcluirEditora.setActionListenerExcluirEditora(e);
	}
	
	public int getIdExcluirEditora() {
		return janelaEditora.janelaAlterarExcluirEditora.getIdEditora();
	}
	
	//Editora - Alterar
	public void setActionListenerAlterarEditora(ActionListener e) {
		janelaEditora.janelaAlterarExcluirEditora.setActionListenerAlterarEditora(e);
	}
	
	public Editora getEditoraAlterarEditora() {
		int id = janelaEditora.janelaAlterarExcluirEditora.getIdEditora();
		String nome = janelaEditora.janelaAlterarExcluirEditora.getNomeEditora();
		String site = janelaEditora.janelaAlterarExcluirEditora.getSiteEditora();
		
		return new Editora(id, nome, site);
	}
	
	
	//Autor - Busca
	class AbrirAutores implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaAutor.LimparDados();
			janelaAutor.actionBuscarAutor.actionPerformed(e);
			janelaAutor.setVisible(true);
		}
		
	}
	
	@Override
	public void setActionListenerBuscaAutor(ActionListener e) {
		janelaAutor.setActionListenerBuscarAutor(e);
	}

	@Override
	public String getNomeAutor() {
		return janelaAutor.getInputNome();
	}

	@Override
	public void listaAutores(Collection<Autor> autores) {
		janelaAutor.mostraAutores(autores);
	}
	
	//Autor - Cadastro
	public void setActionListenerCadastraAutor(ActionListener e) {
		janelaAutor.janelaCadastraAutor.setActionListenerCadastrarAutor(e);
	}
	public String getNomeCadastraAutor() {
		return janelaAutor.janelaCadastraAutor.getNomeAutor();
	}
	public String getSobrenomeCadastraAutor() {
		return janelaAutor.janelaCadastraAutor.getSobrenomeAutor();
	}
	
	//Autor - Exclusão
	public void setActionListenerExcluirAutor(ActionListener e) {
		janelaAutor.janelaAlterarExcluirAutor.setActionListenerExcluirAutor(e);
	}
	
	public int getIdExcluirAutor() {
		return janelaAutor.janelaAlterarExcluirAutor.getIdAutor();
	}
	
	//Autor - Alterar
	public void setActionListenerAlterarAutor(ActionListener e) {
		janelaAutor.janelaAlterarExcluirAutor.setActionListenerAlterarAutor(e);
	}
	
	public Autor getAutorAlterarAutor() {
		int id = janelaAutor.janelaAlterarExcluirAutor.getIdAutor();
		String nome = janelaAutor.janelaAlterarExcluirAutor.getNomeAutor();
		String sobrenome = janelaAutor.janelaAlterarExcluirAutor.getSobrenomeAutor();
		
		return new Autor(id, nome, sobrenome);
	}
	
	
	@Override
	public void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
}
