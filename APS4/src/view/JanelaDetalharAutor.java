package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Livro;

public class JanelaDetalharAutor extends JFrame{
	JTextField txtIdAutor;
	JTextField txtNomeAutor;
	JTextField txtSobrenomeAutor;

	JTable tabela;
	DefaultTableModel dtm;
	
	ActionListener actionDetalharAutor;
	
	JLabel lblLivros;
	
	public JanelaDetalharAutor() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Autor detalhado");
		setSize(420, 280);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		//Label Título
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("AUTOR:"));
		
		//Form
		JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("ID: "), c);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        txtIdAutor = new JTextField(5);
        txtIdAutor.setEditable(false);
        panelForm.add(txtIdAutor, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("NOME: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        txtNomeAutor = new JTextField(20);
        txtNomeAutor.setEditable(false);
        panelForm.add(txtNomeAutor, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("SOBRENOME: "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        txtSobrenomeAutor = new JTextField(25);
        txtSobrenomeAutor.setEditable(false);
        panelForm.add(txtSobrenomeAutor, c);
        
        JPanel panelLblLivros = new JPanel(new FlowLayout(FlowLayout.LEADING));
        lblLivros = new JLabel("LIVROS:");
		panelLblLivros.add(lblLivros);
        
        dtm = new DefaultTableModel(new Object[] {"Título", "ISBN", "Preço"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(dtm);
		tabela.getColumnModel().getColumn(1).setMinWidth(100);
		tabela.getColumnModel().getColumn(1).setMaxWidth(100);
		tabela.getColumnModel().getColumn(2).setMaxWidth(60);
        JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setPreferredSize(new Dimension(200, 200));
        
        add(panelTitulo);
        add(panelForm);
        add(panelLblLivros);
        add(scrollPane);
	}
	
	public void setActionListenerDetalharAutor(ActionListener e) {
		actionDetalharAutor = e;
	}
	
	public void setIdEditora(String id, ActionEvent e) {
		txtIdAutor.setText(id);
		actionDetalharAutor.actionPerformed(e);
	}
	
	public void setNomeEditora(String nome) {
		txtNomeAutor.setText(nome);
	}
	
	public void setSiteEditora(String site) {
        txtSobrenomeAutor.setText(site);
	}
	
	public int getIdEditora() {
		return Integer.parseInt(txtIdAutor.getText());
	}
	
	public void mostraLivros(Collection<Livro> livros) {
		lblLivros.setText("LIVROS: " + livros.size() + " Livro(s)");
		
		dtm.setNumRows(0);
		for(Livro livro: livros){
            Object[] linha = new Object[] {livro.getTitle(), livro.getIsbn(), livro.getPreco()};
            dtm.addRow(linha);
        }
	}
	
}
