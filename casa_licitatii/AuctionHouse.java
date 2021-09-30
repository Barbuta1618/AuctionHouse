package casa_licitatii;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;


public class AuctionHouse {
	
	
	
	private ProductList products;
	private AuctionsList auctions;
	private ClientsList clients;
	
	//se va folosi patern ul MEDIATOR
	//intrucat comunicarea dintre
	//casa de licitatii si clienti
	//nu poate fi directa
	private Vector<Broker> brokers;
	
	public AuctionHouse() {
		products = new ProductList();
		auctions = new AuctionsList();
		clients = new ClientsList();
	}
	
	
	public void addBrokers(Integer nrBrokers) {
		brokers = new Vector<Broker>(nrBrokers);
		for(int i = 0; i < nrBrokers; i++) {
			brokers.add(new Broker(this));
		}
	}
	
	public void addClient(Client client ,Integer productId,  Double max_sum) {
		
		//clientul se adauga in lista clientilor din sistem
		clients.addClient(client);
		
		//se alege un broker aleator
		Integer brokerId = (int) (Math.random() * brokers.size());
		Broker broker = brokers.get(brokerId);
		
		
		//brokerul incepe se intermedieze
		//legatura dintre client si casa de licitatii
		broker.addClient(client, productId);
		
		//se incrementeaza nr de solicitari
		//pt produs
		products.incrementNrParticipants(productId);
		
		//se cauta licitatia in functie de produs
		Integer auctionId = auctions.getAuctionId(productId);
		
		//se inregistreaza suma maxima a clientului
		//in licitatia solicitata
		auctions.getAuction(auctionId).addClient(client.getId(), max_sum);
		
		client.addProduct(productId);
		//daca sunt suficienti clienti interesati de produs
		//se porneste licitatia
		if(products.getNrParticipants(productId) == auctions.getNrParticipants(productId)) {
			
			//licitatia este pornita intr-un fir de executie
			//nou
			Thread t = new Thread(() -> {
				try {
					startAuction(auctionId, productId);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			});
			t.start();
		}
	}
	
	public void startAuction(Integer auctionId, Integer productId) throws IOException{
		
		//se creeaza un fisier special pt licitatia curenta
		String fileName = "Licitatia" + auctionId + ".txt";
		FileWriter print = new FileWriter(fileName);
		
		//se creeaza o lista cu preturile clientilor
		Hashtable<Integer, Double> newPrices = new Hashtable<Integer, Double>();
		
		//se preia lista licitatiei cu preturile maxime ale clientilor
		Hashtable<Integer, Double> clientsMaxPrices = auctions.getClientsMaxPrices(auctionId);
		
		Double max_price = (double) -1;
		Integer winnerId = -1;
		
		for(int i = 1; i <= auctions.getMaxSteps(auctionId); i++) {
			
			for(Broker broker : brokers) {
				
				if(broker.containsClients(productId)) {
					
					//fiecare broker aduce noile preturi de la clientii lui
					Hashtable<Integer, Double> brokerMap = broker.getNewPrices(productId, max_price);
					
					//lista mare este actualizata
					newPrices.putAll(brokerMap);
				}
			}
			
			//se verifica daca clientii au depasit suma maxima cu care s-au inscris
			//tot in acest for se vor scrie in fisier sumele oferite de clienti
			//la pasul i
			for(Integer clientId : clientsMaxPrices.keySet()) {
				if(newPrices.get(clientId) > clientsMaxPrices.get(clientId)) {
					newPrices.put(clientId, clientsMaxPrices.get(clientId));
				}
				
				print.write(clientId + " " + newPrices.get(clientId) + '\n');
			}
			
			//se calculeaza pretul maxim de la pasul i
			for(Double price : newPrices.values()) {
				if(price > max_price) {
					max_price = price;
				}
			}
			
			print.write("Pretul maxim la pasul " + i + " este " + max_price + "\n\n");
		}
		//se verifica daca pretul maxim este mai mic decat pretul minim
		//al produsului
		if(max_price < products.getMinPrice(productId)) {
			
			print.write("Licitatia nu a fost castigata de nimeni!");
			notifyBrokers(auctionId, max_price, -1, auctions.getProductId(auctionId));
			
			products.refreshNrParticipants(productId);
		}else {
		
			//se cauta id ul castigatorului 
			for(Integer clientId : newPrices.keySet()) {
				if(newPrices.get(clientId) == max_price) {
					winnerId = clientId;
					break;
				}
			}
			
			print.write("Id-ul castigatorului este " + winnerId + " iar suma oferita este " + max_price);
			
			//sunt anuntati brokerii de rezultatul licitatiei
			notifyBrokers(auctionId, max_price, winnerId, auctions.getProductId(auctionId));
		}
		print.close();
		
	}
	public void notifyBrokers(Integer auctionId, Double max_price, Integer winnerId, Integer productId) {
		for(Broker broker : brokers) {
			
			if(broker.containsClients(productId)) {
				broker.notifyClients(auctionId, max_price, winnerId, productId);
			}
		}
	}
	
	//clasele interne din entitatile Administrator, Broker, Client
	//permit accesul la anumite metode din clasa auctionHouse
	public void addProduct(Administrator.AdminAccess adminAccess, Product product) {
		
		//instructiunea cere ca obiectul transmis sa fie diferit de null
		//in caz contrar se va arunca exceptia "NullPointerException"
		Objects.requireNonNull(adminAccess);
		products.addProduct(product);
	}

	public void sellProduct(Broker.BrokerAccess brokerAccess, Integer productId) {
		
		//analog ca mai sus
		Objects.requireNonNull(brokerAccess);
		products.sellProduct(productId);
	}
	
	public Set<Product> getProducts(Client.ClientAccess clientAccess){
		
		//analog ca mai sus
		Objects.requireNonNull(clientAccess);
		return products.getProducts();
	}
	
	public void addAuction(Administrator.AdminAccess adminAccess, Auction auction) {
		
		Objects.requireNonNull(adminAccess);
		auctions.addAuction(auction);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*public Vector<Product> getProducts(Client.ClientAcces clientAcces){
		
		Objects.requireNonNull(clientAcces);
		return products.getProducts();
	}
	
	public Vector<Product> getProducts(Broker.BrokerAcces brokerAcces){
		
		Objects.requireNonNull(brokerAcces);
		return products.getProducts();
	}
	
	
	public void sellProduct(Broker.BrokerAcces brokerAcces, Product product) {
		
		Objects.requireNonNull(brokerAcces);
		products.sellProduct(product);
	}
	
	
	*/
	
	
	
}	