package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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

import entity.Editora;

public class JanelaEditora extends JFrame{
	ActionListener actionBuscarEditora;
	
	DefaultTableModel dtm;
	JTable tabela;
	
	JTextField inputBuscaEditora;
	JButton btnBuscaEditora;
	
	JButton btnCadNovaEditora;
	JanelaCadastraEditora janelaCadastraEditora;
	
	JButton btnAltExcEditora;
	JanelaAlterarExcluirEditora janelaAlterarExcluirEditora;
	
	JanelaDetalharEditora janelaDetalharEditora;
	
	public JanelaEditora() {
		janelaCadastraEditora = new JanelaCadastraEditora();
		janelaAlterarExcluirEditora = new JanelaAlterarExcluirEditora();
		janelaDetalharEditora = new JanelaDetalharEditora();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Consultar Editoras");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		// Area da tabela
		dtm = new DefaultTableModel(new Object[] {"ID", "Nome", "Site"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(dtm);
		tabela.getColumnModel().getColumn(0).setMaxWidth(50);
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setPreferredSize(new Dimension(100, 200));
		add(scrollPane);
		
		// Area de busca
		JPanel panelBuscaEditora = new JPanel(new FlowLayout());
		panelBuscaEditora.add(new JLabel("NOME: "));
		inputBuscaEditora = new JTextField(15);
		panelBuscaEditora.add(inputBuscaEditora);
		btnBuscaEditora = new JButton("Buscar");
		btnBuscaEditora = new JButton("Buscar");;
		panelBuscaEditora.add(btnBuscaEditora);
		add(panelBuscaEditora);
		
		JPanel panelBotoes = new JPanel();
		JButton btnDetalhar = new JButton("Detalhar");
		btnDetalhar.addActionListener(new ActionBtnDetalhar());
		btnCadNovaEditora = new JButton("Cadastrar NOVA+");
		btnCadNovaEditora.addActionListener(new ActionBtnCadNovaEditora());
		btnAltExcEditora = new JButton("Alterar / Excluir");
		btnAltExcEditora.addActionListener(new ActionBtnAltExcEditora());
		panelBotoes.add(btnDetalhar);
		panelBotoes.add(btnAltExcEditora);
		panelBotoes.add(btnCadNovaEditora);
		add(panelBotoes);
	}
	
	public void setActionListenerBuscaEditora(ActionListener e) {
		actionBuscarEditora = e;
		btnBuscaEditora.addActionListener(actionBuscarEditora);
	}
	
	class ActionBtnDetalhar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione na tabela a Editora que deseja Detalhar");
			}else {
				String idEditora = tabela.getValueAt(linhaSelecionada, 0).toString();
				String nomeEditora = tabela.getValueAt(linhaSelecionada, 1).toString();
				String siteEditora = tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaDetalharEditora.setIdEditora(idEditora, e);
				janelaDetalharEditora.setNomeEditora(nomeEditora);
				janelaDetalharEditora.setSiteEditora(siteEditora);
				
				janelaDetalharEditora.setVisible(true);
				
				dispose();
			}
		}
		
	}
	
	class ActionBtnCadNovaEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaCadastraEditora.setVisible(true);
		}
		
	}
	
	class ActionBtnAltExcEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = tabela.getSelectedRow();
			
			if(linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(null, "Selecione na tabela a Editora que deseja Alterar/Excluir");
			}else {
				String idEditora = tabela.getValueAt(linhaSelecionada, 0).toString();
				String nomeEditora = tabela.getValueAt(linhaSelecionada, 1).toString();
				String siteEditora = tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaAlterarExcluirEditora.setIdEditora(idEditora);
				janelaAlterarExcluirEditora.setNomeEditora(nomeEditora);
				janelaAlterarExcluirEditora.setSiteEditora(siteEditora);
				
				janelaAlterarExcluirEditora.setVisible(true);
				
				dispose();
			}
		}
		
	}
	
	public void mostraEditoras(Map<Integer, Editora> editoras) {
		this.dtm.setNumRows(0);
		for(int idEditora: editoras.keySet()) {
			Object[] linha = new Object[] {idEditora, editoras.get(idEditora).getName(), editoras.get(idEditora).getUrl()};
			this.dtm.addRow(linha);
		}
	}
	
	public String getNome() {
		return inputBuscaEditora.getText();
	}
	
	public void LimparDados() {
		dtm.setNumRows(0);
		inputBuscaEditora.setText("");
	}
	
}
