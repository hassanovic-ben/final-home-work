package be.benabdelali.services;

import be.benabdelali.model.Book;

import java.util.List;


public interface BookService {

    Book createBook(Book book);

    Long deleteBook(Long id);


    Book updateBook(Long id, Book book);

    List<Book> getBooks();

    Book getBookById(Long id);

    List<Book> getAvailaibleBooks();

    Book getBestBookBought();

    long getSumTotalBook();

    List<Book> getBookByName(String nameBook);



}
