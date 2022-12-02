//perform insertion opertion and also perform retrieval operation on the following data
//name => 
//address=> 
//gender => 
//DOB => dd-MM-yyyy
//DOJ => MM-dd-yyyy
//DOM => yyyy-MM-dd

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class JDBC_Assignment_Question2 {

	public static void main(String[] args) throws SQLException, ParseException
	{
		operations obj=new operations();
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
		System.out.println("\n     Menu");
		System.out.println("***************");
		System.out.println(" 1. Insert Record\n 2. Read Record\n 3. Exit");
		System.out.println("please select the range between (1-3)");
		int n=sc.nextInt();
		
		switch(n)
		{
		  case 1:
			  obj.insert();
			  break;
			  
		  case 2:
			  obj.read();
			  break;
			  
		  case 3:
			  System.exit(0);
			  
		  default:
			  System.out.println("please select the proper range ");
			 
		}
		}
	}
}

class operations
{
	void insert()throws SQLException, ParseException
	{
				Connection connection = null;
				PreparedStatement pstmt = null;
				
				String url="jdbc:mysql://localhost:3306/Course";
				String username="root";
				String passward="Shivani@123";

				Scanner scanner = new Scanner(System.in);

				System.out.println("Enter the name: ");
				String name = scanner.nextLine();
				
				System.out.println("Enter the Address: ");
				String address = scanner.nextLine();

				System.out.println("Enter the Gender: ");
				String gender = scanner.nextLine();

                System.out.println("Enter the DOB:(dd-mm-YYYY) ");
				String dob = scanner.nextLine();
				
			    System.out.println("Enter the DOJ:(mm-dd-YYYY) ");
				String doj = scanner.nextLine();

			    System.out.println("Enter the DOM:(YYYY-mm-dd) ");
				String dom = scanner.nextLine();


				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date uDate = sdf.parse(dob);
                long time = uDate.getTime();
				java.sql.Date sqlDOB = new java.sql.Date(time);
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
				java.util.Date uDate1 = sdf1.parse(doj);
				long time1 = uDate1.getTime();
				java.sql.Date sqlDOJ = new java.sql.Date(time1);
				
				java.sql.Date sqlDOM=java.sql.Date.valueOf(dom);

				String sqlInsertQuery = "insert into employee(`Name`,`Address`,`Gender`,`DOB`,`DOJ`,`DOM`) values (?,?,?,?,?,?)";

				try {

					connection = DriverManager.getConnection(url,username,passward);

					if (connection != null)
						pstmt = connection.prepareStatement(sqlInsertQuery);

					if (pstmt != null) {
						pstmt.setString(1, name);
						pstmt.setString(2, address);
						pstmt.setString(3, gender);
						pstmt.setDate(4, sqlDOB);
						pstmt.setDate(5, sqlDOJ);
						pstmt.setDate(6, sqlDOM);
						pstmt.executeUpdate();

						System.out.println("Record inserted...");
					}

				}
				catch (SQLException se) {
					se.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
					// closing the resources used

					if (pstmt != null) {
						pstmt.close();

					}
					if (connection != null) {
						connection.close();

					}
	}
}
	
	void read()throws SQLException, ParseException
	{
		// resource used
				Connection connection = null;
				PreparedStatement pstmt = null;
				ResultSet resultSet = null;
				
				String url="jdbc:mysql://localhost:3306/Course";
				String username="root";
				String passward="Shivani@123";

				Scanner scanner = new Scanner(System.in);

				System.out.print("Enter the name: ");
				String name = scanner.next();

				String sqlSelectQuery = "select * from employee where Name = ?";

				try {

					connection=DriverManager.getConnection(url,username,passward);

					if (connection != null)
						pstmt = connection.prepareStatement(sqlSelectQuery);

					if (pstmt != null) {
						pstmt.setString(1, name);
						resultSet = pstmt.executeQuery();
					}
					if (resultSet != null) {

						if (resultSet.next()) {
							
							String Name = resultSet.getString(1);
							String Address = resultSet.getString(2);
							String Gender = resultSet.getString(3);
							java.sql.Date dob = resultSet.getDate(4);
							java.sql.Date doj = resultSet.getDate(5);
							java.sql.Date DOM = resultSet.getDate(6);
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String DOB = sdf.format(dob);
							SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
							String DOJ = sdf1.format(doj);
							
							System.out.println("Name : " + Name);
							System.out.println("Address : " + Address);
							System.out.println("Gender : " + Gender);
							System.out.println("DOB  : " + DOB);
							System.out.println("DOJ  : " + DOJ);
							System.out.println("DOM  : " + DOM);
						
						} else {
							System.out.println("Record not available for the given name: " + name);
						}

					}

				} catch (SQLException se) {
					se.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// closing the resources used

					if (resultSet != null) {
						resultSet.close();

					}
					if (pstmt != null) {
						pstmt.close();

					}
					
					if (connection != null) {
						connection.close();

					}
					
				}
	}
}
