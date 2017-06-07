package dankmemes.myleswh.dankmemes.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dankmemes.myleswh.dankmemes.R;

/**
 * Created by myleswh on 06/06/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    List<String> items = new ArrayList<String>;

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new MainViewHolder(inflater.inflate(R.layout.listitem_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        String item = items.get(position)

        //holder.getV
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setItems(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
