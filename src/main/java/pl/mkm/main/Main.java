package pl.mkm.main;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;
import pl.mkm.service.AccountService;
import pl.mkm.service.ClientService;
import pl.mkm.service.MenuService;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        ClientService clientService = new ClientService();
        AccountService accountService = new AccountService();

        System.out.println("Welcome at Simple Bank Application");

        ClientStore clientStore = clientService.loadClientStore();
        AccountStore accountStore = accountService.loadAccountStore();

        System.out.println("what you want to do?\n" +
                "1-add new client\n" +
                "2-add new account\n" +
                "3-display clients\n" +
                "4-display accounts\n" +
                "5-exit");

        MenuService.adminMenu(clientService, accountService, clientStore, accountStore);


    }

}
