import java.sql.*;
import java.util.Scanner;

public class ProjectOne {
    static  int number = 10;
    static Scanner scanner = new Scanner(System.in);
    static Statement statement;
    public static void createTable(Connection connect) throws SQLException {
        statement = connect.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS data(Name Text NOT NULL, Email Text, Age int, location Text, Designation Text)");
        System.out.println("Database created");
    }

    public static int populateTable(){
        try(
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "WisdomPassword1#");){
            createTable(connect);
            String Name, Email, Location, Designation, answer;
            int Age, count;
            String name, email, location, designation;
            int age;
            count = 0;
            PreparedStatement insertStatement = connect.prepareStatement("INSERT INTO data(Name, Email, Age, Location, Designation) VALUES(?,?,?,?,?)");
            do{
                System.out.println("type in your name: ");
                Name = scanner.nextLine();
                System.out.println("type in your email: ");
                Email = scanner.nextLine();
                System.out.println("type in your age: ");
                Age = scanner.nextInt();
                scanner.nextLine();
                System.out.println("type in your location: ");
                Location = scanner.nextLine();
                System.out.println("type in your designation: ");
                Designation = scanner.nextLine();
                insertStatement.setString(1, Name);
                insertStatement.setString(2, Email);
                insertStatement.setInt(3, Age);
                insertStatement.setString(4, Location);
                insertStatement.setString(5, Designation);
                insertStatement.execute();
                System.out.println("Do you want to add data (yes/no)?");
                answer = scanner.nextLine();
                count++;
            } while (count < number && answer.equalsIgnoreCase("yes"));
            System.out.println("total: " + count);
            return count;
            }catch(SQLException exception){
            exception.printStackTrace();
        }
        return 0;
        }
        public static void main(String...args){
        populateTable();
        }

    }
