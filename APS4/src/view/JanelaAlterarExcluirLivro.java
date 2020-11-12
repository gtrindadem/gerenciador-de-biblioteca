package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JanelaAlterarExcluirLivro extends JFrame {
	JTextField iptTitulo;
	JTextField iptIsbn;
	JTextField iptPreco;
	JTextField iptIdEditora;
	JTextField iptNomeEditora;
	JButton btnListarEditora;
	JTable tabelaAutores;
	DefaultTableModel dtmTabelaAutores;
	JButton btnSelecionarAutores;
	JButton btnAlterar;
	
	ActionListener actionAlterarLivro;
	

	public JanelaAlterarExcluirLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Alteração de Livro");
		setBounds(765, 445, 450, 350);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		//Label Titulo
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("ALTERAÇÃO DE LIVRO:"));
		
		//Form
		JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("TÍTULO: "), c);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        iptTitulo = new JTextField(20);
        panelForm.add(iptTitulo, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("ISBN: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        iptIsbn = new JTextField(10);
        panelForm.add(iptIsbn, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("PREÇO: "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        iptPreco = new JTextField(5);
        panelForm.add(iptPreco, c);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("EDITORA: "), c);
        c.gridx = 1;
        c.gridy = 3;
        JPanel panelIptsEditora = new JPanel();
        iptIdEditora = new JTextField(3);
        iptIdEditora.setEditable(false);
        iptNomeEditora = new JTextField(12);
        iptNomeEditora.setEditable(false);
        btnListarEditora = new JButton("Listar Editora");
        btnListarEditora.addActionListener(new ActionBtnListarEditora());
        panelIptsEditora.add(iptIdEditora);
        panelIptsEditora.add(iptNomeEditora);
        panelIptsEditora.add(btnListarEditora);
        panelForm.add(panelIptsEditora, c);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("AUTOR(ES): "), c);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        dtmTabelaAutores = new DefaultTableModel(new Object[] {"ID", "Nome", "Sobrenome"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
        tabelaAutores = new JTable(dtmTabelaAutores);
        tabelaAutores.getColumnModel().getColumn(0).setMaxWidth(50);
        JScrollPane scrollPaneTabelaAutores = new JScrollPane(tabelaAutores);
        scrollPaneTabelaAutores.setPreferredSize(new Dimension(300,100));
        panelForm.add(scrollPaneTabelaAutores, c);
        c.gridx = 1;
        c.gridy = 5;
        btnSelecionarAutores = new JButton("Selecionar Autores");
        btnSelecionarAutores.addActionListener(new ActionBtnSelecionarAutores());
        panelForm.add(btnSelecionarAutores, c);
        
        JPanel panelBtnAlterar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnAlterar = new JButton("Alterar Livro");
        btnAlterar.addActionListener(new ActionBtnAlterar());
        panelBtnAlterar.add(btnAlterar);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBtnAlterar);
	}
	
	public void setActionListenerAlterarLivro(ActionListener e) {
		actionAlterarLivro = e;
	}
	
	class ActionBtnListarEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ActionBtnAlterar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ActionBtnSelecionarAutores implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//Getters do form
	public String getTitulo() {
		return iptTitulo.getText().trim();
	}
	
	public String getIsbn() {
		return iptIsbn.getText().trim();
	}
	
	public float getPreco() {
		return Float.parseFloat(iptPreco.getText().trim());
	}
	
	public int getIdEditora() {
		return Integer.parseInt(iptIdEditora.getText().trim());
	}
	
	public Collection<Integer> getIdAutores() {
		ArrayList<Integer> idAutores = new ArrayList<>();
		for(int i = 0; i < dtmTabelaAutores.getRowCount(); i++) {
			idAutores.add(Integer.parseInt(tabelaAutores.getValueAt(i, 0).toString()));
		}
		
		return idAutores;
	}
	
}
