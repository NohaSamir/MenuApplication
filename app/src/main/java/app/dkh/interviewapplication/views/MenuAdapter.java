package app.dkh.interviewapplication.views;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.databinding.ItemMenuBinding;
import app.dkh.interviewapplication.models.FoodItem;

public class MenuAdapter extends ListAdapter<FoodItem, MenuAdapter.ItemViewHolder> {


    private static final DiffUtil.ItemCallback<FoodItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<FoodItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull FoodItem oldItem, @NonNull FoodItem newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    // private AsyncListDiffer<FoodItem> differ = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    private OnItemClickListener onItemClickListener;

    MenuAdapter(OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_menu, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        FoodItem item = getItem(position);

        holder.bind(item);

        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(item));

    }

    /*void submitList(List<FoodItem> items) {
        differ.submitList(items);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }*/

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemMenuBinding binding;

        ItemViewHolder(ItemMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FoodItem item) {
            binding.setFoodItem(item);
            binding.executePendingBindings();
        }
    }


    interface OnItemClickListener {
        void onClick(FoodItem item);
    }
}



