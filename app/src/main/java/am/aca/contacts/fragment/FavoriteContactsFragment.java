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
import am.aca.contacts.adapter.RecyclerAdapter;

import static am.aca.contacts.activity.MainActivity.sContacts;
import static am.aca.contacts.adapter.RecyclerAdapter.mFavoriteContactList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteContactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public FavoriteContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteContactsFragment newInstance(String param1, String param2) {
        FavoriteContactsFragment fragment = new FavoriteContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewFavorite);
        FavoriteRecyclerAdapter recyclerAdapter=new FavoriteRecyclerAdapter(mFavoriteContactList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

    }

}
