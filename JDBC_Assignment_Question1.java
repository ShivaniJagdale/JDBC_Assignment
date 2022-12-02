//Give the menu to the user as the operation listed below on student table
//1. Create 2. Read 3. Update 4. Delete
//Student table has 3 columns(Roll_no,Name,Marks)

import java.sql.*;
import java.util.*;
public class JDBC_Assignment_Question1 {

	public static void main(String[] args) throws Exception {
		Demo d=new Demo();
		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
		System.out.println("\n     Menu");
		System.out.println("***************");
		System.out.println(" 1. Create\n 2. Read\n 3. Update\n 4. Delete\n 5. Exit");
		System.out.println("please select the range between (1-4)");
		int n=sc.nextInt();
		
		switch(n)
		{
		  case 1:
			  d.Create();
			  break;
			  
		  case 2:
			  d.Read();
			  break;
			  
		  case 3:
			  d.Update();
			  break;
			  
		  case 4:
			  d.Delete();
			  break;
			  
		  case 5:
			  System.exit(0);
			  
		  default:
			  System.out.println("please select the proper range ");
			 
		}
		}
	}	
}

class Demo
{
	void Create()throws Exception
	{
		Scanner sc=new Scanner(System.in);
		Connection connection=null;
		Statement statement=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no.: ");
		int rollNo = sc.nextInt();

		System.out.println("Enter the Name: ");
		String name = sc.next();

		System.out.println("Enter the Marks: ");
		int marks = sc.nextInt();

		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  statement=connection.createStatement();
			  if(statement!=null) 
			  {
				  String insertQuery = "insert into student(`Roll_no`,`Name`,`Marks`) values ("+rollNo+",'"+name+"',"+marks+")";
				  statement.executeUpdate(insertQuery);

					System.out.println(" row inserted... ");

				 
				  
			  }
		  }
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// closing the resources used

			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();

			}
	}
	}
	
    void Read()throws Exception
    {
    	Connection connection=null;
		Statement statement=null;
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  statement=connection.createStatement();
			  if(statement!=null) 
			  {
				 String selectQuery="select * from Student";
				  resultset=statement.executeQuery(selectQuery);
				  if(resultset!=null)
				  {
					    System.out.println("Roll no\tNAME\tMarks");
						System.out.println("======================");
						while (resultset.next()) 
						{
								int roll = resultset.getInt("Roll_no");
								String name = resultset.getString("Name");
								int marks = resultset.getInt("Marks");
								
								System.out.println(roll + "\t" + name + "\t" + marks);
                    
						}
						
				  }
				  
			  }
		  }
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the resources used

			if (resultset != null) {
				resultset.close();

			}
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();

			}
	}

	}
    
    void Update()throws Exception
    {
    	Scanner sc=new Scanner(System.in);
    	
    	Connection connection=null;
		Statement statement=null;
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no. of Student for which you want to update the marks: ");
		int rollNo = sc.nextInt();

		System.out.println("Enter the new marks of student: ");
		int marks = sc.nextInt();

		
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  statement=connection.createStatement();
			  if(statement!=null) 
			  {
				  String updateSqlQuery = "update student set Marks ="+marks+"  where Roll_no= "+rollNo;

				  statement.executeUpdate(updateSqlQuery);
					System.out.println("row updated...");
	  
			  }
		  }
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the resources used

			if (resultset != null) {
				resultset.close();

			}
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();

			}
	}
    }
    
    void Delete()throws Exception
    {
    	Scanner sc=new Scanner(System.in);
    	
    	Connection connection=null;
		Statement statement=null;
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no. of Student for which you have to delete the record:");
		int rollNo =sc.nextInt();
		
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  statement=connection.createStatement();
			  if(statement!=null) 
			  {
				 String deleteSqlQuery = "delete from student where Roll_no = "+rollNo;
				 statement.executeUpdate(deleteSqlQuery);
					System.out.println("row deleted...");
	  
				  
				  
			  }
		  }
		}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing the resources used

			if (resultset != null) {
				resultset.close();

			}
			if (statement != null) {
				statement.close();

			}
			if (connection != null) {
				connection.close();

			}
	}
    }

}
