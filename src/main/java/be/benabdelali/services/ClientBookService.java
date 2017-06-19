package be.benabdelali.services;

import be.benabdelali.model.ClientBook;

import java.util.List;

public interface ClientBookService {

    ClientBook createClientBook(ClientBook clientBook);

    Long delete(Long id);

    List<ClientBook> listClientBook();

}
