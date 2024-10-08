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


    private Neighbour neighbour;
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
        neighbour = (Neighbour) getIntent().getSerializableExtra("clickedNeighbour");

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
        Intent intent = new Intent(activityContext, DetailNeighbourActivity.class);
        intent.putExtra("clickedNeighbour", clickedNeighbour);
        ActivityCompat.startActivity(activityContext, intent, null);
    }

    @OnClick(R.id.backButtonDetail)
    void backToListNeighbourActivity() {
        finish();
    }

    @OnClick(R.id.add_favorite_neighbour)
    void addFavoriteNeighbour() {
        Long neighbourId = neighbour.getId();
        if (!favoriteNeighbourIdRepository.getIdList().contains(neighbourId)) {
            favoriteNeighbourIdRepository.addIdNeighbour(neighbourId);
            Toast.makeText(getApplicationContext(), "Ajout dans les favoris", Toast.LENGTH_SHORT).show();
        }
        else {
            favoriteNeighbourIdRepository.deleteIdNeighbour(neighbourId);
            Toast.makeText(getApplicationContext(), "Enlevé des favoris", Toast.LENGTH_SHORT).show();
        }
    }
}
