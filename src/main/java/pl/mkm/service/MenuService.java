package pl.mkm.service;

import pl.mkm.data.Account;
import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;

import java.util.List;
import java.util.Scanner;

public class MenuService {

    public static Client loggingClient(ClientStore clientStore) {
        Client client = null;
        Scanner cin = new Scanner(System.in);
        System.out.print("login: ");
        String login = cin.nextLine();
        System.out.print("password: ");
        String password = cin.nextLine();
        if (clientStore.getClients()
                .stream()
                .anyMatch(e -> (e.getLogin().equals(login) && e.getPassword().equals(password)))) {
            List<Client> clients = clientStore.getClients();

            for (Client e : clients) {
                if (e.getLogin().equals(login) && e.getPassword().equals(password)) {
                    client = e;
                }
            }
            System.out.println("Hello " + client.getFirstName() + " " + client.getLastName());
        } else {
            System.out.println("Wrong login or password \n" +
                    "try again!\n" +
                    "Or contact with Admin");
        }
        return client;
    }

    public static void adminMenu(ClientStore clientStore, AccountStore accountStore) {
        System.out.println("what you want to do?\n" +
                "1-add new client\n" +
                "2-add new account\n" +
                "3-display clients\n" +
                "4-display accounts\n" +
                "5-exit");
        Scanner in = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    Client client = ClientService.createNewClient();
                    ClientService.saveClient(client, clientStore);
                    System.out.println("what you want to do?");
                    break;
                case 2:
                    Account account = AccountService.createNewAccount(clientStore);
                    AccountService.saveAccount(account, accountStore);
                    System.out.println("what you want to do?");
                    break;
                case 3:
                    ClientService.displayClientStore();
                    System.out.println("what you want to do?");
                    break;
                case 4:
                    AccountService.displayAccountStore();
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

    public static void userMenu(ClientStore clientStore, AccountStore accountStore, Client client) {
        System.out.println("what you want to do?\n" +
                "1-display clients\n" +
                "2-display accounts\n" +
                "3-transfer money\n" +
                "4-exit");
        Scanner in = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    ClientService.displayClientStore();
                    System.out.println("what you want to do?");
                    break;
                case 2:
                    AccountService.displayAccountStore();
                    System.out.println("what you want to do?");
                    break;
                case 3:
                    AccountService.transferMoney(client, accountStore);
                    System.out.println("what you want to do?");
                    break;
                case  4:
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
