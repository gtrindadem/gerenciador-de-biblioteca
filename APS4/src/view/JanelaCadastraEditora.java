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

public class JanelaCadastraEditora extends JFrame {
	JTextField iptNomeEditora;
	JTextField iptSiteEditora;
	
	JButton btnCadastrarEditora;
	
	ActionListener actionCadastrarEditora;
	
	public JanelaCadastraEditora() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Cadastro de Editora");
		setBounds(765, 445, 400, 170);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		//Label Titulo
		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTitulo.add(new JLabel("CADASTRO DE EDITORA:"));
		
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
        iptNomeEditora = new JTextField(20);
        panelForm.add(iptNomeEditora, c);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panelForm.add(new JLabel("SITE(URL): "), c);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        iptSiteEditora = new JTextField(25);
        panelForm.add(iptSiteEditora, c);
        
        //Btn Cadastrar
        JPanel panelBtnCadastrar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        btnCadastrarEditora = new JButton("Cadastrar Editora");
        btnCadastrarEditora.addActionListener(new ActionBtnCadastrarEditora());
        panelBtnCadastrar.add(btnCadastrarEditora);
        
        add(panelTitulo);
        add(panelForm);
        add(panelBtnCadastrar);
	}
	
	public void setActionListenerCadastraEditora(ActionListener e) {
		actionCadastrarEditora = e;
	}
	
	class ActionBtnCadastrarEditora implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Validação de entrada de dados do form
			String msg = "";
			if(iptNomeEditora.getText().trim().equals("")) {
				msg += " Preencha o campo NOME;";
			}
			if(iptNomeEditora.getText().trim().length() > 30) {
				msg += " Campo NOME não pode conter mais que 30 caracteres;";
			}
			if(iptSiteEditora.getText().trim().equals("")) {
				msg += " Preencha o campo SITE;";
			}
			if(iptSiteEditora.getText().trim().length() > 80) {
				msg += " Campo SITE não pode conter mais que 80 caracteres;";
			}
			
			if(msg.equals("")) {
				actionCadastrarEditora.actionPerformed(e);
				limparDados();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar editora:" + msg,
						"Erro ao cadastrar", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	//Getters do form
	public String getNomeEditora() {
		return iptNomeEditora.getText();
	}
	
	public String getSiteEditora() {
		return iptSiteEditora.getText();
	}
	
	public void limparDados() {
		iptNomeEditora.setText("");
		iptSiteEditora.setText("");
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
