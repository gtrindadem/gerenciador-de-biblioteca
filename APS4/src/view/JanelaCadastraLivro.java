package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JanelaCadastraLivro extends JFrame {
	DefaultTableModel dtm;
	JTextField txtFldBuscaLivro;
	JButton btnBuscaLivro;
	
	public JanelaCadastraLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		dtm = new DefaultTableModel(new Object[] {"ID", "Nome", "Editora", "Preço"}, 0);
		JTable tabela = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane, BorderLayout.PAGE_START);
		
		JPanel panelBuscaLivro = new JPanel(new FlowLayout());
		txtFldBuscaLivro = new JTextField(25);
		btnBuscaLivro = new JButton("Buscar");
		panelBuscaLivro.add(txtFldBuscaLivro);
		panelBuscaLivro.add(btnBuscaLivro);
		add(panelBuscaLivro, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
}
