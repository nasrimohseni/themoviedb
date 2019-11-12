package af.nasri.imdb.view;

import androidx.recyclerview.widget.RecyclerView;

import af.nasri.imdb.databinding.ItemMoviesListBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ItemMoviesListBinding itemMoviesListBinding;
    public MovieViewHolder(ItemMoviesListBinding itemMoviesListBinding) {
        super(itemMoviesListBinding.getRoot());
        this.itemMoviesListBinding = itemMoviesListBinding;
    }
}
