package yxmingy.ymenu;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import yxmingy.yupi.UISender;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.nukkit.Player;
import cn.nukkit.block.BlockButtonStone;
import cn.nukkit.command.*;


public class Main extends PluginBase{
	private Config conf;
	public void onEnable() {
		getLogger().notice("YMenu is Enabled! Author: xMing.");
		conf = new Config(getDataFolder()+"/Config,yml",Config.YAML);
		/*[
		 * {菜单名}=>
		 *   [
		 *     {按钮名}=>
		 *     [
		 *       {指令}=>(cmd),
		 *       {图标}=>(file)
		 *     ]
		 *   ]
		 * ]
		 */
		getServer().getPluginManager().registerEvents(new Responser(), this);;
	}
	public void onDisable() {
		getLogger().warning("YMenu is Turned Off!");
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int length = args.length;
		if(length != 1) return false;
		if(!(conf.exists(args[0]))) {
			sender.sendMessage("没有名为"+args[0]+"的菜单！");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("控制台去死！");
			return true;
		}
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>(),
				                          buttons = new LinkedHashMap<String, Object>();
		data.put("type", "form");
		data.put("title", args[0]);
		
		UISender.sendUI(526817087, data, (Player)sender);
		return true;
	}
}
