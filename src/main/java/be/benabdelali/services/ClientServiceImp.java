package be.benabdelali.services;

import be.benabdelali.model.Admin;
import be.benabdelali.model.Client;
import be.benabdelali.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hassan on 4/06/2017.
 */


@Service
@Transactional
public class ClientServiceImp implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client c) {
        return clientRepository.saveAndFlush(c);
    }

    @Override
    public void deleteClient(Client c) {
        clientRepository.delete(c);
    }

    @Override
    public Client updateClient(Client client, Long idClient) {
        if(clientRepository.findByUserName(client.getUserName()).size()!=0){
            System.out.println("coocococo");
            return null;
        }
        int numberRowUpdated = clientRepository.updateClient(client.getName(), client.getUserName(), client.getPassword(), client.getAddress(), client.getEmail(), idClient);
        if (0 != numberRowUpdated) {
            return null;
            }

        Client clientUpdated = clientRepository.findOne(client.getIdUser());
        return clientUpdated;

    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public List<Client> getClientsByUserName(String userName) {
        return clientRepository.findByUserName(userName);
    }


}
