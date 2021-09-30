package casa_licitatii;

public class Jewelry extends Product{
	
	public Jewelry() {
	}
	
	private String material;
	private boolean gemstone;
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public void setGemstone(boolean gemstone) {
		this.gemstone = gemstone;
	}
	
	public String getMaterial() {
		return material;
	}
	
	public boolean getGemstone() {
		return gemstone;
	}
}
