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
	JanelaBuscaLivro janelaBuscaLivro = new JanelaBuscaLivro();
	JanelaBuscaEditora janelaBuscaEditora = new JanelaBuscaEditora();
	JanelaBuscaAutor janelaBuscaAutor = new JanelaBuscaAutor();
	
	public JanelaHome() {
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
		
		JMenuItem menuItemConsultarLivros = new JMenuItem("Consultar Livros");
		menuConsulta.add(menuItemConsultarLivros);
		menuItemConsultarLivros.addActionListener(new ActionAbrirJanela(janelaBuscaLivro));
		
		JMenuItem menuItemConsultarEditoras = new JMenuItem("Consultar Editoras");
		menuConsulta.add(menuItemConsultarEditoras);
		menuItemConsultarEditoras.addActionListener(new ActionAbrirJanela(janelaBuscaEditora));
		
		JMenuItem menuItemConsultarAutores = new JMenuItem("Consultar Autores");
		menuConsulta.add(menuItemConsultarAutores);
		menuItemConsultarAutores.addActionListener(new ActionAbrirJanela(janelaBuscaAutor));
		
		setVisible(true);
	}
	
	class ActionAbrirJanela implements ActionListener {
		JFrame janela;
		
		public ActionAbrirJanela(JFrame janela) {
			this.janela = janela;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			janela.setVisible(true);
		}
		
	}

	@Override
	public void setActionListenerBuscaLivro(ActionListener e) {
		janelaBuscaLivro.setActionListenerBuscarLivro(e);
	}

	@Override
	public String getTituloBuscaLivro() {
		return janelaBuscaLivro.getTitulo();
	}

	@Override
	public void listaLivros(Collection<Livro> livros) {
		janelaBuscaLivro.mostraLivros(livros);
	}
	
	public void setActionListenerBuscaEditora(ActionListener e) {
		janelaBuscaEditora.setActionListenerBuscarEditora(e);
	}
	
	@Override
	public String getNomeBuscaEditora() {
		return janelaBuscaEditora.getNome();
	}
	
	@Override
	public void listaEditoras(Map<Integer, Editora> editoras) {
		janelaBuscaEditora.mostraEditoras(editoras);
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
	public int getTipoBuscaAutor() {
		if(janelaBuscaAutor.porNome.isSelected()) {
			return 0;
		}
		return 1;
	}
	

}
