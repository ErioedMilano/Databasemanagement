import java.sql.*;
import java.util.Scanner;

public class Bpproject {
    public static void main(String[] args) {
        printOptions();
        Scanner sc = new Scanner(System.in);

        int selection = sc.nextInt();
        while (selection != 13){
            int student_id = 0;
            String voornaam = "";
            String achternaam = "";
            int leeftijd = 0;
            String geboortedatum = "";
            String ict_vaardigheid = "";
            int team_id = 0;
            String teamnaam = "";
            int contactnummer = 0;
            String unasat_emailadres = "";
            String verblijfplaats = "";


            switch (selection){
                case 1:
                    readFromPersoonsGegevens();
                    break;
                case 2:
                    readFromTeamGegevens();
                    break;
                case 3:
                    readFromContactGegevens();
                    break;
                case 4:
                    System.out.println("Voer de student_id van de persoon in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    System.out.println("Voer de voornaam van de persoon in:");
                    sc.nextLine();
                    voornaam = sc.nextLine();

                    System.out.println("Voer de achternaam van de persoon in:");
                    achternaam = sc.nextLine();
                    System.out.println("Voer de leeftijd van de persoon in:");

                    leeftijd = sc.nextInt();
                    System.out.println("Voer de geboortedatum van de persoon in:");
                    sc.nextLine();
                    geboortedatum = sc.nextLine();

                    System.out.println("Voer de ict_vaardigheid van de persoon in:");
                    ict_vaardigheid = sc.nextLine();


                    System.out.println("Voer de team_id van de persoon in:");
                    team_id = sc.nextInt();

                    updatePersoonsGegevens(voornaam, achternaam, leeftijd, geboortedatum, ict_vaardigheid, team_id, student_id);
                    break;
                case 5:
                    System.out.println("Voer de team_id in:");
                    team_id = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Voer de teamnaam in:");
                    teamnaam = sc.nextLine();

                    updateTeamGegevens(team_id,teamnaam);
                    break;
                case 6:
                    System.out.println("Voer de student_id van de gebruiker in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    System.out.println("Voer het contactnummer van de gebruiker  in:");
                    contactnummer = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Voer het unasat_emailadres van de gebruiker in:");
                    unasat_emailadres = sc.nextLine();

                    System.out.println("Voer de verblijfplaats van de gebruiker in:");
                    verblijfplaats = sc.nextLine();

                    updatecontactgegevens(contactnummer, unasat_emailadres, verblijfplaats, student_id);
                    break;
                case 7:
                    System.out.println("Voer uw student_id in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    System.out.println("Voer uw voornaam in:");
                    sc.nextLine();
                    voornaam = sc.nextLine();

                    System.out.println("Voer uw achternaam in:");
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

                    insertIntoPersoonsGegevens(student_id,voornaam,achternaam,leeftijd,geboortedatum,ict_vaardigheid,team_id);
                    break;
                case 8:
                    System.out.println("Voer uw team_id in:");
                    team_id = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Voer uw teamnaam in:");
                    teamnaam = sc.nextLine();

                    insertIntoTeamGegevens(team_id,teamnaam);
                    break;
                case 9:
                    System.out.println("Voer uw student_id in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    System.out.println("Voer uw contactnummer in:");
                    contactnummer = sc.nextInt();

                    sc.nextLine();

                    System.out.println("Voer uw unasat_emailadres in:");
                    unasat_emailadres = sc.nextLine();

                    System.out.println("Voer uw verblijfplaats in:");
                    verblijfplaats = sc.nextLine();

                    insertIntoContactGegevens(student_id, contactnummer, unasat_emailadres, verblijfplaats);
                    break;
                case 10:
                    System.out.println("Voer de student_id van de persoon in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    deleteFromPersoonsGegevens(student_id);
                    break;
                case 11:
                    System.out.println("Voer de team id van de groep in:");
                    team_id = sc.nextInt();

                    deleteFromTeamGegevens(team_id);
                    break;
                case 12:
                    System.out.println("Voer de student_id van de persoon in:");
                    sc.nextLine();
                    student_id = sc.nextInt();

                    deleteFromContactGegevens(student_id);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            printOptions();
            selection = sc.nextInt();
        }
        System.out.println("Thank you for using this application. Goodbye!");
    }

    public static void printOptions(){
        System.out.println("-------------------");
        System.out.println("List of commands");
        System.out.println("1: Display list of users");
        System.out.println("2: Display list of groups");
        System.out.println("3: Display list of contact details");
        System.out.println("4: Edit user by id");
        System.out.println("5: Edit group");
        System.out.println("6: Edit user's contact details");
        System.out.println("7: Create user");
        System.out.println("8: Create group");
        System.out.println("9: Create user's contact details");
        System.out.println("10: Delete user");
        System.out.println("11: Delete group");
        System.out.println("12: Delete user's contact details");
        System.out.println("13: Exit application");
        System.out.println("Select your option:");
    }

    public static Connection createConnection(){
        // Define the database connection URL, username, and password
        String url = "jdbc:mysql://localhost/javaproject";
        String username = "root";
        String password = "12345678";
        // Create a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void readFromPersoonsGegevens(){
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

                System.out.println(student_id + " " + voornaam + " " + achternaam + " " + leeftijd + " " +geboortedatum + " " + ict_vaardigheid + " " + team_id);
            }

            // Close the ResultSet, statement, and connection objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromTeamGegevens(){
        try {

            Connection conn = createConnection();
            Statement stmt = conn.createStatement();

            // Execute a SELECT query to retrieve data from the table
            ResultSet rs = stmt.executeQuery("SELECT team_id, teamnaam FROM teamgegevens");

            // Process the ResultSet object to retrieve the data
            while (rs.next()) {
                String team_id = rs.getString("team_id");
                String teamnaam = rs.getString("teamnaam");

                System.out.println(team_id + " " + teamnaam);
            }

            // Close the ResultSet, statement, and connection objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromContactGegevens(){
        try {

            Connection conn = createConnection();
            Statement stmt = conn.createStatement();

            // Execute a SELECT query to retrieve data from the table
            ResultSet rs = stmt.executeQuery("SELECT student_id, contactnummer, unasat_emailadres, verblijfplaats FROM contactgegevens");

            // Process the ResultSet object to retrieve the data
            while (rs.next()) {
                String student_id = rs.getString("student_id");
                String contactnummer = rs.getString("contactnummer");
                String unasat_emailadres = rs.getString("unasat_emailadres");
                String verblijfplaats = rs.getString("verblijfplaats");

                System.out.println(student_id + " " + contactnummer + " " + unasat_emailadres + " " + verblijfplaats);
            }

            // Close the ResultSet, statement, and connection objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoPersoonsGegevens(int student_id, String voornaam, String achternaam, int leeftijd, String geboortedatum, String ict_vaardigheid, int team_id) {
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

            System.out.println("User created succesfully!");

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoTeamGegevens(int team_id, String teamnaam) {
        try {
            Connection conn = createConnection();

            String query = "INSERT INTO teamgegevens (team_id, teamnaam) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, team_id);
            pstmt.setString(2, teamnaam);

            // Execute the INSERT query
            pstmt.executeUpdate();

            System.out.println("Group created succesfully!");

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoContactGegevens(int student_id, int contactnummer, String unasat_emailadres, String verblijfplaats) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an INSERT query
            String query = "INSERT INTO contactgegevens (student_id, contactnummer, unasat_emailadres, verblijfplaats) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the INSERT query
            pstmt.setInt(1, student_id);
            pstmt.setInt(2,contactnummer);
            pstmt.setString(3, unasat_emailadres);
            pstmt.setString(4, verblijfplaats);

            // Execute the INSERT query
            pstmt.executeUpdate();

            System.out.println("User's contact details created succesfully!");

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromPersoonsGegevens(int student_id) {
        Connection conn = createConnection();
        String query = "Delete from persoonsgegevens where student_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();

            System.out.println("The user has been succesfully deleted!");

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteFromTeamGegevens(int team_id) {
        Connection conn = createConnection();
        String query = "Delete from teamgegevens where team_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, team_id);
            pstmt.executeUpdate();

            System.out.println("The group has been succesfully deleted!");

            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFromContactGegevens(int student_id) {
        Connection conn = createConnection();
        String query = "Delete from contactgegevens where student_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();

            System.out.println("The user's contact details has been succesfully deleted!");

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updatePersoonsGegevens(String newVoornaam, String newAchternaam, int newleeftijd, String newgeboortedatum, String newict_vaardigheid, int newteam_id, int newstudent_id) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an UPDATE query
            String query = "UPDATE Persoonsgegevens SET voornaam = ?, achternaam = ?, leeftijd = ?, geboortedatum = ?, ict_vaardigheid = ?, team_id = ? WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the UPDATE query
            pstmt.setString(1, newVoornaam);
            pstmt.setString(2, newAchternaam);
            pstmt.setInt(3, newleeftijd);
            pstmt.setString(4, newgeboortedatum);
            pstmt.setString(5, newict_vaardigheid);
            pstmt.setInt(6, newteam_id);
            pstmt.setInt(7, newstudent_id);



            // Execute the UPDATE query
            int numRowsUpdated = pstmt.executeUpdate();


            if (numRowsUpdated > 0) {
                System.out.println("Record with student_id = " + newstudent_id + " has been updated in Persoonsgegevens.");
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

    public static void updateTeamGegevens(int newteam_id, String newteamnaam) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an UPDATE query
            String query = "UPDATE teamgegevens SET teamnaam = ? WHERE team_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the UPDATE query
            pstmt.setString(1, newteamnaam);
            pstmt.setInt(2, newteam_id);



            // Execute the UPDATE query
            int numRowsUpdated = pstmt.executeUpdate();


            if (numRowsUpdated > 0) {
                System.out.println("Record with team_id = " + newteam_id + " has been updated in teamgegevens.");
            } else {
                System.out.println("No record with team_id = " + newteam_id + " was found in teamgegevens.");
            }

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updatecontactgegevens(int newcontactnummer, String newunasat_emailadres, String newverblijfplaats, int newstudent_id) {
        try {
            Connection conn = createConnection();

            // Create a PreparedStatement object to execute an UPDATE query
            String query = "UPDATE contactgegevens SET contactnummer = ?, unasat_emailadres = ?, verblijfplaats = ? WHERE student_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            // Set the parameter values for the UPDATE query
            pstmt.setInt(1, newcontactnummer);
            pstmt.setString(2, newunasat_emailadres);
            pstmt.setString(3, newverblijfplaats);
            pstmt.setInt(4, newstudent_id);



            // Execute the UPDATE query
            int numRowsUpdated = pstmt.executeUpdate();


            if (numRowsUpdated > 0) {
                System.out.println("Record with student_id = " + newstudent_id + " has been updated in contactgegevens.");
            } else {
                System.out.println("No record with student_id = " + newstudent_id + " was found in contactgegevens.");
            }

            // Close the PreparedStatement and connection objects
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}






