package be.benabdelali.services;

import be.benabdelali.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */

public interface ClientService {

    Client createClient(Client c);

    void deleteClient(Client c);

    Client updateClient(Client client, Long idClient);

    List<Client> getClients();

    Client getById(Long id);

    List<Client> getClientsByUserName(String userName);
}
