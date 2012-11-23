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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UserController;
import exception.PassNotFoundException;
import exception.UserNotFoundException;

@SuppressWarnings("serial")
public class Login extends JDialog {
	private JTextField userTf;
	private JPasswordField passTf;

	/**
	 * Create the dialog.
	 */

	private void doLogin(String user, char[] pass) {
		try {
			System.out.println("trying to login [" + user + ", " + new String(pass) + " ]");
			UserController.validarLogin(user, new String(pass));
			System.out.println(" >> LOGOU com " + user);

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainFrame window = new MainFrame();
						window.frmMain.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			dispose();
		} catch (UserNotFoundException ex) {
			JOptionPane.showMessageDialog(this, ">> USER INVALIDO!");
		} catch (PassNotFoundException ex) {
			JOptionPane.showMessageDialog(this, ">> " + user
					+ ": SENHA INVALIDA!");
		}
	}

	public Login() {
		setBounds(200, 200, 250, 139);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton loginBtn = new JButton("LOGIN");
				loginBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						doLogin(userTf.getText(), passTf.getPassword());
					}
				});
				buttonPane.add(loginBtn);
				getRootPane().setDefaultButton(loginBtn);
			}
			{
				JButton exitBtn = new JButton("SAIR");
				exitBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(exitBtn);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(400, 40));
			panel.setBackground(Color.DARK_GRAY);
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JLabel label = new JLabel("USER");
				label.setForeground(Color.WHITE);
				panel.add(label);
			}
			{
				userTf = new JTextField();
				userTf.setColumns(10);
				panel.add(userTf);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(400, 40));
			panel.setBackground(Color.DARK_GRAY);
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JLabel label = new JLabel("SENHA");
				label.setForeground(Color.WHITE);
				panel.add(label);
			}
			{
				passTf = new JPasswordField();
				passTf.setColumns(10);
				panel.add(passTf);
			}
		}
	}

}
