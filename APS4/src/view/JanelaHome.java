package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import entity.Autor;
import entity.Editora;
import entity.Livro;

public class JanelaHome extends JFrame implements View {
	JanelaBuscaLivro janelaBuscaLivro;
	JanelaCadastraLivro janelaCadastraLivro;
	
	JanelaBuscaEditora janelaBuscaEditora;
	JanelaCadastraEditora janelaCadastraEditora;
	
	JanelaBuscaAutor janelaBuscaAutor;	
	
	public JanelaHome() {
		janelaBuscaLivro = new JanelaBuscaLivro();
		janelaBuscaAutor = new JanelaBuscaAutor();
		janelaBuscaEditora = new JanelaBuscaEditora();
		
		janelaCadastraLivro = new JanelaCadastraLivro(janelaBuscaAutor.dtm);
		janelaCadastraEditora = new JanelaCadastraEditora();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		setBounds(760, 440, 400, 200);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuConsulta = new JMenu("Consulta");
		JMenu menuCadastro = new JMenu("Cadastro");
		menuBar.add(menuConsulta);
		menuBar.add(menuCadastro);
		
		// ----- Menu Consultar -----
		JMenuItem menuItemConsultarLivros = new JMenuItem("Consultar Livros");
		menuConsulta.add(menuItemConsultarLivros);
		menuItemConsultarLivros.addActionListener(new AbrirBuscaLivro());
		
		JMenuItem menuItemConsultarEditoras = new JMenuItem("Consultar Editoras");
		menuConsulta.add(menuItemConsultarEditoras);
		menuItemConsultarEditoras.addActionListener(new AbrirBuscaEditora());
		
		JMenuItem menuItemConsultarAutores = new JMenuItem("Consultar Autores");
		menuConsulta.add(menuItemConsultarAutores);
		menuItemConsultarAutores.addActionListener(new AbrirBuscaAutor());
		
		// ----- Menu Cadastrar -----
		JMenuItem menuItemCadastrarLivro = new JMenuItem("Cadastrar Livro");
		menuCadastro.add(menuItemCadastrarLivro);
		menuItemCadastrarLivro.addActionListener(new AbrirCadastrarLivro());
		
		JMenuItem menuItemCadastrarEditora = new JMenuItem("Cadastrar Editora");
		menuCadastro.add(menuItemCadastrarEditora);
		menuItemCadastrarEditora.addActionListener(new AbrirCadastrarEditora());
		
		setVisible(true);
	}
	
	//Livro - Busca
	class AbrirBuscaLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaLivro.actionBuscarLivro.actionPerformed(e);
			janelaBuscaLivro.setVisible(true);
		}
		
	}
	
	@Override
	public void setActionListenerBuscaLivro(ActionListener e) {
		this.janelaBuscaLivro.setActionListenerBuscarLivro(e);;
	}

	@Override
	public String getTituloBuscaLivro() {
		return this.janelaBuscaLivro.getTitulo();
	}

	@Override
	public void listaLivros(Collection<Livro> livros) {
		janelaBuscaLivro.mostraLivros(livros);
	}
	
	//Livro - Cadastro
	class AbrirCadastrarLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaAutor.actionBuscarAutor.actionPerformed(e);
			janelaBuscaEditora.actionBuscarEditora.actionPerformed(e);
			janelaCadastraLivro.setVisible(true);
			janelaCadastraLivro.janelaBuscaEditora = janelaBuscaEditora;
			janelaCadastraLivro.janelaBuscaAutor = janelaBuscaAutor;
		}
		
	}
	
	@Override
	public void setActionListenerCadastraLivro(ActionListener e) {
		janelaCadastraLivro.setActionListenerCadastraLivro(e);
	}
	
	@Override
	public String getTituloCadastraLivro() {
		return janelaCadastraLivro.getTitulo();
	}

	@Override
	public String getIsbnCadastraLivro() {
		return janelaCadastraLivro.getIsbn();
	}

	@Override
	public int getIdEditoraCadastraLivro() {
		return janelaCadastraLivro.getIdEditora();
	}

	@Override
	public float getPrecoCadastraLivro() {
		return janelaCadastraLivro.getPreco();
	}
	
	@Override
	public Collection<Integer> getIdAutoresCadastraLivro() {
		return janelaCadastraLivro.getIdAutores();
	}
	
	
	
	
	
	
	//Editora - Busca
	class AbrirBuscaEditora implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaEditora.actionBuscarEditora.actionPerformed(e);
			janelaBuscaEditora.setVisible(true);
		}
		
	}
	
	public void setActionListenerBuscaEditora(ActionListener e) {
		janelaBuscaEditora.setActionListenerBuscaEditora(e);
	}
	
	@Override
	public String getNomeBuscaEditora() {
		return janelaBuscaEditora.getNome();
	}
	
	@Override
	public void listaEditoras(Map<Integer, Editora> editoras) {
		janelaBuscaEditora.mostraEditoras(editoras);
	}

	//Editora - Cadastro
	class AbrirCadastrarEditora implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaCadastraEditora.setVisible(true);
		}
		
	}
	public void setActionListenerCadastraEditora(ActionListener e) {
		janelaCadastraEditora.setActionListenerCadastraEditora(e);
	}
	public String getNomeCadastraEditora() {
		return janelaCadastraEditora.getNomeEditora();
	}
	public String getSiteCadastraEditora() {
		return janelaCadastraEditora.getSiteEditora();
	}
	
	
	
	
	// Autor
	class AbrirBuscaAutor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaAutor.actionBuscarAutor.actionPerformed(e);
			janelaBuscaAutor.setVisible(true);
		}
		
	}
	
	@Override
	public void setActionListenerBuscaAutor(ActionListener e) {
		janelaBuscaAutor.setActionListenerBuscarAutor(e);
	}

	@Override
	public String getNomeAutor() {
		return janelaBuscaAutor.getInputNome();
	}

	@Override
	public void listaAutores(Collection<Autor> autores) {
		janelaBuscaAutor.mostraAutores(autores);
	}

	@Override
	public void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
