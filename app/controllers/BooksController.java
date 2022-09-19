package controllers;

import java.util.Set;

import javax.inject.Inject;

import akka.http.impl.util.JavaMapping.AsScala;
import models.Book;
import play.i18n.MessagesApi;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import views.html.book.*;
import views.html.helper.form;

public class BooksController extends Controller{
	private final Form<BookData> bookForm;
	private MessagesApi messagesApi;
	private FormFactory formFactory;
	
	@Inject
	public BooksController(FormFactory formFactory, MessagesApi messagesApi) {
		this.bookForm = formFactory.form(BookData.class);
		this.messagesApi = messagesApi;
	}
	
	public Result index() {
		Set<Book> books = Book.allBooks();
		return ok(index.render(books));
	}
	
	public Result create(Http.Request request) {
		return ok(create.render(bookForm, messagesApi.preferred(request)));
	}
	
	public Result save(Http.Request request) {
		Form<BookData> boundForm = bookForm.bindFromRequest(request);
		if(boundForm.hasErrors()) {
			return badRequest(create.render(boundForm, messagesApi.preferred(request)));
		}else {
			BookData book = boundForm.get();
			Book.addBook(new Book(book.getId(), book.getTitle(), book.getPrice(), book.getAuthor()));
			return redirect(routes.BooksController.index());
		}	
	}
	
	public Result edit(Http.Request request, Integer id) {
		Book book = Book.findById(id);
		Form<BookData> form = bookForm.fill(new BookData(book.id, book.title, book.price, book.author));
		return ok(edit.render(form, messagesApi.preferred(request)));
	}
	
	public Result update(Http.Request request) {
		Form<BookData> boundForm = bookForm.bindFromRequest(request);
		if(boundForm.hasErrors()) {
			return badRequest(edit.render(boundForm, messagesApi.preferred(request)));
		}else {
			BookData bookData = boundForm.get();
			Book oldBook = Book.findById(bookData.getId());
			if(oldBook == null) {
				return notFound("Book not found");
			}
			oldBook.title = bookData.getTitle();
			oldBook.price = bookData.getPrice();
			oldBook.author = bookData.getAuthor();
			return redirect(routes.BooksController.index());
		}
		
	}
	
	public Result show(Http.Request request, Integer id) {
		Book book = Book.findById(id);
		if(book == null) {
			return notFound("Book not found");
		}
		return ok(show.render(book));
	}
	
	public Result destroy(Http.Request request, Integer id) {
		Book book = Book.findById(id);
		if(book == null) {
			return notFound("Book not found");
		}
		Book.removeBook(book);
		return redirect(routes.BooksController.index());
	}
	
}
