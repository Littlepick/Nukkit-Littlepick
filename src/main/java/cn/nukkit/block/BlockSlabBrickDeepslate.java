package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;

/**
 * @author GoodLucky777
 */
public class BlockSlabBrickDeepslate extends BlockSlab {
    public BlockSlabBrickDeepslate() {
        this(0);
    }
    
    public BlockSlabBrickDeepslate(int meta) {
        super(meta, DEEPSLATE_BRICK_SLAB);
    }
    
    @Override
    public int getId() {
        return DEEPSLATE_BRICK_SLAB;
    }
    
    @Override
    public String getName() {
        return "Deepslate Brick";
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
        return new ItemBlock(this, this.getDamage() & 0x07);
    }

}
