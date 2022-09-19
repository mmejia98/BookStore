package models;

import java.util.HashSet;
import java.util.Set;

public class Book{
	public Integer id;
	public String title;
	public double price;
	public String author;
	public Book(Integer id, String title, double price, String author) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
	}
	private static Set<Book> books;
	
	static {
		books = new HashSet<Book>();
		books.add(new Book(1, "C++", 50.50, "ABC"));
		books.add(new Book(2, "Java", 50.50, "ABC"));
		books.add(new Book(3, "C#", 50.50, "ABC"));
		books.add(new Book(4, "Python", 50.50, "ABC"));
		books.add(new Book(5, "Ruby", 50.50, "ABC"));
	}
	
	public static Set<Book> allBooks(){
		return books;
	}
	
	public static Book findById(Integer id) {
		for(Book book : books) {
			if(id.equals(book.id)) {
				return book;
			}
		}
		return null;
	}
	
	public static void addBook(Book book) {
		books.add(book);
	}
	
	public static boolean removeBook(Book book) {
		return books.remove(book);
	}
}
