package casa_licitatii;

public class JuridicalPerson extends Client{

	public JuridicalPerson() {
		commission = new JuridicalPersCommission();
	}
	
	private Double socialCapital;
	private Company company;
	
	public void setSocialCapital(Double socialCapital) {
		this.socialCapital = socialCapital;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
}
