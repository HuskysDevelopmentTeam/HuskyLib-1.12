package net.hdt.huskylib2.interf;

import net.minecraft.util.IStringSerializable;

public interface IVariantEnumHolder<T extends Enum<T> & IStringSerializable> {

    public static final String HEADER = "variant";

    public Class<T> getVariantEnum();

}
