package com.qbent.enfinsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.qbent.enfinsapp.CollectionPointDetailActivity;
import com.qbent.enfinsapp.R;
import com.qbent.enfinsapp.model.CollectionPoint;

import java.util.List;

public class CollectionPointListRecyclerViewAdapter extends RecyclerView.Adapter<CollectionPointListRecyclerViewAdapter.CollectionPointListViewHolder> {

    private final List<CollectionPoint> collectionPoints;

    //----Developed by Debmalya---//
    private Context context;
    //----Ended by Debmalya---//

    public CollectionPointListRecyclerViewAdapter(List<CollectionPoint> collectionPoints) {
        this.collectionPoints = collectionPoints;
    }

    @NonNull
    @Override
    public CollectionPointListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_collection_point, viewGroup, false);
        return new CollectionPointListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionPointListViewHolder collectionPointListViewHolder, int i)
    {
        collectionPointListViewHolder.mItem = collectionPoints.get(i);
        collectionPointListViewHolder.mNameView.setText(collectionPoints.get(i).getName());
        collectionPointListViewHolder.mAddressView.setText(collectionPoints.get(i).getMobileNo());
        collectionPointListViewHolder.mCollectionDayView.setText(collectionPoints.get(i).getCollectionDay());

//        //----Developed by Debmalya---//
//        collectionPointListViewHolder.mNameView.setText(collectionPoints.get(i).getName());
//        collectionPointListViewHolder.mCollectionDayView.setText(collectionPoints.get(i).getCollectionDay());
//        collectionPointListViewHolder.mAddressView.setText(collectionPoints.get(i).getMobileNo());
//        collectionPointListViewHolder.mMobileView.setText(collectionPoints.get(i).getName());
//        //----Ended by Debmalya---//

//        //----Developed by Debmalya---//
//        final CollectionPoint listItem = collectionPoints.get(i);
//        collectionPointListViewHolder.mNameView.setText(listItem.getName());
//        collectionPointListViewHolder.mCollectionDayView.setText(listItem.getCollectionDay());
//        collectionPointListViewHolder.mAddressView.setText(listItem.getAddress());
//        collectionPointListViewHolder.mMobileView.setText(listItem.getMobileNo());
//        //----Ended by Debmalya---//

        //----Developed by Debmalya---//
//        collectionPointListViewHolder.tableLayout.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent3 = new Intent(context,CollectionPointDetailActivity.class);
//                //intent3.putExtra("emp_id",mItem.getId());
//                intent3.putExtra("emp_name",listItem.getName());
//                intent3.putExtra("collection_days",listItem.getCollectionDay());
//                intent3.putExtra("emp_address",listItem.getAddress());
//                intent3.putExtra("emp_mobile",listItem.getMobileNo());
//
//                context.startActivity(intent3);
//            }
//        });
        //----Ended by Debmalya---//
    }

    @Override
    public int getItemCount() {
        return collectionPoints.size();
    }

    public class CollectionPointListViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mCollectionDayView;
        public final TextView mAddressView;
        public CollectionPoint mItem;

        public CollectionPointListViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.textName);
            mCollectionDayView = (TextView) view.findViewById(R.id.textCollectionDay);
            mAddressView = (TextView) view.findViewById(R.id.textAddress);

        }



        @Override
        public String toString() {
            return super.toString() + " '" + mAddressView.getText() + "'";
        }
    }
}
