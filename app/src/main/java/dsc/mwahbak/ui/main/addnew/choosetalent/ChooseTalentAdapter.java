package dsc.mwahbak.ui.main.addnew.choosetalent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dsc.mwahbak.R;
import dsc.mwahbak.ui.main.addnew.uploadtalent.TalentTypeModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ChooseTalentAdapter extends RecyclerView.Adapter<ChooseTalentAdapter.ViewHolder> {
    private static final String TAG = "ChooseTalentAdapter";
    private Context context;
    private List<TalentTypeModel> my_data;
    private static int currentItem = 0;

    Map<Integer,Object> deletedItems;


    public void hideItem(final int position) {

        notifyItemRemoved(position);
    }



    public ChooseTalentAdapter(Context context, List<TalentTypeModel> my_data) {
        this.context = context;
        this.my_data = my_data;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.talent_type_item, parent, false);

        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final TalentTypeModel model = my_data.get(position);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                currentItem = position;
                notifyDataSetChanged();


            }


        });

        if (position == currentItem) {
            holder.item.setBackgroundColor(context.getColor(R.color.black));


        } else {
            holder.item.setBackgroundColor(context.getColor(R.color.white));

        }


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView name;
        public ImageView image;
        public LinearLayout item;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) view.findViewById(R.id.talentTypeName);
            image = (ImageView) view.findViewById(R.id.talentTypeImage);
            item = (LinearLayout) view.findViewById(R.id.talentTypeItem);

        }
    }


}