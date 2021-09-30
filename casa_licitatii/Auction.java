package casa_licitatii;

import java.util.Hashtable;

public class Auction{
	
	private Integer id;
	private Integer participantsNr;
	private Integer productId;
	private Integer maxSteps;
	
	private Hashtable<Integer, Double> clientsMaxPrices;
	
	public Auction(Integer id, Integer participantsNr, Integer productId, Integer maxSteps) {
		
		this.id = id;
		this.participantsNr = participantsNr;
		this.productId = productId;
		this.maxSteps = maxSteps;
		
		clientsMaxPrices = new Hashtable<>();
	}
	
	public void addClient(Integer idClient, Double max_price) {
		clientsMaxPrices.put(idClient, max_price);
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getParticipantsNr() {
		return participantsNr;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public Integer getMaxSteps() {
		return maxSteps;
	}
	
	public Hashtable<Integer, Double> getClientsMaxPrices() {
		
		return clientsMaxPrices;
	}

	
}
