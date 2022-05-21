import java.sql.*;

/**
 * This class is responsible for handling the sql statements to store and fetch data from database
 */
public class DataManager {

    /**
     * This main method is for database configuration only
     * @param args
     */
    public static void main(String args[]){
        //create weather database
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

//            String sql = "CREATE TABLE WEATHER " +
//                    "(USZIPCODE INT PRIMARY KEY     NOT NULL," +
//                    " DATA           TEXT    NOT NULL)";
//            stmt.executeUpdate(sql);

            stmt.execute("DELETE FROM WEATHER");
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if given us zip code has been stored, if yes the returned data would not be null
     * @param targetZipCode the US zip code that the application wich to check if has data stored
     * @return will return the weather data as a string if exist, otherwise a null
     */
    public String checkCache(Integer targetZipCode){
        Connection c = null;
        Statement stmt = null;
        String weatherData = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM WEATHER;" );
            while ( rs.next() ) {
                int USZipCode = rs.getInt("uszipcode");
                if (USZipCode == targetZipCode){
                    weatherData = rs.getString("data");
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("checked done successfully");
        if (weatherData == null) {
            System.out.println("no data found about " + targetZipCode);
        }
        return weatherData;
    }

    /**
     * store relevant weather data in the weather database
     * @param USZipCode
     * @param weatherData
     */
    public void storeData(Integer USZipCode, String weatherData){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");

            stmt = c.createStatement();
            String sql = "INSERT INTO WEATHER (USZIPCODE, DATA) " +
                    "VALUES (" + USZipCode + ",\'" + weatherData + "\');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updata db with new data passed from application
     * @param USZipCode
     * @param weatherData
     */
    public void updateData(Integer USZipCode, String weatherData){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE WEATHER set DATA =\'" + weatherData + "\'where USZIPCODE=" + USZipCode + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * print all database
     */
    public void printAllData(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");
            c.setAutoCommit(false);
            System.out.println("open db successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM WEATHER;" );
            System.out.println("print stmt excuted");
            while ( rs.next() ) {
                int USZipCode = rs.getInt("USZIPCODE");
                String weatherData = rs.getString("DATA");
                System.out.println(USZipCode);
                System.out.println(weatherData);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * clean database
     */
    public void cleanDB(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:weather.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.execute("DELETE FROM weather");
            System.out.println("delete table");
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}
