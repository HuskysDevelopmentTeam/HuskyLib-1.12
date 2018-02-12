package net.thegaminghuskymc.huskylib.enums;

import net.minecraft.util.IStringSerializable;
import net.thegaminghuskymc.huskylib.blocks.EnumBlock.IEnumMeta;

public enum EnumMaterialType implements IStringSerializable, IEnumMeta{

    RED("red", 0),
    BLUE("blue", 1),
    PURPLE("purple", 2),
    GREEN("green", 3),
    ZINC("zinc", 4),
    TUNGSTEN("tungsten", 5),
    BRASS("brass", 6),
    TESSELITE("tesselite", 7),
    PINK("pink", 8),
    YELLOW("yellow", 9),
    CYAN("cyan", 10),
    LIGHT_BLUE("light_blue", 11);

    public static final EnumMaterialType[] VALUES = new EnumMaterialType[]{
            RED,
            BLUE,
            PURPLE,
            GREEN,
            ZINC,
            TUNGSTEN,
            BRASS,
            TESSELITE,
            PINK,
            YELLOW,
            CYAN,
            LIGHT_BLUE
    };
    
    private static final EnumMaterialType[] METADATA_LOOKUP = new EnumMaterialType[values().length];

    static {
        for (EnumMaterialType type : values()) {
            METADATA_LOOKUP[type.getMeta()] = type;
        }
    }

    private int index;
    private String name;

    EnumMaterialType(String name, int index){
        this.index = index;
        this.name = name;
    }
    
    public static EnumMaterialType byMetadata(int metadata) {

        if (metadata < 0 || metadata >= METADATA_LOOKUP.length) {
            metadata = 0;
        }
        return METADATA_LOOKUP[metadata];
    }

    public int getMeta(){
        return this.index;
    }

    public String getName(){
        return this.name;
    }

    public static String[] toStringArray(){
        String[] array = new String[VALUES.length];

        for(int i = 0; i < array.length; i++){
            array[i] = VALUES[i].name;
        }

        return array;
    }

}
