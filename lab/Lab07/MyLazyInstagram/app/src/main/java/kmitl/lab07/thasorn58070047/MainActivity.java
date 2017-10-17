package kmitl.lab07.thasorn58070047;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import kmitl.lab07.thasorn58070047.api.LazyInstagramAPI;
import kmitl.lab07.thasorn58070047.api.UserProfile;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Holder.PostAdapter postAdapter = new Holder.PostAdapter(this);

    private String user = "nature";
    private String mode = "grid";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        postAdapter.setLayout(R.layout.item_grid);
        refresh(user, mode);

    }

    public void refresh(String user, String mode){

        getUserProfile(user);

        if(mode.equals("grid")){

            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        }else {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu:
                Toast.makeText(MainActivity.this, "I love pastel!",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.android:
                user = "android";
                refresh(user, mode);
                Toast.makeText(MainActivity.this, "Android become pastel!",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.cartoon:
                user = "cartoon";
                refresh(user, mode);
                Toast.makeText(MainActivity.this, "Cartoon always pastel!",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.nature:
                user = "nature";
                refresh(user, mode);
                Toast.makeText(MainActivity.this, "Nature love pastel!",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.switch_mode:
                if(mode.equals("grid")){

                    mode = "list";
                    postAdapter.setLayout(R.layout.item_list);
                    Toast.makeText(MainActivity.this, "List of pastel!",
                            Toast.LENGTH_LONG).show();

                }else {

                    mode = "grid";
                    postAdapter.setLayout(R.layout.item_grid);
                    Toast.makeText(MainActivity.this, "Grid of pastel!",
                            Toast.LENGTH_LONG).show();

                }
                refresh(user, mode);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void getUserProfile(String name) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramAPI api = retrofit.create(LazyInstagramAPI.class);

        api.getProfile(name).enqueue(new retrofit2.Callback<UserProfile>() {

            @Override
            public void onResponse(retrofit2.Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                if(response.isSuccessful()) {

                    UserProfile result = response.body();

                    TextView textUser = findViewById(R.id.textUser);
                    TextView textPost = findViewById((R.id.textPost));
                    TextView textFollower = findViewById(R.id.textFollower);
                    TextView textFollowing = findViewById(R.id.textFollwing);
                    TextView textBio = findViewById(R.id.textBio);

                    ImageView imageProfile =  findViewById(R.id.imageProfile) ;

                    textUser.setText("@"+result.getUser());
                    textPost.setText("Post\n"+Integer.toString(result.getPost()));
                    textFollower.setText("Follwer\n"+Integer.toString(result.getFollower()));
                    textFollowing.setText("Following\n"+Integer.toString(result.getFollowing()));
                    textBio.setText(result.getBio());

                    postAdapter.setData(result.getPosts());
                    recyclerView.setAdapter(postAdapter);
                    Glide.with(MainActivity.this).load(result.getUrlProfile()).into(imageProfile);

                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserProfile> call, Throwable t) {

            }
        });
    }

}
