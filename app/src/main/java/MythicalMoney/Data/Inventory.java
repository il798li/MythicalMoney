package MythicalMoney.Data;

import org.json.JSONObject;

import MythicalMoney.Classes.Item;

public class Inventory {
	public int [] items;
	public static final int totalItems = Item.items.size ();
	
	public Inventory () {
		this.items = new int [totalItems];
		for (int index = 0; index < items.length; index += 1) {
			this.items [index] = 0;
		}
	}

	public static Inventory fromJSON (JSONObject jsonObject) {
		return null;
	}
}
