package cn.nukkit.block;

import cn.nukkit.block.BlockSolid;
import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

public abstract class BlockElement extends BlockSolid {

    protected BlockElement() {
        // meta has been removed in the latest version
    }

    @Override
    public double getHardness() {
        return 0;
    }

    @Override
    public double getResistance() {
        return 0;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.BLACK_BLOCK_COLOR; //TODO: check ElementBlock::getMapColor -- 07/30/2022
    }
}
