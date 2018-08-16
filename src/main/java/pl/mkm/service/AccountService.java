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
import java.util.Scanner;

public class AccountService {

    public static File accountFile = new File("accountFile.xml");

    public void saveAccount(Account account, AccountStore accountStore) {
        accountStore.addAccount(account);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountStore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(accountStore, accountFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Account createNewAccount(ClientStore clientStore) {
        Scanner cin = new Scanner(System.in);
        Account account = new Account();
        System.out.print("account number:");
        account.setAccountNumber(cin.nextInt());
        System.out.print("balance: ");
        account.setBalance(cin.nextDouble());
        System.out.println("Owner:");
        ClientService clientService = new ClientService();
        Client client = clientService.createNewClient();
        clientService.saveClient(client, clientStore);
        account.setOwner(client);
        cin.close();
        return account;
    }

    public AccountStore loadAccountStore() {

        if (!accountFile.exists()) {
            try {
                accountFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AccountStore accountStore = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountStore.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            accountStore = (AccountStore) unmarshaller.unmarshal(accountFile);
        } catch (JAXBException e) {
            System.out.println("No accounts yet!!!");
        }
        if (accountStore == null) {
            accountStore = new AccountStore();
        }
        return accountStore;
    }


}
