package login;

import java.sql.*;

import user.ConnectionBehaviour;
import user.UserBehaviour;

public class Login implements ConnectionBehaviour,UserBehaviour{

	private Connection myConn=null;
	private Statement qrStmt=null;
	private PreparedStatement updtStmt=null;
	private ResultSet myRs=null;
	private boolean isValid;
	private String email;
	private String password;
	private String[] details;

	public Login(String email,String password) throws SQLException
	{
		this.email=email;
		this.password=password;

		myConn=DriverManager.getConnection(dbUrl,userId,pass);
		qrStmt=myConn.createStatement();
String info[]=new String[fields];
		String createdStatement="SELECT password from user_info where email_id='"+email+"'";
		myRs=qrStmt.executeQuery(createdStatement);
                try{
                if(myRs!=null)
		myRs.next();
		
		if(myRs!=null && myRs.getString("password").equals(password))
			isValid=true;
               

		
			createdStatement="SELECT * from user_info where email_id='"+email+"'";
			myRs=qrStmt.executeQuery(createdStatement);

			


			myRs.next();
			for(int i=1;i<=fields;i++)
				info[i-1]=myRs.getString(i);

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		details=info;

	}

	
	public boolean isAllowed()
	{

		return isValid;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPassword()
	{
		return password;
	}
	public String getFirstName()
	{
		return details[0];
	}
	public String getLastName()
	{
		return details[1];
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
}
