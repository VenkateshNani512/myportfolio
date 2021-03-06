package com.example.user.myapps1st.Adapter;

/**
 * Created by User on 5/30/2016.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.user.myapps1st.About_fragment;
import com.example.user.myapps1st.Database.DatabaseHelper;
import com.example.user.myapps1st.Model.WorkInfo;
import com.example.user.myapps1st.R;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerSwipeAdapter<WorkAdapter.SimpleViewHolder> {
    ArrayList<WorkInfo> data = new ArrayList<>();
    private int id;
    DatabaseHelper mydb;

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        com.rey.material.widget.TextView title, description, category, technology;
        SwipeLayout swipeLayout;
        RoundCornerProgressBar progressBar;

        @TargetApi(Build.VERSION_CODES.M)
        public SimpleViewHolder(final View itemView) {
            super(itemView);
            title = (com.rey.material.widget.TextView) itemView.findViewById(R.id.title);
            description = (com.rey.material.widget.TextView) itemView.findViewById(R.id.description);
            category = (com.rey.material.widget.TextView) itemView.findViewById(R.id.category);
            technology = (com.rey.material.widget.TextView) itemView.findViewById(R.id.technology);
            progressBar = (RoundCornerProgressBar) itemView.findViewById(R.id.progress);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            mydb = new DatabaseHelper(mContext);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        if (swipeLayout.getOpenStatus() == SwipeLayout.Status.Close) {
//                            Intent intent = new Intent(mContext, DialogSkillActivity.class);
//                            int position = getLayoutPosition();
//                            ArrayList<SkillInfo> list = mydb.selectSkillInfo();
//                            final SkillInfo info = list.get(position);
//                            int idd = Integer.parseInt(info.id);
//                            intent.putExtra("id", idd);
//                            mContext.startActivity(intent);
//                            Log.e("deleteId", String.valueOf(idd));
//                        } else {
//                             //Toast.makeText(view.getContext(),"Something went wrong.Plzz try again.", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });

        }
    }

    private Context mContext;
    private About_fragment fragment;
    AlertDialog.Builder builder;
    View view;


    public WorkAdapter(Activity context, ArrayList<WorkInfo> info) {
        this.mContext = context;
        this.data = info;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_about_worklist, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        final WorkInfo info = data.get(position);
        viewHolder.title.setText(info.title);
        viewHolder.description.setText(info.description);
        viewHolder.category.setText(info.category);
        viewHolder.technology.setText(info.technology);
        id = Integer.parseInt(info.id);

        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
            }

            @Override
            public void onOpen(final SwipeLayout layout) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure, You want to delete.");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        int idd = Integer.parseInt(info.id);
//                        Log.e("delete", String.valueOf(idd));
//                        mydb.deleteSkillInfo(String.valueOf(idd));
//                        data.remove(position);
//                        notifyItemRemoved(position);
                        viewHolder.swipeLayout.close();
//                        if(fragment!=null) {
//                            About_fragment frag = fragment;
//                            frag.Refresh();
//                        }else{
//                            SkillList list = (SkillList) mContext;
//                            list.Refresh();
//
//                        }
                        dialog.dismiss();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewHolder.swipeLayout.close();
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                // Toast.makeText(layout.getContext(), "Start_closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClose(SwipeLayout layout) {
                // Toast.makeText(layout.getContext(), "closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });
    }

    @Override
    public int getItemCount() {
        //Log.e("List", String.valueOf(data.size()));
        return data.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}
