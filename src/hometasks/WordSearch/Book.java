package hometasks.WordSearch;

public class Book extends AuthoredText {
	private String publisher;
	private String annotation;
	
	public Book() {
		super();
		this.publisher = "";
		this.annotation = "";
	}
	public Book(String title, String text, String[] authors, String publisher, String annotation) throws NullPointerException {
		super(title, text, authors);
		if (publisher != null) {
			this.publisher = publisher;
		}
		if (annotation != null) {
			this.annotation = annotation;
		}
	}
	public String getPublisher() {
		return this.publisher;
	}
	public void setPublisher(String publisher) {
		if (publisher != null) {
			this.publisher = publisher;
		}
	}
	public String getAnnotation() {
		return this.annotation;
	}
	public void setAnnotation(String annotation) {
		if (annotation != null) {
			this.annotation = annotation;
		}
	}
	
	
	

}
 