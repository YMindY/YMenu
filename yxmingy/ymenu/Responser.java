package yxmingy.ymenu;

import cn.nukkit.Player;
import cn.nukkit.command.ConsoleCommandSender;
import java.util.*;
import yxmingy.ymenu.Responser;
import yxmingy.yupi.*;

public class Responser extends ResponserBase{
    private Main own;
    public Responser(Main _own){
      own = _own;
    }
	public void response(int id,String index,Player player) {
	    for(Map.Entry<String,Object> e : own.conf.getAll().entrySet()){
	      if(Utils.buildId(e.getKey())==id){
	        int i = 0;
	        Map<String,Object> button,btconf = (LinkedHashMap<String,Object>)(e.getValue());
	        for(Object pair : btconf.values()){
	          if(!index.equals(String.valueOf(i++))) continue;
	          button = (Map<String,Object>)pair;
	          String sender,cmd;
	          cmd = (String)button.get("指令");
	          sender = cmd.substring(0,4);
	          cmd = cmd.substring(4);
	          cmd = cmd.replaceAll("@s",player.getName());
	          switch(sender){
	          case "%py:":
	            own.getServer().dispatchCommand(player,cmd);
	          break;
	          case "%op:":
	            boolean isop = player.isOp();
	            player.setOp(true);
	            own.getServer().dispatchCommand(player,cmd);
	            if(!isop) player.setOp(false);
	          break;
	          case "%ct:":
	            own.getServer().dispatchCommand(new ConsoleCommandSender(),cmd);
	          break;
	          default:
	            player.sendMessage("配置错误! ps: 是服主的锅，砍他!");
	          }
	        }
	      }
        }
	}
}
