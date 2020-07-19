package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.event.block.BlockFadeEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

import java.util.concurrent.ThreadLocalRandom;

public class BlockCoral extends BlockFlowable {

    public static final int TYPE_TUBE = 0;
    public static final int TYPE_BRAIN = 1;
    public static final int TYPE_BUBBLE = 2;
    public static final int TYPE_FIRE = 3;
    public static final int TYPE_HORN = 4;
    
    public BlockCoral() {
        this(0);
    }
    
    public BlockCoral(int meta) {
        super(meta);
    }
    
    @Override
    public int getId() {
        return CORAL;
    }

    @Override
    public int getWaterloggingLevel() {
        return 2;
    }
    
    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            Block down = this.down();
            if (!down.isSolid()) {
                this.getLevel().useBreakOn(this);
            } else if (!isDead()) {
                this.getLevel().scheduleUpdate(this, 60 + ThreadLocalRandom.current().nextInt(40));
            }
            return type;
        } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (!this.isDead() && !(this.getLevelBlockAtLayer(1) instanceof BlockWater)  && !(this.getLevelBlockAtLayer(1) instanceof BlockIceFrosted)) {
                BlockFadeEvent event = new BlockFadeEvent(this, new BlockCoral(this.getDamage() | 0x8));
                if (!event.isCancelled()) {
                    this.setDead(true);
                    this.getLevel().setBlock(this, event.getNewState(), true, true);
                }
            }
            return type;
        }
        return 0;
    }
    
    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        Block down = this.down();
        Block layer1 = block.getLevelBlockAtLayer(1);
        boolean hasWater = layer1 instanceof BlockWater;
        int waterDamage;

        System.out.println("Is DEATH? "+isDead());
        System.out.println("Colour: "+getColor().toString());

        if (layer1.getId() != Block.AIR && (!hasWater || ((waterDamage = layer1.getDamage()) != 0) && waterDamage != 8)) {
            return false;
        }

        if (hasWater && layer1.getDamage() == 8) {
            this.getLevel().setBlock(this, 1, new BlockWater(), true, false);
        }

        if (down.isSolid()) {
            this.getLevel().setBlock(this, 0, this, true, true);
            return true;
        }
        return false;
    }
    
    @Override
    public String getName() {
        String[] names = new String[] {
                "Tube Coral",
                "Brain Coral",
                "Bubble Coral",
                "Fire Coral",
                "Horn Coral",
                // Invalid
                "Tube Coral",
                "Tube Coral",
                "Tube Coral"
        };
        String name = names[this.getDamage() & 0x7];
        if (this.isDead()) {
            return "Dead " + name;
        } else {
            return name;
        }
    }
    
    @Override
    public BlockColor getColor() {
        if (this.isDead()) {
            return BlockColor.GRAY_BLOCK_COLOR;
        }
        
        BlockColor[] colors = new BlockColor[] {
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.PINK_BLOCK_COLOR,
                BlockColor.PURPLE_BLOCK_COLOR,
                BlockColor.RED_BLOCK_COLOR,
                BlockColor.YELLOW_BLOCK_COLOR,
                // Invalid
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.BLUE_BLOCK_COLOR,
                BlockColor.BLUE_BLOCK_COLOR
        };
        return colors[this.getDamage() & 0x7];
    }
    
    @Override
    public boolean canSilkTouch() {
        return true;
    }
    
    @Override
    public Item[] getDrops(Item item) {
        if (item.getEnchantment(Enchantment.ID_SILK_TOUCH) != null) {
            return super.getDrops(item);
        } else {
            return new Item[0];
        }
    }

    public boolean isDead() {
        return (this.getDamage() & 0x8) == 0x8;
    }

    public void setDead(boolean dead) {
        if (dead) {
            this.setDamage(this.getDamage() | 0x8);
        } else {
            this.setDamage(this.getDamage() ^ 0x8);
        }
    }
}
