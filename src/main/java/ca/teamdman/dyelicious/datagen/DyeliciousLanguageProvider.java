package ca.teamdman.dyelicious.datagen;

import ca.teamdman.dyelicious.Dyelicious;
import ca.teamdman.dyelicious.common.registry.DyeliciousItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class DyeliciousLanguageProvider extends LanguageProvider {
	public DyeliciousLanguageProvider(PackOutput output) {
		super(output, Dyelicious.MOD_ID, "en_us");
	}


	@Override
	protected void addTranslations() {
		DyeliciousItems.SEEDS_LOOKUP.forEach((colour, regobj) -> {
			String name = colour.name()
					.substring(0, 1)
					.toUpperCase() + colour.name()
					.substring(1)
					.toLowerCase() + " dye seeds";
			Item item = regobj.get();
			this.add(item, name);
		});

		this.add("item_group.dyelicious.main", "Dyelicious");
	}
}
