package am.aca.contacts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import am.aca.contacts.R;
import am.aca.contacts.adapter.holder.ViewHolder;
import am.aca.contacts.model.Contact;


public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static List<Contact> mFavoriteContactList = new ArrayList<>();
    private ViewHolder.OnContactClickListener mOnContactClickListener = new ViewHolder.OnContactClickListener() {
        @Override
        public void onClickStar(int RecyclerPosition) {
            //addFavoriteContact(RecyclerPosition);
        }

        @Override
        public void onRemoveContact(int FavoriteRecyclerPosition) {

        }
    };
    private Set<Contact> mContactSet;
    private List<Contact> mContactList;

    public RecyclerAdapter(Set<Contact> contactSet) {
        mContactSet = contactSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_contact, parent, false));
        viewHolder.setOnContactClickListener(mOnContactClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mContactList = new ArrayList<>();
        mContactList.addAll(mContactSet);

        Collections.sort(mContactList, (item, t1) -> {
            String s1 = item.getName();
            String s2 = t1.getName();
            return s1.compareToIgnoreCase(s2);
        });
        holder.getName().setText(mContactList.get(position).getName());
        holder.getPhone().setText(mContactList.get(position).getPhone());


    }

    private void addFavoriteContact(int position) {
        // TODO: 23-May-19  
       /* mFavoriteContactList.add(sContacts.get(position));
        sContacts.remove(position);
        notifyItemRemoved(position);*/

    }

    @Override
    public int getItemCount() {
        return mContactSet.size();
    }
}
