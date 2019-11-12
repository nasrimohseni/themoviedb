package af.nasri.imdb.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import af.nasri.imdb.R;
import af.nasri.imdb.databinding.ItemMoviesListBinding;
import af.nasri.imdb.model.Movie;
import af.nasri.imdb.view.MovieViewHolder;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;
    public MoviesRecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMoviesListBinding itemMoviesListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movies_list, parent, false);
        return new MovieViewHolder(itemMoviesListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.itemMoviesListBinding.setMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
