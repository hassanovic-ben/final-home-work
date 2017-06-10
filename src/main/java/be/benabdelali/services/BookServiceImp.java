package be.benabdelali.services;

import be.benabdelali.model.Book;
import be.benabdelali.model.Status;
import be.benabdelali.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */
@Service
@Transactional
public class BookServiceImp implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        if(book.getQuantity()<0){
            return null;
        }
        else {
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
        Book foundBook = getBookById(id);
        if (foundBook != null) {
            if(book.getQuantity()<0){
                return null;
            }
            else{
                if (book.getQuantity()==0){
                    book.setStatus(Status.OUT_OF_STOCK);
                }
                else{
                    book.setStatus(Status.AVAILAIBLE);
                }
            }
            Book b = bookRepository.saveAndFlush(book);
            return b;
        }

        return null;
    }

    @Override
    public List<Book> getBooks() {

        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {

        if(null!=bookRepository.findOne(id)){
            return bookRepository.findOne(id);
        }
        return null;
    }

    @Override
    public List<Book> getAvailaibleBooks() {
        return bookRepository.findAvailaibleBook();
    }

    @Override
    public Book getBookByName(String nameBook) {
        List<Book> listBooks = bookRepository.findByNameBook(nameBook);
        return listBooks.get(0);
    }
}
