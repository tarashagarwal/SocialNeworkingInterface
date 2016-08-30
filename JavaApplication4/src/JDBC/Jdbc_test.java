package JDBC;


import java.sql.*;

public class Jdbc_test {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student" , "student");
			
			System.out.println("Connection Successful");
			
			myStmt=myConn.createStatement();
			// 2. Prepare statement
			//myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?");
			
			// 3. Set the parameters
//			myStmt.setDouble(1, 80000);
//			myStmt.setString(2, "Legal");
			
			// 4. Execute SQL query
			//myRs = myStmt.executeQuery("select * from employees");
			
			// 5. Display the result set
			//display(myRs);
		
			//
			// Reuse the prepared statement:  salary > 25000,  department = HR
//			//
//
//			System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");
//			
//			// 6. Set the parameters
//			myStmt.setDouble(1, 25000);
//			myStmt.setString(2, "HR");
//			
//			// 7. Execute SQL query
//			myRs = myStmt.executeQuery();
//			
//			// 8. Display the result set
			
			
//			display(myRs);
			myStmt.executeUpdate("insert into employees " +"(last_name,first_name,email,department,salary)"+"values ('Wright','Eric','eric.wright@gmail.com','HR',33000.00)");
			
			//System.out.println(myStmt.executeUpdate("delete from employees where first_name='Eric'"));
			myRs=myStmt.executeQuery("select * from demo.employees");
			
           while(myRs.next())
           {
        	   System.out.println(myRs.getInt("salary"));
           }

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}

	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
}
