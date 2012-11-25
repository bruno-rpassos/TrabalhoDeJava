package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

@SuppressWarnings( "serial" )
public abstract class DialogPadrao extends JDialog {

	protected final JPanel	contentPanel;

	public DialogPadrao( final String title ) {
		this.setTitle( title );

		this.setResizable( false );
		this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

		this.contentPanel = new JPanel();
		this.contentPanel.setBackground( Color.DARK_GRAY );
		this.contentPanel.setLayout( new FlowLayout() );

		this.getContentPane().setLayout( new BorderLayout() );
		this.getContentPane().add( this.contentPanel, BorderLayout.CENTER );
		this.setBounds( 100, 100, 450, 250 );
	}
}
