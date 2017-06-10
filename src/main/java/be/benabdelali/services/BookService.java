package be.benabdelali.services;

import be.benabdelali.model.Book;

import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */
public interface BookService {

    Book createBook(Book book);

    Long deleteBook(Long id);

    Book updateBook(Long id, Book book);

    List<Book> getBooks();

    Book getBookById(Long id);

    List<Book> getAvailaibleBooks();

    Book getBookByName(String nameBook);
}
