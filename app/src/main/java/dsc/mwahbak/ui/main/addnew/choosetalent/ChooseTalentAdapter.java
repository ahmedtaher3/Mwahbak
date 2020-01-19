package dsc.mwahbak.ui.main.addnew.choosetalent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dsc.mwahbak.R;
import dsc.mwahbak.models.Categories;
import dsc.mwahbak.models.TalentTypeModel;

public class ChooseTalentAdapter extends RecyclerView.Adapter<ChooseTalentAdapter.ViewHolder> {
    private static final String TAG = "ChooseTalentAdapter";
    private Context context;
    private List<TalentTypeModel> my_data;
    private int currentItem = -1;
    AdapterClick adapterClick;




    public interface AdapterClick {
        void itemClick (int typeID);
    }



    public ChooseTalentAdapter(Context context, List<TalentTypeModel> my_data , AdapterClick adapterClick  ) {
        this.context = context;
        this.my_data = my_data;
        this.adapterClick = adapterClick;



    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.talent_type_item, parent, false);

        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final TalentTypeModel model = my_data.get(position);

        holder.name.setText(model.getName());
        Glide.with(context).load(model.getImage()).into(holder.image);

        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentItem = position;
                notifyDataSetChanged();
                adapterClick.itemClick(model.getId());
            }


        });

        if (position == currentItem) {
            holder.item.setBackgroundColor((Color.parseColor(context.getResources().getString(R.color.black))));
        } else {
            holder.item.setBackgroundColor((Color.parseColor(context.getResources().getString(R.color.white))));
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


    public void setMy_data(List<TalentTypeModel> my_data) {
        this.my_data = my_data;
        notifyDataSetChanged();
    }
}