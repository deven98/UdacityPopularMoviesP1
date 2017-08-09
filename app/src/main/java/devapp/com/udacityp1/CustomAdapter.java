package devapp.com.udacityp1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static devapp.com.udacityp1.MainActivity.movieNames;
import static devapp.com.udacityp1.MainActivity.moviePosterLinks;

/**
 * Created by HP on 09-08-2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context context;

    public CustomAdapter(Context context ){

        this.context = context;
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView movieName;

        ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);

            movieName = (TextView) itemView.findViewById(R.id.movie_name_tv);
            moviePoster = (ImageView) itemView.findViewById(R.id.posterImageView);
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

    }


}
