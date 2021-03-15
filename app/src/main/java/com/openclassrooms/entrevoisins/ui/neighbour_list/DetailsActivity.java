package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.squareup.picasso.Picasso;

/**
 * Created by stéphane Warin OCR on 20/02/2021.
 */
public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    NeighbourApiService mNeighbourApiService;
    FloatingActionButton addFavorite;
    Neighbour neighbour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * serialized clicked neighbour
         */
        neighbour = (Neighbour) getIntent().getSerializableExtra("full id");

        addFavorite = findViewById(R.id.add_Favorite);

        mNeighbourApiService = DI.getNeighbourApiService();

        fabColorSetting();

        ImageView avatar = findViewById(R.id.avatar_detail);
        String url = neighbour.getAvatarUrl();
        Picasso.with(this).load(url).into(avatar);

        TextView bigName = findViewById(R.id.bigName);
        bigName.setText(neighbour.getName());

        TextView littleName = findViewById(R.id.littleName);
        littleName.setText(neighbour.getName());

        TextView localisation = findViewById(R.id.localisation);
        localisation.setText(neighbour.getAddress());

        TextView phoneNumber = findViewById(R.id.phone);
        phoneNumber.setText(neighbour.getPhoneNumber());

        TextView webContact = findViewById(R.id.web_Contact);
        webContact.setText(neighbour.getWebContact());

        TextView bio = findViewById(R.id.bio);
        bio.setText(neighbour.getAboutMe());

        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * toggle the serialized neighbour in Dummy service
                 * @param neighbour
                 */
                DI.getNeighbourApiService().toggleFavoriteNeighbour(neighbour);
                fabColorSetting();
                Intent resultIntent = new Intent();
                setResult(RESULT_OK , resultIntent);
            }
        });
    }

    /**
     * set FAB color
     */
    public void fabColorSetting(){
        if(mNeighbourApiService.getFavoriteNeighbours().contains(neighbour)){
            addFavorite.setColorFilter(Color.argb(255,255,232,117));
        }
        else{
            addFavorite.setColorFilter(Color.argb(255,255,255,255));
        };

    }

    /**
     * back to main activity
     * @return
     */
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
       return true;
    }
}
