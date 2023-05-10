package bancobackend.controller;

import bancobackend.model.Client;
import bancobackend.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/registrarCliente")
    public String registrarCliente(@RequestBody Client client) {
        for (int i = 0; i < clientService.getClients().size(); i++) {
            if (client.getIdClient().equals(clientService.getClients().get(i).getIdClient())) {
                return "El cliente ya esta registrado";
            }
        }
        client.setScoreCredit((int) (Math.random() * 950) + 1);
        clientService.saveClient(client);
        return  "Cliente registrado con exito.";
    }

}
