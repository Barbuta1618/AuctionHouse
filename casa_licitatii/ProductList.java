package casa_licitatii;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class ProductList {
	
	Hashtable<Product, Integer> products;
	
	
	public ProductList(){
		products = new Hashtable<Product, Integer>();
	}
	
	public void addProduct(Product p) {
		products.put(p, 0);
	}
	
	public void incrementNrParticipants(Integer idProduct) {
		
		//se actualizeaza nrParticipanti asociat produsului product
		for(Product product : products.keySet()) {
			if(product.getId() == idProduct) {
				products.replace(product, products.get(product) + 1);
				return;
			}
		}
	}
	
	public Double getMinPrice(Integer idProduct) {
		
		//se cauta pretul minim al produsului cu id ul cerut
		for(Product product : products.keySet()) {
			if(product.getId() == idProduct) {
				return product.getMinPrice();
			}
		}
		
		return null;
	}
	
	public Integer getNrParticipants(Integer idProduct) {
		
		//se cauta nr de solicitari ale produsului cu id ul cerut
		for(Product product : products.keySet()) {
			if(product.getId() == idProduct) {
				return products.get(product);
			}
		}
		
		return -1;
	}
	
	
	public Set<Product> getProducts() {
		
		//se returneaza o multime cu produsele din lista
		return products.keySet();
	}
	

	public void sellProduct(Integer idProduct) {
		
		//se vinde produsul cu id ul cerut
		for(Product product : products.keySet()) {
			if(product.getId() == idProduct) {
				products.remove(product);
				return;
			}
		}
	}
	
	public void refreshNrParticipants(Integer productId) {
		for(Product product : products.keySet()) {
			if(product.getId() == productId) {
				products.put(product, 0);
				return;
			}
		}
	}
}
