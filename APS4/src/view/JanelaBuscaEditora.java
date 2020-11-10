package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Editora;

public class JanelaBuscaEditora extends JFrame{
	ActionListener actionBuscarEditora;
	
	JTextField inputBuscaEditora;
	JButton btnBuscaEditora = new JButton("Buscar");;
	
	DefaultTableModel dtm;
	JTable tabela;
	
	Map<Integer, Editora> editorasRenumeradas;
	
	public JanelaBuscaEditora() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Consultar Editoras");
		setBounds(765, 445, 400, 300);
		setLayout(new BorderLayout());
		
		// Area da tabela
		this.dtm = new DefaultTableModel(new Object[] {"ID", "Nome"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.tabela = new JTable(dtm);
		this.tabela.getColumnModel().getColumn(0).setMaxWidth(50);
		JScrollPane scrollPane = new JScrollPane(this.tabela);
		scrollPane.setPreferredSize(new Dimension(100, 200));
		add(scrollPane, BorderLayout.PAGE_START);
		
		// Area de busca
		JPanel panelBuscaEditora = new JPanel(new FlowLayout());
		panelBuscaEditora.add(new JLabel("NOME: "));
		this.inputBuscaEditora = new JTextField(15);
		panelBuscaEditora.add(this.inputBuscaEditora);
		this.btnBuscaEditora = new JButton("Buscar");;
		panelBuscaEditora.add(this.btnBuscaEditora);
		add(panelBuscaEditora, BorderLayout.CENTER);
		
		addWindowListener(new LimpaTela());
	}
	
	public void setActionListenerBuscaEditora(ActionListener e) {
		actionBuscarEditora = e;
		btnBuscaEditora.addActionListener(actionBuscarEditora);
	}
	
	public void mostraEditoras(Map<Integer, Editora> editoras) {
		this.editorasRenumeradas = new TreeMap<>();
		int l = 0;
		this.dtm.setNumRows(0);
		for(int idEditora: editoras.keySet()) {
			this.editorasRenumeradas.put(l++, editoras.get(idEditora));
			Object[] linha = new Object[] {idEditora, editoras.get(idEditora).getName()};
			this.dtm.addRow(linha);
		}
	}
	
	public String getNome() {
		return inputBuscaEditora.getText();
	}
	
	class LimpaTela implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			inputBuscaEditora.setText("");
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
