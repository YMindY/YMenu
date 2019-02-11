package yxmingy.ymenu;
import cn.nukkit.Player;
import cn.nukkit.command.*;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import java.util.*;
import yxmingy.yupi.ui.MultiOption;


public class Main extends PluginBase{
	public Config conf;
	public void onEnable() {
		getLogger().notice("YMenu is Enabled! Author: xMing.");
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>(),
		                              menu = new LinkedHashMap<String, Object>(),
		                              button = new LinkedHashMap<String, Object>();
		button.put("指令","%py:kill");
		button.put("图标","textures/items/apple.png");
		menu.put("自己来",button.clone());
		button.put("指令","%op:kill");
		button.put("图标","textures/items/apple_golden.png");
		menu.put("op权限来",button.clone());
		button.put("指令","%ct:kill @s");
		button.put("图标","无");
		menu.put("控制台来帮你",button);
		data.put("自杀",menu);
		conf = new Config(getDataFolder()+"/Config.yml",Config.YAML,data);
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
		getServer().getPluginManager().registerEvents(new Responser(this), this);
	}
	public void onDisable() {
		getLogger().warning("YMenu is Turned Off!");
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length != 1) return false;
		if(!(conf.exists(args[0]))) {
			sender.sendMessage("没有名为"+args[0]+"的菜单！");
			return true;
		}
		if(!(sender instanceof Player)) {
			sender.sendMessage("控制台去死！");
			return true;
		}
		MultiOption ui = new MultiOption(args[0]);
		ui.setContent("");
		Map<String, Object> pair,btconf = (LinkedHashMap<String,Object>)(conf.get(args[0]));
		String imagepath;
		for(Map.Entry<String, Object> entry : btconf.entrySet()){
		    pair = (Map<String,Object>)entry.getValue();
		    imagepath = (String)pair.get("图标");
		    if(!(imagepath.equals("无"))){
		        ui.addButton(entry.getKey(),true,imagepath);
		    }else{
		      ui.addButton(entry.getKey());
		    }
		}
		ui.send((Player)sender);
		return true;
	}
}
