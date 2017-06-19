package be.benabdelali.services;

import be.benabdelali.model.Book;
import be.benabdelali.model.Status;
import be.benabdelali.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class BookServiceImp implements BookService {


    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        if (!getBookByName(book.getNameBook()).isEmpty()) {
            return null;
        } else {
            if (book.getQuantity() > 0) {
                book.setStatus(Status.AVAILAIBLE);
            } else {
                book.setStatus(Status.OUT_OF_STOCK);
            }
        }

        return bookRepository.save(book);
    }

    @Override
    public Long deleteBook(Long id) {

        Book foundBook = getBookById(id);

        if (foundBook != null) {
            bookRepository.delete(id);
            return id;
        }
        return null;
    }

    @Override
    public Book updateBook(Long id, Book book) {

        if (book.getQuantity() > 0) {
            book.setStatus(Status.AVAILAIBLE);
        } else {
            book.setStatus(Status.OUT_OF_STOCK);
        }
        int var = bookRepository.updateBook(book.getNameBook(), book.getPrice(), book.getQuantity(),
                book.getStatus(), book.getQuantitySold(), id);
        if (var != 0) {
            return bookRepository.findOne(id);
        }
        return null;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {

        if (null != bookRepository.findOne(id)) {
            return bookRepository.findOne(id);
        }
        return null;
    }

    @Override
    public List<Book> getAvailaibleBooks() {
        return bookRepository.findAvailaibleBook();
    }


    @Override
    public Book getBestBookBought() {

        List<Book> list = bookRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            Collections.sort(list, Comparator.comparing((Book c) -> c.getQuantitySold()));
        }
        return list.get(list.size() - 1);
    }

    @Override
    public long getSumTotalBook() {
        return bookRepository.getSumTotalBook();
    }

    @Override
    public List<Book> getBookByName(String nameBook) {
        List<Book> listBooks = bookRepository.findByNameBook(nameBook);
        return listBooks;
    }
}
