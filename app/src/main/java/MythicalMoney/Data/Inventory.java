package MythicalMoney.Data;

import org.json.JSONException;
import org.json.JSONObject;

import MythicalMoney.Classes.Item;

public class Inventory {
	public int [] items;
	public static final int totalItems = Item.items.size ();
	
	public Inventory () {
		this.items = new int [totalItems];
		for (int index = 0; index < items.length; index++) {
			this.items [index] = 0;
		}
	}

	public static Inventory fromJSON (JSONObject jsonObject) {
		Inventory inventory = new Inventory();
		inventory.setup (jsonObject);
		return inventory;
	}

	public static JSONObject toJSON (Inventory inventory) {
		JSONObject jsonObject = new JSONObject ();
		for (int index = 0; index < inventory.items.length; index++) {
			Item item = Item.items.get (index);
			final int amount = inventory.items [index];

			jsonObject.put (item.display.name, amount);
		}
		
		return jsonObject;
	}

	public void setup (JSONObject jsonObject) {
		final Item [] items = Item.toList ();
		for (int index = 0; index < items.length; index++) {
			final Item item = items [index];
			String name = item.display.name;
			int amount;
			
			try {
				amount = jsonObject.getInt (name);
			} catch (JSONException jsonException) {
				amount = 0;
			}

			this.items [index] = amount;
		}
	}

	public int get (String item) {
		int index = Item.indexOf (item);
		return this.items [index];
	}

	public int get (Item item) {
		return get (item.display.name);
	}

	public int add (Item item, int amount) {
		final int newAmount = get (item) + amount;
		final int index = Item.indexOf (item);
		this.items [index] = newAmount;
		return newAmount;
	}

	public int subtract (Item item, int amount) {
		return add (item, 0 - amount);
	}
}
