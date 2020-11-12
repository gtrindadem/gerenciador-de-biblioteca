package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Livro;

public class JanelaLivro extends JFrame {
	JanelaCadastraLivro janelaCadastraLivro;
	
	JTable tabela;
	DefaultTableModel dtm;
	
	JButton btnBuscaLivro;
	JTextField txtFldBuscaLivro;
	
	JButton btnCadNovoLivro;
	
	public JanelaLivro() {
		janelaCadastraLivro = new JanelaCadastraLivro();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	void initComponents() {
		setTitle("Consultar Livros");
		setLayout(new BorderLayout());
		setSize(600, 330);
		setLocationRelativeTo(null);
		
		//Area da tabela
		dtm = new DefaultTableModel(new Object[] {"Título", "ISBN", "Editora", "Preço"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(dtm);
		tabela.getColumnModel().getColumn(1).setMinWidth(100);
		tabela.getColumnModel().getColumn(1).setMaxWidth(100);
		tabela.getColumnModel().getColumn(2).setMaxWidth(55);
		tabela.getColumnModel().getColumn(3).setMaxWidth(60);
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setPreferredSize(new Dimension(200, 200));
		add(scrollPane, BorderLayout.PAGE_START);
		
		// Area da busca
		JPanel panelBuscaLivro = new JPanel(new FlowLayout());
		panelBuscaLivro.add(new JLabel("TÍTULO: "));
		txtFldBuscaLivro = new JTextField(25);
		btnBuscaLivro = new JButton("Buscar");
		panelBuscaLivro.add(txtFldBuscaLivro);
		panelBuscaLivro.add(btnBuscaLivro);
		panelBuscaLivro.add(new JLabel("Duplo click na tabela para alterar/excluir um livro"));
		add(panelBuscaLivro, BorderLayout.CENTER);
		
		btnCadNovoLivro = new JButton("Cadastrar NOVO+");
		btnCadNovoLivro.addActionListener(new ActionBtnCadNovoLivro());
		add(btnCadNovoLivro, BorderLayout.PAGE_END);
		
	}
	
	public void setActionListenerBuscarLivro(ActionListener e) {
		btnBuscaLivro.addActionListener(e);
	}
	
	public void setActionListenerCadastrarLivro(ActionListener e) {
		janelaCadastraLivro.setActionListenerCadastrarLivro(e);
	}
	
	class ActionBtnCadNovoLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaCadastraLivro.setVisible(true);
		}
		
	}
	
	public void mostraLivros(Collection<Livro> livros) {
		dtm.setNumRows(0);
		for(Livro livro: livros){
            Object[] linha = new Object[] {livro.getTitle(), livro.getIsbn(), livro.getIdEditora(), livro.getPreco()};
            dtm.addRow(linha);
        }
	}
	
	//Get do form
	public String getTitulo() {
		return txtFldBuscaLivro.getText();
	}
	
	public void LimparDados() {
		dtm.setNumRows(0);
		txtFldBuscaLivro.setText("");
	}
	
}
