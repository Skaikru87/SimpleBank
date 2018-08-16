package pl.mkm.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class AccountStore {

    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account){
        accounts.add(account);
    }

    @XmlElements(@XmlElement(name = "accounts"))
    public List<Account> getAccounts() {
        return accounts;
    }
}
