package Data.Inventory;

public class Inventory {
	public int [] items;
	public static final int totalItems = Item.items.size ();
	
	public Inventory () {
		this.items = new int [totalItems];
		for (int index = 0; index < items.size; index += 1) {
			this.items [index] = 0;
		}
	}

	public static Inventory fromJSON (JSONObject jsonObject) {
		return null;
	}
}
