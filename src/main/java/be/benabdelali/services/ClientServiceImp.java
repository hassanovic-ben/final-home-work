package be.benabdelali.services;

import be.benabdelali.model.Client;
import be.benabdelali.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client c) {
        return clientRepository.save(c);
    }

    @Override
    public void deleteClient(Client c) {
        clientRepository.delete(c);
    }


    @Override
    public Client updateClient(Client client, Long idClient) {

        int var = clientRepository.updateClient(client.getName(), client.getUserName(), client.getPassword(), client.getTimeLoggin()
                , client.getAddress(), client.getEmail(), client.getNbrBookBought(), idClient);
        if (var != 0) {
            Client c = clientRepository.findOne(idClient);
            return c;
        }
        return null;
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

    @Override
    public List<Client> findBestClient(String type) {
        return clientRepository.findByTypeOrderByNbrBookBoughtDesc("client");
    }

}
