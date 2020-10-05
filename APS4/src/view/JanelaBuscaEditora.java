package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Editora;

public class JanelaBuscaEditora extends JFrame{
	JTextField txtFldBuscaEditora;
	JButton btnBuscaEditora;
	DefaultTableModel dtm;
	
	public JanelaBuscaEditora() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setBounds(765, 445, 400, 200);
		
		setLayout(new BorderLayout());
		
		dtm = new DefaultTableModel(new Object[] {"ID", "Nome"}, 0);
		JTable tabela = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane, BorderLayout.PAGE_START);
		
		JPanel panelBuscaEditora = new JPanel(new FlowLayout());
		txtFldBuscaEditora = new JTextField(25);
		btnBuscaEditora = new JButton("Buscar");
		panelBuscaEditora.add(txtFldBuscaEditora);
		panelBuscaEditora.add(btnBuscaEditora);
		add(panelBuscaEditora, BorderLayout.CENTER);
		
		pack();
	}
	
	public void setActionListenerBuscarEditora(ActionListener e) {
		btnBuscaEditora.addActionListener(e);
	}
	
	public void mostraEditoras(Map<Integer, Editora> editoras) {
		dtm.setNumRows(0);
		for(int idEditora: editoras.keySet()) {
			Object[] linha = new Object[] {idEditora, editoras.get(idEditora).getName()};
			dtm.addRow(linha);
		}
	}
	
	public String getNome() {
		return txtFldBuscaEditora.getText();
	}
	
}
