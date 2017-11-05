package james.totaldemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 1，主键Id，必须包装类Long型，避免各种坑
   2，DaoSession实例需要单例
   3，当Bean变量被改变（或任何涉及数据库的改变），
     rebuild后数据库对应的表结构也会随之改变，这时必须schema配置必须将schemaVersion +1
 */
//注意：准备做数据库存储的Bean，必须添加@Entity注解
@Entity
public class User {
    //此处有坑，务必注意
    //注意：必须使用包装类型Long，而非基本类型long
    @Id
    private Long id;
    private String name;
    private int age;
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    //下面省去了 setter/getter
    //注意：get、set方法，无需自己生成，当此类创建完成后，rebuild项目，将自动生成
    //注意：如果需要toString，toString是需要自己创建生成的

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
