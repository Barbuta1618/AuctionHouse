package casa_licitatii;

public class JuridicalPersCommission implements Commission{

	@Override
	public Integer getValue(Integer participationsNr) {
		
		if(participationsNr < 25) {
			return 25;
		}
		return 10;
	}

}
