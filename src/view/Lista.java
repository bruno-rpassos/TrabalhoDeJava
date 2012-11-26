package view;

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
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public abstract class Lista<T extends TableModel> extends JDialog {

	protected TableRowSorter<T>	sorter;
	protected JTable			table;
	protected T					tableModel;
	protected JTextField		tfFilter;

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public Lista( final Class listaClass ) throws InstantiationException, IllegalAccessException {
		this.tableModel = ( T ) listaClass.newInstance();
		this.table = new JTable( this.tableModel );
		this.table.setAutoCreateRowSorter( true );
		this.table.setLayout( new FlowLayout() );
		this.setResizable( false );
		this.setBounds( 100, 100, 800, 500 );
		this.getContentPane().setLayout( new BorderLayout() );

		// INICIO >> PANEL BUSCA
		{
			this.tfFilter = new JTextField();
			this.tfFilter.getDocument().addDocumentListener( new DocumentListener() {
				@Override
				public void changedUpdate( final DocumentEvent e ) {
					Lista.this.newFilter();
				}

				@Override
				public void insertUpdate( final DocumentEvent e ) {
					Lista.this.newFilter();
				}

				@Override
				public void removeUpdate( final DocumentEvent e ) {
					Lista.this.newFilter();
				}
			} );

			this.getContentPane().add( this.tfFilter, BorderLayout.NORTH );
		}
		// FIM >> PANEL BUSCA

		// INICIO >> PANEL PRINCIPAL
		{
			this.sorter = new TableRowSorter<T>( this.tableModel );
			this.table.setRowSorter( this.sorter );
			this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

			final JScrollPane scrollPane = new JScrollPane( this.table );
			scrollPane.getViewport().setBackground( Color.DARK_GRAY );
			scrollPane.getViewport().setForeground( Color.WHITE );

			this.getContentPane().add( scrollPane, BorderLayout.SOUTH );
		}
		// FIM >> PANEL PRINCIPAL

		this.table.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( final MouseEvent e ) {
				if ( e.getClickCount() == 2 ) try {
					Lista.this.doubleClicked();
				} catch ( final TypeNotFoundException ex ) {}
			};
		} );
	}

	protected abstract void doubleClicked() throws TypeNotFoundException;

	private void newFilter() {
		RowFilter<T, Object> rf = null;
		try {
			rf = RowFilter.regexFilter( "^" + this.tfFilter.getText().toUpperCase(), 1 );
		} catch ( final java.util.regex.PatternSyntaxException e ) {
			return;
		}

		this.sorter.setRowFilter( rf );
	}
}
