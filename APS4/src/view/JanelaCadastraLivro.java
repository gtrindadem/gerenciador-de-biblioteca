package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collection;

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

import view.JanelaBuscaEditora.LimpaTela;

public class JanelaCadastraLivro extends JFrame {
	
	JTextField inputTitulo;
	JTextField inputIsbn;
	JTextField inputPreco;
	
	JanelaBuscaEditora janelaBuscaEditora;
	JTextField inputIdEditora;
	JTextField inputNomeEditora;
	
	JanelaBuscaAutor janelaBuscaAutor;
	JTable tabelaAutores;
	DefaultTableModel dtmTabelaAutores;
	
	JanelaSelecaoDeAutores janelaSelecaoAutores;
	DefaultTableModel dtmSelecaoAutores;
	
	JButton btnCadastrar;
	ActionListener actionCadastrarLivro;
	
	public JanelaCadastraLivro(DefaultTableModel dtm) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		dtmSelecaoAutores = dtm;
		janelaSelecaoAutores = new JanelaSelecaoDeAutores();
		
		initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de Livro");
		setLocation(765, 445);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		addWindowListener(new eventFecharJanela());
		
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
        btnBuscaEditora.addActionListener(new ActionBtnListarEditoras());
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
		tabelaAutores = new JTable(dtmTabelaAutores);
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
        selectAutores.addActionListener(new ActionBtnSelecionarAutores());
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
	
	class ActionBtnListarEditoras implements ActionListener {

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
	
	class JanelaSelecaoDeAutores extends JFrame{
		JTable tabela;
		DefaultTableModel dtm;
		
		public JanelaSelecaoDeAutores(){
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			dtm = dtmSelecaoAutores;
			
			initComponents();
		}
		
		public void initComponents() {
			setBounds(765, 445, 420, 235);
			tabela = new JTable(dtm);
			tabela.getColumnModel().getColumn(0).setMaxWidth(50);
			JScrollPane scrollPane = new JScrollPane(tabela);
			scrollPane.setPreferredSize(new Dimension(200,150));
			
			add(scrollPane);
		}
	}
	
	class ActionBtnSelecionarAutores implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			janelaSelecaoAutores.setLocation(1165, 445);
			janelaSelecaoAutores.tabela.addMouseListener(new MouseListenerTabelaSelecionaAutores());
			janelaSelecaoAutores.setVisible(true);
		}
		
	}
	
	class MouseListenerTabelaSelecionaAutores implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linhaSelecionada = janelaSelecaoAutores.tabela.getSelectedRow();
				
				String id = janelaSelecaoAutores.tabela.getValueAt(linhaSelecionada, 0).toString();
				String nome = janelaSelecaoAutores.tabela.getValueAt(linhaSelecionada, 1).toString();
				String sobrenome = janelaSelecaoAutores.tabela.getValueAt(linhaSelecionada, 2).toString();
				
				janelaSelecaoAutores.dtm.removeRow(linhaSelecionada);
				
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
			//Validação de entrada de dados do form
			String msg = "";
			String iptTitulo = inputTitulo.getText().trim();
			String iptIsbn = inputIsbn.getText().trim();
;			if(iptTitulo.equals("")) {
				msg += " Título não preenchido;";
			}
			if(iptTitulo.length() > 60) {
				msg += " Título não pode ter mais que 60 caracteres;";
			}
			if(iptIsbn.equals("")) {
				msg += " ISBN não preenchido;";
			}
			if(iptIsbn.length() > 13) {
				msg += " ISBN não pode ter mais que 13 caracteres;";
			}
			try {
				Float.parseFloat(inputPreco.getText().trim());
			}catch(NumberFormatException error) {
				if(inputPreco.getText().trim().equals("")) {
					msg += " Preco não preenchido;";
				}else {
					msg += " Preco inválido (Utilize apenas \".\")";
				}
			}
			if(inputIdEditora.getText().trim().equals("")) {
				msg += " Editora não selecionada;";
			}
			if(tabelaAutores.getRowCount() == 0) {
				msg += " Cadastre pelo menos 1 autor;";
			}
			
			
			if(msg.equals("")) {
				actionCadastrarLivro.actionPerformed(e);
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro:" + msg,
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			}
			dispose();
			janelaSelecaoAutores.dispose();
			LimparDados();
		}
		
	}
	
	//getters do form
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
	
	public Collection<Integer> getIdAutores() {
		ArrayList<Integer> idAutores = new ArrayList<>();
		for(int i = 0; i < dtmTabelaAutores.getRowCount(); i++) {
			idAutores.add(Integer.parseInt(tabelaAutores.getValueAt(i, 0).toString()));
		}
		
		return idAutores;
	}
	
	public void LimparDados() {
		inputTitulo.setText("");
		inputIsbn.setText("");
		inputPreco.setText("");
		inputIdEditora.setText("");
		inputNomeEditora.setText("");

		dtmTabelaAutores.setNumRows(0);
		dtmSelecaoAutores.setNumRows(0);
	}
	
	class eventFecharJanela implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			LimparDados();
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
