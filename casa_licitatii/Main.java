package casa_licitatii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException{
		
		
		AuctionHouse auctionHouse = new AuctionHouse();
		File file = new File("auction_house.in");
		BufferedReader scan = new BufferedReader(new FileReader(file));
		
		
		//se citeste nr de Brokers
		Integer nrBrokers = Integer.parseInt(scan.readLine());
		System.out.println(nrBrokers);
		auctionHouse.addBrokers(nrBrokers);
		
		//se citeste nr de clienti
		Integer nrClients = Integer.parseInt(scan.readLine());
		Vector<Client> clients = new Vector<Client>(nrClients);
		
		//se citesc datele clientilor
		for(int i = 0; i < nrClients; i++) {
			
			Client client = readClient(scan);
			clients.add(client);
		}
		
		//se citeste nr de produse
		Integer nrProducts = Integer.parseInt(scan.readLine());
		Vector<Product> products = new Vector<Product>(nrProducts);
		
		//se adauga produsele in lista
		for(int i = 0; i < nrProducts; i++) {
			
			Product product = readProduct(scan);
			products.add(product);
		}
		
		Administrator.getInstance().addProduct(products, auctionHouse);
		
		Vector<Auction> auctions = new Vector<Auction>(nrProducts);
		for(int i = 0; i < nrProducts; i++) {
			Auction auction = readAuction(scan);
			auctions.add(auction);
		}
		
		Administrator.getInstance().addAuction(auctions, auctionHouse);
		
		Integer requests = Integer.parseInt(scan.readLine());
		
		synchronized(auctionHouse){
			
			for(int i = 0; i < requests; i++) {
				String request = scan.readLine();
				String param[] = request.split(" ");
				
				Integer clientId = Integer.parseInt(param[0]);
				
				for(Client client : clients) {
					if(clientId == client.getId()) {
						clientId = client.getId();
						break;
					}
				}
				Integer productId = Integer.parseInt(param[1]);
				Double max_price = Double.parseDouble(param[2]);
				
				clients.get(clientId - 1).sendRequest(auctionHouse, productId, max_price);
			}
		}
	
	}
	
	private static Auction readAuction(BufferedReader scan) throws IOException{
		
		Auction auction;
		Integer id = Integer.parseInt(scan.readLine());
		Integer participantsNr = Integer.parseInt(scan.readLine());
		Integer productId = Integer.parseInt(scan.readLine());
		Integer maxSteps = Integer.parseInt(scan.readLine());
		
		auction = new Auction(id, participantsNr, productId, maxSteps);
		
		return auction;
	}
	
	private static Product readProduct(BufferedReader scan) throws IOException{
		Product product;
		
		String productType = scan.readLine();
		Integer id = Integer.parseInt(scan.readLine());
		String name = scan.readLine();
		Double min_price = Double.parseDouble(scan.readLine());
		Integer year = Integer.parseInt(scan.readLine());
		
		if(productType.equals("Tablou")) {
			
			String painterName = scan.readLine();
			Color color = Color.valueOf(scan.readLine());
			
			product = Builder.build(Painting.class).with(p -> p.setName(name))
					.with(p -> p.setId(id))
					.with(p -> p.setMinePrice(min_price))
					.with(p -> p.setYear(year))
					.with(p -> p.setColor(color))
					.with(p -> p.setPainterName(painterName))
					.get();
			
			return product;
		}
		
		if(productType.equals("Bijuterie")) {
			
			String material = scan.readLine();
			Boolean gemstone = Boolean.parseBoolean(scan.readLine());
			
			product = Builder.build(Jewelry.class).with(p -> p.setName(name))
					.with(p -> p.setId(id))
					.with(p -> p.setMinePrice(min_price))
					.with(p -> p.setYear(year))
					.with(p -> p.setGemstone(gemstone))
					.with(p -> p.setMaterial(material))
					.get();
			
			return product;
		}
		
		if(productType.equals("Mobila")) {
			
			String type = scan.readLine();
			String material = scan.readLine();
			
			product = Builder.build(Furniture.class).with(p -> p.setName(name))
					.with(p -> p.setId(id))
					.with(p -> p.setMinePrice(min_price))
					.with(p -> p.setYear(year))
					.with(p -> p.setType(type))
					.with(p -> p.setMaterial(material))
					.get();
		}
		
		
		return null;
	}

	private static Client readClient(BufferedReader scan) throws IOException{
		Client client;
		
		String personType = scan.readLine();
		Integer id = Integer.parseInt(scan.readLine());
		String name = scan.readLine();
		String address = scan.readLine();
		Integer participationsNr = Integer.parseInt(scan.readLine());
		Integer auctionsWon = Integer.parseInt(scan.readLine());
		
		
		if(personType.equals("Persoana fizica")) {
			
			String birthDate = scan.readLine();
			client = Builder.build(PhysicalPerson.class).with(c -> c.setId(id))
					.with(c -> c.setName(name))
					.with(c -> c.setAddress(address))
					.with(c -> c.setParticipationsNr(participationsNr))
					.with(c -> c.setAuctionsWon(auctionsWon))
					.with(c -> c.setBirthDate(birthDate))
					.get();
		}else {
			
			
			Company company = Company.valueOf(scan.readLine());
			
			Double socialCapital = Double.parseDouble(scan.readLine());
			
			client = Builder.build(JuridicalPerson.class).with(c -> c.setId(id))
					.with(c -> c.setName(name))
					.with(c -> c.setAddress(address))
					.with(c -> c.setParticipationsNr(participationsNr))
					.with(c -> c.setAuctionsWon(auctionsWon))
					.with(c -> c.setCompany(company))
					.with(c -> c.setSocialCapital(socialCapital))
					.get();
			
		}
		
		return client;
	}

}
