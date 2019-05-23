package am.aca.contacts.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import am.aca.contacts.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView phone;
    private OnContactClickListener mOnContactClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.favorite:
                    if (mOnContactClickListener != null) {
                        mOnContactClickListener.onClickStar(getAdapterPosition());
                    }
                    break;
                case R.id.removeBtn:
                    if (mOnContactClickListener != null) {
                        mOnContactClickListener.onRemoveContact(getAdapterPosition());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public ViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewName);
        phone = itemView.findViewById(R.id.textViewPhone);
        final ImageButton ButtonStar =  itemView.findViewById(R.id.favorite);
        Button remove = itemView.findViewById(R.id.removeBtn);
        if (remove != null) {
            remove.setOnClickListener(mOnClickListener);
        }

        if (ButtonStar != null) {
            ButtonStar.setOnClickListener(mOnClickListener);

        }

    }

    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        mOnContactClickListener = onContactClickListener;
    }

    public TextView getName() {
        return name;
    }

    public TextView getPhone() {
        return phone;
    }

    public interface OnContactClickListener {
        void onClickStar(int RecyclerPosition);
        void onRemoveContact(int FavoriteRecyclerPosition);
    }
}
