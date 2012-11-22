package view.produto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import view.produto.model.ProdutosTableModel;
import controller.produto.ProdutosController;

@SuppressWarnings("serial")
public class Lista extends JDialog {

	private JTable								table;
	private ProdutosTableModel					produtosTable;
	private JTextField							tfFilter;
	private TableRowSorter<ProdutosTableModel>	sorter;

	public Lista() {
		produtosTable = new ProdutosTableModel();
		table = new JTable(produtosTable);
		table.setAutoCreateRowSorter(true);
		table.setLayout(new FlowLayout());
		setResizable(false);
		setBounds(100, 100, 800, 470);
		getContentPane().setLayout(new BorderLayout());

		// INICIO >> PANEL BUSCA
		{
			tfFilter = new JTextField();
			tfFilter.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent e) {
					newFilter();
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					newFilter();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					newFilter();
				}
			});

			getContentPane().add(tfFilter, BorderLayout.NORTH);
		}
		// FIM >> PANEL BUSCA

		// INICIO >> PANEL PRINCIPAL
		{
			sorter = new TableRowSorter<ProdutosTableModel>(produtosTable);
			table.setRowSorter(sorter);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.getViewport().setBackground(Color.DARK_GRAY);
			scrollPane.getViewport().setForeground(Color.WHITE);

			getContentPane().add(scrollPane, BorderLayout.SOUTH);
		}
		// FIM >> PANEL PRINCIPAL

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectedRow();
				}
			};
		});
	}

	private void newFilter() {
		RowFilter<ProdutosTableModel, Object> rf = null;
		try {
			rf = RowFilter.regexFilter(tfFilter.getText().toUpperCase(), 1);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}

		sorter.setRowFilter(rf);
	}

	private void selectedRow() {
		String idDoProduto = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		ProdutosController.edit(idDoProduto);
	}
}