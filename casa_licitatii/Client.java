package casa_licitatii;

import java.util.Vector;

public abstract class Client {
	
	//clasa interna de acces la metode specifice din AuctionHouse
	public static final class ClientAccess{
		private ClientAccess() {}
	}
	
	private static final ClientAccess clientAccess = new ClientAccess();
	
	private Integer id;
	private String name;
	private String address;
	private Integer participationsNr;
	private Integer auctionsWon;
	
	private Vector<Integer> idProducts;
	private Vector<Integer> idAuctions;
	
	public Client() {
		
		idAuctions = new Vector<Integer>();
		idProducts = new Vector<Integer>();
	}
	
	public String toString() {
		return id.toString();
	}
	
	protected Commission commission;
	public Integer getCommission() {
		return commission.getValue(participationsNr);
	}
	
	public void addProduct(Integer idProduct) {
		idProducts.add(idProduct);
	}
	
	
	public void sendRequest(AuctionHouse auctionHouse, Integer idProduct, Double max_sum) {
		//se transmite o solicitare casei de licitatii pentru un anumit produs
		auctionHouse.addClient(this, idProduct,  max_sum);
	}
	

	protected Double getPrice(Integer productId, Double stepMax_price) {
		
		if(idProducts.contains(productId)) {
			Double newPrice = getNewPrice(stepMax_price);
			return newPrice;
		}
		
		return null;
	}
	
	protected Double getNewPrice(Double max_price) {
		
		//daca pretul maxim de la pasul anterior este -1 inseamna
		//ca acesta este primul pas, asa ca fiecare client genereaza un
		//pret random 
		if(max_price == -1) {
			return (double)(Math.random() * 500 + 50);
		}
		//se alege un nr random intre 5 si 65 reprezentand procentul pe care il adauga
		//clientul la suma maxima de la pasul anterior
		Integer percent = (int)(Math.random() * 60 + 5);
		Double newPrice = (double) ((percent * max_price) / 100) + max_price;
		return newPrice;
	}

	public Integer getId() {

		return id;
	}

	public Double notifyWinner(Integer auctionId, Double max_price, Integer winnerId) {

		//se verifica daca clientul castigator este clientul curent
		if(winnerId == id) {
			auctionsWon++;
			//se plateste comisionul brokerului
			return (double) (max_price * getCommission()) / 100;
		}
		
		if(idAuctions.contains(auctionId)) {
			participationsNr++;
			idAuctions.remove(auctionId);
		}
		
		return null;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setParticipationsNr(Integer participationsNr) {
		this.participationsNr = participationsNr;
	}
	
	public void setAuctionsWon(Integer auctionsWon) {
		this.auctionsWon = auctionsWon;
	}
}
