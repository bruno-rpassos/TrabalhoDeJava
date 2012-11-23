package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.vo.VO;
import annotation.Input;

@SuppressWarnings("serial")
public class Form<T extends VO> extends DialogPadrao {

	protected JButton saveButton;
	protected JButton cancelButton;

	protected Class<T> classe;
	private boolean didRequestedFocus;

	public Form(Class<T> VOClass, String title) throws Exception {
		super(title);
		this.classe = VOClass;

		parseFields();
		buildButtons();
		didRequestedFocus = false;
	}

	public void parseFields() throws Exception {
		Field[] fields = this.classe.getFields();

		for (Field f : fields) {
			if (f.isAnnotationPresent(Input.class)) {
				Input in = f.getAnnotation(Input.class);

				{
					JPanel panel = new JPanel();
					panel.setBackground(Color.DARK_GRAY);
					panel.setPreferredSize(new Dimension(400, 40));
					panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
					contentPanel.add(panel);

					{
						JLabel label = new JLabel(in.label());
						label.setForeground(Color.WHITE);
						panel.add(label);
					}

					{
						JTextField textField = new JTextField(25);
						textField.setName(in.name());
						panel.add(textField);
					}
				}
			}
		}
	}

	protected void buildButtons() {
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		addSaveButton(buttonPane);
		addCancelButton(buttonPane);
	}

	private void addCancelButton(JPanel buttonPane) {
		cancelButton = new JButton("CANCELAR");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPane.add(cancelButton);
	}

	private void addSaveButton(JPanel buttonPane) {
		saveButton = new JButton("SALVAR");
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPane.add(saveButton);
		getRootPane().setDefaultButton(saveButton);
	}

	protected void clear() {
		clearAllTextFields(getContentPane());
	}

	private void clearAllTextFields(Container pane) {
		for (int i = 0; i < pane.getComponentCount(); i++) {
			Component c = pane.getComponent(i);

			if (c instanceof JTextField) {
				if (!didRequestedFocus) {
					((JTextField) c).requestFocus();
					didRequestedFocus = true;
				}
				((JTextField) c).setText(null);
			}

			else if (c instanceof Container)
				clearAllTextFields((Container) c);
		}

		didRequestedFocus = false;
	}
}
