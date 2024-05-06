package MythicalMoney.Classes.Helpers;

import MythicalMoney.Utility.BasicUtility;

import java.util.ArrayList;

public class Chances {

    public final int[] chances;
    public final int[] chancesList;
    public final int sum;

    public Chances (int... chances) {
        this.sum = sum (chances);
        this.chances = chances;
        final ArrayList <Integer> integerArrayList = new ArrayList <Integer> ();
        for (int index = 0; index < chances.length; index++) {
            final int weight = chances[index];
            for (int loop = 0; loop < weight; loop++) {
                integerArrayList.add (index);
            }
        }
        this.chancesList = BasicUtility.toList (integerArrayList);
    }

    public static int sum (int... chances) {
        int sum = 0;
        for (int chance : chances) {
            sum += chance;
        }
        return sum;
    }

    public int random () {
        double random = Math.random ();
        random *= this.sum + 1;
        return (int) random;
    }

    public int chance () {
        final int random = this.random ();
        return this.chancesList[random];
    }

    public static final class ChancesPlus extends Chances {

        public ChancesPlus (int chance1, int chance2, int chance3, int chance4) {
            super (chance1, chance2, chance3, chance4);
        }
    }
}
