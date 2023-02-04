package ca.teamdman.dyelicious.common.registry;

import ca.teamdman.dyelicious.common.items.DyeSeedsItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ca.teamdman.dyelicious.Dyelicious.MOD_ID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class DyeliciousItems {
	private static final DeferredRegister<Item> ITEMS      = DeferredRegister.create(
			ForgeRegistries.ITEMS,
			MOD_ID
	);


	public static final HashMap<DyeColor, RegistryObject<DyeSeedsItem>> SEEDS_LOOKUP         = new HashMap<>();
	public static final List<RegistryObject<? extends Item>>                      REGISTRY_OBJECT_LIST = new ArrayList<>();

	static {
		for (DyeColor colour : DyeColor.values()) {
			var regobj = ITEMS.register("seeds_"+colour.name().toLowerCase(), ()->new DyeSeedsItem(colour));
			REGISTRY_OBJECT_LIST.add(regobj);
			SEEDS_LOOKUP.put(colour, regobj);
		}
	}

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
	}
	public static CreativeModeTab tab;

	@SubscribeEvent
	public static void onRegister(CreativeModeTabEvent.Register event) {
		tab = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "main"), builder ->
				// Set name of tab to display
				builder.title(Component.translatable("item_group." + MOD_ID + ".main"))
						// Set icon of creative tab
						.icon(() -> new ItemStack(Items.WHEAT_SEEDS))
						// Add default items to tab
						.displayItems((enabledFlags, populator, hasPermissions) -> {
							populator.accept(SEEDS_LOOKUP.get(DyeColor.GREEN).get());
							var x = DyeliciousItems.REGISTRY_OBJECT_LIST.stream()
									.map(RegistryObject::get)
									.map(ItemStack::new)
									.toList();
							populator.acceptAll(x);
						})
		);
	}
}
