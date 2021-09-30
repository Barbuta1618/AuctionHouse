package casa_licitatii;

import java.util.Hashtable;
import java.util.Vector;

public class AuctionsList {
	
	private Vector<Auction> auctions;
	
	public AuctionsList() {
		auctions = new Vector<Auction>();
	}
	
	public void addAuction(Auction auction) {
		auctions.add(auction);
	}
	
	public Integer getProductId(Integer auctionId) {
		
		//se cauta id-ul produsului dupa id-ul licitatiei
		for(Auction auction : auctions) {
			if(auction.getId() == auctionId) {
				return auction.getProductId();
			}
		}
		return -1;
	}
	public Integer getNrParticipants(Integer idAuction) {
		
		//se cauta nr minim de participanti al licitatiei cautate
		for(Auction auction : auctions) {
			if(auction.getId() == idAuction) {
				return auction.getParticipantsNr();
			}
		}
		return -1;
	}
	
	public Integer getMaxSteps(Integer idAuction) {
		
		//se cauta nr de pasi ai licitatiei cautate
		for(Auction auction : auctions) {
			if(auction.getId() == idAuction) {
				return auction.getMaxSteps();
			}
		}
		return -1;
	}
	
	public Integer getAuctionId(Integer idProduct) {
		
		//se cauta id-ul licitatiei dupa id-ul produsului
		for(Auction auction : auctions) {
			if(auction.getProductId() == idProduct) {
				return auction.getId();
			}
		}
		return -1;
	}
	
	public Auction getAuction(Integer auctionId) {
		
		//se cauta licitatia dupa id-ul ei
		for(Auction auction : auctions) {
			if(auction.getId() == auctionId) {
				return auction;
			}
		}
		
		return null;
	}
	
	public Hashtable<Integer, Double> getClientsMaxPrices(Integer auctionId){
		return getAuction(auctionId).getClientsMaxPrices();
	}
}
