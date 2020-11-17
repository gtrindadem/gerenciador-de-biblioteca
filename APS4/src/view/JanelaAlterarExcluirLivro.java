package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaAlterarExcluirLivro extends JFrame {
	JTextField iptTitulo;
	JTextField iptIsbn;
	JTextField iptPreco;
	
	JButton btnAlterar;
	
	ActionListener actionAlterarLivro;
	ActionListener actionExcluirLivro;
	
	public JanelaAlterarExcluirLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Alteração de Livro");
		setSize(370, 180);
		setLocationRelativeTo(null);
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
        iptTitulo.setEditable(false);
        panelForm.add(iptTitulo, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("ISBN: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        iptIsbn = new JTextField(10);
        iptIsbn.setEditable(false);
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
        
        JPanel panelBotoes = new JPanel();
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionBtnExcluir());
        btnAlterar = new JButton("Salvar");
        btnAlterar.addActionListener(new ActionBtnAlterar());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionBtnCancelar());
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnAlterar);
        panelBotoes.add(btnCancelar);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBotoes);
	}
	
	public void setActionListenerAlterarLivro(ActionListener e) {
		actionAlterarLivro = e;
	}
	
	public void setActionListenerExcluirLivro(ActionListener e) {
		actionExcluirLivro = e;
	}
	
	class ActionBtnExcluir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "SIM", "CANCELAR" };
		    int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir este cadastro?\nTodos os dados serão perdidos!", "Aviso",
		    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		              null, options, options[0]);
		    if(resp == 0) {
		    	actionExcluirLivro.actionPerformed(e);
		    	JOptionPane.showMessageDialog(null, "Livro excluido com sucesso!");
		    	limparDados();
		    	dispose();
		    }
		}
		
	}
	
	class ActionBtnAlterar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "SIM", "CANCELAR" };
		    int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja salvar as alterações?\nTodos os antigos dados serão perdidos!", "Aviso",
		    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		              null, options, options[0]);
		    if(resp == 0) {
		    	//Validação de entrada de dados do form
				String msg = "";
				try {
					Float.parseFloat(iptPreco.getText().trim());
				}catch(NumberFormatException error) {
					if(iptPreco.getText().trim().equals("")) {
						msg += " PREÇO não preenchido;";
					}else {
						msg += " PREÇO inválido (Utilize apenas \".\")";
					}
				}
				
				if(msg.equals("")) {
					actionAlterarLivro.actionPerformed(e);
			    	JOptionPane.showMessageDialog(null, "Preço alterado com sucesso!");
			    	limparDados();
			    	dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao alterar preço:" + msg,
							"Erro ao salvar", JOptionPane.ERROR_MESSAGE);
				}    	
		    }
		}
		
	}
	
	class ActionBtnCancelar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			limparDados();
			dispose();
		}
		
	}
	
	public void setTitulo(String titulo) {
		iptTitulo.setText(titulo);
	}
	
	public void setIsbn(String isbn) {
		iptIsbn.setText(isbn);
	}
	
	public void setPreco(String preco) {
		iptPreco.setText(preco);
	}
	
	//Getters do form
	public String getIsbn() {
		return iptIsbn.getText().trim();
	}
	
	public float getPreco() {
		return Float.parseFloat(iptPreco.getText().trim());
	}
	
	public void limparDados() {
		iptTitulo.setText("");
		iptIsbn.setText("");
		iptPreco.setText("");
	}
	
}
