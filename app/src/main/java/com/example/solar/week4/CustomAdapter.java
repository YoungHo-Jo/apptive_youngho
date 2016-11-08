package com.example.solar.week4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;



/**
 * Created by solar on 2016-11-07.
 */

public class CustomAdapter extends PagerAdapter {

    LayoutInflater inflater;
    private ViewGroup viewGroup = null;
    private ListViewAdapter listViewAdatper = null;

    public CustomAdapter(LayoutInflater inflater)
    {
        // 전달 받은 LayoutInflater를 멤벼변수로 전달
        this.inflater = inflater;
    }


    // PagerAdapter가 가지고 있는 View의 개수를 리턴
    // 보통 보여줘야하는 이미지 배열 데이터의 길이를 리턴
    @Override
    public int getCount() {
        return 4; // 보여줄 View 개수가 4개다
    }


    // ViewPager가 현재 보여질 item(View객체)를 생성할 필요가 있을때 자동으로 호출
    // 스크롤을 통해 현재 보여져야 하는 View를 만들어낸다
    // 첫번째 파라미터: ViewPager
    // 두번째 파라미터: ViewPager가 보여줄 View의 위치(가장 처음부터 0, 1, 2, 3 ... )
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = null;
        switch (position)
        {
            case 0:
                // 새로운 View 객체를 LayoutInflater를 이용해서 생성
                // 만들어질 view의 설계는 res폴더>>layout폴더>>listview_list_of_friends.xml 레이아웃
                view = inflater.inflate(R.layout.listview_list_of_friends, null);

                // 만들어진 view안에 있는 listview 객체 참조
                // 위에서 inflated 되어 만들어진 view로 부터 findViewById()를 해야하는 것에 주의
                ListView listView = (ListView) view.findViewById(R.id.lV_listOfFriend);

                // Listview
                ListViewAdapter adapter;

                // Creating Adapter
                adapter = new ListViewAdapter();

                listView.setAdapter(adapter);


                // Inserting items
                for (int i = 0; i < 10; i++)
                {
                    adapter.addItem(ContextCompat.getDrawable(view.getContext(), R.drawable.default_profile), "Person " + (i + 1), "state message " + (i + 1));
                }



                // Listview click event handler definition
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        // Get Item
                        ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);

                        String titleStr = item.getTitle();
                        String deesStr = item.getDesc();
                        Drawable iconDrawable = item.getIcon();

                        System.out.println("item of listview is clicked ");

                        Intent intent = new Intent(v.getContext(), ProfileDetailActivity.class);
                        intent.putExtra("name", titleStr);
                        v.getContext().startActivity(intent);


                        // Checking that click event is working
                        // Toast.makeText(v.getApplicationContext(), titleStr, Toast.LENGTH_SHORT).show();

                    /*
                    ImageView personalPic = (ImageView) findViewById(R.id.personalPicLayout);
                    personalPic.setImageDrawable(iconDrawable);

                    findViewById(R.id.personalLayout).setVisibility(View.VISIBLE);
                    */
                    }
                });


                listViewAdatper = adapter;
                break;
            case 1:
                view  = inflater.inflate(R.layout.chatting, null);
                break;
            case 2:
                view = inflater.inflate(R.layout.news_feed, null);
                break;
            case 3:
                view = inflater.inflate(R.layout.detail_setting, null);
                break;
        }

        // ViewPager에 만들어낸 View 추가
        container.addView(view);
        System.out.println("making view! " + position);
        viewGroup = container;

        return view;
    }

    //화면에 보이지 않은 View는파괴를 해서 메모리를 관리함.
    //첫번째 파라미터 : ViewPager
    //두번째 파라미터 : 파괴될 View의 인덱스(가장 처음부터 0,1,2,3...)
    //세번째 파라미터 : 파괴될 객체(더 이상 보이지 않은 View 객체)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub

        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View)object);
        System.out.println("destroying view! " + position);

    }


    // instantiateItem() 에서 return 된 object가 view가 맞는지 확인하는 메소드
    @Override
   public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public ViewGroup getViewGroup() {return viewGroup;}
    public ListViewAdapter getListViewAdatper() {return listViewAdatper;}

}
