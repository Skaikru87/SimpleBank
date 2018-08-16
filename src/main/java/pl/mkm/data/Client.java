package pl.mkm.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement(name="Client")
public class Client {

private String firstName;
private String LastName;
private String login;
private String password;
private Date birthDate;
private String rights;



}
