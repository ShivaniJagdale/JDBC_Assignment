//perform CRUD operation using preparedStatement
//1. insert 2. update 3. select 4. delete
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Assignment_Question3 {

	public static void main(String[] args)throws Exception 
	{
		Operation d=new Operation();
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

class Operation
{
	void Create()throws Exception
	{
		Scanner sc=new Scanner(System.in);
		Connection connection=null;
		PreparedStatement pstmt = null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no.: ");
		int rollNo = sc.nextInt();

		System.out.println("Enter the Name: ");
		String name = sc.next();

		System.out.println("Enter the Marks: ");
		int marks = sc.nextInt();

		String insertQuery = "insert into student(`Roll_no`,`Name`,`Marks`) values (?,?,?)";
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  pstmt = connection.prepareStatement(insertQuery);
			  if (pstmt != null)
			  {
					pstmt.setInt(1,rollNo );
					pstmt.setString(2, name);
					pstmt.setInt(3, marks);
					pstmt.executeUpdate();

					System.out.println("Record inserted...");
				}
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
	
    void Read()throws Exception
    {
    	Connection connection=null;
    	PreparedStatement pstmt = null;
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Roll no.: ");
		int roll = scanner.nextInt();
		String selectQuery="select * from Student where Roll_no=?";
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  pstmt = connection.prepareStatement(selectQuery);
			  if (pstmt != null)
			  {
				  pstmt.setInt(1, roll);
				  resultset=pstmt.executeQuery();
				  if(resultset!=null)
				  {
					   
						if(resultset.next()) 
						{
								int rollno = resultset.getInt("Roll_no");
								String name = resultset.getString("Name");
								int marks = resultset.getInt("Marks");
								
								System.out.println("Roll no. : "+rollno + "\nName: " + name + "\nMarks: " + marks);
                    
						}
						 else {
								System.out.println("Record not available for the given Roll no. : " + roll);
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
			if (pstmt != null) {
				pstmt.close();

			}

			if (connection != null) {
				connection.close();

			}
	}

	}
    
    void Update()throws Exception
    {
    	Scanner sc=new Scanner(System.in);
    	PreparedStatement pstmt = null;
    	Connection connection=null;
		
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no. of Student for which you want to update the marks: ");
		int rollNo = sc.nextInt();

		System.out.println("Enter the new marks of student: ");
		int marks = sc.nextInt();

		 String updateSqlQuery = "update student set Marks =?  where Roll_no= ?";

		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  pstmt = connection.prepareStatement(updateSqlQuery);
			  if (pstmt != null)
			  {
					pstmt.setInt(1,marks );
					pstmt.setInt(2, rollNo);
					
					pstmt.executeUpdate();

					System.out.println("Record updated...");
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
			if (pstmt != null) {
				pstmt.close();

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
    	PreparedStatement pstmt = null;
		ResultSet resultset=null;
		
		String url="jdbc:mysql://localhost:3306/Course";
		String username="root";
		String passward="Shivani@123";
		
		System.out.println("Enter the Roll no. of Student for which you have to delete the record:");
		int rollNo =sc.nextInt();
		
		 String deleteSqlQuery = "delete from student where Roll_no = ?";
		try 
		{
		  connection=DriverManager.getConnection(url,username,passward);
		  if(connection!=null) 
		  {
			  pstmt = connection.prepareStatement(deleteSqlQuery);
			  if (pstmt != null)
			  {
				    pstmt.setInt(1,rollNo );
					
					pstmt.executeUpdate();
				 
					System.out.println("Record deleted...");
	  
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
			if (pstmt != null) {
				pstmt.close();

			}
			
			if (connection != null) {
				connection.close();

			}
	}
    }

}

