package casa_licitatii;

public class Furniture extends Product{
	
	public Furniture() {
		
	}
	private String type;
	private String material;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMaterial() {
		return material;
	}
}
