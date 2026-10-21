package mvc.dao;

import mvc.bean.Goods;
import mvc.tools.DbUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodsDao {

    private DbUtils dbUtils = new DbUtils();

    public int addGoods(Goods goods) {
        String sql = "insert into goods (name,price,cnt,pic) values ('"+goods.getName()+"','"+goods.getPrice()+"','"+goods.getCnt()+"','"+goods.getPic()+"')";
        return dbUtils.update(sql);
    }

    public ArrayList<HashMap<String, Object>> queryGoodsAll() {
        String sql = "select * from goods";
        return dbUtils.query(sql);

    }

    public ArrayList<HashMap<String, Object>> queryGoodsById(String id) {
        String sql = "select * from goods where id = '"+id+"'";
        return dbUtils.query(sql);
    }

    //修改商品信息
    public int updateGoods(String id, String name, String price, String cnt) {
        String sql = "update goods set name='"+name+"',price='"+price+"',cnt='"+cnt+"' where id = '"+id+"'";
        return dbUtils.update(sql);
    }


    //修改商品信息 带 图片信息修改
    public int updateGoodsByPic(String id, String name, String price, String cnt, String pic) {
        String sql = "update goods set name='"+name+"',price='"+price+"',cnt='"+cnt+"',pic='"+pic+"' where id = '"+id+"'";
        return dbUtils.update(sql);
    }

    // 删除
    public int deleteGoods(String id) {
        String sql = "delete from goods where id = '" + id + "'";
        return dbUtils.update(sql);
    }
}
