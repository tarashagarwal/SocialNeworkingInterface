package user;

import java.sql.*;

abstract public class User implements UserBehaviour,ConnectionBehaviour{


	private int pokesMade;
	private int msgSent;
	private String[] details;


	Connection myConn=null;
	PreparedStatement updtStmt=null;
	Statement qrStmt=null;
	ResultSet myRs=null;

	protected User(String ...info) throws FieldsMismatchException, SQLException, EmailAddressTakenException
	{
		details=info;
		pokesMade=0;
		msgSent=0;

		if(info.length!=fields)
		{
			FieldsMismatchException e=new FieldsMismatchException();
			throw e;
		}
		myConn=DriverManager.getConnection(dbUrl,userId,pass);

		if(userExists(info)==false)
		{

			

			String temp="";


			for(int i=0;i<info.length-1;i++)
				temp+="'"+info[i]+"',";
			temp+="'"+info[info.length-1]+"'";

			String preparedString="INSERT INTO user_info (" + field_data + ") VALUES(" + temp + ");";
			updtStmt=myConn.prepareStatement(preparedString);
			//int noOfRows=
					updtStmt.executeUpdate();
					//System.out.println("Account Created");
		}
		else
		{
			EmailAddressTakenException e=new EmailAddressTakenException();
			throw e;
		}



	}


	private boolean userExists(String...strings) throws SQLException
	{

		String createdString="SELECT count(1) FROM user_info WHERE email_id='"+strings[2]+"'";
		qrStmt=myConn.createStatement();
		myRs=qrStmt.executeQuery(createdString);
		myRs.next();
		//System.out.println(myRs.getInt("count(1)"));
		if(myRs.getString("count(1)").equals("1"))
			return true;
		return false;
	}
	public int getPokesMade()
	{
		return pokesMade;
	}

	public int getMsgSent()
	{
		return msgSent;
	}

	public void increasePoke()
	{
		pokesMade++;
	}

	public void increaseMsg()
	{
		msgSent++;
	}
	
	public String getFirstName()
	{
		return details[0];
	}
	public String getLastName()
	{
		return details[1];
	}
	
	public String getEmailId()
	{
		return details[2];
	}
	public String getPassword()
	{
		return details[3];
	}
	public String getBirthdate()
	{
		return details[4];
	}
	public String getSex()
	{
		return details[5];
	}
	public String getImprovement()
	{
		return details[6];
	}

	abstract public boolean isFreeUser();




}
