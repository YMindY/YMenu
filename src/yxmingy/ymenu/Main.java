package yxmingy.ymenu;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.command.*;
import cn.nukkit.event.*;


public class Main extends PluginBase implements Listener{
	private Config conf;
	public void onEnable() {
		getLogger().notice("YMenu is Enabled! Author: xMing.");
		conf = new Config(getDataFolder()+"/Config,yml",Config.YAML);
		/*[
		 * name=>
		 *   [
		 *     {指令}=>(cmd),
		 *     {图标}=>(file)
		 *   ]
		 * ]
		 */
		getServer().getPluginManager().registerEvents(this, this);
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
		
		return true;
	}
}
