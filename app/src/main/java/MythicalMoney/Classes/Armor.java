package MythicalMoney.Classes;

import java.util.ArrayList;

import MythicalMoney.Classes.Display.DisplayPlus;

public class Armor {
	public int health;
	public int defense;
	public DisplayPlus display;

	public static ArrayList <Armor> armors = new ArrayList <Armor> ();

	public static enum ArmorType {
		Helmet,
		Chestplate,
		Leggings,
		Boots
	}
	public Armor (int health, int defense, DisplayPlus display, ArmorType armorType) {
		this.health = health;
		this.defense = defense;
		this.display = display;

		armors.add (this);
	}

	public Armor (int health, DisplayPlus display) {
		this.health = health;
		this.defense = 0;
		this.display = display;

		armors.add (this);
	}

	public static int indexOf (final String name) {
		final int size = armors.size ();
		for (int index = 0; index < size; index++) {
			final Armor armor = armors.get (index);
			if (armor.display.name.equals (name)) {
				return index;
			}
		}
		return -1;
	}

	public static int indexOf (final Armor armor) {
		return indexOf (armor.display.name);
	}

	public static Armor get (final String name) {
		final int index = indexOf (name);
		final Armor armor = armors.get (index);
		
		return armor;
	}
}