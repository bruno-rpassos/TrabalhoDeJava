package view.produto;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.produto.model.JListaDeProdutosTableModel;
import controller.ProdutosController;

@SuppressWarnings("serial")
public class Lista extends JDialog {
	private JTable jtLista;
	private JListaDeProdutosTableModel jtmListaDeProdutos;
	
	public Lista() {
		jtmListaDeProdutos = new JListaDeProdutosTableModel();
		jtLista = new JTable(jtmListaDeProdutos);
		
		getContentPane().add(new JScrollPane(jtLista));
		setSize(500,300);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		jtLista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					linhaClicada();
				}
			}
		});
	}
	
	private void linhaClicada() {
		String idDoProduto = String.valueOf(jtLista.getValueAt(jtLista.getSelectedRow(), 0));
		ProdutosController.edit(idDoProduto);
	}
}
