package net.thegaminghuskymc.huskylib.tiles;

public class TilePoweredBase extends TileEntityBase{
	
	public static int maxInput = 8192;
	public static int baseOutput = 16;
	public static int storagePerBlock = 1000000;
	public static int extraIOPerBlock = 8;

	public int connectedBlocks = 0;
	private double euChange;
	private int ticks;
	
	public double getEuChange() {
		if (euChange == -1) {
			return 0;
		}
		return (euChange / ticks);
	}

}
