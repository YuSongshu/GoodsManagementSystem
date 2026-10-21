package mvc.service;

import mvc.bean.Goods;
import mvc.dao.GoodsDao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//针对goods表业务  商品信息的业务
public class GoodsService {

    //调用持久层
    private GoodsDao goodsDao = new GoodsDao();

    public boolean addGoods(HashMap<String, String> map) {
        //添加商品业务
        String name = map.get("name");
        String price = map.get("price");
        String cnt = map.get("cnt");
        String pic = map.get("pic");
        Goods goods = new Goods(name, Double.parseDouble(price), Integer.parseInt(cnt), pic);
        int rows = goodsDao.addGoods(goods);
        if (rows == 1) {
            return true;
        }
        return false;
    }


    public List<Goods> queryGoodsAll() {

        ArrayList<HashMap<String, Object>> list = goodsDao.queryGoodsAll();

        //业务逻辑
        ArrayList<Goods> goodsArrayList = new ArrayList<>();

        for (HashMap<String, Object> map : list) {
            Goods goods = new Goods(
                    Integer.parseInt(map.get("id").toString()),
                    map.get("name").toString(),
                    Double.parseDouble(map.get("price").toString()),
                    Integer.parseInt(map.get("cnt").toString()),
                    map.get("pic").toString()
            );
            goodsArrayList.add(goods);
        }

        return goodsArrayList;
    }

    public Goods queryGoodsById(String id) {

        ArrayList<HashMap<String, Object>> list = goodsDao.queryGoodsById(id);

        //数据 转为 Goods 返回
        HashMap<String, Object> map = list.get(0);
        Goods goods = new Goods(
                Integer.parseInt(map.get("id").toString()),
                map.get("name").toString(),
                Double.parseDouble(map.get("price").toString()),
                Integer.parseInt(map.get("cnt").toString()),
                map.get("pic").toString()
        );
        return goods;

    }

    public boolean updateGoodsById(HashMap<String, String> map) {
        //1.map中解析所有数据取出
        String id = map.get("id");
        String name = map.get("name");
        String price = map.get("price");
        String cnt = map.get("cnt");
        String pic = map.get("pic");
        String oldPic = map.get("oldPic");
        String path = map.get("path");

        int rows;
        boolean bol = true;

        //2.确认是否修改了图片
        if (pic.equals("没有上传文件")){
            //没有修改图片
            rows = goodsDao.updateGoods(id,name,price,cnt);
        }else {
            //一定修改了图片
            rows = goodsDao.updateGoodsByPic(id,name,price,cnt,pic);
            //图片删除
            if (!oldPic.equals("没有上传文件")){
                File file = new File(path + "\\" + oldPic);
                bol = file.delete();
            }
        }

        if (rows == 1 && bol){
            return true;
        }


        return false;
    }
    public boolean deleteGoods(String id, String path) {
        ArrayList<HashMap<String, Object>> list = goodsDao.queryGoodsById(id);
        if (list.isEmpty()) {
            return false;
        }
        String pic = list.get(0).get("pic").toString();

        if (!"没有上传文件".equals(pic)) {
            File file = new File(path + "\\" + pic);
            file.delete();
        }

        int rows = goodsDao.deleteGoods(id);
        return rows == 1;
    }
}
