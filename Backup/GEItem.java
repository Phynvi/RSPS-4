/**
 * @author 64base
 */
public class GEItem {

	public client owner;
	public String name;
	public int min, max, amount, id;
	public boolean bought = false;

	public GEItem(client player, int item, int[] prices, int amnt)
	{
		bought = false;
		id = item;
		min = 1;
		max = 10;
		owner = player;
		name = player.getItemName(item).replaceAll("_", " ").toLowerCase();
		amount = amnt;
		if (prices.length < 2) return;
		min = prices[0];
		max = prices[1];
		if (min <= 0) min = 1;
		if (max <= 0) max = 5;
		if (min > max) min = max / 2;
	}

	public int getPrice(int buying)
	{
		return ((max / 2) + min) * buying;
	}

	public boolean sell (client buyer, int amtToBuy)
	{
		if (buyer.itemAmount(995) < getPrice(amtToBuy))
		{
			buyer.sendMessage("Not enough coins to buy this item.");
			return false;
		}
		buyer.deleteItem(995, buyer.getItemSlot(995), getPrice(amtToBuy));
		owner.addItem(995, getPrice(amtToBuy) + (min / 4));
		return true;
	}
	
	public boolean giveTo(client buyer, int buyingAmount)
	{
		int buy = buyingAmount;
		if (buyer.itemAmount(id) > amount)
		{
			buy = amount;
			buyer.sendMessage("There was only " + amount + " of this item..");
			buyer.sendMessage("..you have yet to buy "+ (buyer.itemAmount(id) - amount)+ " of this item.");
		}
		if (buy <= 0)
		{
			buyer.sendMessage("You can't buy no amount of this item..");
			return false;
		}
		if (buyer.itemAmount(995) < getPrice(buy))
		{
			buyer.sendMessage("Not enough coins to buy this item.");
			return false;
		}
		bought = sell(buyer, buy);
		return bought;
	}

	public client owner()
	{
		return owner;
	}
}