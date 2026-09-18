package net.pomagierko.tutorialmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pomagierko.tutorialmod.TutorialMod;
import net.pomagierko.tutorialmod.item.custom.EntityDetectorItem;
import net.pomagierko.tutorialmod.item.custom.MetalDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector", () -> new MetalDetectorItem(new Item.Properties()
            .durability(128)));
    public static final RegistryObject<Item> ENTITY_DETECTOR = ITEMS.register("entity_detector", () -> new EntityDetectorItem(new Item.Properties()
            .durability(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
