package casa_licitatii;

public class PhysicalPersonCommission implements Commission{

	@Override
	public Integer getValue(Integer participationsNr) {
		
		if(participationsNr < 5) {
			return 20;
		}
		return 15;
	}
	
}
