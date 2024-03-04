package MythicalMoney.Classes;

import MythicalMoney.Utility.StringUtility;

public class Obtainable {
    public String single;
    public String plural;
    public Item drop;

    public void initialize (final String single, final String plural, final String drop) {
        this.single = StringUtility.title (single);
        this.plural = StringUtility.title (plural);
        this.drop = Item.find (drop);
    }

    public Obtainable (final String single, final String plural, final String drop) {
        this.initialize (single, plural, drop);
    }

    public Obtainable (final String single, final String drop) {
        final String plural = single + "s";
        this.initialize (single, plural, drop);
    }
}
