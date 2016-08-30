package user;

import java.sql.SQLException;

public class PremiumUser extends User{
	public PremiumUser(String...info) throws FieldsMismatchException, SQLException, EmailAddressTakenException
	{
		super(info);
		
	}

	@Override
	public boolean isFreeUser() {
		return false;
}
}
