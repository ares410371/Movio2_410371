package cz.muni.fi.pv256.movio2.uco_410371.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cz.muni.fi.pv256.movio2.uco_410371.R;

/**
 * Adapter for empty recycler view
 * Created by Benjamin Varga on 17.10.2016.
 */

public class EmptyRecyclerViewAdapter extends RecyclerView.Adapter<EmptyRecyclerViewAdapter.EmptyViewHolder>{

    private String mMessage;

    public EmptyRecyclerViewAdapter(String message) {
        mMessage = message;
    }

    @Override
    public EmptyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_empty, parent, false);
        return new EmptyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmptyViewHolder viewHolder, int position) {
        if (mMessage != null) {
            viewHolder.getMessageView().setText(mMessage);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView mMessageView;

        public EmptyViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mMessageView = (TextView)itemView.findViewById(R.id.empty_item_message);
        }

        public View getView() {
            return mView;
        }

        public void setView(View view) {
            mView = view;
        }

        public TextView getMessageView() {
            return mMessageView;
        }

        public void setMessageView(TextView messageView) {
            mMessageView = messageView;
        }
    }
}
