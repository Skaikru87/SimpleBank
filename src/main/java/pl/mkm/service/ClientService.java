package pl.mkm.service;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientService {

    private static File clientFile = new File("clientFile.xml");

    public static void saveClient(Client client, ClientStore clientStore) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClientStore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            if (clientStore != null) {
                clientStore.addClient(client);
            }
            marshaller.marshal(clientStore, clientFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Client createNewClient() {
        Client client = new Client();
        Scanner cin = new Scanner(System.in);
        System.out.print("first name: ");
        client.setFirstName(cin.nextLine());
        System.out.print("last name: ");
        client.setLastName(cin.nextLine());
        System.out.print("login: ");
        client.setLogin(cin.nextLine());
        System.out.print("password: ");
        client.setPassword(cin.nextLine());
        System.out.print("birth date(in format YYYY-MM-DD):");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = cin.nextLine();
        Date formattedDate = null;
        try {
            formattedDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        client.setBirthDate(formattedDate);
        client.setRights(false);
        return client;
    }

    public static ClientStore loadClientStore() {
        if (!clientFile.exists()) {
            try {
                clientFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ClientStore clientStore = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClientStore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            clientStore = (ClientStore) unmarshaller.unmarshal(clientFile);
        } catch (JAXBException e) {
            System.out.println("No clients yet");
        }
        if (clientStore == null) {
            clientStore = new ClientStore();
        }
        return clientStore;
    }

    public static void displayClientStore() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ClientStore clientStore = loadClientStore();
        List<Client> clients = clientStore.getClients();
        clients.stream()
                .sorted(Comparator.comparing(Client::getLastName))
                .forEach(e -> System.out.println(e.getLastName()
                        + " " + e.getFirstName()
                        + " " + e.getLogin()
                        + " " + simpleDateFormat.format(e.getBirthDate())
                ));
    }


}
