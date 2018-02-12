package net.thegaminghuskymc.huskylib.utils;

public interface IInitializer {

    public abstract boolean preInit();

    public abstract boolean initialize();

    public abstract boolean postInit();
    
}