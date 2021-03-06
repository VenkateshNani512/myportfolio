package com.example.user.myapps1st;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.myapps1st.Database.DatabaseHelper;
import com.example.user.myapps1st.Model.CategoryInfo;
import com.example.user.myapps1st.Portfolio.CategoryAddActivity;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Work_fragment extends Fragment {

    TabLayout tabLayout;
    TextView error;
    Button add;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    DatabaseHelper mydb;
    View view;

    public Work_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_work, container, false);
        error = (TextView) view.findViewById(R.id.error);
        add = (Button) view.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryAddActivity.class);
                startActivity(intent);
            }
        });
        Category();
        return view;
    }

    public void Category() {
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mydb = new DatabaseHelper(getActivity());
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        int count = mydb.selectCategoryInfo().size();
        Log.e("Count", String.valueOf(count));
        ArrayList<CategoryInfo> list = mydb.selectCategoryInfo();
        if (list.size() <= 0) {
            tabLayout.setVisibility(View.GONE);
            error.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
        } else {
            tabLayout.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
            for (int i = 0; i < list.size(); i++) {
                final CategoryInfo info = list.get(i);
                Log.e("ABC","abc");
                Bundle b = new Bundle();
                b.putString("cid", info.id);
                Android_fragment fragment = new Android_fragment();
                fragment.setArguments(b);
                viewPagerAdapter.addFragment(fragment, info.category);
            }
            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }

//        viewPagerAdapter.addFragment(new Android_fragment(),"ANDROID");
//        viewPagerAdapter.addFragment(new Java_fragment(),"JAVA");
//        viewPagerAdapter.addFragment(new Php_fragment(),"PHP");
//        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onResume() {
        Log.e("Onresume", "WorkResume");
        super.onResume();
    //    Category();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("OnPause", "WorkPause");
    }
}
