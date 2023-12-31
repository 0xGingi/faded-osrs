/*
 * Copyright (c) 2021, Jordan Atwood <nightfirecat@protonmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.skillcalculator.skills;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;

@AllArgsConstructor
@Getter
public enum ConstructionAction implements NamedSkillAction
{
	EXIT_PORTAL("Exit Portal", 1, 100, ItemID.EXIT_PORTAL),
	PLANK("Plank", 1, 29, ItemID.PLANK),
	OAK_PLANK("Oak Plank", 1, 60, ItemID.OAK_PLANK),
	TEAK_PLANK("Teak Plank", 1, 90, ItemID.TEAK_PLANK),
	MAHOGANY_PLANK("Mahogany Plank", 1, 140, ItemID.MAHOGANY_PLANK),
	PLANT("Plant", 1, 31, ItemID.PLANT),
	FERN_BIG_PLANT("Fern (big plant)", 1, 31, ItemID.FERN_8186),
	SHORT_PLANT("Short Plant", 1, 31, ItemID.SHORT_PLANT),
	DOCK_LEAF("Dock Leaf", 1, 31, ItemID.DOCK_LEAF),
	CRUDE_WOODEN_CHAIR("Crude Wooden Chair", 1, 58, ItemID.CRUDE_WOODEN_CHAIR),
	BROWN_RUG("Brown Rug", 2, 30, ItemID.BROWN_RUG),
	TORN_CURTAINS("Torn Curtains", 2, 132, ItemID.TORN_CURTAINS),
	CLAY_FIREPLACE("Clay Fireplace", 3, 30, ItemID.CLAY_FIREPLACE),
	WOODEN_BOOKCASE("Wooden Bookcase", 4, 115, ItemID.WOODEN_BOOKCASE),
	FIREPIT("Firepit", 5, 40, ItemID.FIREPIT),
	CAT_BLANKET("Cat Blanket", 5, 15, ItemID.CAT_BLANKET),
	DECORATIVE_ROCK("Decorative Rock", 5, 100, ItemID.DECORATIVE_ROCK),
	TREE("Tree", 5, 31, ItemID.TREE),
	SMALL_FERN("Small Fern", 6, 70, ItemID.SMALL_FERN),
	THISTLE("Thistle", 6, 70, ItemID.THISTLE),
	BUSH("Bush", 6, 70, ItemID.BUSH),
	LARGE_LEAF_BUSH("Large Leaf Bush", 6, 70, ItemID.LARGELEAF_PLANT),
	WOODEN_SHELVES_1("Wooden Shelves 1", 6, 87, ItemID.WOODEN_SHELVES_1),
	PUMP_AND_DRAIN("Pump and Drain", 7, 100, ItemID.PUMP_AND_DRAIN),
	BEER_BARREL("Beer Barrel", 7, 87, ItemID.BEER_BARREL),
	WOODEN_CHAIR("Wooden Chair", 8, 87, ItemID.WOODEN_CHAIR),
	WOODEN_LARDER("Wooden Larder", 9, 228, ItemID.WOODEN_LARDER),
	WOOD_DINING_TABLE("Wood Dining Table", 10, 115, ItemID.WOOD_DINING_TABLE),
	POND("Pond", 10, 100, ItemID.POND),
	NICE_TREE("Nice Tree", 10, 44, ItemID.NICE_TREE),
	WOODEN_BENCH("Wooden Bench", 10, 115, ItemID.WOODEN_BENCH),
	FIREPIT_WITH_HOOK("Firepit with Hook", 11, 60, ItemID.FIREPIT_WITH_HOOK),
	REEDS("Reeds", 12, 100, ItemID.REEDS),
	FERN_SMALL_PLANT("Fern (small plant)", 12, 100, ItemID.FERN),
	CIDER_BARREL("Cider Barrel", 12, 91, ItemID.CIDER_BARREL),
	WOODEN_SHELVES_2("Wooden Shelves 2", 12, 147, ItemID.WOODEN_SHELVES_2),
	WOOD_TABLE("Wood Table", 12, 87, ItemID.WOOD_DINING_TABLE),
	HUGE_PLANT("Huge Plant", 12, 100, ItemID.HUGE_PLANT),
	TALL_PLANT("Tall Plant", 12, 100, ItemID.TALL_PLANT),
	RUG("Rug", 13, 60, ItemID.RUG),
	ROCKING_CHAIR("Rocking Chair", 14, 87, ItemID.ROCKING_CHAIR),
	IMP_STATUE("Imp Statue", 15, 150, ItemID.IMP_STATUE),
	OAK_TREE("Oak Tree", 15, 70, ItemID.OAK_TREE),
	OAK_DECORATION("Oak Decoration", 16, 120, ItemID.OAK_DECORATION),
	FIREPIT_WITH_POT("Firepit with Pot", 17, 80, ItemID.FIREPIT_WITH_POT),
	CURTAINS("Curtains", 18, 225, ItemID.CURTAINS),
	ASGARNIAN_ALE("Asgarnian Ale", 18, 184, ItemID.ASGARNIAN_ALE),
	CAT_BASKET("Cat Basket", 19, 58, ItemID.CAT_BASKET),
	OAK_CHAIR("Oak Chair", 19, 120, ItemID.OAK_CHAIR),
	WOODEN_BED("Wooden Bed", 20, 117, ItemID.WOODEN_BED),
	SHOE_BOX("Shoe Box", 20, 58, ItemID.SHOE_BOX),
	SHAVING_STAND("Shaving Stand", 21, 30, ItemID.SHAVING_STAND),
	OAK_DINING_TABLE("Oak Dining Table", 22, 240, ItemID.OAK_DINING_TABLE),
	OAK_BENCH("Oak Bench", 22, 240, ItemID.OAK_BENCH),
	WOODEN_SHELVES_3("Wooden Shelves 3", 23, 147, ItemID.WOODEN_SHELVES_3),
	SMALL_OVEN("Small Oven", 24, 80, ItemID.SMALL_OVEN),
	OAK_CLOCK("Oak Clock", 25, 142, ItemID.OAK_CLOCK),
	GREENMANS_ALE("Greenman's Ale", 26, 184, ItemID.GREENMANS_ALE),
	OAK_ARMCHAIR("Oak Armchair", 26, 180, ItemID.OAK_ARMCHAIR),
	ROPE_BELL_PULL("Rope Bell-Pull", 26, 64, ItemID.ROPE_BELLPULL),
	PUMP_AND_TUB("Pump and Tub", 27, 200, ItemID.PUMP_AND_TUB),
	OAK_DRAWERS("Oak Drawers", 27, 120, ItemID.OAK_DRAWERS),
	OAK_BOOKCASE("Oak Bookcase", 29, 180, ItemID.OAK_BOOKCASE),
	LARGE_OVEN("Large Oven", 29, 100, ItemID.LARGE_OVEN),
	OAK_SHAVING_STAND("Oak Shaving Stand", 29, 61, ItemID.OAK_SHAVING_STAND),
	WILLOW_TREE("Willow Tree", 30, 100, ItemID.WILLOW_TREE),
	OAK_BED("Oak Bed", 30, 210, ItemID.OAK_BED),
	LONG_BONE("Long Bone", 30, 4500, ItemID.LONG_BONE),
	CURVED_BONE("Curved Bone", 30, 6750, ItemID.CURVED_BONE),
	CARVED_OAK_BENCH("Carved Oak Bench", 31, 240, ItemID.CARVED_OAK_BENCH),
	CARVED_OAK_TABLE("Carved Oak Table", 31, 360, ItemID.CARVED_OAK_TABLE),
	OAK_KITCHEN_TABLE("Oak Kitchen Table", 32, 180, ItemID.TEAK_TABLE),
	BOXING_RING("Boxing Ring", 32, 420, ItemID.BOXING_RING),
	OAK_LARDER("Oak Larder", 33, 480, ItemID.OAK_LARDER),
	CUSHIONED_BASKET("Cushioned Basket", 33, 58, ItemID.CUSHIONED_BASKET),
	STONE_FIREPLACE("Stone Fireplace", 33, 40, ItemID.STONE_FIREPLACE),
	STEEL_RANGE("Steel Range", 34, 120, ItemID.STEEL_RANGE),
	OAK_SHELVES_1("Oak Shelves 1", 34, 240, ItemID.OAK_SHELVES_1),
	GLOVE_RACK("Glove Rack", 34, 120, ItemID.GLOVE_RACK),
	LARGE_OAK_BED("Large Oak Bed", 34, 330, ItemID.LARGE_OAK_BED),
	TEAK_ARMCHAIR("Teak Armchair", 35, 180, ItemID.TEAK_ARMCHAIR),
	DRAGON_BITTER("Dragon Bitter", 36, 224, ItemID.DRAGON_BITTER),
	TEAK_DECORATION("Teak Decoration", 36, 180, ItemID.TEAK_DECORATION),
	BELL_PULL("Bell-Pull", 37, 120, ItemID.BELLPULL),
	OAK_DRESSER("Oak Dresser", 37, 121, ItemID.OAK_DRESSER),
	TEAK_BENCH("Teak Bench", 38, 360, ItemID.CARVED_TEAK_BENCH),
	TEAK_TABLE("Teak Table", 38, 360, ItemID.TEAK_TABLE),
	OAK_WARDROBE("Oak Wardrobe", 39, 180, ItemID.OAK_WARDROBE),
	TEAK_BED("Teak Bed", 40, 300, ItemID.TEAK_BED),
	MAHOGANY_BOOKCASE("Mahogany Bookcase", 40, 420, ItemID.MAHOGANY_BOOKCASE),
	OAK_LECTERN("Oak Lectern", 40, 60, ItemID.OAK_LECTERN),
	OPULENT_CURTAINS("Opulent Curtains", 40, 315, ItemID.OPULENT_CURTAINS),
	FENCING_RING("Fencing Ring", 41, 570, ItemID.FENCING_RING),
	GLOBE("Globe", 41, 180, ItemID.GLOBE),
	FANCY_RANGE("Fancy Range", 42, 160, ItemID.FANCY_RANGE),
	CRYSTAL_BALL("Crystal Ball", 42, 280, ItemID.CRYSTAL_BALL),
	ALCHEMICAL_CHART("Alchemical Chart", 43, 30, ItemID.ALCHEMICAL_CHART),
	TEAK_LARDER("Teak larder", 43, 750, ItemID.TEAK_LARDER),
	WOODEN_TELESCOPE("Wooden Telescope", 44, 121, ItemID.OAK_TELESCOPE),
	WEAPONS_RACK("Weapons Rack", 44, 180, ItemID.WEAPONS_RACK),
	CARVED_TEAK_BENCH("Carved Teak Bench", 44, 360, ItemID.CARVED_TEAK_BENCH),
	OAK_SHELVES_2("Oak Shelves 2", 45, 240, ItemID.OAK_SHELVES_2),
	CARVED_TEAK_TABLE("Carved Teak Table", 45, 600, ItemID.CARVED_TEAK_TABLE),
	LARGE_TEAK_BED("Large Teak Bed", 45, 480, ItemID.LARGE_TEAK_BED),
	MAPLE_TREE("Maple Tree", 45, 122, ItemID.MAPLE_TREE),
	TEAK_DRESSER("Teak Dresser", 46, 181, ItemID.TEAK_DRESSER),
	SINK("Sink", 47, 300, ItemID.SINK),
	EAGLE_LECTERN("Eagle Lectern", 47, 120, ItemID.EAGLE_LECTERN),
	DEMON_LECTERN("Demon Lectern", 47, 120, ItemID.DEMON_LECTERN),
	MOUNTED_MYTHICAL_CAPE("Mounted Mythical Cape", 47, 370, ItemID.MYTHICAL_CAPE),
	CHEFS_DELIGHT("Chef's Delight", 48, 224, ItemID.CHEFS_DELIGHT),
	TEAK_PORTAL("Teak Portal", 50, 270, ItemID.TEAK_PORTAL),
	MAHOGANY_ARMCHAIR("Mahogany Armchair", 50, 280, ItemID.MAHOGANY_ARMCHAIR),
	ORNAMENTAL_GLOBE("Ornamental Globe", 50, 270, ItemID.ORNAMENTAL_GLOBE),
	TELEPORT_FOCUS("Teleport Focus", 50, 40, ItemID.TELEPORT_FOCUS),
	TEAK_DRAWERS("Teak Drawers", 51, 180, ItemID.TEAK_DRAWERS),
	COMBAT_RING("Combat Ring", 51, 630, ItemID.COMBAT_RING),
	TEAK_KITCHEN_TABLE("Teak Kitchen Table", 52, 270, ItemID.TEAK_TABLE),
	MAHOGANY_BENCH("Mahogany Bench", 52, 560, ItemID.MAHOGANY_BENCH),
	MAHOGANY_TABLE("Mahogany Table", 52, 840, ItemID.MAHOGANY_TABLE),
	FOUR_POSTER_BED("4-Poster Bed", 53, 450, ItemID._4POSTER),
	EXTRA_WEAPONS_RACK("Extra Weapons Rack", 54, 440, ItemID.EXTRA_WEAPONS_RACK),
	ELEMENTAL_SPHERE("Elemental Sphere", 54, 580, ItemID.ELEMENTAL_SPHERE),
	TEAK_CLOCK("Teak Clock", 55, 202, ItemID.TEAK_CLOCK),
	GILDED_DECORATION("Gilded Decoration", 56, 1020, ItemID.GILDED_DECORATION),
	FANCY_TEAK_DRESSER("Fancy Teak Dresser", 56, 182, ItemID.FANCY_TEAK_DRESSER),
	TEAK_SHELVES_1("Teak Shelves 1", 56, 330, ItemID.TEAK_SHELVES_1),
	TEAK_EAGLE_LECTERN("Teak Eagle Lectern", 57, 180, ItemID.TEAK_EAGLE_LECTERN),
	TEAK_DEMON_LECTERN("Teak Demon Lectern", 57, 180, ItemID.TEAK_DEMON_LECTERN),
	LIMESTONE_ATTACK_STONE("Limestone attack stone", 59, 200, ItemID.ATTACK_STONE),
	LUNAR_GLOBE("Lunar Globe", 59, 570, ItemID.LUNAR_GLOBE),
	GILDED_FOUR_POSTER_BED("Gilded 4-Poster Bed", 60, 1330, ItemID.GILDED_4POSTER),
	POSH_BELL_PULL("Posh Bell-Pull", 60, 420, ItemID.POSH_BELLPULL),
	SPICE_RACK("Spice Rack", 60, 374, ItemID.SPICE_RACK),
	YEW_TREE("Yew Tree", 60, 141, ItemID.YEW_TREE),
	GILDED_BENCH("Gilded Bench", 61, 1760, ItemID.GILDED_BENCH),
	TEAK_WARDROBE("Teak Wardrobe", 63, 270, ItemID.TEAK_WARDROBE),
	MARBLE_FIREPLACE("Marble Fireplace", 63, 500, ItemID.MARBLE_FIREPLACE),
	ASTRONOMICAL_CHART("Astronomical Chart", 63, 45, ItemID.ASTRONOMICAL_CHART),
	TEAK_TELESCOPE("Teak Telescope", 64, 181, ItemID.TEAK_TELESCOPE),
	MAHOGANY_DRESSER("Mahogany Dresser", 64, 281, ItemID.MAHOGANY_DRESSER),
	MAHOGANY_PORTAL("Mahogany Portal", 65, 420, ItemID.MAHOGANY_PORTAL),
	GREATER_FOCUS("Greater Focus", 65, 500, ItemID.GREATER_FOCUS),
	OPULENT_RUG("Opulent Rug", 65, 360, ItemID.OPULENT_RUG),
	TEAK_GARDEN_BENCH("Teak Garden Bench", 66, 540, ItemID.TEAK_GARDEN_BENCH),
	CRYSTAL_OF_POWER("Crystal of Power", 66, 890, ItemID.CRYSTAL_OF_POWER),
	TEAK_SHELVES_2("Teak Shelves 2", 67, 930, ItemID.TEAK_SHELVES_2),
	MAHOGANY_DEMON_LECTERN("Mahogany Demon Lectern", 67, 580, ItemID.TEAK_DEMON_LECTERN),
	MAHOGANY_EAGLE_LECTERN("Mahogany Eagle Lectern", 67, 580, ItemID.TEAK_DEMON_LECTERN),
	CELESTIAL_GLOBE("Celestial Globe", 68, 570, ItemID.CELESTIAL_GLOBE),
	DUNGEON_ENTRANCE("Dungeon Entrance", 70, 500, ItemID.DUNGEON_ENTRANCE),
	RANGING_PEDESTALS("Ranging Pedestals", 71, 720, ItemID.RANGING_PEDESTALS),
	OPULENT_TABLE("Opulent Table", 72, 3100, ItemID.OPULENT_TABLE),
	OAK_DOOR("Oak Door", 74, 600, ItemID.OAK_DOOR),
	GILDED_DRESSER("Gilded Dresser", 74, 582, ItemID.GILDED_DRESSER),
	MAHOGANY_WARDROBE("Mahogany Wardrobe", 75, 420, ItemID.MAHOGANY_WARDROBE),
	MAGIC_TREE("Magic Tree", 75, 223, ItemID.MAGIC_TREE),
	ARMILLARY_GLOBE("Armillary Globe", 77, 960, ItemID.GLOBE),
	GNOME_BENCH("Gnome Bench", 77, 840, ItemID.GNOME_BENCH),
	MARBLE_PORTAL("Marble Portal", 80, 1500, ItemID.MARBLE_PORTAL),
	SCRYING_POOL("Scrying Pool", 80, 2000, ItemID.SCRYING_POOL),
	BALANCE_BEAM("Balance Beam", 81, 1000, ItemID.BALANCE_BEAM),
	INFERNAL_CHART("Infernal Chart", 83, 60, ItemID.INFERNAL_CHART),
	MAHOGANY_TELESCOPE("Mahogany Telescope", 84, 281, ItemID.MAHOGANY_TELESCOPE),
	GILDED_CLOCK("Gilded Clock", 85, 602, ItemID.GILDED_CLOCK),
	SMALL_ORRERY("Small Orrery", 86, 1320, ItemID.SMALL_ORRERY),
	GILDED_WARDROBE("Gilded Wardrobe", 87, 720, ItemID.GILDED_WARDROBE),
	LARGE_ORRERY("Large Orrery", 95, 1420, ItemID.LARGE_ORRERY),
	;

	private final String name;
	private final int level;
	private final float xp;
	private final int icon;

	@Override
	public boolean isMembers(final ItemManager itemManager)
	{
		return true;
	}
}
