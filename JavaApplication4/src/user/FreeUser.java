package user;

import java.sql.SQLException;

public class FreeUser extends User{
	
	public FreeUser(String...info) throws FieldsMismatchException, SQLException, EmailAddressTakenException
	{
		super(info);
	}

	@Override
	public boolean isFreeUser() {
		return true;
	}

}
