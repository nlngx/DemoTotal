package james.totaldemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.greendao3.dao.UserDao;

import java.util.List;

import james.totaldemo.R;
import james.totaldemo.app.MyApp;
import james.totaldemo.entity.User;

/**
 * Created by james on 2017/11/4.
 */

public class MyTestActivity extends AppCompatActivity {
    private UserDao dao;
    EditText etId,etUsername,etAge;
    Button btnInsert,btnDelete,btnQuery,btnUpdate;
    String name;
    int age;
    long id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        dao= MyApp.getApplication().getDaoSession().getUserDao();
        initView();
    }

    private void initView() {
        etId= (EditText) findViewById(R.id.etId);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etAge= (EditText) findViewById(R.id.etAge);
        btnInsert= (Button) findViewById(R.id.insert);
        btnDelete= (Button) findViewById(R.id.delete);
        btnQuery= (Button) findViewById(R.id.query);
        btnUpdate= (Button) findViewById(R.id.update);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(Long.parseLong(etId.getText().toString()));
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(Long.parseLong(etId.getText().toString()));
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryData();
            }
        });
    }

    private void queryData() {
        //between表示查询id介于2到13之间(包含2和13)的数据，limit表示查询5条数据
        List<User> list = dao.queryBuilder().where(UserDao.Properties.Id.between(2, 13)).limit(5).build().list();
        for (int i = 0; i < list.size(); i++) {
            Log.i("google_lenve", "search: " + list.get(i).toString());
        }

    }

    private void updateData(long i) {
        name = etUsername.getText().toString().trim();
        age = Integer.parseInt(etAge.getText().toString());
        id=Long.parseLong(etId.getText().toString());
        User  user= new User(id, name, age);
        dao.update(user);
    }

    private void deleteData(long i) {
       // where表示查询条件，这里我是查询id小于等于i的数据，where中的参数可以有多个，
        // 就是说可以添加多个查询条件。最后的list表示查询结果是一个List集合
        List<User> userList = (List<User>) dao.queryBuilder().where(UserDao.Properties.Id.le(i)).build().list();
        for (User user : userList) {
            dao.delete(user);
        }

        //删除一条数据
        //如果你只想查询一条数据，最后unique即可。当然，我们也可以根据id来删除数据：
        User user = dao.queryBuilder().where(UserDao.Properties.Id.eq(17)).build().unique();
        if (user == null) {
            Toast.makeText(MyTestActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
        }else{
            dao.deleteByKey(user.getId());
        }

//        也可以将表中所有数据一次删除：
//        dao.deleteAll();


    }

    private void insertData() {
        Log.i("google_lenve", "insertData  执行了 ");
        name = etUsername.getText().toString().trim();
        age = Integer.parseInt(etAge.getText().toString());
        id=Long.parseLong(etId.getText().toString());
        User user = new User(id, name, age);

        dao.insert(user);

        Toast.makeText(this,"name="+name+";"+"age="+age+";"+"id="+id,Toast.LENGTH_SHORT).show();
        etId.setText("");
        etUsername.setText("");
        etAge.setText("");
    }
}
