package pl.mkm.main;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.ClientStore;
import pl.mkm.service.AccountService;
import pl.mkm.service.ClientService;


public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();


        System.out.println("Welcome at Simple Bank Application");

        ClientStore clientStore = clientService.loadClientStore();
//        Client client = clientService.createNewClient();
//        clientService.saveClient(client, clientStore);

        AccountStore accountStore = accountService.loadAccountStore();
        Account account = accountService.createNewAccount(clientStore);
        accountService.saveAccount(account, accountStore);


    }


}
