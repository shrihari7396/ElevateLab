package Task7;

import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {

    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "emp_user";
    static final String PASS = "emp_pass";


    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            Scanner sc = new Scanner(System.in);

            System.out.println("Connected to DB");

            while(true) {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                switch(ch) {
                    case 1:
                        addEmp(con, sc);
                        break;
                    case 2:
                        viewEmp(con);
                        break;
                    case 3:
                        updateEmp(con, sc);
                        break;
                    case 4:
                        deleteEmp(con, sc);
                        break;
                    case 5:
                        System.out.println("Bye...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void addEmp(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter position: ");
        String pos = sc.nextLine();
        System.out.print("Enter salary: ");
        double sal = sc.nextDouble();

        String q = "INSERT INTO employees(name, position, salary) VALUES(?,?,?)";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setString(1, name);
        pst.setString(2, pos);
        pst.setDouble(3, sal);
        pst.executeUpdate();
        System.out.println("Employee Added");
    }

    static void viewEmp(Connection con) throws SQLException {
        String q = "SELECT * FROM employees";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q);
        System.out.println("ID | Name | Position | Salary");
        while(rs.next()) {
            System.out.println(rs.getInt("id")+" | "+rs.getString("name")+" | "+
                               rs.getString("position")+" | "+rs.getDouble("salary"));
        }
    }

    static void updateEmp(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new position: ");
        String pos = sc.nextLine();
        System.out.print("Enter new salary: ");
        double sal = sc.nextDouble();

        String q = "UPDATE employees SET position=?, salary=? WHERE id=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setString(1, pos);
        pst.setDouble(2, sal);
        pst.setInt(3, id);
        int rows = pst.executeUpdate();
        if(rows > 0) System.out.println("Updated");
        else System.out.println("Employee not found");
    }

    static void deleteEmp(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();

        String q = "DELETE FROM employees WHERE id=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setInt(1, id);
        int rows = pst.executeUpdate();
        if(rows > 0) System.out.println("Deleted");
        else System.out.println("Employee not found");
    }
}
