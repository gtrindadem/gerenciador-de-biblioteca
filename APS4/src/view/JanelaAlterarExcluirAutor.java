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

import view.JanelaAlterarExcluirEditora.ActionBtnCancelar;
import view.JanelaAlterarExcluirEditora.ActionBtnExcluir;
import view.JanelaAlterarExcluirEditora.ActionBtnSalvar;

public class JanelaAlterarExcluirAutor extends JFrame {
	JTextField txtIdAutor;
	JTextField txtNomeAutor;
	JTextField txtSobrenomeAutor;
	
	JButton btnSalvar;
	JButton btnExcluir;
	
	ActionListener actionExcluirAutor;
	ActionListener actionAlterarAutor;
	
	public JanelaAlterarExcluirAutor() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Alterar/Excluir Autor");
		setSize(400, 170);
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
        panelForm.add(txtNomeAutor, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("SITE(URL): "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        txtSobrenomeAutor = new JTextField(25);
        panelForm.add(txtSobrenomeAutor, c);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionBtnExcluir());
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionBtnSalvar());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionBtnCancelar());
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBotoes);
	}
	
	public void setActionListenerExcluirAutor(ActionListener e) {
		actionExcluirAutor = e;
	}
	
	public void setActionListenerAlterarAutor(ActionListener e) {
		actionAlterarAutor = e;
	}
	
	class ActionBtnExcluir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "SIM", "CANCELAR" };
		    int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir este cadastro?\nTodos os dados serão perdidos!", "Aviso",
		    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		              null, options, options[0]);
		    if(resp == 0) {
		    	actionExcluirAutor.actionPerformed(e);
		    	JOptionPane.showMessageDialog(null, "Autor excluido com sucesso!");
		    	LimparDados();
		    	dispose();
		    }
		}
		
	}
	
	class ActionBtnSalvar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "SIM", "CANCELAR" };
		    int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja salvar as alterações?\nTodos os antigos dados serão perdidos!", "Aviso",
		    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		              null, options, options[0]);
		    if(resp == 0) {
		    	//Validação de entrada de dados do form
				String msg = "";
				if(txtNomeAutor.getText().trim().equals("")) {
					msg += " Campo NOME não pode estar vazio;";
				}
				if(txtNomeAutor.getText().trim().length() > 25) {
					msg += " Campo NOME não pode conter mais que 25 caracteres;";
				}
				if(txtSobrenomeAutor.getText().trim().equals("")) {
					msg += " Campo SOBRENOME não pode estar vazio;";
				}
				if(txtSobrenomeAutor.getText().trim().length() > 25) {
					msg += " Campo SOBRENOME não pode conter mais que 25 caracteres;";
				}
				
				if(msg.equals("")) {
					actionAlterarAutor.actionPerformed(e);
			    	JOptionPane.showMessageDialog(null, "Autor alterado com sucesso!");
			    	LimparDados();
			    	dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao alterar autor:" + msg,
							"Erro ao salvar", JOptionPane.ERROR_MESSAGE);
				}    	
		    }
		}
		
	}
	
	class ActionBtnCancelar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LimparDados();
			dispose();
		}
		
	}
	
	public void setIdAutor(String id) {
		txtIdAutor.setText(id);
	}
	
	public void setNomeAutor(String nome) {
		txtNomeAutor.setText(nome);
	}
	
	public void setSobrenomeAutor(String site) {
		txtSobrenomeAutor.setText(site);
	}
	
	public int getIdAutor() {
		return Integer.parseInt(txtIdAutor.getText());
	}
	
	public String getNomeAutor() {
		return txtNomeAutor.getText();
	}
	
	public String getSobrenomeAutor() {
		return txtSobrenomeAutor.getText();
	}
	
	public void LimparDados() {
		txtIdAutor.setText("");
		txtNomeAutor.setText("");
		txtSobrenomeAutor.setText("");
	}
	
}
