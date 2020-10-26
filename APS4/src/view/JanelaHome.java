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

import entity.Autor;
import entity.Editora;
import entity.Livro;

public class JanelaHome extends JFrame implements View {
	JanelaBuscaLivro janelaBuscaLivro;
	JanelaCadastraLivro janelaCadastraLivro;
	
	JanelaBuscaEditora janelaBuscaEditora;
	
	JanelaBuscaAutor janelaBuscaAutor;	
	
	public JanelaHome() {
		janelaBuscaLivro = new JanelaBuscaLivro();
		janelaCadastraLivro = new JanelaCadastraLivro();
		
		janelaBuscaEditora = new JanelaBuscaEditora();
		
		janelaBuscaAutor = new JanelaBuscaAutor();
		
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
		menuItemConsultarLivros.addActionListener(new ActionAbrirJanela(janelaBuscaLivro));
		
		JMenuItem menuItemConsultarEditoras = new JMenuItem("Consultar Editoras");
		menuConsulta.add(menuItemConsultarEditoras);
		menuItemConsultarEditoras.addActionListener(new ActionAbrirJanela(janelaBuscaEditora));
		
		JMenuItem menuItemConsultarAutores = new JMenuItem("Consultar Autores");
		menuConsulta.add(menuItemConsultarAutores);
		menuItemConsultarAutores.addActionListener(new ActionAbrirJanela(janelaBuscaAutor));
		
		// ----- Menu Cadastrar -----
		JMenuItem menuItemCadastrarLivro = new JMenuItem("Cadastrar Livro");
		menuCadastro.add(menuItemCadastrarLivro);
		menuItemCadastrarLivro.addActionListener(new AbrirCadastrarLivro());
		
		setVisible(true);
	}
	
	class ActionAbrirJanela implements ActionListener {
		JFrame janela;
		
		public ActionAbrirJanela(JFrame janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.janela.setVisible(true);
		}
		
	}
	
	//Livro
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
	
	class AbrirCadastrarLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
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
	
	
	//Editora
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

	// Autor
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

}
