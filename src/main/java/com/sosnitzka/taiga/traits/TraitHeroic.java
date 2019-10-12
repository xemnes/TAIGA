package com.sosnitzka.taiga.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitHeroic extends AbstractTrait {

    public TraitHeroic() {
        super("heroic", TextFormatting.DARK_GRAY);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        int durability = ToolHelper.getCurrentDurability(tool);
        int durabilitymax = ToolHelper.getMaxDurability(tool);

        int safeDenominator = noLessThanOne(durabilitymax - durability - 1);

        float calc = newDamage + (newDamage / 2) / (durability * durabilitymax / safeDenominator);

        if ((float) durability < (float) (0.125 * durabilitymax) || player.getHealth() < player.getMaxHealth() / 8 || (target.getHealth() == target.getMaxHealth() && random.nextFloat() > 0.8)) {
            return super.damage(tool, player, target, damage, calc, isCritical);
        } else return super.damage(tool, player, target, damage, newDamage * 0.9f, isCritical);
    }

    private int noLessThanOne(int input){
        if(input <= 1)
            return 1;
        else
            return input;
    }
}
