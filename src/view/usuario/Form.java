package view.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.vo.UserVO;

@SuppressWarnings("serial")
public class Form extends JDialog {

	private final JPanel contentPanel;
	protected JTextField tfDescricao;
	protected JTextField tfQuantidade;
	protected JTextField tfValor;

	protected JButton saveButton;
	protected JButton cancelButton;

	public Form() {
		setTitle("USUARIO");
		setResizable(false);

		// INICIO >> PANEL PRINCIPAL
		{
			contentPanel = new JPanel();
			contentPanel.setBackground(Color.DARK_GRAY);
			setBounds(100, 100, 400, 250);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setLayout(new FlowLayout());
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			getContentPane().add(contentPanel, BorderLayout.CENTER);

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

			// INICIO >> PANEL QUANTIDADE
			{
				JPanel pQuantidade = new JPanel();
				pQuantidade.setBackground(Color.DARK_GRAY);
				pQuantidade.setPreferredSize(new Dimension(400, 40));
				pQuantidade.setLayout(new FlowLayout(FlowLayout.RIGHT));
				contentPanel.add(pQuantidade);
				// INICIO >> LABEL
				{
					JLabel lblQuantidade = new JLabel("Quantidade");
					lblQuantidade.setForeground(Color.WHITE);
					pQuantidade.add(lblQuantidade);
				}
				// FIM << LABEL

				// INICIO >> TEXTFIELD
				{
					tfQuantidade = new JTextField();
					tfQuantidade.setColumns(25);
					pQuantidade.add(tfQuantidade);
				}
				// FIM << TEXTFIELD
			}
			// FIM << PANEL QUANTIDADE

			// INICIO >> PANEL VALOR
			{
				JPanel pValor = new JPanel();
				pValor.setBackground(Color.DARK_GRAY);
				pValor.setPreferredSize(new Dimension(400, 40));
				pValor.setLayout(new FlowLayout(FlowLayout.RIGHT));
				contentPanel.add(pValor);
				// INICIO >> LABEL
				{
					JLabel lblValor = new JLabel("Valor");
					lblValor.setForeground(Color.WHITE);
					pValor.add(lblValor);
				}
				// FIM << LABEL

				// INICIO >> TEXTFIELD
				{
					tfValor = new JTextField();
					tfValor.setColumns(25);
					pValor.add(tfValor);
				}
				// FIM << TEXTFIELD

			}
			// FIM << PANEL VALOR

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
		this.tfDescricao.requestFocus();
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

	// PODE NAO HAVER NUMEROS NO CAMPO QUANTIDADE E VALOR
	protected UserVO getVO() throws Exception {
		UserVO vo = new UserVO();

		// vo.setDescricao(new String(this.tfDescricao.getText()));
		// vo.setQuantidade(new Integer(this.tfQuantidade.getText()));
		// vo.setValor(new BigDecimal(this.tfValor.getText()));

		return vo;
	}

	protected UserVO atualizaVO(UserVO vo) throws Exception {
		UserVO alterado = getVO();
		alterado.setId(vo.getId());
		return alterado;
	}

	protected void atualizaTextFieldsComVO(UserVO vo) {
		// this.tfDescricao.setText(vo.getDescricao());
		// this.tfQuantidade.setText(vo.getQuantidade().toString());
		// this.tfValor.setText(vo.getValor().toString());
	}
}
