package controllers;
import play.data.validation.Constraints;

public class BookData {

	@Constraints.Required
	private Integer id;
	
	@Constraints.Required
	private String title;

	@Constraints.Required
	private double price;

	@Constraints.Required
	private String author;

	public BookData() {
		
	}
	

	public BookData(Integer id, String title, double price, String author) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
