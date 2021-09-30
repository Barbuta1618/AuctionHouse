package casa_licitatii;

import java.util.Vector;

public class ClientsList {
	
	private Vector<Client> clients;
	
	public ClientsList() {
		clients = new Vector<>();
	}
	
	public void addClient(Client client) {
		clients.add(client);
	}
	
}
