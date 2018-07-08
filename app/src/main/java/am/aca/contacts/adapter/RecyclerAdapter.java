package am.aca.contacts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import am.aca.contacts.R;
import am.aca.contacts.adapter.holder.ViewHolder;
import am.aca.contacts.model.Contact;

import static am.aca.contacts.activity.MainActivity.sContacts;


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    ViewHolder.OnContactClickListener mOnContactClickListener=new ViewHolder.OnContactClickListener() {
        @Override
        public void onClickStar(int RecyclerPosition) {
            addFavoriteContact(RecyclerPosition);
        }

        @Override
        public void onRemoveContact(int FavoriteRecyclerPosition) {

        }
    }
        ;
    List<Contact> mContactList;
public static List<Contact> mFavoriteContactList=new ArrayList<>();
    public RecyclerAdapter(List<Contact> stringList) {


        mContactList = stringList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_contact, parent, false));
        viewHolder.setOnContctClickListener(mOnContactClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact=mContactList.get(position);

holder.getName().setText(contact.getName());
holder.getPhone().setText(contact.getPhone());

    }
    private void addFavoriteContact(int position) {
        mFavoriteContactList.add(sContacts.get(position));
        sContacts.remove(position);
        notifyItemRemoved(position);

    }
    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
