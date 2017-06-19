package be.benabdelali.services;

import be.benabdelali.model.ClientBook;
import be.benabdelali.repositories.ClientBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ClientBookServiceImp implements ClientBookService {

    @Autowired
    private ClientBookRepository clientBookRepository;

    @Override
    public ClientBook createClientBook(ClientBook clientBook) {
        return clientBookRepository.save(clientBook);
    }

    @Override
    public Long delete(Long id) {

        ClientBook sale = clientBookRepository.findOne(id);
        if(null==sale){
            return null;
        }
        clientBookRepository.delete(id);
        return id;

    }

    @Override
    public List<ClientBook> listClientBook() {
        return clientBookRepository.findAll();
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
