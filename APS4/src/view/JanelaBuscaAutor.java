package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Autor;
import entity.Editora;

public class JanelaBuscaAutor extends JFrame {
	JTextField txtFldBuscaAutor;
	JButton btnBuscaAutor;
	DefaultTableModel dtm;
	ButtonGroup bg;
	JRadioButton porNome;
	
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
		porNome = new JRadioButton("Por Nome");
		JRadioButton porSobrenome = new JRadioButton("Por Sobrenome");
		porNome.setSelected(true);
		bg = new ButtonGroup();
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
	
	public void setActionListenerBuscarAutor(ActionListener e) {
		btnBuscaAutor.addActionListener(e);
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
	
}
