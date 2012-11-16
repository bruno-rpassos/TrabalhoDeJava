package view.produto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.produto.model.ProdutosTableModel;

@SuppressWarnings("serial")
public class Listaa extends JDialog {

	private JTable table;
	private ProdutosTableModel produtosTable;

	public Listaa() {
		produtosTable = new ProdutosTableModel();
		table = new JTable(produtosTable);
		
		// INICIO >> PANEL PRINCIPAL
		{
			setBounds(100, 100, 600, 660);
			getContentPane().setLayout(new BorderLayout());
			table.setLayout(new FlowLayout());
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBackground(Color.DARK_GRAY);
			scrollPane.setForeground(Color.WHITE);

			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}

	}
}
