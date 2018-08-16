package pl.mkm.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ClientStore {

    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client){
        clients.add(client);
    }

    @XmlElements(@XmlElement(name="clients"))
    public List<Client> getClients() {
        return clients;
    }
}
