package be.benabdelali.services;

import be.benabdelali.model.Client;

import java.util.List;


public interface ClientService {

    Client createClient(Client c);

    void deleteClient(Client c);

    Client updateClient(Client client, Long idClient);

    List<Client> getClients();

    Client getById(Long id);

    List<Client> getClientsByUserName(String userName);

    List<Client> findBestClient(String type);

}
