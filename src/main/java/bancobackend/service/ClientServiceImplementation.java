package bancobackend.service;

import bancobackend.model.Client;
import bancobackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(String idClient) {
        return clientRepository.findById(idClient).get();
    }

    @Override
    public void deleteClient(String idClient) {
        clientRepository.deleteById(idClient);
    }
}
