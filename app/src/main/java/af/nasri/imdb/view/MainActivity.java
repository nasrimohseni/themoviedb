package af.nasri.imdb.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import af.nasri.imdb.Adapter.MoviesRecyclerAdapter;
import af.nasri.imdb.R;
import af.nasri.imdb.databinding.ActivityMainBinding;
import af.nasri.imdb.model.ActivityMainViewModel;
import af.nasri.imdb.model.Movie;
import af.nasri.imdb.model.MovieRepository;
import af.nasri.imdb.model.Popular;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "ActMain";
    RecyclerView moviesRecycler;
    MoviesRecyclerAdapter moviesRecyclerAdapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActivityMainViewModel mainViewModel = ViewModelProviders.of(this).get(ActivityMainViewModel.class);
        moviesRecycler = binding.rvMoviesList;
        moviesRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));

        mainViewModel.getPopularMovies().observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> theMovies) {
                moviesRecyclerAdapter = new MoviesRecyclerAdapter();
                moviesRecycler.setAdapter(moviesRecyclerAdapter);
                moviesRecyclerAdapter.submitList(theMovies);
            }
        });


    }
}
