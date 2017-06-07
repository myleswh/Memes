package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import dankmemes.myleswh.dankmemes.R;

/**
 * Created by myleswh on 06/06/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<String> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MainAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new MainViewHolder(inflater.inflate(R.layout.listitem_main, parent, false), onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        String item = items.get(position);
        Glide.with(holder.getImageView()).load(item).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void addImages(List<String> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clearImages() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public int getListSize() {
        return items.size();
    }

    public String getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
