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

import entity.Autor;

public class JanelaAutor extends JFrame {
	ActionListener actionBuscarAutor;
	
	JTextField txtFldBuscaAutor;
	JButton btnBuscaAutor;
	
	DefaultTableModel dtm;
	JTable tabela;
	
	JButton btnCadNovoAutor;
	JanelaCadastraAutor janelaCadastraAutor;
	
	JButton btnAltExcAutor;
	JanelaAlterarExcluirAutor janelaAlterarExcluirAutor;
	
	JanelaDetalharAutor janelaDetalharAutor;
	
	public JanelaAutor() {
		janelaCadastraAutor = new JanelaCadastraAutor();
		janelaAlterarExcluirAutor = new JanelaAlterarExcluirAutor();
		janelaDetalharAutor = new JanelaDetalharAutor();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Consultar Autores");
		setSize(420, 250);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		// Area da tabela
		dtm = new DefaultTableModel(new Object[] {"ID", "Nome", "Sobrenome"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(dtm);
		tabela.getColumnModel().getColumn(0).setMaxWidth(50);
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setPreferredSize(new Dimension(200,150));
		add(scrollPane);
		
		// Area da busca
		JPanel panelBuscaAutor = new JPanel(new FlowLayout());
		panelBuscaAutor.add(new JLabel("NOME/SOBRENOME: "));
		txtFldBuscaAutor = new JTextField(17);
		btnBuscaAutor = new JButton("Buscar");
		panelBuscaAutor.add(txtFldBuscaAutor);
		panelBuscaAutor.add(btnBuscaAutor);
		add(panelBuscaAutor);
		
		JPanel panelBotoes = new JPanel();
		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.addActionListener(new ActionBtnDetalhar());
		btnCadNovoAutor = new JButton("Cadastrar NOVO+");
		btnCadNovoAutor.addActionListener(new ActionBtnCadNovoAutor());
		btnAltExcAutor = new JButton("Alterar / Excluir");
		btnAltExcAutor.addActionListener(new ActionBtnAltExcAutor());
		panelBotoes.add(btnDetalhar);
		panelBotoes.add(btnAltExcAutor);
		panelBotoes.add(btnCadNovoAutor);
		add(panelBotoes);
	}
	
	public void setActionListenerBuscarAutor(ActionListener e) {
		actionBuscarAutor = e;
		btnBuscaAutor.addActionListener(actionBuscarAutor);
	}
	
	class ActionBtnDetalhar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione na tabela o Autor que deseja Detalhar");
			}else {
				String idAutor = tabela.getValueAt(linhaSelecionada, 0).toString();
				String nomeAutor = tabela.getValueAt(linhaSelecionada, 1).toString();
				String sobrenomeAutor = tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaDetalharAutor.setIdEditora(idAutor, e);
				janelaDetalharAutor.setNomeEditora(nomeAutor);
				janelaDetalharAutor.setSiteEditora(sobrenomeAutor);
				
				janelaDetalharAutor.setVisible(true);
				
				dispose();
			}
		}
		
	}
	
	class ActionBtnAltExcAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione na tabela o Autor que deseja Alterar/Excluir");
			}else {
				String idAutor = tabela.getValueAt(linhaSelecionada, 0).toString();
				String nomeAutor = tabela.getValueAt(linhaSelecionada, 1).toString();
				String sobrenomeAutor = tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaAlterarExcluirAutor.setIdAutor(idAutor);
				janelaAlterarExcluirAutor.setNomeAutor(nomeAutor);
				janelaAlterarExcluirAutor.setSobrenomeAutor(sobrenomeAutor);
				
				janelaAlterarExcluirAutor.setVisible(true);
				
				dispose();
			}
		}
		
	}
	
	class ActionBtnCadNovoAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaCadastraAutor.setVisible(true);
		}
		
	}
	
	public void mostraAutores(Collection<Autor> autores) {
		dtm.setNumRows(0);
		for(Autor autor: autores) {
			Object[] linha = new Object[] {autor.getId(), autor.getNome(), autor.getSobrenome()};
            dtm.addRow(linha);
		}
	}
	
	public String getInputNome() {
		return txtFldBuscaAutor.getText();
	}
	
	public void LimparDados() {
		dtm.setNumRows(0);
		txtFldBuscaAutor.setText("");
	}
	
}
