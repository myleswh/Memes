package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import dankmemes.myleswh.dankmemes.R;

/**
 * Created by myleswh on 06/06/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_EMPTY_VIEW = 1;

    private List<String> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MainAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_EMPTY_VIEW:
                return new EmptyViewHolder(inflater.inflate(R.layout.listitem_main_empty, parent, false));
            case TYPE_ITEM:
                return new MainViewHolder(inflater.inflate(R.layout.listitem_main, parent, false), onItemClickListener);
            default:
                throw new IllegalStateException("invalid viewType");
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // Only bind TYPE_ITEM
        if (holder.getItemViewType() != TYPE_ITEM) return;
        bindMainViewHolder((MainViewHolder) holder, position);
    }

    private void bindMainViewHolder(final MainViewHolder holder, int position) {
        String item = items.get(position);

        holder.getPbLoading().setVisibility(View.VISIBLE);

        Glide.with(holder.getImageView().getContext())
                .load(item)
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.getPbLoading().setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.getPbLoading().setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        // Shows empty view
        if (items.size() == 0) {
            return 1;
        }

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

    @Override
    public int getItemViewType(int position) {
        if (items.size() == 0) {
            return TYPE_EMPTY_VIEW;
        } else {
            return TYPE_ITEM;
        }
    }


}
