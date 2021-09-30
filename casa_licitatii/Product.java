package casa_licitatii;

public abstract class Product {

	protected Integer id;
	protected String name;
	protected Double selling_price;
	protected Double min_price;
	protected Integer year;
	
	public Product() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Double getSellingPrice() {
		return selling_price;
	}
	
	public Double getMinPrice() {
		return min_price;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSellingPrice(Double selling_price) {
		this.selling_price = selling_price;
	}
	
	public void setMinePrice(Double min_price) {
		this.min_price = min_price;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
}
