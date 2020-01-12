package app.dkh.interviewapplication.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.databinding.ActivityMainBinding;
import app.dkh.interviewapplication.models.Error;
import app.dkh.interviewapplication.models.MenuResponse;
import app.dkh.interviewapplication.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel _viewModel;
    private ActivityMainBinding _binding;
    private MenuAdapter _adapter;
    private Context _context;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        _viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        _context = this;

        _adapter = new MenuAdapter(item -> DetailsActivity.start(_context, item));

        _binding.menuRecycler.setAdapter(_adapter);

        showLoading(true);

        _viewModel.getMenuItems().observe(this, menuResponseStateData -> {

            if (menuResponseStateData != null) {

                Log.d(TAG, menuResponseStateData.getStatus().name());

                switch (menuResponseStateData.getStatus()) {
                    case SUCCESS:
                    case CACHED:
                        MenuResponse response = menuResponseStateData.getData();
                        if (response != null && response.getItems().size() > 0) {
                            _adapter.submitList(response.getItems());
                            showLoading(false);
                        }
                        break;

                    case ERROR:
                        Error error = menuResponseStateData.getError();
                        if (error != null) {
                            showError(error.getMessage());
                            showLoading(false);
                        }
                        break;

                    case Failure:
                        showError(getString(R.string.error));
                        showLoading(false);
                        break;
                }
            }
        });
    }

    private void showLoading(boolean show) {
        _binding.setLoading(show);
    }


    private void showError(String msg) {
        final Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_INDEFINITE);

        snackBar.setAction(getString(R.string.retry), v -> {
            // Call your action method here
            _viewModel.refresh();

            showLoading(true);
            snackBar.dismiss();
        });
        snackBar.show();
    }
}
