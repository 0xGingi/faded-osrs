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
public enum SmithingAction implements ItemSkillAction
{
	BRONZE_BAR(ItemID.BRONZE_BAR, 1, 6.2f),
	BRONZE_AXE(ItemID.BRONZE_AXE, 1, 12.5f),
	BRONZE_DAGGER(ItemID.BRONZE_DAGGER, 1, 12.5f),
	BRONZE_MACE(ItemID.BRONZE_MACE, 2, 12.5f),
	BRONZE_MED_HELM(ItemID.BRONZE_MED_HELM, 3, 12.5f),
	BRONZE_BOLTS_UNF(ItemID.BRONZE_BOLTS_UNF, 3, 12.5f),
	BRONZE_NAILS(ItemID.BRONZE_NAILS, 4, 12.5f),
	BRONZE_SWORD(ItemID.BRONZE_SWORD, 4, 12.5f),
	BRONZE_WIRE(ItemID.BRONZE_WIRE, 4, 12.5f),
	BRONZE_DART_TIP(ItemID.BRONZE_DART_TIP, 4, 12.5f),
	BRONZE_ARROWTIPS(ItemID.BRONZE_ARROWTIPS, 5, 12.5f),
	BRONZE_SCIMITAR(ItemID.BRONZE_SCIMITAR, 5, 25),
	BRONZE_HASTA(ItemID.BRONZE_HASTA, 5, 25),
	BRONZE_SPEAR(ItemID.BRONZE_SPEAR, 5, 25),
	BRONZE_JAVELIN_HEADS(ItemID.BRONZE_JAVELIN_HEADS, 6, 12.5f),
	BRONZE_LONGSWORD(ItemID.BRONZE_LONGSWORD, 6, 25),
	BRONZE_LIMBS(ItemID.BRONZE_LIMBS, 6, 12.5f),
	BRONZE_KNIFE(ItemID.BRONZE_KNIFE, 7, 12.5f),
	BRONZE_FULL_HELM(ItemID.BRONZE_FULL_HELM, 7, 25),
	BRONZE_SQ_SHIELD(ItemID.BRONZE_SQ_SHIELD, 8, 25),
	BRONZE_WARHAMMER(ItemID.BRONZE_WARHAMMER, 9, 37.5f),
	BRONZE_BATTLEAXE(ItemID.BRONZE_BATTLEAXE, 10, 37.5f),
	BRONZE_CHAINBODY(ItemID.BRONZE_CHAINBODY, 11, 37.5f),
	BRONZE_KITESHIELD(ItemID.BRONZE_KITESHIELD, 12, 37.5f),
	BRONZE_CLAWS(ItemID.BRONZE_CLAWS, 13, 25),
	BRONZE_2H_SWORD(ItemID.BRONZE_2H_SWORD, 14, 37.5f),
	BARRONITE_DEPOSITS(ItemID.BARRONITE_DEPOSIT, 14, 30),
	IRON_BAR(ItemID.IRON_BAR, 15, 12.5f),
	IRON_DAGGER(ItemID.IRON_DAGGER, 15, 25),
	IRON_AXE(ItemID.IRON_AXE, 16, 25),
	BRONZE_PLATELEGS(ItemID.BRONZE_PLATELEGS, 16, 37.5f),
	BRONZE_PLATESKIRT(ItemID.BRONZE_PLATESKIRT, 16, 37.5f),
	IRON_SPIT(ItemID.IRON_SPIT, 17, 25),
	IRON_MACE(ItemID.IRON_MACE, 17, 25),
	IRON_BOLTS_UNF(ItemID.IRON_BOLTS_UNF, 18, 25),
	BRONZE_PLATEBODY(ItemID.BRONZE_PLATEBODY, 18, 62.5f),
	IRON_MED_HELM(ItemID.IRON_MED_HELM, 18, 25),
	IRON_NAILS(ItemID.IRON_NAILS, 19, 25),
	IRON_DART_TIP(ItemID.IRON_DART_TIP, 19, 25),
	IRON_SWORD(ItemID.IRON_SWORD, 19, 25),
	SILVER_BAR(ItemID.SILVER_BAR, 20, 13.7f),
	IRON_ARROWTIPS(ItemID.IRON_ARROWTIPS, 20, 25),
	IRON_SCIMITAR(ItemID.IRON_SCIMITAR, 20, 50),
	IRON_HASTA(ItemID.IRON_HASTA, 20, 50),
	IRON_SPEAR(ItemID.IRON_SPEAR, 20, 50),
	IRON_LONGSWORD(ItemID.IRON_LONGSWORD, 21, 50),
	IRON_JAVELIN_HEADS(ItemID.IRON_JAVELIN_HEADS, 21, 25),
	IRON_FULL_HELM(ItemID.IRON_FULL_HELM, 22, 50),
	IRON_KNIFE(ItemID.IRON_KNIFE, 22, 25),
	IRON_LIMBS(ItemID.IRON_LIMBS, 23, 25),
	IRON_SQ_SHIELD(ItemID.IRON_SQ_SHIELD, 23, 50),
	IRON_WARHAMMER(ItemID.IRON_WARHAMMER, 24, 75),
	IRON_BATTLEAXE(ItemID.IRON_BATTLEAXE, 25, 75),
	OIL_LANTERN_FRAME(ItemID.OIL_LANTERN_FRAME, 26, 25),
	IRON_CHAINBODY(ItemID.IRON_CHAINBODY, 26, 75),
	IRON_KITESHIELD(ItemID.IRON_KITESHIELD, 27, 75),
	IRON_CLAWS(ItemID.IRON_CLAWS, 28, 50),
	IRON_2H_SWORD(ItemID.IRON_2H_SWORD, 29, 75),
	STEEL_DAGGER(ItemID.STEEL_DAGGER, 30, 37.5f),
	STEEL_BAR(ItemID.STEEL_BAR, 30, 17.5f),
	IRON_PLATESKIRT(ItemID.IRON_PLATESKIRT, 31, 75),
	IRON_PLATELEGS(ItemID.IRON_PLATELEGS, 31, 75),
	STEEL_AXE(ItemID.STEEL_AXE, 31, 37.5f),
	STEEL_MACE(ItemID.STEEL_MACE, 32, 37.5f),
	IRON_PLATEBODY(ItemID.IRON_PLATEBODY, 33, 125),
	STEEL_MED_HELM(ItemID.STEEL_MED_HELM, 33, 37.5f),
	STEEL_BOLTS_UNF(ItemID.STEEL_BOLTS_UNF, 33, 37.5f),
	STEEL_DART_TIP(ItemID.STEEL_DART_TIP, 34, 37.5f),
	STEEL_NAILS(ItemID.STEEL_NAILS, 34, 37.5f),
	STEEL_SWORD(ItemID.STEEL_SWORD, 34, 37.5f),
	CANNONBALL(ItemID.CANNONBALL, 35, 25.6f),
	STEEL_SCIMITAR(ItemID.STEEL_SCIMITAR, 35, 75),
	STEEL_ARROWTIPS(ItemID.STEEL_ARROWTIPS, 35, 37.5f),
	STEEL_HASTA(ItemID.STEEL_HASTA, 35, 75),
	STEEL_SPEAR(ItemID.STEEL_SPEAR, 35, 75),
	STEEL_LIMBS(ItemID.STEEL_LIMBS, 36, 37.5f),
	STEEL_STUDS(ItemID.STEEL_STUDS, 36, 37.5f),
	STEEL_LONGSWORD(ItemID.STEEL_LONGSWORD, 36, 75),
	STEEL_JAVELIN_HEADS(ItemID.STEEL_JAVELIN_HEADS, 36, 37.5f),
	STEEL_KNIFE(ItemID.STEEL_KNIFE, 37, 37.5f),
	STEEL_FULL_HELM(ItemID.STEEL_FULL_HELM, 37, 75),
	STEEL_SQ_SHIELD(ItemID.STEEL_SQ_SHIELD, 38, 75),
	STEEL_WARHAMMER(ItemID.STEEL_WARHAMMER, 39, 112.5f),
	STEEL_BATTLEAXE(ItemID.STEEL_BATTLEAXE, 40, 112.5f),
	GOLD_BAR_GOLDSMITH_GAUNTLETS(ItemID.GOLD_BAR, 40, 56.2f)
	{
		@Override
		public String getName(final ItemManager itemManager)
		{
			return "Gold bar (Goldsmith gauntlets)";
		}

		@Override
		public boolean isMembers(final ItemManager itemManager)
		{
			return true;
		}
	},
	GOLD_BAR(ItemID.GOLD_BAR, 40, 22.5f),
	STEEL_CHAINBODY(ItemID.STEEL_CHAINBODY, 41, 112.5f),
	STEEL_KITESHIELD(ItemID.STEEL_KITESHIELD, 42, 112.5f),
	STEEL_CLAWS(ItemID.STEEL_CLAWS, 43, 75),
	STEEL_2H_SWORD(ItemID.STEEL_2H_SWORD, 44, 112.5f),
	STEEL_PLATELEGS(ItemID.STEEL_PLATELEGS, 46, 112.5f),
	STEEL_PLATESKIRT(ItemID.STEEL_PLATESKIRT, 46, 112.5f),
	STEEL_PLATEBODY(ItemID.STEEL_PLATEBODY, 48, 187.5f),
	BULLSEYE_LANTERN_UNF(ItemID.BULLSEYE_LANTERN_UNF, 49, 37),
	MITHRIL_DAGGER(ItemID.MITHRIL_DAGGER, 50, 50),
	MITHRIL_BAR(ItemID.MITHRIL_BAR, 50, 30),
	MITHRIL_AXE(ItemID.MITHRIL_AXE, 51, 50),
	MITHRIL_MACE(ItemID.MITHRIL_MACE, 52, 50),
	MITHRIL_MED_HELM(ItemID.MITHRIL_MED_HELM, 53, 50),
	MITHRIL_BOLTS_UNF(ItemID.MITHRIL_BOLTS_UNF, 53, 50),
	MITHRIL_SWORD(ItemID.MITHRIL_SWORD, 54, 50),
	MITHRIL_DART_TIP(ItemID.MITHRIL_DART_TIP, 54, 50),
	MITHRIL_NAILS(ItemID.MITHRIL_NAILS, 54, 50),
	MITHRIL_ARROWTIPS(ItemID.MITHRIL_ARROWTIPS, 55, 50),
	MITHRIL_SCIMITAR(ItemID.MITHRIL_SCIMITAR, 55, 100),
	MITHRIL_HASTA(ItemID.MITHRIL_HASTA, 55, 100),
	MITHRIL_SPEAR(ItemID.MITHRIL_SPEAR, 55, 100),
	MITHRIL_LONGSWORD(ItemID.MITHRIL_LONGSWORD, 56, 100),
	MITHRIL_JAVELIN_HEADS(ItemID.MITHRIL_JAVELIN_HEADS, 56, 50),
	MITHRIL_LIMBS(ItemID.MITHRIL_LIMBS, 56, 50),
	MITHRIL_FULL_HELM(ItemID.MITHRIL_FULL_HELM, 57, 100),
	MITHRIL_KNIFE(ItemID.MITHRIL_KNIFE, 57, 50),
	MITHRIL_SQ_SHIELD(ItemID.MITHRIL_SQ_SHIELD, 58, 100),
	MITH_GRAPPLE_TIP(ItemID.MITH_GRAPPLE_TIP, 59, 50),
	MITHRIL_WARHAMMER(ItemID.MITHRIL_WARHAMMER, 59, 150),
	DRAGON_SQ_SHIELD(ItemID.DRAGON_SQ_SHIELD, 60, 75),
	MITHRIL_BATTLEAXE(ItemID.MITHRIL_BATTLEAXE, 60, 150),
	MITHRIL_CHAINBODY(ItemID.MITHRIL_CHAINBODY, 61, 150),
	MITHRIL_KITESHIELD(ItemID.MITHRIL_KITESHIELD, 62, 150),
	MITHRIL_CLAWS(ItemID.MITHRIL_CLAWS, 63, 100),
	MITHRIL_2H_SWORD(ItemID.MITHRIL_2H_SWORD, 64, 150),
	MITHRIL_PLATESKIRT(ItemID.MITHRIL_PLATESKIRT, 66, 150),
	MITHRIL_PLATELEGS(ItemID.MITHRIL_PLATELEGS, 66, 150),
	MITHRIL_PLATEBODY(ItemID.MITHRIL_PLATEBODY, 68, 250),
	ADAMANT_DAGGER(ItemID.ADAMANT_DAGGER, 70, 62.5f),
	ADAMANTITE_BAR(ItemID.ADAMANTITE_BAR, 70, 37.5f),
	ADAMANT_AXE(ItemID.ADAMANT_AXE, 71, 62.5f),
	ADAMANT_MACE(ItemID.ADAMANT_MACE, 72, 62.5f),
	ADAMANT_BOLTS_UNF(ItemID.ADAMANT_BOLTSUNF, 73, 62.5f),
	ADAMANT_MED_HELM(ItemID.ADAMANT_MED_HELM, 73, 62.5f),
	ADAMANT_DART_TIP(ItemID.ADAMANT_DART_TIP, 74, 62.5f),
	ADAMANT_SWORD(ItemID.ADAMANT_SWORD, 74, 62.5f),
	ADAMANTITE_NAILS(ItemID.ADAMANTITE_NAILS, 74, 62.5f),
	ADAMANT_ARROWTIPS(ItemID.ADAMANT_ARROWTIPS, 75, 62.5f),
	ADAMANT_SCIMITAR(ItemID.ADAMANT_SCIMITAR, 75, 125),
	ADAMANT_HASTA(ItemID.ADAMANT_HASTA, 75, 125),
	ADAMANT_SPEAR(ItemID.ADAMANT_SPEAR, 75, 125),
	ADAMANTITE_LIMBS(ItemID.ADAMANTITE_LIMBS, 76, 62.5f),
	ADAMANT_LONGSWORD(ItemID.ADAMANT_LONGSWORD, 76, 125),
	ADAMANT_JAVELIN_HEADS(ItemID.ADAMANT_JAVELIN_HEADS, 76, 62.5f),
	ADAMANT_FULL_HELM(ItemID.ADAMANT_FULL_HELM, 77, 125),
	ADAMANT_KNIFE(ItemID.ADAMANT_KNIFE, 77, 62.5f),
	ADAMANT_SQ_SHIELD(ItemID.ADAMANT_SQ_SHIELD, 78, 125),
	ADAMANT_WARHAMMER(ItemID.ADAMANT_WARHAMMER, 79, 187.5f),
	ADAMANT_BATTLEAXE(ItemID.ADAMANT_BATTLEAXE, 80, 187.5f),
	ADAMANT_CHAINBODY(ItemID.ADAMANT_CHAINBODY, 81, 187.5f),
	ADAMANT_KITESHIELD(ItemID.ADAMANT_KITESHIELD, 82, 187.5f),
	ADAMANT_CLAWS(ItemID.ADAMANT_CLAWS, 83, 125),
	ADAMANT_2H_SWORD(ItemID.ADAMANT_2H_SWORD, 84, 187.5f),
	RUNITE_BAR(ItemID.RUNITE_BAR, 85, 50),
	RUNE_DAGGER(ItemID.RUNE_DAGGER, 85, 75),
	RUNE_AXE(ItemID.RUNE_AXE, 86, 75),
	ADAMANT_PLATESKIRT(ItemID.ADAMANT_PLATESKIRT, 86, 187.5f),
	ADAMANT_PLATELEGS(ItemID.ADAMANT_PLATELEGS, 86, 187.5f),
	RUNE_MACE(ItemID.RUNE_MACE, 87, 75),
	RUNITE_BOLTS_UNF(ItemID.RUNITE_BOLTS_UNF, 88, 75),
	RUNE_MED_HELM(ItemID.RUNE_MED_HELM, 88, 75),
	ADAMANT_PLATEBODY(ItemID.ADAMANT_PLATEBODY, 88, 312.5f),
	RUNE_SWORD(ItemID.RUNE_SWORD, 89, 75),
	RUNE_NAILS(ItemID.RUNE_NAILS, 89, 75),
	RUNE_DART_TIP(ItemID.RUNE_DART_TIP, 89, 75),
	RUNE_ARROWTIPS(ItemID.RUNE_ARROWTIPS, 90, 75),
	RUNE_SCIMITAR(ItemID.RUNE_SCIMITAR, 90, 150),
	RUNE_HASTA(ItemID.RUNE_HASTA, 90, 150),
	RUNE_SPEAR(ItemID.RUNE_SPEAR, 90, 150),
	DRAGONFIRE_SHIELD(ItemID.DRAGONFIRE_SHIELD, 90, 2000),
	RUNE_LONGSWORD(ItemID.RUNE_LONGSWORD, 91, 150),
	RUNE_JAVELIN_HEADS(ItemID.RUNE_JAVELIN_HEADS, 91, 75),
	RUNITE_LIMBS(ItemID.RUNITE_LIMBS, 91, 75),
	RUNE_KNIFE(ItemID.RUNE_KNIFE, 92, 75),
	RUNE_FULL_HELM(ItemID.RUNE_FULL_HELM, 92, 150),
	RUNE_SQ_SHIELD(ItemID.RUNE_SQ_SHIELD, 93, 150),
	RUNE_WARHAMMER(ItemID.RUNE_WARHAMMER, 94, 225),
	RUNE_BATTLEAXE(ItemID.RUNE_BATTLEAXE, 95, 225),
	RUNE_CHAINBODY(ItemID.RUNE_CHAINBODY, 96, 225),
	RUNE_KITESHIELD(ItemID.RUNE_KITESHIELD, 97, 225),
	RUNE_CLAWS(ItemID.RUNE_CLAWS, 98, 150),
	RUNE_PLATEBODY(ItemID.RUNE_PLATEBODY, 99, 375),
	RUNE_PLATESKIRT(ItemID.RUNE_PLATESKIRT, 99, 225),
	RUNE_PLATELEGS(ItemID.RUNE_PLATELEGS, 99, 225),
	RUNE_2H_SWORD(ItemID.RUNE_2H_SWORD, 99, 225),
	;

	private final int itemId;
	private final int level;
	private final float xp;
}
