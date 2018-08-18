package pl.mkm.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "accounts")
@NoArgsConstructor
@Data
public class AccountStore {

    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountByOwner(Client client) {
        Account account = null;
        for (Account e : accounts) {
            if (e.getOwner().getLogin().equals(client.getLogin()) && e.getOwner().getPassword().equals(client.getPassword())) {
                account = e;
            }
        }
        return account;
    }

    public Account getAccountByNumber(int accountNumber) {
        List<Account> list = accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .collect(Collectors.toList());
        if (list.size() != 1) {
            throw new IllegalArgumentException();
        }
        return list.get(0);
    }

    @XmlElements(@XmlElement(name = "account"))
    public List<Account> getAccounts() {
        return accounts;
    }
}
