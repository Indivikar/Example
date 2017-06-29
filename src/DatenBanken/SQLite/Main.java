package DatenBanken.SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Erstellt eine Datenbank-Datei und legt eine Tabelle an, mit Inhalt und gibt es dann aus

public class Main
{
  public static void main(String[] args) throws Exception
  {
    Class.forName("org.sqlite.JDBC");
    Connection conn = DriverManager.getConnection("jdbc:sqlite:indivikar.db");
    Statement stat = conn.createStatement();
    stat.executeUpdate("drop table if exists test;");
    stat.executeUpdate("CREATE TABLE test (name, occupation);");
    PreparedStatement prep = conn.prepareStatement("INSERT INTO test VALUES (?, ?);");

    prep.setString(1, "Gandhi");
    prep.setString(2, "politics");
    prep.addBatch();

    prep.setString(1, "Turing");
    prep.setString(2, "computers");
    prep.addBatch();

    prep.setString(1, "Wittgenstein");
    prep.setString(2, "smartypants");
    prep.addBatch();

    conn.setAutoCommit(false);
    prep.executeBatch();
    conn.setAutoCommit(true);

    ResultSet rs = stat.executeQuery("SELECT * FROM test;");
    while (rs.next())
    {
      System.out.println("name = " + rs.getString("name"));
      System.out.println("job = " + rs.getString("occupation"));
    }
    rs.close();
    conn.close();
  }
}
