package am.aca.contacts.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import am.aca.contacts.R;
import am.aca.contacts.adapter.ViewPagerFragmentAdapter;
import am.aca.contacts.fragment.ContactsFragment;
import am.aca.contacts.fragment.FavoriteContactsFragment;

public class MainActivity extends AppCompatActivity {


    private List<Fragment> mFragmentList = new ArrayList<>();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init() {
        mFragmentList.add(ContactsFragment.newInstance());
        mFragmentList.add(FavoriteContactsFragment.newInstance());
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        TabLayout tableLayout = findViewById(R.id.tab_layout);
        tableLayout.setupWithViewPager(mViewPager);
    }
}
