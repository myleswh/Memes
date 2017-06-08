package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.utils.OnItemClickListener;
import dankmemes.myleswh.dankmemes.R;

/**
 * Created by myleswh on 06/06/2017.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv) ImageView imageView;
    @BindView(R.id.pbLoading) View pbLoading;

    public MainViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(getLayoutPosition());
            }
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public View getPbLoading() {
        return pbLoading;
    }
}
