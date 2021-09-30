package casa_licitatii;

public class PhysicalPerson extends Client{
	
	public PhysicalPerson() {
		commission = new PhysicalPersonCommission();
	}

	private String birthDate;
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
