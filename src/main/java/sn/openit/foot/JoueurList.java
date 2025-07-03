package sn.openit.foot;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class JoueurList {
    public static Joueur RAFAEL_NADAL = new Joueur( "Rafael", "Nadal", LocalDate.of(1986, Month.JUNE, 3), new Rank(1, 5000) );
    public static Joueur NOVAK_DJOKOVIC = new Joueur( "Novak", "Djokovic", LocalDate.of(1987, Month.MAY, 22), new Rank(2, 4000) );
    public static Joueur ROGER_FEDERER = new Joueur( "Roger", "Federer", LocalDate.of(1981, Month.AUGUST, 8), new Rank(3, 3000) );
    public static Joueur ANDY_MURRAY = new Joueur( "Andy", "Murray", LocalDate.of(1987, Month.MAY, 15), new Rank(4, 2000) );
    public static  List<Joueur> ALL = Arrays.asList(RAFAEL_NADAL, NOVAK_DJOKOVIC, ROGER_FEDERER, ANDY_MURRAY);
}
