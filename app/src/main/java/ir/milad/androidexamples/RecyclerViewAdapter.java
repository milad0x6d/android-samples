package ir.milad.androidexamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.milad.androidexamples.databinding.ItemRowBinding;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements CustomClickListener{

    private List<DataModel> dataModelList;
    private Context context;

    public RecyclerViewAdapter(List<DataModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        ItemRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_row,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        DataModel dataModel = dataModelList.get(position);
        viewHolder.bind(dataModel);
        viewHolder.itemRowBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    @Override
    public void cardClicked(DataModel f) {
        Toast.makeText(context, "You clicked " + f.androidName, Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ItemRowBinding itemRowBinding;

        public ViewHolder(ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj){
            itemRowBinding.setVariable(BR.model,obj);
            itemRowBinding.executePendingBindings();
        }
    }
}

/**
 * 
 Difference between setVariable() and setModel()
 setVariable() is used in generic circumstances when the type of the data is not known.

 setModel() is auto-generated. We can use the following instead of holder.bind(dataModel);.

 holder.itemRowBinding.setModel(dataModel);
 */