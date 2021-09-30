package casa_licitatii;

public class Painting extends Product{
	
	public Painting() {
		
	}
	
	private String painterName;
	private Color color;
	
	public void setPainterName(String painterName) {
		this.painterName = painterName;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getPainterName() {
		return painterName;
	}
	
	public Color getColor() {
		return color;
	}
}
