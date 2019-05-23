package am.aca.contacts.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

import am.aca.contacts.fragment.ContactsFragment;
import am.aca.contacts.fragment.FavoriteContactsFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ContactsFragment.FRAGMENT_TITLE;
            case 1:
                return FavoriteContactsFragment.FRAGMENT_TITLE;

            default:
                return null;
        }
    }
}
