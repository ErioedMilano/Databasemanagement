import java.sql.*;
import java.util.Scanner;

public class teamgegevens {
    public static void main(String[] args) {
        printOptions();
        Scanner sc = new Scanner(System.in);

        int selection = sc.nextInt();
        while (selection != 9) {
            int student_id = 0;
            String voornaam = "";
            String achternaam = "";
            int leeftijd = 0;
            String geboortedatum = "";
            String ict_vaardigheid = "";
            int team_id = 0;


            switch (selection) {
                case 1:
                    readFromDatabase();
                    break;
                case 2:
                    readFromDatabase();
                    break;
                case 3:
                    System.out.println("Voer uw student_id in:");
                    sc.nextLine();
                    student_id = sc.nextInt();
                    System.out.println("Voer de voornaam in:");
                    sc.nextLine();
                    voornaam = sc.nextLine();
                    System.out.println("Voer de achternaam in:");
                    achternaam = sc.nextLine();
                    System.out.println("Voer uw leeftijd in:");

                    leeftijd = sc.nextInt();
                    System.out.println("Voer uw geboortedatum in:");
                    sc.nextLine();
                    geboortedatum = sc.nextLine();

                    System.out.println("Voer uw ict_vaardigheid in:");
                    ict_vaardigheid = sc.nextLine();


                    System.out.println("Voer uw team_id in:");
                    team_id = sc.nextInt();


                    updatePersoonsgegevens(student_id, voornaam, achternaam, leeftijd, geboortedatum, ict_vaardigheid, team_id);
                    break;
                case 4:
                    readFromDatabase();
                    break;
                case 5:
                    System.out.println("Voer uw student_id in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    System.out.println("Voer de voornaam in:");
                    sc.nextLine();
                    voornaam = sc.nextLine();

                    System.out.println("Voer de achternaam in:");
                    achternaam = sc.nextLine();

                    System.out.println("Voer uw leeftijd in:");
                    leeftijd = sc.nextInt();

                    System.out.println("Voer uw geboortedatum in:");
                    sc.nextLine();
                    geboortedatum = sc.nextLine();

                    System.out.println("Voer uw ict_vaardigheid in:");
                    ict_vaardigheid = sc.nextLine();

                    System.out.println("Voer uw team_id in:");
                    team_id = sc.nextInt();


                    insertIntopersoonsgegevens(student_id, voornaam, achternaam, leeftijd, geboortedatum, ict_vaardigheid, team_id);
                    break;
                case 6:
                    readFromDatabase();
                    break;
                case 7:
                    System.out.println("Voer de student_id van de persoon in:");
                    sc.nextLine();
                    student_id = sc.nextInt();
                    deleteFrompersoonsgegevens(student_id);
                    break;
                case 8:
                    readFromDatabase();
                    break;
                default:
                    break;
            }
            printOptions();
            selection = sc.nextInt();
        }
        System.out.println("Exiting program");
    }

    public static void printOptions() {
        System.out.println("-------------------");
        System.out.println("List of commands");
        System.out.println("1: Display list of users");
        System.out.println("2: Display list of groups");
        System.out.println("3: Edit user by id");
        System.out.println("4: Edit group");
        System.out.println("5: Create user");
        System.out.println("6: Create group");
        System.out.println("7: Delete user");
        System.out.println("8: Delete group");
        System.out.println("9: Exit application");
        System.out.println("Select your option's:");
    }

    public static Connection createConnection() {
        // Define the database connection URL, username, and password
        String url = "jdbc:mysql://localhost/javaproject";
        String username = "root";
        String password = "Junior%$#12";
        // Create a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void readFromDatabase() {
        try {

            Connection conn = createConnection();
            Statement stmt = conn.createStatement();

            // Execute a SELECT query to retrieve data from the table
            ResultSet rs = stmt.executeQuery("SELECT student_id, voornaam, achternaam, leeftijd, geboortedatum, ict_vaardigheid, team_id FROM Persoonsgegevens");

            // Process the ResultSet object to retrieve the data
            while (rs.next()) {
                String student_id = rs.getString("student_id");
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String leeftijd = rs.getString("leeftijd");
                String geboortedatum = rs.getString("geboortedatum");
                String ict_vaardigheid = rs.getString("ict_vaardigheid");
                String team_id = rs.getString("team_id");

                System.out.println(student_id + " " + voornaam + " " + achternaam + " " + leeftijd + " " + geboortedatum + " " + ict_vaardigheid + " " + team_id);
            }

            // Close the ResultSet, statement, and connection objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntopersoonsgegevens(int student_id, String voornaam, String achternaam, int leeftijd, String geboortedatum, String ict_vaardigheid, int team_id) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an INSERT query
            String query = "INSERT INTO Persoonsgegevens (student_id, voornaam, achternaam, leeftijd, geboortedatum, ict_vaardigheid,team_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the INSERT query
            pstmt.setInt(1, student_id);
            pstmt.setString(2, voornaam);
            pstmt.setString(3, achternaam);
            pstmt.setInt(4, leeftijd);
            pstmt.setString(5, geboortedatum);
            pstmt.setString(6, ict_vaardigheid);
            pstmt.setInt(7, team_id);

            // Execute the INSERT query
            pstmt.executeUpdate();

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFrompersoonsgegevens(int student_id) {
        Connection conn = createConnection();
        String query = "Delete from persoonsgegevens where student_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updatePersoonsgegevens(int newstudent_id, String newVoornaam, String newAchternaam, int newleeftijd, String newgeboortedatum, String newict_vaardigheid, int newteam_id) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an UPDATE query
            String query = "UPDATE Persoonsgegevens SET voornaam = ?, achternaam = ?, leeftijd = ?, geboortedatum = ?, ict_vaardigheid = ?, team_id = WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the UPDATE query
            pstmt.setString(1, newVoornaam);
            pstmt.setString(2, newAchternaam);
            pstmt.setInt(3, newleeftijd);
            pstmt.setString(4, newgeboortedatum);
            pstmt.setString(5, newict_vaardigheid);
            pstmt.setInt(7, newstudent_id);
            pstmt.setInt(6, newteam_id);


            // Execute the UPDATE query
            int numRowsUpdated = pstmt.executeUpdate();


            if (numRowsUpdated > 0) {
                System.out.println("Record with student_id = " + newstudent_id + " has been updated in Persoonsgegevns.");
            } else {
                System.out.println("No record with student_id = " + newstudent_id + " was found in Persoonsgegens.");
            }

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
