package am.aca.contacts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import am.aca.contacts.R;
import am.aca.contacts.adapter.holder.ViewHolder;
import am.aca.contacts.model.Contact;


public class FavoriteRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ViewHolder.OnContactClickListener mOnContactClickListener = new ViewHolder.OnContactClickListener() {
        @Override
        public void onClickStar(int RecyclerPosition) {

        }

        @Override
        public void onRemoveContact(int FavoriteRecyclerPosition) {
            //removeContact(FavoriteRecyclerPosition);
        }
    };
    private List<Contact> mContactList;

    public FavoriteRecyclerAdapter(List<Contact> stringList) {
        mContactList = stringList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.container_favorite, parent, false));
        viewHolder.setOnContactClickListener(mOnContactClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = mContactList.get(position);
        holder.getName().setText(contact.getName());
        holder.getPhone().setText(contact.getPhone());

    }

    private void removeContact(int position) {
        // TODO: 23-May-19  
       /* sContacts.add(mFavoriteContactList.get(position));
        mFavoriteContactList.remove(position);
        notifyItemRemoved(position);*/
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
