package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Autor;
import entity.Editora;
import view.JanelaBuscaLivro.eventFecharJanela;

public class JanelaBuscaAutor extends JFrame {
	ActionListener actionBuscarAutor;
	
	JTextField txtFldBuscaAutor;
	JButton btnBuscaAutor;
	DefaultTableModel dtm;
	JTable tabela;
	ButtonGroup bg;
	JRadioButton porNome;
	
	public JanelaBuscaAutor() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Consultar Autores");
		setBounds(765, 445, 420, 235);
		setLayout(new BorderLayout());
		addWindowListener(new eventFecharJanela());
		
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
		add(scrollPane, BorderLayout.PAGE_START);
		
		// Area da busca
		JPanel panelBuscaAutor = new JPanel(new FlowLayout());
		panelBuscaAutor.add(new JLabel("NOME/SOBRENOME: "));
		txtFldBuscaAutor = new JTextField(17);
		btnBuscaAutor = new JButton("Buscar");
		panelBuscaAutor.add(txtFldBuscaAutor);
		panelBuscaAutor.add(btnBuscaAutor);
		add(panelBuscaAutor, BorderLayout.CENTER);
		
	}
	
	public void setActionListenerBuscarAutor(ActionListener e) {
		actionBuscarAutor = e;
		btnBuscaAutor.addActionListener(actionBuscarAutor);
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
	
	class eventFecharJanela implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			txtFldBuscaAutor.setText("");
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
