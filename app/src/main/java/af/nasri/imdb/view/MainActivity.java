package af.nasri.imdb.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import af.nasri.imdb.Adapter.MoviesRecyclerAdapter;
import af.nasri.imdb.R;
import af.nasri.imdb.databinding.ActivityMainBinding;
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

        moviesRecycler = binding.rvMoviesList;
        moviesRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        MovieRepository.getPopularMovies().observe(this, new Observer<Popular>() {
            @Override
            public void onChanged(Popular popular) {
                moviesRecyclerAdapter = new MoviesRecyclerAdapter(popular.getMovies());
                moviesRecycler.setAdapter(moviesRecyclerAdapter);
            }
        });


    }
}
