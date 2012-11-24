package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.UserController;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

@SuppressWarnings( "serial" )
public class Login extends JDialog {
	private JPasswordField	passTf;
	private JTextField		userTf;

	public Login() {
		this.setBounds( 200, 200, 250, 139 );
		this.getContentPane().setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		{
			final JPanel buttonPane = new JPanel();
			this.getContentPane().add( buttonPane, BorderLayout.SOUTH );
			buttonPane.setLayout( null );
			buttonPane.setLayout( new FlowLayout( FlowLayout.CENTER, 5, 5 ) );
			{
				final JButton loginBtn = new JButton( "LOGIN" );
				loginBtn.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed( final ActionEvent arg0 ) {
						Login.this.doLogin( Login.this.userTf.getText(), Login.this.passTf.getPassword() );
					}
				} );
				buttonPane.add( loginBtn );
				this.getRootPane().setDefaultButton( loginBtn );
			}
			{
				final JButton exitBtn = new JButton( "SAIR" );
				exitBtn.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed( final ActionEvent arg0 ) {
						Login.this.dispose();
					}
				} );
				buttonPane.add( exitBtn );
			}
		}
		{
			final JPanel panel = new JPanel();
			panel.setPreferredSize( new Dimension( 400, 40 ) );
			panel.setBackground( Color.DARK_GRAY );
			this.getContentPane().add( panel, BorderLayout.NORTH );
			panel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
			{
				final JLabel label = new JLabel( "USER" );
				label.setForeground( Color.WHITE );
				panel.add( label );
			}
			{
				this.userTf = new JTextField();
				this.userTf.setColumns( 10 );
				panel.add( this.userTf );
			}
		}
		{
			final JPanel panel = new JPanel();
			panel.setPreferredSize( new Dimension( 400, 40 ) );
			panel.setBackground( Color.DARK_GRAY );
			this.getContentPane().add( panel, BorderLayout.EAST );
			panel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
			{
				final JLabel label = new JLabel( "SENHA" );
				label.setForeground( Color.WHITE );
				panel.add( label );
			}
			{
				this.passTf = new JPasswordField();
				this.passTf.setColumns( 10 );
				panel.add( this.passTf );
			}
		}
	}

	/**
	 * Create the dialog.
	 */

	private void doLogin( final String user, final char[] pass ) {
		try {
			System.out.println( "trying to login [" + user + ", " + new String( pass ) + " ]" );
			UserController.validarLogin( user, new String( pass ) );
			System.out.println( " >> LOGOU com " + user );

			EventQueue.invokeLater( new Runnable() {
				@Override
				public void run() {
					try {
						final MainFrame window = new MainFrame();
						window.frmMain.setVisible( true );
					} catch ( final Exception e ) {
						e.printStackTrace();
					}
				}
			} );

			this.dispose();
		} catch ( final UserNotFoundException ex ) {
			JOptionPane.showMessageDialog( this, ">> USER INVALIDO!" );
		} catch ( final PassNotFoundException ex ) {
			JOptionPane.showMessageDialog( this, ">> " + user + ": SENHA INVALIDA!" );
		}
	}

}
