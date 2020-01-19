package dsc.mwahbak.ui.main.addnew.uploadtalent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dsc.mwahbak.R;
import dsc.mwahbak.models.TalentTypeModel;

public class UploadTalentAdapter extends RecyclerView.Adapter<UploadTalentAdapter.ViewHolder> {

    private Context context;
    private List<TalentTypeModel> my_data;


    public UploadTalentAdapter(Context context, List<TalentTypeModel> my_data) {
        this.context = context;
        this.my_data = my_data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.talent_type_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final TalentTypeModel model = my_data.get(position);

        holder.name.setText(model.getName());
        Glide.with(context).load(model.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        View view;
        TextView name;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.talentTypeName);
            imageView = itemView.findViewById(R.id.talentTypeImage);

        }
    }
}