package db;

import model.Filmas;

import java.sql.*;
import java.util.ArrayList;

public class DuombazesVeiksmai {
    public final static String DB_PAVADINIMAS = "jdbc:mysql://localhost:3306/java9_filmai?useUnicode=true&characterEncoding=UTF-8";
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "";

    public final static String FILMO_LENTELES_SUKURIMAS = "CREATE TABLE IF NOT EXISTS filmai(\n" +
            "    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "    pavadinimas VARCHAR(60) NOT NULL,\n" +
            "    trukme SMALLINT UNSIGNED NOT NULL,\n" +
            "    ivertinimas DECIMAL(4,2) UNSIGNED NOT NULL,\n" +
            "    aprasymas VARCHAR(300) NOT NULL,\n" +
            "    PRIMARY KEY(id)\n" +
            ")ENGINE=INNODB;";

    public final static String FILMO_IDEJIMAS = "INSERT INTO filmai(pavadinimas, trukme, ivertinimas, aprasymas)\n" +
            "VALUES\n" +
            "(?, ?, ?, ?);";

    public final static String FILMO_REDAGAVIMAS = "UPDATE filmai\n" +
            "SET pavadinimas = ?, trukme = ?, ivertinimas = ?, aprasymas = ?\n" +
            "WHERE id = ?;";

    public final static String FILMO_ISTRYNIMAS = "DELETE FROM filmai\n" +
            "WHERE id = ?;";

    public final static String VISU_FILMU_GAVIMAS = "SELECT * FROM filmai;";


    public static Connection prisijungtiPrieDb() throws SQLException {
        Connection jungtis = DriverManager.getConnection(DB_PAVADINIMAS, DB_USER, DB_PASSWORD);
        return jungtis;
    }

    public static boolean sukurtiLentele(Connection jungtis, String sqlUzklausa) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(sqlUzklausa);

        boolean ats = paruostukas.execute();
        paruostukas.close();

        return ats;
    }

    public static boolean idetiFilma(Connection jungtis, String pavadinimas, int trukme, double ivertinimas, String aprasymas) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_IDEJIMAS);

        paruostukas.setString(1, pavadinimas);
        paruostukas.setInt(2, trukme);
        paruostukas.setDouble(3, ivertinimas);
        paruostukas.setString(4, aprasymas);

        boolean ats = paruostukas.execute();
        paruostukas.close();

        return ats;
    }

    public static int atnaujintiFilma(Connection jungtis, String pavadinimas, int trukme, double ivertinimas, String aprasymas, int keiciamoId) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_REDAGAVIMAS);

        paruostukas.setString(1, pavadinimas);
        paruostukas.setInt(2, trukme);
        paruostukas.setDouble(3, ivertinimas);
        paruostukas.setString(4, aprasymas);
        paruostukas.setInt(5, keiciamoId);

        int ats = paruostukas.executeUpdate();
        paruostukas.close();

        return ats;
    }

    public static int istrintiFilma(Connection jungtis, int trinamoFilmoId) throws SQLException {
        PreparedStatement paruostukas = jungtis.prepareStatement(FILMO_ISTRYNIMAS);

        paruostukas.setInt(1, trinamoFilmoId);

        int ats = paruostukas.executeUpdate();
        paruostukas.close();

        return ats;
    }

    public static ArrayList<Filmas> grazintiVisusFilmus(Connection jungtis) throws SQLException {
        ArrayList<Filmas> filmai = new ArrayList<>();

        PreparedStatement paruostukas = jungtis.prepareStatement(VISU_FILMU_GAVIMAS);
        ResultSet rezultatas = paruostukas.executeQuery();

        while (rezultatas.next()) {
            int id = rezultatas.getInt("id");
            String pavadinimas = rezultatas.getString("pavadinimas");
            int trukme = rezultatas.getInt("trukme");
            double ivertinimas = rezultatas.getDouble("ivertinimas");
            String aprasymas = rezultatas.getString("aprasymas");

            Filmas filmas = new Filmas(id, pavadinimas, trukme, ivertinimas, aprasymas);

            filmai.add(filmas);
        }
        return filmai;
    }
}
