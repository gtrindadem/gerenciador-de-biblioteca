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

public class JanelaAlterarExcluirEditora extends JFrame {
	JTextField txtIdEditora;
	JTextField txtNomeEditora;
	JTextField txtSiteEditora;
	
	JButton btnSalvar;
	JButton btnExcluir;
	
	ActionListener actionExcluirEditora;
	ActionListener actionAlterarEditora;
	
	public JanelaAlterarExcluirEditora() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Alterar/Excluir Editora");
		setSize(400, 170);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		//Label Título
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("EDITORA:"));
		
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
        txtIdEditora = new JTextField(5);
        txtIdEditora.setEditable(false);
        panelForm.add(txtIdEditora, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("NOME: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        txtNomeEditora = new JTextField(20);
        panelForm.add(txtNomeEditora, c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("SITE(URL): "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        txtSiteEditora = new JTextField(25);
        panelForm.add(txtSiteEditora, c);
        
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
	
	public void setActionListenerExcluirEditora(ActionListener e) {
		actionExcluirEditora = e;
	}
	
	public void setActionListenerAlterarEditora(ActionListener e) {
		actionAlterarEditora = e;
	}
	
	class ActionBtnExcluir implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = { "SIM", "CANCELAR" };
		    int resp = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir este cadastro?\nTodos os dados serão perdidos!", "Aviso",
		    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		              null, options, options[0]);
		    if(resp == 0) {
		    	actionExcluirEditora.actionPerformed(e);
		    	JOptionPane.showMessageDialog(null, "Editora excluida com sucesso!");
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
				if(txtNomeEditora.getText().trim().equals("")) {
					msg += " Campo NOME não pode estar vazio;";
				}
				if(txtNomeEditora.getText().trim().length() > 30) {
					msg += " Campo NOME não pode conter mais que 30 caracteres;";
				}
				if(txtSiteEditora.getText().trim().equals("")) {
					msg += " Campo SITE não pode estar vazio;";
				}
				if(txtSiteEditora.getText().trim().length() > 80) {
					msg += " Campo SITE não pode conter mais que 80 caracteres;";
				}
				
				if(msg.equals("")) {
					actionAlterarEditora.actionPerformed(e);
			    	JOptionPane.showMessageDialog(null, "Editora alterada com sucesso!");
			    	LimparDados();
			    	dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao alterar editora:" + msg,
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
	
	public void setIdEditora(String id) {
		txtIdEditora.setText(id);
	}
	
	public void setNomeEditora(String nome) {
		txtNomeEditora.setText(nome);
	}
	
	public void setSiteEditora(String site) {
        txtSiteEditora.setText(site);
	}
	
	public int getIdEditora() {
		return Integer.parseInt(txtIdEditora.getText());
	}
	
	public String getNomeEditora() {
		return txtNomeEditora.getText();
	}
	
	public String getSiteEditora() {
		return txtSiteEditora.getText();
	}
	
	public void LimparDados() {
		txtIdEditora.setText("");
		txtNomeEditora.setText("");
		txtSiteEditora.setText("");
	}
	
}
