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
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.vo.ProdutoVO;
import view.vo.UserVO;
import view.vo.VO;

@SuppressWarnings("serial")
public class Form<T extends VO> extends JDialog {

	private final JPanel contentPanel;
	protected List<JTextField> fields;
	protected JButton saveButton;
	protected JButton cancelButton;

	public Form(String title) {
		setTitle(title);

		// INICIO >> PANEL PRINCIPAL
		{
			// >> ABSTRAIR
			{
				setResizable(false);
				contentPanel = new JPanel();
				contentPanel.setBackground(Color.DARK_GRAY);
				setBounds(100, 100, 400, 250);
				getContentPane().setLayout(new BorderLayout());
				contentPanel.setLayout(new FlowLayout());
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getContentPane().add(contentPanel, BorderLayout.CENTER);
			}

			
			
			// INICIO >> PANEL DESCRICAO
			{
				JPanel pDescricao = new JPanel();
				pDescricao.setBackground(Color.DARK_GRAY);
				pDescricao.setPreferredSize(new Dimension(400, 40));
				pDescricao.setLayout(new FlowLayout(FlowLayout.RIGHT));
				contentPanel.add(pDescricao);
				// INICIO >> LABEL
				{
					JLabel lblDescricao = new JLabel("Descricao");
					lblDescricao.setForeground(Color.WHITE);
					pDescricao.add(lblDescricao);
				}
				// FIM << LABEL

				// INICIO >> TEXTFIELD
				{
					tfDescricao = new JTextField();
					tfDescricao.setColumns(25);
					pDescricao.add(tfDescricao);
				}
				// FIM << TEXTFIELD
			}
			// FIM << PANEL DESCRICAO
		}
		// FIM << PANEL PRINCIPAL

		// INICIO >> PANEL INFERIOR / BOTOES
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// INICIO >> BOTAO SALVAR
			{
				saveButton = new JButton("SALVAR");
				saveButton.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			// FIM << BOTAO SALVAR

			// INICIO >> BOTAO CANCELAR
			{
				cancelButton = new JButton("CANCELAR");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

				cancelButton.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));
				buttonPane.add(cancelButton);
			}
			// FIM << BOTAO CANCELAR

		}
		// FIM << PANEL INFERIOR / BOTOES

	}

	protected void clear() {
		clearAllTextFields(getContentPane());
		this.fields.get(0).requestFocus();
	}

	private void clearAllTextFields(Container pane) {
		for (int i = 0; i < pane.getComponentCount(); i++) {
			Component c = pane.getComponent(i);

			if (c instanceof JTextField)
				((JTextField) c).setText(null);

			else if (c instanceof Container)
				clearAllTextFields((Container) c);
		}
	}

	protected ProdutoVO getVO() throws Exception {
		UserVO vo = new UserVO();

		vo.setDescricao(new String(this.tfDescricao.getText()));
		vo.setQuantidade(new Integer(this.tfQuantidade.getText()));
		vo.setValor(new BigDecimal(this.tfValor.getText()));

		return vo;
	}

	protected ProdutoVO atualizaVO(UserVO vo) throws Exception {
		ProdutoVO alterado = getVO();
		alterado.setId(vo.getId());
		return alterado;
	}

	protected void atualizaTextFieldsComVO(UserVO vo) {
		this.tfDescricao.setText(vo.getDescricao());
		this.tfQuantidade.setText(vo.getQuantidade().toString());
		this.tfValor.setText(vo.getValor().toString());
	}
}