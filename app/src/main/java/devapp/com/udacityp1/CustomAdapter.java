package devapp.com.udacityp1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static devapp.com.udacityp1.MainActivity.movieNames;
import static devapp.com.udacityp1.MainActivity.moviePosterLinks;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;

    final private ListItemClickListener mOnClickListener;

    public CustomAdapter(Context context, ListItemClickListener listener){

        this.context = context;

        mOnClickListener = listener;
    }


    public interface ListItemClickListener{

        void onListItemClick(int clickedItemIndex);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.rv_list_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return movieNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView movieName;

        ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);

            movieName = (TextView) itemView.findViewById(R.id.movie_name_tv);
            moviePoster = (ImageView) itemView.findViewById(R.id.posterImageView);

            itemView.setOnClickListener(this);
        }

        void bind(int position){

            movieName.setText(movieNames.get(position));

            try {
                Picasso.with(context).load(moviePosterLinks.get(position)).into(moviePoster);
            }catch(Exception e){
                e.printStackTrace();
                Log.d("Load error","Cannot load image for "+ movieNames.get(position));

            }
        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

            view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.on_click));

        }
    }


}
