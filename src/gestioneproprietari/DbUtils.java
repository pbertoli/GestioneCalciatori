package gestioneproprietari;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DbUtils {

    public static Connection con = null;

    public static boolean initDb(String db) {
        System.out.println("Connecting database...");
        String url = "jdbc:mysql://localhost:3306/" + db
                + "?zeroDateTimeBehavior "
                + "= convertToNull&serverTimezone = UTC";
        try {
            con = DriverManager.getConnection(url, "root", "root");
            System.out.println("Database connected to " + con.getCatalog());
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: \t" + ex.getMessage());
            System.out.println("SQLState: \t" + ex.getSQLState());
            System.out.println("VendorError: \t" + ex.getErrorCode());
            System.out.println("Cannot connect the database!" + ex);
            return false;
        }
    }

    public static ArrayList elencaProprietari() {
        Statement stmt;
        ResultSet rs;
        String SQL = "SELECT * FROM proprietario LIMIT 10;";
        String s = "";
        ArrayList list = new ArrayList();
        try {
            System.out.println("Elenco dei proprietari");
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String nome = rs.getString("Nome");
                String targa = rs.getString("Targa");
                int anno = rs.getInt("Anno");
                s = " - " + nome + "\t" + targa + "\t" + anno;
                list.add(s);
                System.out.println(s);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Scorrimento tabella con errori" + e);
        }
        return list;
    }

    public static void insertProprietario(String nome,
            String targa, int anno) {
        Statement stmt = null;
        String SQL = "INSERT INTO proprietario (nome,targa,anno)"
                + "VALUES ('" + nome + "','" + targa + "'," + anno + ");";
        try {
            System.out.println(SQL);
            stmt = con.createStatement();
            int n = stmt.executeUpdate(SQL);
            System.out.println("Inserite " + n + " righe.");
            JOptionPane.showMessageDialog(null, "Inserite " + n + " righe.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Inserimento non riuscito."
                    + "Verifica la seguente comunicazione \n"+e);
        }
    }

    public static void deleteProprietario(String nome, String targa) {
        Statement stmt = null;
        String SQL = "DELETE FROM proprietario "
                + "WHERE nome='" + nome + "' AND targa='" + targa + "';";
        try {
            System.out.println(SQL);
            stmt = con.createStatement();
            int n = stmt.executeUpdate(SQL);
            System.out.println("Cancellate " + n + " righe.");
            JOptionPane.showMessageDialog(null, "Cancellate " + n + " righe.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void createStoredProcedure() {
        Statement stmt = null;
        String SQL = "CREATE PROCEDURE QuantoSpendo (OUT spesa INT)"
                + "BEGIN SELECT SUM(tasse) FROM Moto INTO spesa;"
                + "SELECT spesa; END;";
        try {
            System.out.println("Inserimento procedura calcolo tasse.");
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            System.out.println("Tutto è andato bene.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void useStoredProcedure() {
        ResultSet rs = null;
        CallableStatement cs = null;
        try {
            System.out.println("Chiamata procedura calcolo tasse.");
            cs = con.prepareCall("{call QuantoSpendo(?)}");// 1 out parameter
            rs = cs.executeQuery();
            if (rs.next()) {
                BigDecimal spesa = rs.getBigDecimal("spesa");
                System.out.println("Spesa in tasse: " + spesa + " €");
            }
            rs.close();
            cs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Può aprire una transazione, chiuderla, rifiutarla ecc:
    public static void doCommand(String command) {
        Statement stmt = null;
        try {
            System.out.println("Esecuzione del comando " + command);
            stmt = con.createStatement();
            stmt.executeUpdate(command + ";");
            System.out.println("Comando completato.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void test() {
        DbUtils.initDb("test");
        DbUtils.elencaProprietari();
        DbUtils.insertProprietario("Mario", "54321", 2000);
        DbUtils.doCommand("commit");
        DbUtils.elencaProprietari();
        DbUtils.deleteProprietario("Mario", "54321");
        DbUtils.elencaProprietari();
        DbUtils.doCommand("rollback");
    }
}
