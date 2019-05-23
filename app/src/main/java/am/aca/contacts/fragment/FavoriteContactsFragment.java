package am.aca.contacts.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.aca.contacts.R;
import am.aca.contacts.adapter.FavoriteRecyclerAdapter;

import static am.aca.contacts.adapter.RecyclerAdapter.mFavoriteContactList;


public class FavoriteContactsFragment extends Fragment {
    public static  String  FRAGMENT_TITLE="Favorites";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public FavoriteContactsFragment() {
        // Required empty public constructor
    }

    public static FavoriteContactsFragment newInstance() {
        FavoriteContactsFragment fragment = new FavoriteContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavorite);
        FavoriteRecyclerAdapter recyclerAdapter = new FavoriteRecyclerAdapter(mFavoriteContactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

}
