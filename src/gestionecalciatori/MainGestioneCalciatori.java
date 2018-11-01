package gestionecalciatori;

public class MainGestioneCalciatori {

    public static void main(String[] args) {
        
        if (DbUtils.initDb("test")) {
            new GestioneCalciatori();
        } else {
            System.out.println("Il DBServer è avviato? Non mi sembra!");
        }
    }

}
