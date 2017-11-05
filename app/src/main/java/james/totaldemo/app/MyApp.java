package james.totaldemo.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.greendao3.dao.DaoMaster;
import com.greendao3.dao.DaoSession;

/**
 * Created by james on 2017/11/5.
 */

public class MyApp  extends Application {
    private static MyApp application;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setDatabase();
    }
    private void setDatabase() {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this,"notes-db", null);
        db =mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        //Daomaster对象来获取一个DaoSession对象
        mDaoSession = mDaoMaster.newSession();
    }
    public  static MyApp getApplication(){//构造方法
        return  application;//调用getApplication()方法得到 MyAPP 对象
    }
    public DaoSession getDaoSession() {
        return mDaoSession;//调用MyAPP的getDaoSession() 方法得到DaoSession对象
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
