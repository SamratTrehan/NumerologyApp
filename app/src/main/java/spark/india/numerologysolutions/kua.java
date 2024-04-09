package spark.india.numerologysolutions;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.india.numerologysolutions.R;
import java.util.Objects;

public class kua extends AppCompatActivity {


    public static class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener (Context ctx){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

    }



    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> kua.this.finish())
                .setNeutralButton("Change Values", (dialogInterface, i) -> {
                    Intent na= new Intent(kua.this,info.class);
                    startActivity(na);
                    finish();
                })
                .setNegativeButton("No", null)
                .show();

    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kua);

        ScrollView s=findViewById(R.id.swipe4);

        Intent grid= new Intent(kua.this,grid.class);
        Intent personal= new Intent(kua.this,personal.class);
        Intent overall= new Intent(kua.this,overall.class);
        Intent i3= getIntent();


        String nn,cond,driver,py,gender;
        nn=i3.getStringExtra("nn");
        cond=i3.getStringExtra("cond");
        driver=i3.getStringExtra("driver");
        py=i3.getStringExtra("py");
        gender=i3.getStringExtra("gender");
        String Kua = i3.getStringExtra("kua");
        int day=i3.getIntExtra("day",0);
        int month=i3.getIntExtra("month",0);
        int[] gri;
        gri=i3.getIntArrayExtra("grid");

        int i = Integer.parseInt(driver);
        int j = Integer.parseInt(py);

        ((TextView)findViewById(R.id.textKuaViewer)).setText(Kua);

        overall.putExtra("nn", nn);
        overall.putExtra("driver", driver);
        overall.putExtra("cond", cond);
        overall.putExtra("py", py);
        overall.putExtra("grid",gri);
        overall.putExtra("day",day);
        overall.putExtra("month",month);
        overall.putExtra("gender",gender);
        overall.putExtra("kua",Kua);

        grid.putExtra("nn", nn);
        grid.putExtra("driver", driver);
        grid.putExtra("cond", cond);
        grid.putExtra("py", py);
        grid.putExtra("grid",gri);
        grid.putExtra("day",day);
        grid.putExtra("month",month);
        grid.putExtra("gender",gender);
        grid.putExtra("kua",Kua);

        personal.putExtra("nn", nn);
        personal.putExtra("driver", driver);
        personal.putExtra("cond", cond);
        personal.putExtra("py", py);
        personal.putExtra("grid",gri);
        personal.putExtra("day",day);
        personal.putExtra("month",month);
        personal.putExtra("gender",gender);
        personal.putExtra("kua",Kua);



        s.setOnTouchListener(new OnSwipeTouchListener(kua.this) {

            public void onSwipeRight() {
                startActivity(grid);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
            public void onSwipeLeft() {
                startActivity(personal);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }

        });


        TabLayout tl;
        tl=findViewById(R.id.tablay4);
        Objects.requireNonNull(tl.getTabAt(2)).select();
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        startActivity(overall);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        finish();
                        break;
                    case 1:
                        startActivity(grid);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        finish();
                        break;
                    case 3:
                        startActivity(personal);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}