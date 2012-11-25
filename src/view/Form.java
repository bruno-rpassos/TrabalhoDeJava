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

	protected JButton		saveButton;
	private JPanel			buttonPane;

	private final Class<T>	classe;
	private boolean			didRequestedFocus;

	protected Form( final Class<T> entityClass, final String title ) throws TypeNotFoundException {
		super( title );
		this.classe = entityClass;
		this.didRequestedFocus = false;

		this.parseFields();
		this.buildButtons();
	}

	public void addSaveButton() {
		this.saveButton = new JButton( "SALVAR" );
		this.saveButton.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		this.buttonPane.add( this.saveButton );
		this.getRootPane().setDefaultButton( this.saveButton );
	}

	protected void addButton( final JButton button ) {
		button.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		this.buttonPane.add( button );
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

	protected void updateTextFieldsWithEntity( final T entity ) {
		try {
			for ( final Field f : this.classe.getDeclaredFields() ) {
				if ( f.isAnnotationPresent( Input.class ) ) {
					final Input in = f.getAnnotation( Input.class );

					f.setAccessible( true );
					final JTextField txtField = this.getTextField( in.name() );
					System.out.println( in.name() + " value " + f.get( entity ) );
					txtField.setText( String.valueOf( f.get( entity ) ) );
				}
			}
		} catch ( final IllegalAccessException e ) {
			e.printStackTrace();
		}
	}

	private void addCancelButton() {
		final JButton cancelButton = new JButton( "CANCELAR" );
		cancelButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( final ActionEvent arg0 ) {
				Form.this.dispose();
			}
		} );

		cancelButton.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
		this.buttonPane.add( cancelButton );
	}

	private void buildButtons() {
		this.buttonPane = new JPanel();
		this.buttonPane.setBackground( Color.DARK_GRAY );
		this.buttonPane.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		this.getContentPane().add( this.buttonPane, BorderLayout.SOUTH );

		this.addSaveButton();
		this.addCancelButton();
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

	private JTextField getTextField( final String txtName ) {
		final JTextField result = this.getTextFieldOnPane( txtName, this.getContentPane() );
		return result;
	}

	private JTextField getTextFieldOnPane( final String tf, final Container pane ) {

		for ( int i = 0; i < pane.getComponentCount(); i++ ) {
			final Component c = pane.getComponent( i );

			if ( c instanceof JTextField ) {
				if ( ( ( JTextField ) c ).getName().equals( tf ) ) return ( JTextField ) c;
			}

			else if ( c instanceof Container ) {
				final JTextField result = this.getTextFieldOnPane( tf, ( Container ) c );
				if ( result != null ) return result;
			}
		}

		return null;
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
