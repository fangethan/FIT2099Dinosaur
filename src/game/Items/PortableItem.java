package game.Items;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	/**
	 * portableItem constructor
	 * @param name name of the portable item
	 * @param displayChar character to display what a portable item is on the map
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
