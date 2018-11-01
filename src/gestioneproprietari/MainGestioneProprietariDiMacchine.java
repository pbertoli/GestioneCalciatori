package gestioneproprietari;

public class MainGestioneProprietariDiMacchine {

    public static void main(String[] args) {
        
        if (DbUtils.initDb("test")) {
            new GestionePropietari();
        } else {
            System.out.println("Il DBServer è avviato? Non mi sembra!");
        }
    }

}
