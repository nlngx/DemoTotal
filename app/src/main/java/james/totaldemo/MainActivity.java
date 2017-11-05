package james.totaldemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import james.totaldemo.ui.MyTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();//获取到了当前界面的DecorView
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;//SYSTEM_UI_FLAG_FULLSCREEN 表示全屏的意思
        int option1=View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//
        //调用DecorView的setSystemUiVisibility()方法来设置系统UI元素的可见性
        decorView.setSystemUiVisibility(option|option1);//注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间
        getWindow().setStatusBarColor(Color.TRANSPARENT);//调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了

        int option3 = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;//导航栏
        decorView.setSystemUiVisibility(option3);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        ActionBar actionBar = getSupportActionBar();//标题栏
        actionBar.hide();

        initView();

    }

    private void initView() {
       TextView tvTest1= (TextView) findViewById(R.id.textView1);
       TextView tvTest2= (TextView) findViewById(R.id.textView2);
        tvTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MyTestActivity.class);
                startActivity(intent);
            }
        });

    }

    /**当你确定要使用沉浸式模式，那么只需要重写Activity的onWindowFocusChanged()方法，然后加入如下逻辑即可**/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    /***执行后的效果是：
     * 当我们需要用到状态栏或导航栏时，只需要在屏幕顶部向下拉，或者在屏幕右侧向左拉，
     * 状态栏和导航栏就会显示出来，此时界面上任何元素的显示或大小都不会受影响。
     * 过一段时间后如果没有任何操作，状态栏和导航栏又会自动隐藏起来，重新回到全屏状态*/
}
