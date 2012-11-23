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

	private Class<T> classe;
	private boolean didRequestedFocus;

	protected Form(Class<T> VOClass, String title) throws Exception {
		super(title);
		this.classe = VOClass;

		parseFields();
		buildButtons();
		didRequestedFocus = false;
	}

	protected void updateTextFieldsWithVO(T vo) {
		
	}
	
	protected T generateVO() {
		T vo = null;

		try {
			vo = this.classe.newInstance();

			for (Field f : this.classe.getDeclaredFields()) {
				if (f.isAnnotationPresent(Input.class)) {
					Input in = f.getAnnotation(Input.class);

					f.setAccessible(true);
					Object txtField = getTextFieldValue(in.name());
					f.set(vo, parseToType(txtField, f.getType()));
				}
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	protected void clear() {
		clearAllTextFields(getContentPane());
	}
	
	private void parseFields() throws Exception {
		Field[] fields = this.classe.getDeclaredFields();

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

	private void buildButtons() {
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
	
	private Object getTextFieldValueOnPane(String tf, Container pane) {

		for (int i = 0; i < pane.getComponentCount(); i++) {
			Component c = pane.getComponent(i);

			if (c instanceof JTextField) {
				if (((JTextField) c).getName().equals(tf))
					return ((JTextField) c).getText();
			}

			else if (c instanceof Container) {
				Object result = getTextFieldValueOnPane(tf, (Container) c); 
				if (result != null)
					return result;
			}
		}

		return null;		
	}

	private Object getTextFieldValue(String txtName) {
		Object result = getTextFieldValueOnPane(txtName, getContentPane());
		return result;
	}

	private Object parseToType(Object object, Class<?> type) {
		System.out.println(" >> parsing : ");
		if (type.equals(java.lang.String.class)) {
			System.out.println(" string << ");
			return (String) object;
		}

		if (type.equals(java.lang.Integer.class)) {
			System.out.println(" integer << ");
			return Integer.parseInt((String)object);
		}

		if (type.equals(java.lang.Double.class)) {
			System.out.println(" double << ");			
			return Double.parseDouble((String) object);
		}

		return object;
	}

}
