package pl.mkm.main;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;
import pl.mkm.service.AccountService;
import pl.mkm.service.ClientService;

import java.io.File;


public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();

        File clientFile = new File("clientFile.xml");
        File accountFile = new File("accountFile.xml");

        System.out.println("Welcome at Simple Bank Application");

//        ClientStore clientStore = clientService.loadClientStore(clientFile);
//        Client client = clientService.createNewClient();
//        clientService.saveClient(client, clientStore, clientFile);

        AccountStore accountStore = accountService.loadAccountStore(accountFile);
        Account account = accountService.createNewAccount();
        accountService.saveAccount(account, accountStore, accountFile);


    }


}
