package ca.teamdman.dyelicious.common.items;

import ca.teamdman.dyelicious.common.registry.DyeliciousBlocks;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

public class DyeSeedsItem extends ItemNameBlockItem {
	public static DyeColor COLOUR;

	public DyeSeedsItem(DyeColor colour) {
		super(DyeliciousBlocks.CROP_LOOKUP.get(colour).get(), new Item.Properties());
		this.COLOUR = colour;
	}
}
