package yxmingy.ymenu;

import cn.nukkit.Player;
import yxmingy.ymenu.Responser;
import yxmingy.yupi.ResponserBase;

public class Responser extends ResponserBase{
    private Main own;
    public Responser(Main _own){
      own = _own;
    }
	public void response(int id,String index,Player player) {
	    for(String key : own.conf.getAll().keySet()){
	      if(Main.buildId(key)==id){
	        player.sendMessage(String.valueOf(index.length())+"["+index+"]");
	      }
        }
	}
}
