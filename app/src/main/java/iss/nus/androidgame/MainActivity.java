package iss.nus.androidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // https://www.google.com/search?tbm=isch&q=findSomeImage

    Set<Integer> selectedImagePosition = new HashSet<>();
    Set<Integer> selectedImageId = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fetchButton = findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText imageUrl = findViewById(R.id.imageUrl);
                if(imageUrl != null) {
                    String urlToFetch = imageUrl.getText().toString().trim();
                    if(!urlToFetch.isEmpty()) {
                        Toast.makeText(getApplicationContext(), urlToFetch , Toast.LENGTH_SHORT).show();
                        // TODO: Fetch Images here
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a URL" , Toast.LENGTH_SHORT).show();
                        // Temporary start the game here
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        // Render the images
        Integer[] imagesId = {
                R.drawable.afraid,
                R.drawable.full,
                R.drawable.hug,
                R.drawable.laugh,
                R.drawable.no_way,
                R.drawable.peep,
                R.drawable.snore,
                R.drawable.stop,
                R.drawable.tired,
                R.drawable.what,
                R.drawable.afraid,
                R.drawable.full,
                R.drawable.hug,
                R.drawable.laugh,
                R.drawable.no_way,
                R.drawable.peep,
                R.drawable.snore,
                R.drawable.stop,
                R.drawable.tired,
                R.drawable.what,
        };

        //  Include this whole part after we have get the images
        GridView gridview = (GridView) findViewById(R.id.gridView);

        gridview.setAdapter(new ImageAdapter(this, imagesId));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                // Get the R.drawable id of the selected picture
                Integer imageId = (Integer) parent.getItemAtPosition(position);

                MemoryImageView iv = v.findViewById(position + 1);

                iv.toggle();
                // Position Based
                if(iv.getSelected() && selectedImagePosition.size() < 6) {
                    selectedImagePosition.add(position);
                } else if(iv.getSelected() && selectedImagePosition.size() >= 6) {
                    iv.toggle();
                    Toast.makeText(getApplicationContext(), "6 image chosen", Toast.LENGTH_SHORT).show();
                } else {
                    selectedImagePosition.remove(position);
                }

                // Id based
//                if(iv.getSelected() && selectedImageId.size() < 6) {
//                    selectedImageId.add(imageId);
//                } else if(iv.getSelected() && selectedImageId.size() >= 6) {
//                    iv.toggle();
//                    Toast.makeText(getApplicationContext(), "6 image chosen", Toast.LENGTH_SHORT).show();
//                } else {
//                    selectedImageId.remove(imageId);
//                }

//                if(selectedImageId.size() < 6) {
//                    iv.toggle();
//                    if(iv.getSelected()) {
//                        selectedImageId.add(imageId);
//                    } else {
//                        selectedImageId.remove(imageId);
//                    }
//                } else {
//                    if(iv.getSelected()) {
//                        iv.toggle();
//                        selectedImageId.remove(imageId);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_SHORT).show();
//                    }
//                }

//                Integer pos = (Integer) position;
//                Toast.makeText(getApplicationContext(), pos.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}