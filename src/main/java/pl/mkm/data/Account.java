package pl.mkm.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@XmlRootElement(name = "account")
public class Account {

    private int accountNumber;
    private double balance;
    private Client owner;


}
