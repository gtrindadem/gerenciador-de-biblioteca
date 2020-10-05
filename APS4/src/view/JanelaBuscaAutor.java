package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Editora;

public class JanelaBuscaAutor extends JFrame {
	JTextField txtFldBuscaAutor;
	JButton btnBuscaAutor;
	DefaultTableModel dtm;
	
	public JanelaBuscaAutor() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	private void initComponents() {
		setBounds(765, 445, 400, 200);
		
		setLayout(new BorderLayout());
		
		dtm = new DefaultTableModel(new Object[] {"ID", "Nome", "Sobrenome"}, 0);
		JTable tabela = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane, BorderLayout.PAGE_START);
		
		JPanel panelBuscaEditora = new JPanel(new FlowLayout());
		JRadioButton porNome = new JRadioButton("Por Nome");
		JRadioButton porSobrenome = new JRadioButton("Por Sobrenome");
		ButtonGroup bg = new ButtonGroup();
		bg.add(porNome);
		bg.add(porSobrenome);
		panelBuscaEditora.add(porNome);
		panelBuscaEditora.add(porSobrenome);
		
		txtFldBuscaAutor = new JTextField(25);
		btnBuscaAutor = new JButton("Buscar");
		panelBuscaEditora.add(txtFldBuscaAutor);
		panelBuscaEditora.add(btnBuscaAutor);
		add(panelBuscaEditora, BorderLayout.CENTER);
		
		pack();
	}
	
	public void setActionListenerBuscarEditora(ActionListener e) {
		btnBuscaAutor.addActionListener(e);
	}
	
	public void mostraEditoras(Map<Integer, Editora> editoras) {
		dtm.setNumRows(0);
		for(int idEditora: editoras.keySet()) {
			Object[] linha = new Object[] {idEditora, editoras.get(idEditora).getName()};
			dtm.addRow(linha);
		}
	}
	
	public String getNome() {
		return txtFldBuscaAutor.getText();
	}
	
}
