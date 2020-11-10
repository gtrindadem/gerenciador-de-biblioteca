package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import view.JanelaCadastraLivro.eventFecharJanela;

public class JanelaBuscaLivro extends JFrame {
	DefaultTableModel dtm;
	JButton btnBuscaLivro;
	JTextField txtFldBuscaLivro;
	
	ActionListener actionBuscarLivro;
	
	public JanelaBuscaLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	void initComponents() {
		setTitle("Consultar Livros");
		setLayout(new BorderLayout());
		setBounds(770, 450, 600, 280);
		addWindowListener(new eventFecharJanela());
		
		//Area da tabela
		dtm = new DefaultTableModel(new Object[] {"Título", "ISBN", "Editora", "Preço"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable tabela = new JTable(dtm);
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
		add(panelBuscaLivro, BorderLayout.CENTER);
		
	}
	
	public void setActionListenerBuscarLivro(ActionListener e) {
		actionBuscarLivro = e;
		btnBuscaLivro.addActionListener(actionBuscarLivro);
	}
	
	public void mostraLivros(Collection<Livro> livros) {
		dtm.setNumRows(0);
		for(Livro livro: livros){
            Object[] linha = new Object[] {livro.getTitle(), livro.getIsbn(), livro.getIdEditora(), livro.getPreco()};
            dtm.addRow(linha);
        }
	}
	
	public String getTitulo() {
		return txtFldBuscaLivro.getText();
	}
	
	class eventFecharJanela implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			txtFldBuscaLivro.setText("");
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
