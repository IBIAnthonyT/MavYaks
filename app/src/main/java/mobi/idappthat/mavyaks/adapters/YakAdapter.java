package mobi.idappthat.mavyaks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mobi.idappthat.mavyaks.R;
import mobi.idappthat.mavyaks.models.Yak;

/**
 * Created by Cameron on 2/11/16.
 */
public class YakAdapter extends RecyclerView.Adapter<YakAdapter.YakHolder> {

    /*
    * The adapter is what knows which layout to choose for each
    * list item and also keeps track of the list it represents,
    * This should extends a RecyclerView adapter which has a
    * ViewHolder
    *
    * Here we just have a basic list of all the "yaks" on the home
    * page, as well as a date formatter.
    *
    * We pass the date formatter to each bind function of the
    * ViewHolder because we don't want to instantiate a new
    * formatter object EVERY time
    * */

    List<Yak> yaks;
    SimpleDateFormat dateFormat;

    public YakAdapter() {
        yaks = new ArrayList<>();
        dateFormat = new SimpleDateFormat("hh:mm a, MM/dd/yyyy", Locale.getDefault());
    }

    /*
    * This function is used to add a yak to the list.
    * We then notify that the data has been changed on the last
    * element of the list.
    * */
    public void addYak(Yak yak) {
        yaks.add(yak);
        notifyItemInserted(yaks.size() - 1);
    }

    public void removeYak(int index) {
        yaks.remove(index);
        notifyItemRemoved(index);
    }


    /*
    * These next few methods are needed whenever you
    * want a custom list like we're doing.
    *
    * If you're interested, you can read a little more at
    * https://www.binpress.com/tutorial/android-l-recyclerview-and-cardview-tutorial/156
    * and
    * http://developer.android.com/training/material/lists-cards.html
    * */

    @Override
    public YakHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row_yak, parent, false);

        return new YakHolder(itemView);
    }

    @Override
    public void onBindViewHolder(YakHolder holder, int position) {
        Yak yak = yaks.get(position);
        holder.bind(yak, dateFormat);
    }

    @Override
    public int getItemCount() {
        return yaks.size();
    }


    /*
    * A ViewHolder is each cell or list item.
    * it holds and inflates every element which can
    * be bind to a "Yak"
    * */
    public class YakHolder extends RecyclerView.ViewHolder {

        TextView tvYak, tvUser, tvDate;
        ImageView ivProfile;
        ImageButton ibReply, ibRetweet, ibStar;

        public YakHolder(View itemView) {
            super(itemView);

            tvYak = (TextView) itemView.findViewById(R.id.tv_yak);
            tvUser = (TextView) itemView.findViewById(R.id.tv_yak_user);
            tvDate = (TextView) itemView.findViewById(R.id.tv_yak_date);

            ivProfile = (ImageView) itemView.findViewById(R.id.iv_yak_profile);

            ibReply = (ImageButton) itemView.findViewById(R.id.ib_reply);
            ibRetweet = (ImageButton) itemView.findViewById(R.id.ib_retweet);
            ibStar = (ImageButton) itemView.findViewById(R.id.ib_star);
        }

        public void bind(Yak yak, SimpleDateFormat dateFormat) {
            tvYak.setText(yak.getTweet());
            tvUser.setText(yak.getUser().getName());
            tvDate.setText(dateFormat.format(yak.getCreatedAt()));
        }
    }
}
