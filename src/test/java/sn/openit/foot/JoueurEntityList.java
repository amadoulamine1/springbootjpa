package sn.openit.foot;

import sn.openit.foot.data.JoueurEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class JoueurEntityList {
    public static JoueurEntity RAFAEL_NADAL = new JoueurEntity("Nadal", "Rafael", LocalDate.of(1986, Month.JUNE, 3), 5000, 1);
    public static JoueurEntity NOVAK_DJOKOVIC = new JoueurEntity("Djokovic", "Novak", LocalDate.of(1987, Month.MAY, 22), 4000, 2);
    public static JoueurEntity ROGER_FEDERER = new JoueurEntity("Federer", "Roger", LocalDate.of(1981, Month.AUGUST, 8), 3000, 3);
    public static JoueurEntity ANDY_MURRAY = new JoueurEntity("Murray", "Andy", LocalDate.of(1987, Month.MAY, 15), 2000, 4);
    public static List<JoueurEntity> ALL = Arrays.asList(ROGER_FEDERER, ANDY_MURRAY, NOVAK_DJOKOVIC, RAFAEL_NADAL);
}

