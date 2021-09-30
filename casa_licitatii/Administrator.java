package casa_licitatii;

import java.util.Vector;

public class Administrator extends Employee{
	
	private static Administrator instance = new Administrator();
	
	private Administrator() {
		
	}
	
	public static Administrator getInstance() {
		return instance;
	}
	
	public static final class AdminAccess{
		private AdminAccess(){}
	}
	
	private static AdminAccess adminAccess = new AdminAccess();
	
	public void addProduct(Vector<Product> products, AuctionHouse auctionHouse) {
		for(Product product : products) {
			addProduct(product, auctionHouse);
		}
	}
	
	public void addProduct(Product product, AuctionHouse auctionHouse) {
		auctionHouse.addProduct(adminAccess, product);
	}
	
	public void addAuction(Auction auction, AuctionHouse auctionHouse) {
		auctionHouse.addAuction(adminAccess, auction);
	}
	
	public void addAuction(Vector<Auction> auctions, AuctionHouse auctionHouse) {
		for(Auction auction : auctions) {
			addAuction(auction, auctionHouse);
		}
	}
}
