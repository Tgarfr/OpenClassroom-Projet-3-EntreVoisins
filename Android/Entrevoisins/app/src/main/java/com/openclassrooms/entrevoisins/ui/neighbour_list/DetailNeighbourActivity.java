package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.repository.FavoriteNeighbourIdRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends AppCompatActivity {


    public static Neighbour neighbour;
    FavoriteNeighbourIdRepository favoriteNeighbourIdRepository;

    @BindView(R.id.avatarDetail)
    ImageView avatarView;
    @BindView(R.id.nameTitreDetail)
    TextView nameTitreView;
    @BindView(R.id.nameDetail)
    TextView nameView;
    @BindView(R.id.phoneNumberDetail)
    TextView phoneInputView;
    @BindView(R.id.addressDetail)
    TextView addressView;
    @BindView(R.id.socialNetworkDetail)
    TextView socialNetwork;
    @BindView(R.id.aboutMeDetail)
    TextView aboutMeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        favoriteNeighbourIdRepository = FavoriteNeighbourIdRepository.getInstance();

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(avatarView);
        nameTitreView.setText(neighbour.getName());
        nameView.setText(neighbour.getName());
        phoneInputView.setText(neighbour.getPhoneNumber());
        addressView.setText(neighbour.getAddress());
        socialNetwork.setText(neighbour.getSocialNetwork());
        aboutMeView.setText(neighbour.getAboutMe());
    }

    public static void navigate(Activity activityContext, Neighbour clickedNeighbour) {
        neighbour = clickedNeighbour;
        Intent intent = new Intent(activityContext, DetailNeighbourActivity.class);
        ActivityCompat.startActivity(activityContext, intent, null);
    }

    @OnClick(R.id.backButtonDetail)
    void backToListNeighbourActivity() {
        finish();
    }

    @OnClick(R.id.add_favorite_neighbour)
    void addFavoriteNeighbour() {
        favoriteNeighbourIdRepository.addIdNeighbour(neighbour.getId());
        Toast.makeText(getApplicationContext(), "Ajout en favori", Toast.LENGTH_SHORT).show();
    }
}
