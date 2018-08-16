package pl.mkm.data;

import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="accounts")
@NoArgsConstructor
public class AccountStore {

    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account){
        accounts.add(account);
    }

    @XmlElements(@XmlElement(name = "account"))
    public List<Account> getAccounts() {
        return accounts;
    }
}
