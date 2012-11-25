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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Entity;
import annotation.Input;
import exception.TypeNotFoundException;

@SuppressWarnings( "serial" )
public class Form<T extends Entity> extends DialogPadrao {

	protected JButton		cancelButton;
	protected JButton		saveButton;

	private final Class<T>	classe;
	private boolean			didRequestedFocus;

	protected Form( final Class<T> entityClass, final String title ) throws TypeNotFoundException {
		super( title );
		this.classe = entityClass;
		this.didRequestedFocus = false;

		this.parseFields();
		this.buildButtons();
	}

	protected void clear() {
		this.clearAllTextFields( this.getContentPane() );
	}

	protected T generateEntity() {
		T entity = null;
		try {
			entity = this.classe.newInstance();
			for ( final Field f : this.classe.getDeclaredFields() ) {
				if ( f.isAnnotationPresent( Input.class ) ) {
					final Input in = f.getAnnotation( Input.class );

					f.setAccessible( true );
					final Object txtField = this.getTextFieldValue( in.name() );
					f.set( entity, this.parseToType( txtField, f.getType() ) );
				}
			}
		} catch ( final InstantiationException e ) {
			e.printStackTrace();
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
		return entity;
	}

	protected T parseEntity() throws Exception {
		return this.generateEntity();
	}

	protected T update( final T entity ) throws Exception {
		final T alterado = this.parseEntity();
		alterado.setId( entity.getId() );
		return alterado;
	}

	protected void updateTextFieldsWithEntity( T entity ) {
		try {
			entity = this.classe.newInstance();
			for ( final Field f : this.classe.getDeclaredFields() ) {
				if ( f.isAnnotationPresent( Input.class ) ) {
					final Input in = f.getAnnotation( Input.class );

					f.setAccessible( true );
					final Object txtField = this.getTextFieldValue( in.name() );
					f.set( entity, this.parseToType( txtField, f.getType() ) );
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

	private void parseFields() throws TypeNotFoundException {
		final Field[] fields = this.classe.getDeclaredFields();

		for ( final Field f : fields ) {
			if ( f.isAnnotationPresent( Input.class ) ) {
				final Input in = f.getAnnotation( Input.class );

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
						final Component c = this.parseFieldType( in.type() );
						c.setName( in.name() );
						panel.add( c );
					}
				}
			}
		}
	}

	private Component parseFieldType( final String type ) throws TypeNotFoundException {
		if ( type.equals( "PASSWORD" ) ) return new JPasswordField( 25 );
		else if ( type.equals( "TEXTFIELD" ) ) return new JTextField( 25 );

		throw new TypeNotFoundException();
	}

	private Object parseToType( final Object object, final Class<?> type ) {
		if ( type.equals( java.lang.String.class ) ) return object;

		if ( type.equals( java.lang.Integer.class ) ) return Integer.parseInt( ( String ) object );

		if ( type.equals( java.lang.Double.class ) ) return Double.parseDouble( ( String ) object );

		return object;
	}

}
