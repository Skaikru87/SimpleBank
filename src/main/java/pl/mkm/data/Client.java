package pl.mkm.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@XmlRootElement(name="Client")
public class Client {

private String firstName;
private String LastName;
private String login;
private String password;
private Date birthDate;
private boolean rights;

    public Client(String firstName, String lastName, String login, String password, Date birthDate, boolean rights) {
        this.firstName = firstName;
        LastName = lastName;
        this.login = login;
        this.password = password;
        this.birthDate = birthDate;
        this.rights = rights;
    }
}
