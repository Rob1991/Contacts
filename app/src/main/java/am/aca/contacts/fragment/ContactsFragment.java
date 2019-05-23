package am.aca.contacts.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import am.aca.contacts.R;
import am.aca.contacts.adapter.RecyclerAdapter;
import am.aca.contacts.model.Contact;


public class ContactsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String FRAGMENT_TITLE = "Contacts";
    private static Set<Contact> sContacts = new HashSet<>();
    private String mParam1;
    private String mParam2;
    private Cursor cursor;
    private String id, name, phoneNumber;

    public ContactsFragment() {

    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isEnableRuntimePermission();

    }

    public void GetContactsIntoArrayList(View view) {

        cursor = view.getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (Objects.requireNonNull(cursor).moveToNext()) {
            Contact mContact = new Contact();
            id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContact.setId(id);
            mContact.setName(name);
            mContact.setPhone(phoneNumber);
            sContacts.add(mContact);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewContact);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(sContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);

        cursor.close();

    }

    public void isEnableRuntimePermission() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                    Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);
            } else {
                GetContactsIntoArrayList(getView());
            }

        } else {
            GetContactsIntoArrayList(getView());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    GetContactsIntoArrayList(getView());
                } else {
                    Toast.makeText(getContext(), "Please give your permission.", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
