package pl.mkm.service;

import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientService {

    public  void saveClient(Client client, ClientStore clientStore, File clientFile) {
        clientStore.addClient(client);
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

    public Client createNewClient() {
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
        cin.close();
        return client;
    }

    public ClientStore loadClientStore(File clientFile) {
        ClientStore clientStore = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ClientStore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            clientStore = (ClientStore) unmarshaller.unmarshal(clientFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return clientStore;
    }
}
