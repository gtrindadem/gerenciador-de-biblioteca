package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Livro;
import model.DaoMySql;

public class JanelaBuscaLivro extends JFrame {
	DefaultTableModel dtm;
	JButton btnBuscaLivro;
	JTextField txtFldBuscaLivro;
	
	public JanelaBuscaLivro() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initComponents();
	}

	void initComponents() {
		setLayout(new BorderLayout());
		setBounds(765, 445, 400, 200);
		
		dtm = new DefaultTableModel(new Object[] {"Título", "ISBN", "Editora", "Preço"}, 0);
		JTable tabela = new JTable(dtm);
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane, BorderLayout.PAGE_START);
		
		JPanel panelBuscaLivro = new JPanel(new FlowLayout());
		txtFldBuscaLivro = new JTextField(25);
		btnBuscaLivro = new JButton("Buscar");
		panelBuscaLivro.add(txtFldBuscaLivro);
		panelBuscaLivro.add(btnBuscaLivro);
		add(panelBuscaLivro, BorderLayout.CENTER);
		
		pack();
	}
	
	public void setActionListenerBuscarLivro(ActionListener e) {
		btnBuscaLivro.addActionListener(e);
	}
	
	public void mostraLivros(Collection<Livro> livros) {
		dtm.setNumRows(0);
		for(Livro livro: livros){
            Object[] linha = new Object[] {livro.getTitle(), livro.getIsbn(), livro.getIdEditora(), livro.getPreco()};
            dtm.addRow(linha);
        }
	}
	
	public String getTitulo() {
		return txtFldBuscaLivro.getText();
	}
}
