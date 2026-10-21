package mvc.bean;

public class User {   //实体类：承载业务数据，user表对应
    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private Double money;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public User(Integer id, String username, String password, Integer role, Double money, String createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.money = money;
        this.createTime = createTime;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", money=" + money +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
