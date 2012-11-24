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

@SuppressWarnings( "serial" )
public class Form<T extends VO> extends DialogPadrao {

	protected JButton		cancelButton;
	protected JButton		saveButton;

	private Integer			boundsBase;
	private final Class<T>	classe;
	private boolean			didRequestedFocus;

	protected Form( final Class<T> VOClass, final String title ) throws Exception {
		super( title );
		this.classe = VOClass;
		this.boundsBase = 200;

		this.parseFields();
		this.buildButtons();
		this.refreshBounds();
		this.didRequestedFocus = false;
	}

	protected void clear() {
		this.clearAllTextFields( this.getContentPane() );
	}

	protected T generateVO() {
		T vo = null;
		try {
			vo = this.classe.newInstance();
			for ( final Field f : this.classe.getDeclaredFields() ) {
				if ( f.isAnnotationPresent( Persistencia.class ) ) {
					final Persistencia in = f.getAnnotation( Persistencia.class );

					f.setAccessible( true );
					final Object txtField = this.getTextFieldValue( in.name() );
					f.set( vo, this.parseToType( txtField, f.getType() ) );
				}
			}
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
		return vo;
	}

	protected T parseVO() throws Exception {
		return this.generateVO();
	}

	protected T update( final T vo ) throws Exception {
		final T alterado = this.parseVO();
		alterado.setId( vo.getId() );
		return alterado;
	}

	protected void updateTextFieldsWithVO( T vo ) {
		try {
			vo = this.classe.newInstance();
			for ( final Field f : this.classe.getDeclaredFields() ) {
				if ( f.isAnnotationPresent( Persistencia.class ) ) {
					final Persistencia in = f.getAnnotation( Persistencia.class );

					f.setAccessible( true );
					final Object txtField = this.getTextFieldValue( in.name() );
					f.set( vo, this.parseToType( txtField, f.getType() ) );
				}
			}
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	private void addCancelButton( final JPanel buttonPane ) {
		this.cancelButton = new JButton( "CANCELAR" );
		this.cancelButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent e ) {
				Form.this.dispose();
			}
		} );

		this.cancelButton.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		buttonPane.add( this.cancelButton );
	}

	private void addSaveButton( final JPanel buttonPane ) {
		this.saveButton = new JButton( "SALVAR" );
		this.saveButton.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		buttonPane.add( this.saveButton );
		this.getRootPane().setDefaultButton( this.saveButton );
	}

	private void buildButtons() {
		final JPanel buttonPane = new JPanel();
		buttonPane.setBackground( Color.DARK_GRAY );
		buttonPane.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		this.getContentPane().add( buttonPane, BorderLayout.SOUTH );

		this.addSaveButton( buttonPane );
		this.addCancelButton( buttonPane );
	}

	private void clearAllTextFields( final Container pane ) {
		for ( int i = 0; i < pane.getComponentCount(); i++ ) {
			final Component c = pane.getComponent( i );

			if ( c instanceof JTextField ) {
				if ( !this.didRequestedFocus ) {
					( ( JTextField ) c ).requestFocus();
					this.didRequestedFocus = true;
				}

				( ( JTextField ) c ).setText( null );
			}

			else if ( c instanceof Container ) {
				this.clearAllTextFields( ( Container ) c );
			}
		}

		this.didRequestedFocus = false;
	}

	private Object getTextFieldValue( final String txtName ) {
		final Object result = this.getTextFieldValueOnPane( txtName, this.getContentPane() );
		return result;
	}

	private Object getTextFieldValueOnPane( final String tf, final Container pane ) {

		for ( int i = 0; i < pane.getComponentCount(); i++ ) {
			final Component c = pane.getComponent( i );

			if ( c instanceof JTextField ) {
				if ( ( ( JTextField ) c ).getName().equals( tf ) ) return ( ( JTextField ) c ).getText();
			}

			else if ( c instanceof Container ) {
				final Object result = this.getTextFieldValueOnPane( tf, ( Container ) c );
				if ( result != null ) return result;
			}
		}

		return null;
	}

	private void parseFields() throws Exception {
		final Field[] fields = this.classe.getDeclaredFields();

		for ( final Field f : fields ) {
			if ( f.isAnnotationPresent( Persistencia.class ) ) {
				final Persistencia in = f.getAnnotation( Persistencia.class );
				this.boundsBase += 50;

				{
					final JPanel panel = new JPanel();
					panel.setBackground( Color.DARK_GRAY );
					panel.setPreferredSize( new Dimension( 400, 40 ) );
					panel.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
					this.contentPanel.add( panel );

					{
						final JLabel label = new JLabel( in.label() );
						label.setForeground( Color.WHITE );
						panel.add( label );
					}

					{
						final JTextField textField = new JTextField( 25 );
						textField.setName( in.name() );
						panel.add( textField );
					}
				}
			}
		}
	}

	private Object parseToType( final Object object, final Class<?> type ) {
		if ( type.equals( java.lang.String.class ) ) return object;

		if ( type.equals( java.lang.Integer.class ) ) return Integer.parseInt( ( String ) object );

		if ( type.equals( java.lang.Double.class ) ) return Double.parseDouble( ( String ) object );

		return object;
	}

	private void refreshBounds() {
		super.setBounds( 100, 100, this.boundsBase, 250 );
	}

}
