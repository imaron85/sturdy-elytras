# Sturdy Elytras
Sturdy Elytras adds the **Sturdyness** enchantment to the game. In the late-game, Elytras tend to be far too fragile, especially for technical players flying thousands of blocks. Constantly having to swap to a new one from your Ender Chest is just annoying.

### How it works
Sturdyness can only be applied to Elytras and acts as a **second layer of Unbreaking**.
*   It stacks **multiplicatively** with vanilla Unbreaking III.
*   The game checks Unbreaking first; if that fails, it checks Sturdyness.
*   With **Sturdyness V** + **Unbreaking III**, your Elytra effectively has **~23x** the durability of a standard Elytra.

### The Trade-off: Mending Costs
This durability comes at a price. The sturdier the Elytra, the harder it is to repair in the field.
The XP required to repair the item via **Mending scales exponentially** (2^Level) based on the Sturdyness level:

*   **Level I:** 2x XP cost (50% Mending efficiency)
*   **Level III:** 8x XP cost
*   **Level V:** 32x XP cost (~3% Mending efficiency)

At max level, the Elytra rarely breaks, but fully repairing it with XP becomes a significant investment.

### Compatibility
To use this mod properly, it is required on both client and server.
*   **Client only:** The enchantment won't be accessible/visible.
*   **Server only:** Clients won't see the enchantment glow or name, but the durability and repair logic will still work perfectly if an operator or server-side recipe provides the item.
*   **Mod Compatibility:** This mod uses the Item Tag `#sturdy_elytras:sturdyness_applicable`. Other mods can add their custom wings to this tag to automatically support Sturdyness.