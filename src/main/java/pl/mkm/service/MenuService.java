package pl.mkm.service;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;

import java.util.Scanner;

public class MenuService {

    public static void adminMenu(ClientService clientService, AccountService accountService, ClientStore clientStore, AccountStore accountStore) {
        Scanner in = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    Client client = clientService.createNewClient();
                    clientService.saveClient(client, clientStore);
                    System.out.println("what you want to do?");
                    break;
                case 2:
                    Account account = accountService.createNewAccount(clientStore);
                    accountService.saveAccount(account, accountStore);
                    System.out.println("what you want to do?");
                    break;
                case 3:
                    System.out.println("what you want to do?");
                    break;
                case 4:
                    System.out.println("what you want to do?");
                    break;
                case 5:
                    exit = false;
                    break;
                default:
                    System.out.println("wrong choice");
                    System.out.println("what you want to do?");
                    break;
            }

        }
    }


}
