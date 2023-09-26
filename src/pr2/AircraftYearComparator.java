package pr2;

import java.util.Comparator;

public class AircraftYearComparator implements Comparator<Aircraft> {
    @Override
    public int compare(Aircraft aircraft1, Aircraft aircraft2) {
        // Obtener los años de registro de cada avión
        int year1 = aircraft1.getYear();
        int year2 = aircraft2.getYear();

        // Comparar los años de registro
        return Integer.compare(year1, year2);
    }
}