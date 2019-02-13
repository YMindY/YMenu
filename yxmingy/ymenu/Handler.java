package yxmingy.ymenu;

import java.util.*;

import yxmingy.yupi.*;

import cn.nukkit.Player;
import cn.nukkit.command.ConsoleCommandSender;

public class Handler extends HandlerBase {
  private Main own;

  public Handler(String title,Main own) {
    super(title);
    this.own = own;
  }

  public void handle(String data, Player player) {
    for (Map.Entry<String, Object> e : own.conf.getAll().entrySet()) {
      if (e.getKey().equals(title)) {
        int i = 0;
        Map<String, Object> button, btconf = (LinkedHashMap<String, Object>) (e.getValue());
        for (Object pair : btconf.values()) {
          if (!data.equals(String.valueOf(i++))) continue;
          button = (Map<String, Object>) pair;
          String sender, cmd;
          cmd = (String) button.get("指令");
          sender = cmd.substring(0, 4);
          cmd = cmd.substring(4);
          cmd = cmd.replaceAll("@s", player.getName());
          switch (sender) {
            case "%py:":
              own.getServer().dispatchCommand(player, cmd);
              break;
            case "%op:":
              boolean isop = player.isOp();
              player.setOp(true);
              own.getServer().dispatchCommand(player, cmd);
              if (!isop) player.setOp(false);
              break;
            case "%ct:":
              own.getServer().dispatchCommand(new ConsoleCommandSender(), cmd);
              break;
            default:
              player.sendMessage("配置错误! ps: 是服主的锅，砍他!");
          }
        }
      }
    }
  }
}
