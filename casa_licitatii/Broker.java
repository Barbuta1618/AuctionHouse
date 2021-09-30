package casa_licitatii;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class Broker extends Employee{
	
	public static final class BrokerAccess{
		private BrokerAccess(){}
	}
	
	private Integer id;
	private static BrokerAccess brokerAccess = new BrokerAccess();
	private AuctionHouse auctionHouse;
	private Hashtable<Integer, Vector<Client>> clientsTable ;
	
	public Broker(AuctionHouse auctionHouse) {
		clientsTable = new Hashtable<Integer, Vector<Client>>();
		this.auctionHouse = auctionHouse;
	}
	
	public boolean containsClients(Integer productId) {
		for(Integer pId : clientsTable.keySet()) {
			if(pId == productId) {
				return true;
			}
		}
		return false;
	}
	
	public void addClient(Client client, Integer productId) {
		
		Vector<Client> clients = clientsTable.get(productId);
		
		if(clients == null) {
			clients = new Vector<Client>();
			
		}
		clients.add(client);
		
		clientsTable.put(productId, clients);
			
	}
	
	//brokerul face o lista cu noile preturi 
	//pe care o va trimite ulterior casei de licitatii
	public Hashtable<Integer, Double> getNewPrices(Integer productId, Double max_price) {
		
		Vector<Client> clients = clientsTable.get(productId);
	
		Hashtable<Integer, Double> newPrices = new Hashtable<Integer, Double>();
		for(Client client : clients) {
			
			//se ia fiecare client in parte si i se cere noul pret
			Double newPrice = client.getPrice(productId, max_price);
			Integer clientId = client.getId();
			
			//daca clientul anuntat nu este un participant la licitatia curenta
			//acesta va returna null
			if(newPrice != null) {
				
				//se pun in hashtable noile valori 
				newPrices.put(clientId, newPrice);
			}
			
			//System.out.println(newPrices);
		}
		
		return newPrices;
	}

	public void notifyClients(Integer auctionId, Double max_price, Integer winnerId, Integer productId) {
		
		Vector<Client> clients = clientsTable.get(productId);
		Double clientWon = null;
		
		//se anunta clientii iar cel care este castigator
		//returneaza suma pe care o datoreaza brokerului
		for(Client client : clients) {
			clientWon = client.notifyWinner(auctionId, max_price, winnerId);
		}
		
		//brokerul a carui client este castigator
		//vinde produsul
		if(clientWon != null) {
			auctionHouse.sellProduct(brokerAccess, productId);
		}
		
		clientsTable.remove(productId);
	}
	
}
