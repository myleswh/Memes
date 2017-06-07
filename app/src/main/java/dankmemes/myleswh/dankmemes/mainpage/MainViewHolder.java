package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dankmemes.myleswh.dankmemes.R;

/**
 * Created by myleswh on 06/06/2017.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv) ImageView imageView;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
