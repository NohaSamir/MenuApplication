package app.dkh.interviewapplication.views;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.dkh.interviewapplication.R;
import app.dkh.interviewapplication.databinding.ActivityDetailsBinding;
import app.dkh.interviewapplication.models.FoodItem;

public class DetailsActivity extends AppCompatActivity {

    public static final String ITEM = "item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Intent intent = getIntent();
        if (intent.hasExtra(ITEM)) {
            binding.setFoodItem(intent.getParcelableExtra(ITEM));
        }
    }

    public static void start(Context context, FoodItem foodItem) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(ITEM, foodItem);
        context.startActivity(intent);
    }
}
