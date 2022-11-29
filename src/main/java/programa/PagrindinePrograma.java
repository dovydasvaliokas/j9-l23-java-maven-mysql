package programa;

import db.DuombazesVeiksmai;
import model.Filmas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagrindinePrograma {
    public static void main(String[] args) {
        try {
            Connection jungtis = DuombazesVeiksmai.prisijungtiPrieDb();
            DuombazesVeiksmai.sukurtiLentele(jungtis, DuombazesVeiksmai.FILMO_LENTELES_SUKURIMAS);
            DuombazesVeiksmai.idetiFilma(jungtis, "Toy Story", 127, 4.12, "Žaislų istorija");
            DuombazesVeiksmai.atnaujintiFilma(jungtis, "Miškas", 147, 3.76, "Filmas apie girią", 2);
            DuombazesVeiksmai.istrintiFilma(jungtis, 2);

            ArrayList<Filmas> filmai = DuombazesVeiksmai.grazintiVisusFilmus(jungtis);
            System.out.println("filmai = " + filmai);

            jungtis.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko prisijungti prie duomenų bazės");
        }
    }
}
