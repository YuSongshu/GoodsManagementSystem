package mvc.bean;

public class Goods {
    private Integer id;
    private String name;
    private Double price;
    private Integer cnt;
    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Goods(Integer id, String name, Double price, Integer cnt, String pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cnt = cnt;
        this.pic = pic;
    }

    public Goods(String name, Double price, Integer cnt, String pic) {
        this.name = name;
        this.price = price;
        this.cnt = cnt;
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", cnt=" + cnt +
                ", pic='" + pic + '\'' +
                '}';
    }
}
