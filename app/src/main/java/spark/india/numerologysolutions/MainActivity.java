package spark.india.numerologysolutions;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.india.numerologysolutions.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        ImageView image1 = findViewById(R.id.imageView);
        image1.startAnimation(animation);
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent i= new Intent(MainActivity.this,info.class);
            startActivity(i);
            finish();
        }, 2000);
    }
}