package com.qbent.enfinsapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qbent.enfinsapp.R;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.model.Member;

import java.util.List;

public class MemberRecyclerViewAdapter extends
        RecyclerView.Adapter<MemberRecyclerViewAdapter.MemberViewHolder> {
    private final List<Member> members;

    public MemberRecyclerViewAdapter(List<Member> members) {
        this.members = members;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_member, viewGroup, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberRecyclerViewAdapter.MemberViewHolder memberViewHolder, int i) {
        memberViewHolder.mItem = members.get(i);
        memberViewHolder.memberCodeView.setText(members.get(i).getCode());
        memberViewHolder.memberNameView.setText(members.get(i).getFullName());
        memberViewHolder.memberAadharView.setText(String.valueOf(members.get(i).getAadharNo()));
        memberViewHolder.memberDobView.setText(members.get(i).getDateOfDeath());
        memberViewHolder.memberGuardianView.setText(members.get(i).getGuardianName());
        memberViewHolder.memberCollectionPointView.setText(members.get(i).getCollectionPointName());
        memberViewHolder.memberStatusView.setText(members.get(i).getVisitStatus());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        public final View memberView;
        public final TextView memberCodeView;
        public final TextView memberNameView;
        public final TextView memberAadharView;
        public final TextView memberDobView;
        public final TextView memberGuardianView;
        public final TextView memberCollectionPointView;
        public final TextView memberStatusView;
        public Member mItem;

        public MemberViewHolder(View view) {
            super(view);
            memberView = view;
            memberCodeView = (TextView) view.findViewById(R.id.textMemberCode);
            memberNameView = (TextView) view.findViewById(R.id.textMemberName);
            memberAadharView = (TextView) view.findViewById(R.id.textMemberAadhar);
            memberDobView = (TextView) view.findViewById(R.id.textMemberDOB);
            memberGuardianView = (TextView) view.findViewById(R.id.textMemberGuardianName);
            memberCollectionPointView = (TextView) view.findViewById(R.id.textMemberCollectionPoint);
            memberStatusView = (TextView) view.findViewById(R.id.textMemberVisitStatus);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mAddressView.getText() + "'";
//        }
    }
}
