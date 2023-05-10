package bancobackend.controller;

import bancobackend.model.Client;
import bancobackend.model.Loan;
import bancobackend.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/banco")
public class BankController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/validarDatacredito/{idClient}")
    public String validarDatacredito(@PathVariable String idClient) throws IOException {
        Client client = null;
        for (int i = 0; i < clientService.getClients().size(); i++) {
            if (idClient.equals(clientService.getClients().get(i).getIdClient())) {
                client = clientService.getClients().get(i);
                i = clientService.getClients().size();
            }
        }

        if (client == null) {
            return "El ciente con id " + idClient + " no esta registrado.";
        }

        StringBuilder resultado = new StringBuilder();
        String direccion = "http://localhost:8081/datacredito/consultaDatacredito/" + client.getScoreCredit();
        URL url = new URL(direccion);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();
        return resultado.toString();
    }

    @PostMapping("/nuevoPrestamo")
    public String nuevoPrestamo(@RequestBody Map<String, String> prestamo) throws IOException {
        Loan loan = new Loan();
        Client client = null;
        String respuesta = "";
        for (int i = 0; i < clientService.getClients().size(); i++) {
            if (prestamo.get("client").equals(clientService.getClients().get(i).getIdClient())){
                client = clientService.getClient(prestamo.get("client"));
                i = clientService.getClients().size();
            }
        }

        if (client == null) {
            return "El ciente con id " + prestamo.get("client") + " no esta registrado";
        }

        loan.setValueLoan(Integer.parseInt(prestamo.get("value")));
        loan.setIdClient(client.getIdClient());
        loan.setState("ACTIVO");

        String json = new ObjectMapper().writeValueAsString(loan);
        StringBuilder resultado = new StringBuilder();
        String direccion = "http://localhost:8081/prestamo/nuevoPrestamo/" + client.getScoreCredit();
        URL url = new URL(direccion);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "application/json");
        conexion.setDoOutput(true);

        OutputStream output = conexion.getOutputStream();
        output.write(json.getBytes("UTF-8"));
        output.close();

        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        rd.close();
        return resultado.toString();
    }

    @PostMapping("/validarCliente")
    public String validarCliente(@RequestBody Map<String, String> datosCliente) {
        String resultado = "";
        for (int i = 0; i < clientService.getClients().size(); i++) {
            if (clientService.getClients().get(i).getIdClient().equals(datosCliente.get("idCliente"))) {
                if (clientService.getClients().get(i).getIdClient().equals(datosCliente.get("idCliente")) && clientService.getClients().get(i).getPassword().equals(datosCliente.get("contraseña"))) {
                    i = clientService.getClients().size();
                    resultado = "Datos validos";
                }else {
                    resultado = "Datos no validos";
                }
                i = clientService.getClients().size();
            }else {
                resultado = "Cliente";
            }
        }
        return resultado;
    }

    @GetMapping("/mostrarClientes")
    public List<Client> mostrarClientes() {
        List<Client> clients = clientService.getClients();
        return clients;
    }

}
