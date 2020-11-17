package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Livro;

public class JanelaLivro extends JFrame {
	JTable tabela;
	DefaultTableModel dtm;
	
	ActionListener actionBuscarLivro;
	JButton btnBuscaLivro;
	JTextField txtFldBuscaLivro;
	
	JButton btnCadNovoLivro;
	JanelaCadastraLivro janelaCadastraLivro;
	
	JanelaAlterarExcluirLivro janelaAlterarExcluirLivro;
	
	public JanelaLivro() {
		janelaCadastraLivro = new JanelaCadastraLivro();
		janelaAlterarExcluirLivro = new JanelaAlterarExcluirLivro();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	void initComponents() {
		setTitle("Consultar Livros");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
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
		add(scrollPane);
		
		// Area da busca
		JPanel panelBuscaLivro = new JPanel(new FlowLayout());
		panelBuscaLivro.add(new JLabel("TÍTULO: "));
		txtFldBuscaLivro = new JTextField(25);
		btnBuscaLivro = new JButton("Buscar");
		panelBuscaLivro.add(txtFldBuscaLivro);
		panelBuscaLivro.add(btnBuscaLivro);
		add(panelBuscaLivro);
		
		JPanel panelBotoes = new JPanel();
		JButton btnAltExcLivro = new JButton("Alterar preço / Excluir");
		btnAltExcLivro.addActionListener(new ActionBtnAltExcLivro());
		btnCadNovoLivro = new JButton("Cadastrar NOVO+");
		btnCadNovoLivro.addActionListener(new ActionBtnCadNovoLivro());
		panelBotoes.add(btnAltExcLivro);
		panelBotoes.add(btnCadNovoLivro);
		add(panelBotoes);
		
	}
	
	public void setActionListenerBuscarLivro(ActionListener e) {
		actionBuscarLivro = e;
		btnBuscaLivro.addActionListener(e);
	}
	
	public void setActionListenerCadastrarLivro(ActionListener e) {
		janelaCadastraLivro.setActionListenerCadastrarLivro(e);
	}
	
	class ActionBtnAltExcLivro implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione na tabela o Livro que deseja Alterar Preço/Excluir");
			}else {
				String titulo = tabela.getValueAt(linhaSelecionada, 0).toString();
				String isbn = tabela.getValueAt(linhaSelecionada, 1).toString();
				String preco = tabela.getValueAt(linhaSelecionada, 3).toString();
				
				janelaAlterarExcluirLivro.setIsbn(isbn);
				janelaAlterarExcluirLivro.setTitulo(titulo);
				janelaAlterarExcluirLivro.setPreco(preco);
				
				janelaAlterarExcluirLivro.setVisible(true);
				
				dispose();
			}
		}
		
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
