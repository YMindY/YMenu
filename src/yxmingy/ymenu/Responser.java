package yxmingy.ymenu;

import cn.nukkit.Player;
import yxmingy.yupi.ResponserBase;

public class Responser extends ResponserBase{
	public void response(int id,String index,Player player) {
		if(id!=526817087) return;
		player.sendMessage(index);
	}
}
