package pl.mkm.main;

import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;
import pl.mkm.service.ClientService;
import java.io.File;


public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        File clientFile = new File("clientFile.xml");
        File accountFile = new File("accountFile.xml");

        System.out.println("Welcome at Simple Bank Application");
        ClientStore clientStore = clientService.loadClientStore(clientFile);
        Client client = clientService.createNewClient();
        clientService.saveClient(client, clientStore, clientFile);


    }


}
