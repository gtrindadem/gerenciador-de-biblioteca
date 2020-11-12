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

public class JanelaCadastraLivro extends JFrame {
	JTextField iptTitulo;
	JTextField iptIsbn;
	JTextField iptPreco;
	JTextField iptIdEditora;
	JTextField iptNomeEditora;
	
	JanelaEditora janelaBuscaEditora;
	MouseListener mouseListenerTabelaEditoras;

	JButton selectAutores;
	JanelaAutor janelaBuscaAutor;
	MouseListener mouseListenerTabelaAutor;
	
	JTable tabelaAutores;
	DefaultTableModel dtmTabelaAutores;
	
	JButton btnCadastrar;
	ActionListener actionCadastrarLivro;
	
	public JanelaCadastraLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		setTitle("Cadastro de Livro");
		setSize(450, 350);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		addWindowListener(new eventFecharJanela());
		
		//Label Titulo
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("CADASTRO DE LIVRO"));
		
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
        c.anchor = GridBagConstraints.LINE_START;
        JPanel panelInputEditora = new JPanel(new FlowLayout());
        iptIdEditora = new JTextField(3);
        iptIdEditora.setEditable(false);
        iptNomeEditora = new JTextField(12);
        iptNomeEditora.setEditable(false);
        JButton btnBuscaEditora = new JButton("Listar Editoras");
        btnBuscaEditora.addActionListener(new ActionBtnListarEditoras());
        panelInputEditora.add(iptIdEditora);
        panelInputEditora.add(iptNomeEditora);
        panelInputEditora.add(btnBuscaEditora);
        panelForm.add(panelInputEditora, c);
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
		tabelaAutores.addMouseListener(new MouseListenerTabelaAutores());
		JScrollPane scrollPane = new JScrollPane(tabelaAutores);
		scrollPane.setPreferredSize(new Dimension(300,100));
		panelForm.add(scrollPane, c);
		c.gridx = 1;
        c.gridy = 5;
        selectAutores = new JButton("Selecionar Autores");
        selectAutores.addActionListener(new ActionBtnSelecionarAutores());
        panelForm.add(selectAutores, c);
		
        JPanel panelBtnCadastrar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnCadastrar = new JButton("Cadastrar Livro");
        btnCadastrar.addActionListener(new ActionBtnCadastrar());
        
        panelBtnCadastrar.add(btnCadastrar);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBtnCadastrar);
	}
	
	public void setActionListenerCadastrarLivro(ActionListener e) {
		actionCadastrarLivro = e;
	}
	
	class ActionBtnListarEditoras implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaEditora.LimparDados();
			janelaBuscaEditora.actionBuscarEditora.actionPerformed(e);
			janelaBuscaEditora.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			janelaBuscaEditora.setVisible(true);
			
			mouseListenerTabelaEditoras = new MouseListenerTabelaEditoras();
			janelaBuscaEditora.tabela.addMouseListener(mouseListenerTabelaEditoras);
		}
		
	}
	
	class MouseListenerTabelaEditoras implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linhaSelecionada = janelaBuscaEditora.tabela.getSelectedRow();
				
				String idEditora = janelaBuscaEditora.tabela.getValueAt(linhaSelecionada, 0).toString();
				String nomeEditora = janelaBuscaEditora.tabela.getValueAt(linhaSelecionada, 1).toString();
				
				iptIdEditora.setText(idEditora);
				iptNomeEditora.setText(nomeEditora);
				
				janelaBuscaEditora.tabela.removeMouseListener(mouseListenerTabelaEditoras);
				janelaBuscaEditora.dispose();
				janelaBuscaEditora.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				janelaBuscaEditora.setBounds(765, 445, 400, 300);
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
	
	class ActionBtnSelecionarAutores implements ActionListener{
		JPanel panelBtnOk;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			janelaBuscaAutor.LimparDados();
			janelaBuscaAutor.actionBuscarAutor.actionPerformed(e);
			janelaBuscaAutor.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			janelaBuscaAutor.setVisible(true);
			
			panelBtnOk = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionBtnOk());
			panelBtnOk.add(btnOk);
			
			janelaBuscaAutor.add(panelBtnOk);
			
			mouseListenerTabelaAutor = new MouseListenerTabelaAutor();
			janelaBuscaAutor.tabela.addMouseListener(mouseListenerTabelaAutor);
		}
	
		class ActionBtnOk implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				janelaBuscaAutor.dispose();
				janelaBuscaAutor.remove(panelBtnOk);
				janelaBuscaAutor.tabela.removeMouseListener(mouseListenerTabelaAutor);
				janelaBuscaAutor.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
			
		}
		
	}
	
	class MouseListenerTabelaAutor implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linhaSelecionada = janelaBuscaAutor.tabela.getSelectedRow();
				
				String id = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 0).toString();
				String nome = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 1).toString();
				String sobrenome = janelaBuscaAutor.tabela.getValueAt(linhaSelecionada, 2).toString();
				
				boolean linhaNova = true;
				for(int i=0; i < dtmTabelaAutores.getRowCount(); i++){
					if(id.equals(dtmTabelaAutores.getValueAt(i, 0).toString())) {
						linhaNova = false;
					}
				}
				
				janelaBuscaAutor.dtm.removeRow(linhaSelecionada);
				
				if(linhaNova) {
					dtmTabelaAutores.addRow(new Object[] {id, nome, sobrenome});
				}
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
	
	class MouseListenerTabelaAutores implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int linhaSelecionada = tabelaAutores.getSelectedRow();
				
				String id = tabelaAutores.getValueAt(linhaSelecionada, 0).toString();
				String nome = tabelaAutores.getValueAt(linhaSelecionada, 1).toString();
				String sobrenome = tabelaAutores.getValueAt(linhaSelecionada, 2).toString();
				
				dtmTabelaAutores.removeRow(linhaSelecionada);
				
				janelaBuscaAutor.dtm.insertRow(0, new Object[] {id, nome, sobrenome});
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
			String inputTitulo = iptTitulo.getText().trim();
			String inputIsbn = iptIsbn.getText().trim();
			if(inputTitulo.equals("")) {
				msg += " Campo TÍTULO não preenchido;";
			}
			if(inputTitulo.length() > 60) {
				msg += " TÍTULO não pode ter mais que 60 caracteres;";
			}
			if(inputIsbn.equals("")) {
				msg += " Campo ISBN não preenchido;";
			}
			if(inputIsbn.length() > 13) {
				msg += " ISBN não pode ter mais que 13 caracteres;";
			}
			try {
				Float.parseFloat(iptPreco.getText().trim());
			}catch(NumberFormatException error) {
				if(iptPreco.getText().trim().equals("")) {
					msg += " PREÇO não preenchido;";
				}else {
					msg += " PREÇO inválido (Utilize apenas \".\")";
				}
			}
			if(iptIdEditora.getText().trim().equals("")) {
				msg += " EDITORA não selecionada;";
			}
			if(tabelaAutores.getRowCount() == 0) {
				msg += " Selecione pelo menos 1 AUTOR;";
			}
			
			
			if(msg.equals("")) {
				actionCadastrarLivro.actionPerformed(e);
				dispose();
				LimparDados();
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro:" + msg,
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	//Getters do form
	public String getTitulo() {
		return iptTitulo.getText().trim();
	}
	
	public String getIsbn() {
		return iptIsbn.getText().trim();
	}
	
	public int getIdEditora() {
		System.out.println("a:" + iptIdEditora.getText());
		return Integer.parseInt(iptIdEditora.getText());
	}
	
	public float getPreco() {
		return Float.parseFloat(iptPreco.getText().trim());
	}
	
	public Collection<Integer> getIdAutores() {
		ArrayList<Integer> idAutores = new ArrayList<>();
		for(int i = 0; i < dtmTabelaAutores.getRowCount(); i++) {
			idAutores.add(Integer.parseInt(tabelaAutores.getValueAt(i, 0).toString()));
		}
		
		return idAutores;
	}
	
	public void LimparDados() {
		iptTitulo.setText("");
		iptIsbn.setText("");
		iptPreco.setText("");
		iptIdEditora.setText("");
		iptNomeEditora.setText("");

		dtmTabelaAutores.setNumRows(0);
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
