package bancobackend.service;

import bancobackend.model.Client;

import java.util.List;

public interface ClientService {

    public void saveClient(Client client);

    public List<Client> getClients();

    public Client getClient(String idClient);

    public void deleteClient(String idClient);

}
