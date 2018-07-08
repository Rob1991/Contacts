package am.aca.contacts.activity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import am.aca.contacts.R;
import am.aca.contacts.adapter.PagerAdapter;
import am.aca.contacts.model.Contact;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                GetContactsIntoArrayList();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       isEnableRuntimePermission();

    }

    Cursor cursor;
    String id,name,phonenumber;
  public static List<Contact> sContacts =new ArrayList<>();
    public void GetContactsIntoArrayList() {

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            Contact mContact=new Contact();
            id=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContact.setId(id);
            mContact.setName(name);
            mContact.setPhone(phonenumber);

            sContacts.add(mContact);
        }

       cursor.close();

    }
    public void isEnableRuntimePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);
            } else {
                GetContactsIntoArrayList();
            }

        } else {
            GetContactsIntoArrayList();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    GetContactsIntoArrayList();
                } else {
                    Toast.makeText(this, "Please give your permission.", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
