package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class JanelaCadastraLivro extends JFrame {
	
	JTextField inputTitulo;
	JTextField inputIsbn;
	JTextField inputPreco;
	
	JanelaBuscaEditora janelaBuscaEditora;
	JTextField inputIdEditora;
	JTextField inputNomeEditora;
	
	JanelaBuscaAutor janelaBuscaAutor;
	DefaultTableModel dtmTabelaAutores;
	
	JButton btnCadastrar;
	ActionListener actionCadastrarLivro;
	
	public JanelaCadastraLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		setLocation(765, 445);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel panelCadastro = new JPanel();
        panelCadastro.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelCadastro.add(new JLabel("TÍTULO: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        inputTitulo = new JTextField(20);
        panelCadastro.add(inputTitulo, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panelCadastro.add(new JLabel("ISBN: "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        inputIsbn = new JTextField(5);
        panelCadastro.add(inputIsbn, c);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_END;
        panelCadastro.add(new JLabel("PREÇO: "), c);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LINE_START;
        inputPreco = new JTextField(7);
        panelCadastro.add(inputPreco, c);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        panelCadastro.add(new JLabel("EDITORA:"), c);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        JPanel panelInputEditora = new JPanel(new FlowLayout());
        inputIdEditora = new JTextField(3);
        inputIdEditora.setEditable(false);
        inputNomeEditora = new JTextField(12);
        inputNomeEditora.setEditable(false);
        JButton btnBuscaEditora = new JButton("Listar Editoras");
        btnBuscaEditora.addActionListener(new ActionBuscaEditora());
        panelInputEditora.add(inputIdEditora);
        panelInputEditora.add(inputNomeEditora);
        panelInputEditora.add(btnBuscaEditora);
        panelCadastro.add(panelInputEditora, c);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_END;
        panelCadastro.add(new JLabel("AUTOR(ES):"), c);
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        JPanel panelInputAutores = new JPanel(new FlowLayout());
        dtmTabelaAutores = new DefaultTableModel(new Object[] {"ID", "Nome", "Sobrenome"}, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable tabelaAutores = new JTable(dtmTabelaAutores);
		tabelaAutores.getColumnModel().getColumn(0).setMaxWidth(100);
		tabelaAutores.getColumnModel().getColumn(1).setMaxWidth(100);
		tabelaAutores.getColumnModel().getColumn(2).setMaxWidth(100);
		JScrollPane scrollPane = new JScrollPane(tabelaAutores);
		scrollPane.setPreferredSize(new Dimension(300,100));
		panelInputAutores.add(scrollPane);
		panelCadastro.add(panelInputAutores, c);
		c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.CENTER;
        JButton selectAutores = new JButton("Selecionar Autores");
        selectAutores.addActionListener(new ActionBuscaAutores());
        panelCadastro.add(selectAutores, c);
		
        add(panelCadastro);
        
        JPanel panelBtnCadastrar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnCadastrar = new JButton("Cadastrar Livro");
        btnCadastrar.addActionListener(new ActionBtnCadastrar());
        
        panelBtnCadastrar.add(btnCadastrar);
        add(panelBtnCadastrar);
        
        pack();
	}
	
	public void setActionListenerCadastraLivro(ActionListener e) {
		actionCadastrarLivro = e;
	}
	
	class ActionBuscaEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaEditora.setVisible(true);
			janelaBuscaEditora.setLocation(1065, 445);
			janelaBuscaEditora.tabela.addMouseListener(new MouseListenerTabelaEditoras());
		}
		
	}
	
	class MouseListenerTabelaEditoras implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linha = janelaBuscaEditora.tabela.getSelectedRow();
				String idEditora = Integer.toString(janelaBuscaEditora.editorasRenumeradas.get(linha).getId());
				String nomeEditora = janelaBuscaEditora.editorasRenumeradas.get(linha).getName();
				inputIdEditora.setText(idEditora);
				inputNomeEditora.setText(nomeEditora);
				janelaBuscaEditora.dispose();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
	}
	
	class ActionBuscaAutores implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaAutor.setVisible(true);
			janelaBuscaAutor.setLocation(1165, 445);
			janelaBuscaAutor.tabela.addMouseListener(new MouseListenerTabelaAutores());
		}
		
	}
	
	class MouseListenerTabelaAutores implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linhaSelecionada = janelaBuscaAutor.tabela.getSelectedRow();
				
				String id = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 0).toString();
				String nome = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 1).toString();
				String sobrenome = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaBuscaAutor.dtm.removeRow(linhaSelecionada);
				
				dtmTabelaAutores.addRow(new Object[] {id, nome, sobrenome});
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ActionBtnCadastrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = "";
			if(inputTitulo.getText().trim().equals("")) {
				msg += " Título não preenchido;";
			}
			if(inputIsbn.getText().trim().equals("")) {
				msg += " ISBN não preenchido;";
			}
			if(inputIdEditora.getText().trim().equals("")) {
				msg += " Editora não selecionada;";
			}
			try {
				Float.parseFloat(inputPreco.getText().trim());
			}catch(NumberFormatException error) {
				if(inputPreco.getText().trim().equals("")) {
					msg += " Preco não preenchido;";
				}else {
					msg += " Preco inválido";
				}
			}
			
			
			if(msg.equals("")) {
				actionCadastrarLivro.actionPerformed(e);
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro:" + msg,
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public String getTitulo() {
		return inputTitulo.getText();
	}
	
	public String getIsbn() {
		return inputIsbn.getText();
	}
	
	public int getIdEditora() {
		return Integer.parseInt(inputIdEditora.getText());
	}
	
	public float getPreco() {
		return Float.parseFloat(inputPreco.getText());
	}
	
}
