package com.example.solar.week4;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

  String sharedpreferenceName = "myFile";
  SharedPreferences sf;
  SharedPreferences.Editor editor;
  int count;
  ViewPager pager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Sharedpreference for counting the staring numbers of app
    sf = getSharedPreferences(sharedpreferenceName, 0);
    count = sf.getInt("count", 1);
    String countStr = "App을 " + count + "번 키셨습니다.";
    Toast.makeText(getApplicationContext(), countStr, Toast.LENGTH_SHORT).show();


    pager = (ViewPager) findViewById(R.id.viewPager);

    // ViewPager에 설정할 Adapter 객체 생성
    // Listview에서 사용하는 Adapter와 같은 역할
    // PagerAdapter를 상속받은 CustomAdapter 객체 생성
    // CustomAdapter에게 LayoutInflater 객체 전달
    final CustomAdapter pagerAdapter = new CustomAdapter(getLayoutInflater());

    // ViewPager에 Adapter 설정
    pager.setAdapter(pagerAdapter);


    pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Button btn = null;
        FloatingActionButton fBtn = (FloatingActionButton) findViewById(R.id.bt_floating);
        TextView textTitle = (TextView) findViewById(R.id.tV_title);
        TextView textNumberOfFriends = (TextView) findViewById(R.id.tV_numberOfFriends);
        // setting selected icon
        switch (pager.getCurrentItem()) {
          case 0:
            btn = (Button) findViewById(R.id.bt_friends);
            btn.setBackgroundResource(R.drawable.people_64_selected);
            textTitle.setText("친구");
            Integer num = pagerAdapter.getListViewAdatper().getCount();

            fBtn.setVisibility(View.VISIBLE);
            fBtn.setImageResource(R.drawable.heart_64);
            fBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getApplicationContext(), "Friends.db", null, 1);
                dbHelper.insert("new", "new");
                dbHelper.close();
                pagerAdapter.notifyDataSetChanged();

              }
            });

            textNumberOfFriends.setText(num.toString());
            break;
          case 1:
            btn = (Button) findViewById(R.id.bt_chat);
            btn.setBackgroundResource(R.drawable.chat_64_selected);
            textTitle.setText("대화");
            textNumberOfFriends.setText("");

            fBtn.setVisibility(View.VISIBLE);
            fBtn.setImageResource(R.drawable.plus_64);
            break;
          case 2:
            btn = (Button) findViewById(R.id.bt_newsFeed);
            btn.setBackgroundResource(R.drawable.newspaper_64_selected);
            textTitle.setText("뉴스");
            textNumberOfFriends.setText("");

            fBtn.setVisibility(View.INVISIBLE);
            break;
          case 3:
            btn = (Button) findViewById(R.id.bt_setMyDetails);
            btn.setBackgroundResource(R.drawable.settings_64_selected);
            textTitle.setText("설정");
            textNumberOfFriends.setText("");
            fBtn.setVisibility(View.INVISIBLE);
            break;
        }

        // setting tab bar
        int marginForTabBar = btn.getWidth();
        LinearLayout tabBar = (LinearLayout) findViewById(R.id.lL_tabBar);
        LinearLayout.LayoutParams tabBarPrams = new LinearLayout.LayoutParams(marginForTabBar, 5);

        tabBarPrams.setMarginStart(position * marginForTabBar + positionOffsetPixels / 4);
        tabBar.setLayoutParams(tabBarPrams);
        //System.out.println(positionOffsetPixels + " " + screenWidth + " " + marginForTabBar); // for debugging

        // Setting non-selected icon
        int[] btBackgroundList = {R.drawable.people_64, R.drawable.chat_64, R.drawable.newspaper_64, R.drawable.settings_64};
        int[] btIdList = {R.id.bt_friends, R.id.bt_chat, R.id.bt_newsFeed, R.id.bt_setMyDetails};
        position = pager.getCurrentItem();
        for (int i = 0; i < 4; i++) {
          if (i != position) {
            btn = (Button) findViewById(btIdList[i]);
            btn.setBackgroundResource(btBackgroundList[i]);
          }
        }
      }

      @Override
      public void onPageSelected(int position) {


      }

      @Override
      public void onPageScrollStateChanged(int state) {
        System.out.println("onPageScrollStateChanged = " + state);
      }
    });


  }


  @Override
  protected void onStop() {
    sf = getSharedPreferences(sharedpreferenceName, 0);
    editor = sf.edit(); // For storing
    count++;

    editor.putInt("count", count);
    editor.apply();

    super.onStop();
  }

  //onClick속성이 지정된 View를 클릭했을때 자동으로 호출되는 메소드
  public void mOnClick(View v) {

    int position;
    Button btn = null;
    switch (v.getId()) {
      case R.id.bt_friends://이전버튼 클릭

        // position=pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴

        //현재 위치(position)에서 -1 을 해서 이전 position으로 변경
        //이전 Item으로 현재의 아이템 변경 설정(가장 처음이면 더이상 이동하지 않음)
        //첫번째 파라미터: 설정할 현재 위치
        //두번째 파라미터: 변경할 때 부드럽게 이동하는가? false면 팍팍 바뀜
        // pager.setCurrentItem(position-1,true);

        pager.setCurrentItem(0, true);
        btn = (Button) findViewById(R.id.bt_friends);
        btn.setBackgroundResource(R.drawable.people_64_selected);
        break;
      case R.id.bt_chat:
        pager.setCurrentItem(1, true);
        btn = (Button) findViewById(R.id.bt_chat);
        btn.setBackgroundResource(R.drawable.chat_64_selected);
        break;
      case R.id.bt_newsFeed:
        pager.setCurrentItem(2, true);
        btn = (Button) findViewById(R.id.bt_newsFeed);
        btn.setBackgroundResource(R.drawable.newspaper_64_selected);
        break;
      case R.id.bt_setMyDetails://다음버튼 클릭

        // position=pager.getCurrentItem();//현재 보여지는 아이템의 위치를 리턴

        //현재 위치(position)에서 +1 을 해서 다음 position으로 변경
        //다음 Item으로 현재의 아이템 변경 설정(가장 마지막이면 더이상 이동하지 않음)
        //첫번째 파라미터: 설정할 현재 위치
        //두번째 파라미터: 변경할 때 부드럽게 이동하는가? false면 팍팍 바뀜
        //pager.setCurrentItem(position+1,true);
        pager.setCurrentItem(3, true);
        btn = (Button) findViewById(R.id.bt_setMyDetails);
        btn.setBackgroundResource(R.drawable.settings_64_selected);

        break;
    }


    System.out.println("mOnClick is called!");

  }
}
