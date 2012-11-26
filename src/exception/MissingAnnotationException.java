package exception;

@SuppressWarnings("serial")
public class MissingAnnotationException extends DefaultException {

	public MissingAnnotationException(String annotationName) {
		super("Annotation obrigatória não encontrada("+ annotationName +"");
	}
	
}
