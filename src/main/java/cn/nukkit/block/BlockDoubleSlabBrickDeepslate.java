package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;

/**
 * @author GoodLucky777
 */
public class BlockDoubleSlabBrickDeepslate extends BlockSolid {
    public BlockDoubleSlabBrickDeepslate() {
        
    }
    
    @Override
    public int getId() {
        return DEEPSLATE_BRICK_DOUBLE_SLAB;
    }

    @Override
    public String getName() {
        return "Double Deepslate Brick Slab";
    }

    @Override
    public double getHardness() {
        return 3.5;
    }
    
    @Override
    public double getResistance() {
        return 6;
    }
    
    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
    
    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }
    
    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }
    
    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(DEEPSLATE_BRICK_SLAB), this.getDamage() & 0x07);
    }
    
    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe()) {
            Item slab = toItem();
            slab.setCount(2);
            return new Item[]{ slab };
        } else {
            return new Item[0];
        }
    }

}
