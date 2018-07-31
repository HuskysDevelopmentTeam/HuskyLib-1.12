package net.hdt.huskylib2.config;

import net.minecraftforge.common.config.Config;

import static net.hdt.huskylib2.HuskyLib.*;

@Config(modid = MOD_ID, name = "HuskyLib2/" + MOD_NAME)
public class ConfigHandler {

	public static Logging logging = new Logging();

	public static class Logging {
		@Config.LangKey("config.hl2:consoleBlockRegisteringLog")
		@Config.Comment("Should we log for every registered block?")
		public boolean consoleBlockRegisteringLog = true;

		@Config.LangKey("config.hl2:consoleItemRegisteringLog")
		@Config.Comment("Should we log for every registered block?")
		public boolean consoleItemRegisteringLog = true;

		@Config.LangKey("config.hl2:consoleRecipeRegisteringLog")
		@Config.Comment("Should we log for every registered block?")
		public boolean consoleRecipeRegisteringLog = true;
	}

}