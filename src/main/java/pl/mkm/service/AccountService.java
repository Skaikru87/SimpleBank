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
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountService {

    public static File accountFile = new File("accountFile.xml");

    public static void saveAccount(Account account, AccountStore accountStore) {
        accountStore.addAccount(account);
        saveToFile(accountStore);
    }

    public static void removeAccount(Account account) {
        AccountStore accountStore = loadAccountStore();
        List <Account> list = accountStore.getAccounts();
        List<Account> listWithRemovedAccount = list.stream()
                .filter(e -> e.getAccountNumber()!= account.getAccountNumber())
                .collect(Collectors.toList());
        accountStore.setAccounts(listWithRemovedAccount);
        saveToFile(accountStore);
    }

    public static void saveToFile(AccountStore accountStore) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AccountStore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(accountStore, accountFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Account createNewAccount(ClientStore clientStore) {
        Scanner cin = new Scanner(System.in);
        Account account = new Account();
        System.out.print("account number:");
        account.setAccountNumber(cin.nextInt());
        System.out.print("balance: ");
        account.setBalance(cin.nextDouble());
        System.out.println("Owner:");
        Client client = ClientService.createNewClient();
        ClientService.saveClient(client, clientStore);
        account.setOwner(client);
        return account;
    }

    public static AccountStore loadAccountStore() {

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

    public static void displayAccountStore() {
        AccountStore accountStore = loadAccountStore();
        List<Account> accounts = accountStore.getAccounts();
        accounts.stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .forEach(e -> System.out.println(e.getAccountNumber()
                        + " " + e.getBalance()
                        + " " + e.getOwner().getLastName()
                        + " " + e.getOwner().getFirstName()));
    }

    public static void transferMoney(Client client, AccountStore accountStore) {
        Account accountFrom = accountStore.getAccountByOwner(client);
        Scanner cin = new Scanner(System.in);
        System.out.println("Who do you want to transfer money?");
        System.out.print("account number: ");
        int accountNumberTo = cin.nextInt();
        Account accountTo = accountStore.getAccountByNumber(accountNumberTo);
        System.out.print("money: ");
        double moneyToTransfer = cin.nextDouble();

        removeAccount(accountFrom);
        removeAccount(accountTo);

        accountFrom.setBalance(accountFrom.getBalance() - moneyToTransfer);
        accountTo.setBalance(accountTo.getBalance() + moneyToTransfer);

        AccountStore accountStoreAfterRemoving = loadAccountStore();
        saveAccount(accountFrom, accountStoreAfterRemoving);
        saveAccount(accountTo, accountStoreAfterRemoving);
    }
}
