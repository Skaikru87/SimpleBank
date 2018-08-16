package pl.mkm.main;

import pl.mkm.data.AccountStore;
import pl.mkm.data.Client;
import pl.mkm.data.ClientStore;
import pl.mkm.service.AccountService;
import pl.mkm.service.ClientService;
import pl.mkm.service.MenuService;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome at Simple Bank Application");

        ClientStore clientStore = ClientService.loadClientStore();
        AccountStore accountStore = AccountService.loadAccountStore();

        Client client = MenuService.loggingClient(clientStore);

        if (client.isRights()) {
            MenuService.adminMenu(clientStore, accountStore);
        } else {
            MenuService.userMenu(clientStore, accountStore);
        }

    }
}
