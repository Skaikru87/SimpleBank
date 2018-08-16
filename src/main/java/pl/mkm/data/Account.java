package pl.mkm.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "account")
public class Account {

    private int accountNumber;
    private double balance;
    private Client owner;


}
