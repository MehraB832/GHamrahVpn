package com.gold.hamrahvpn.recyclerview;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gold.hamrahvpn.R;
import com.gold.hamrahvpn.recyclerview.cmp.OpenVpnServerList;
import com.gold.hamrahvpn.recyclerview.cmp.SetHolderHelper;
import com.gold.hamrahvpn.util.FinishActivityListener;
import com.gold.hamrahvpn.util.LogManager;

import java.util.List;

import de.blinkt.openvpn.core.App;

/**
 * Created by Daichi Furiya / Wasabeef on 2020/08/26.
 * From animators library
 * add by MehraB832
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<OpenVpnServerList> dataSet;
    public static Context context;
    public static FinishActivityListener finishActivityListener;

    public MainAdapter(Context context, List<OpenVpnServerList> dataSet, FinishActivityListener finishActivityListener) {
        MainAdapter.context = context;
        this.dataSet = dataSet;
        MainAdapter.finishActivityListener = finishActivityListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.server_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            final OpenVpnServerList OpenVpnServerList = dataSet.get(position);
            // openvpn serverlist
            SetHolderHelper.setItemHolder(OpenVpnServerList, holder);
            // setbackground darkmode
            SetHolderHelper.setBackgroundHolder(holder.itemView, position);
        } catch (Exception e) {
            Bundle params = new Bundle();
            params.putString("device_id", App.device_id);
            params.putString("exception", "BV0" + e);
            LogManager.logEvent(params);
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Deprecated
    public void remove(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    @Deprecated
    public void add(List<OpenVpnServerList> dataAdd, int position) {
//        final OpenVpnServerList OpenVpnServerList = dataAdd.get(position);
//            dataAdd.add(position, text);
//        final ServerListItem ServerListItem = dataSet.get(position);
//        holderHelper.setItemHolder(ServerListItem, holder);
        // bottom
//        holderHelper.setBackgroundHolder(holder.itemView, position);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_country;
        public View vitem;
        public Typeface RobotoRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");

        public TextView showBool = itemView.findViewById(R.id.boolShowListServer);
        public ImageView iv_flag;
        public ImageView iv_signal_strength;
        public final LinearLayout ll_item = itemView.findViewById(R.id.ll_item);

        public ViewHolder(View itemView) {
            super(itemView);
            vitem = itemView;
            tv_country = itemView.findViewById(R.id.tv_country);
            iv_flag = itemView.findViewById(R.id.iv_flag);
            iv_signal_strength = itemView.findViewById(R.id.iv_signal_strength);

        }
    }
}

