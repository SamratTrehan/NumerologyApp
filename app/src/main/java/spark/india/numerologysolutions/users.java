package spark.india.numerologysolutions;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.india.numerologysolutions.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class users extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent i = new Intent(users.this,info.class);
        startActivity(i);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_users);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String filename = "UserProfiles.txt";
        StringBuilder fileContents = new StringBuilder();

        ArrayList<UserData> userList = new ArrayList<>();

        HashSet<String> uniqueProfiles = new HashSet<>();

        try {
            FileInputStream inputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 4) {
                    String userName = userDetails[0];
                    String dateOfBirth = userDetails[1];
                    String gender = userDetails[2];
                    int userImage = Integer.parseInt(userDetails[3]);
                    String profileIdentifier = userName + dateOfBirth;

                    if (!uniqueProfiles.contains(profileIdentifier)) {
                        uniqueProfiles.add(profileIdentifier);
                        userList.add(new UserData(userName, dateOfBirth, userImage, gender));
                    }
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserData[] userDataArray = new UserData[userList.size()];
        userList.toArray(userDataArray);

        UserListAdapter userListAdapter = new UserListAdapter(userDataArray, users.this);
        recyclerView.setAdapter(userListAdapter);

        }
    }
