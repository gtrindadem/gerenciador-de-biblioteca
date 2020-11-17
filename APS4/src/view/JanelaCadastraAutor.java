package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaCadastraAutor extends JFrame {
	ActionListener actionCadastrarAutor;
	
	JTextField iptNomeAutor;
	JTextField iptSobrenomeAutor;
	
	JButton btnCadastrarAutor;
	
	public JanelaCadastraAutor() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Cadastro de Autor");
		setSize(400, 170);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		//Label Titulo
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("CADASTRO DE AUTOR:"));
		
		//Form
		JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("NOME: "), c);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        iptNomeAutor = new JTextField(20);
        panelForm.add(iptNomeAutor, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("SOBRENOME: "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        iptSobrenomeAutor = new JTextField(25);
        panelForm.add(iptSobrenomeAutor, c);
        
        //Btn Cadastrar
        JPanel panelBtnCadastrar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnCadastrarAutor = new JButton("Cadastrar Autor");
        btnCadastrarAutor.addActionListener(new ActionBtnCadastrarAutor());
        panelBtnCadastrar.add(btnCadastrarAutor);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBtnCadastrar);
	}
	
	public void setActionListenerCadastrarAutor(ActionListener e) {
		actionCadastrarAutor = e;
	}
	
	class ActionBtnCadastrarAutor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Validação de entrada de dados do form
			String msg = "";
			if(iptNomeAutor.getText().trim().equals("")) {
				msg += " Preencha o campo NOME;";
			}
			if(iptNomeAutor.getText().trim().length() > 25) {
				msg += " Campo NOME não pode conter mais que 25 caracteres;";
			}
			if(iptSobrenomeAutor.getText().trim().equals("")) {
				msg += " Preencha o campo SOBRENOME;";
			}
			if(iptSobrenomeAutor.getText().trim().length() > 25) {
				msg += " Campo SOBRENOME não pode conter mais que 25 caracteres;";
			}
			
			if(msg.equals("")) {
				actionCadastrarAutor.actionPerformed(e);
				limparDados();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar autor:" + msg,
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	//Getters do form
	public String getNomeAutor() {
		return iptNomeAutor.getText().trim();
	}
	
	public String getSobrenomeAutor() {
		return iptSobrenomeAutor.getText().trim();
	}
	
	public void limparDados() {
		iptNomeAutor.setText("");
		iptSobrenomeAutor.setText("");
	}
	
	class EventFecharJanela implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			limparDados();
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
