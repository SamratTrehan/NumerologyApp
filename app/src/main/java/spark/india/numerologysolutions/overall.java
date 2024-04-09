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

public class overall extends AppCompatActivity {

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
                .setPositiveButton("Yes", (dialog, id) -> overall.this.finish())
                .setNeutralButton("Change Values", (dialogInterface, i) -> {
                    Intent na= new Intent(overall.this,info.class);
                    startActivity(na);
                    finish();
                })
                .setNegativeButton("No", null)
                .show();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);


        Intent i1= new Intent(overall.this,grid.class);
        Intent kua= new Intent(overall.this,kua.class);
        Intent i2= new Intent(overall.this,personal.class);
        Intent i= getIntent();

        String nn,cond,driver,py,gender;
        nn=i.getStringExtra("nn");
        cond=i.getStringExtra("cond");
        driver=i.getStringExtra("driver");
        py=i.getStringExtra("py");
        gender=i.getStringExtra("gender");
        String Kua = i.getStringExtra("kua");
        int day=i.getIntExtra("day",0);
        int month=i.getIntExtra("month",0);
        int[] gri;
        gri=i.getIntArrayExtra("grid");

        kua.putExtra("nn", nn);
        kua.putExtra("driver", driver);
        kua.putExtra("cond", cond);
        kua.putExtra("py", py);
        kua.putExtra("grid",gri);
        kua.putExtra("day",day);
        kua.putExtra("month",month);
        kua.putExtra("gender",gender);
        kua.putExtra("kua",Kua);

        i1.putExtra("nn", nn);
        i1.putExtra("driver", driver);
        i1.putExtra("cond", cond);
        i1.putExtra("py", py);
        i1.putExtra("grid",gri);
        i1.putExtra("day",day);
        i1.putExtra("month",month);
        i1.putExtra("gender",gender);
        i1.putExtra("kua",Kua);

        i2.putExtra("nn", nn);
        i2.putExtra("driver", driver);
        i2.putExtra("cond", cond);
        i2.putExtra("py", py);
        i2.putExtra("grid",gri);
        i2.putExtra("day",day);
        i2.putExtra("month",month);
        i2.putExtra("gender",gender);
        i2.putExtra("kua",Kua);

        ScrollView s= findViewById(R.id.swipe);

        s.setOnTouchListener(new OnSwipeTouchListener(overall.this) {

            public void onSwipeLeft() {
                startActivity(i1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }
        });



        TabLayout tl;
        tl=findViewById(R.id.tablay);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 1:
                        startActivity(i1);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        break;
                    case 2:
                        startActivity(kua);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                        break;
                    case 3:
                        startActivity(i2);
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

        TextView tv=findViewById(R.id.textView4);
        int a,b;
        a= Integer.parseInt(driver);
        b= Integer.parseInt(cond);



        ((TextView)findViewById(R.id.textView7)).setText(nn);
        ((TextView)findViewById(R.id.textView8)).setText(driver);
        ((TextView)findViewById(R.id.textView9)).setText(cond);
        ((TextView)findViewById(R.id.textViewKua2)).setText(Kua);


        if (a==1 && b==1)
            tv.setText(getString (R.string.oneone));
        if (a==1 && b==2)
            tv.setText(getString (R.string.onetwo));
        if (a==1 && b==3)
            tv.setText(getString (R.string.onethree));
        if (a==1 && b==4)
            tv.setText(getString (R.string.onefour));
        if (a==1 && b==5)
            tv.setText(getString (R.string.onefive));
        if (a==1 && b==6)
            tv.setText(getString (R.string.onesix));
        if (a==1 && b==7)
            tv.setText(getString (R.string.oneseven));
        if (a==1 && b==8)
            tv.setText(getString (R.string.oneeight));
        if (a==1 && b==9)
            tv.setText(getString (R.string.onenine));

        if (a==2 && b==1)
            tv.setText(getString (R.string.twoone));
        if (a==2 && b==2)
            tv.setText(getString (R.string.twotwo));
        if (a==2 && b==3)
            tv.setText(getString (R.string.twothree));
        if (a==2 && b==4)
            tv.setText(getString (R.string.twofour));
        if (a==2 && b==5)
            tv.setText(getString (R.string.twofive));
        if (a==2 && b==6)
            tv.setText(getString (R.string.twosix));
        if (a==2 && b==7)
            tv.setText(getString (R.string.twoseven));
        if (a==2 && b==8)
            tv.setText(getString (R.string.twoeight));
        if (a==2 && b==9)
            tv.setText(getString (R.string.twonine));

        if (a==3 && b==1)
            tv.setText(getString (R.string.threeone));
        if (a==3 && b==2)
            tv.setText(getString (R.string.threetwo));
        if (a==3 && b==3)
            tv.setText(getString (R.string.threethree));
        if (a==3 && b==4)
            tv.setText(getString (R.string.threefour));
        if (a==3 && b==5)
            tv.setText(getString (R.string.threefive));
        if (a==3 && b==6)
            tv.setText(getString (R.string.threesix));
        if (a==3 && b==7)
            tv.setText(getString (R.string.threeseven));
        if (a==3 && b==8)
            tv.setText(getString (R.string.threeeight));
        if (a==3 && b==9)
            tv.setText(getString (R.string.threenine));

        if (a==4 && b==1)
            tv.setText(getString (R.string.fourone));
        if (a==4 && b==2)
            tv.setText(getString (R.string.fourtwo));
        if (a==4 && b==3)
            tv.setText(getString (R.string.fourthree));
        if (a==4 && b==4)
            tv.setText(getString (R.string.fourfour));
        if (a==4 && b==5)
            tv.setText(getString (R.string.fourfive));
        if (a==4 && b==6)
            tv.setText(getString (R.string.foursix));
        if (a==4 && b==7)
            tv.setText(getString (R.string.fourseven));
        if (a==4 && b==8)
            tv.setText(getString (R.string.foureight));
        if (a==4 && b==9)
            tv.setText(getString (R.string.fournine));

        if (a==5 && b==1)
            tv.setText(getString (R.string.fiveone));
        if (a==5 && b==2)
            tv.setText(getString (R.string.fivetwo));
        if (a==5 && b==3)
            tv.setText(getString (R.string.fivethree));
        if (a==5 && b==4)
            tv.setText(getString (R.string.fivefour));
        if (a==5 && b==5)
            tv.setText(getString (R.string.fivefive));
        if (a==5 && b==6)
            tv.setText(getString (R.string.fivesix));
        if (a==5 && b==7)
            tv.setText(getString (R.string.fiveseven));
        if (a==5 && b==8)
            tv.setText(getString (R.string.fiveeight));
        if (a==5 && b==9)
            tv.setText(getString (R.string.fivenine));

        if (a==6 && b==1)
            tv.setText(getString (R.string.sixone));
        if (a==6 && b==2)
            tv.setText(getString (R.string.sixtwo));
        if (a==6 && b==3)
            tv.setText(getString (R.string.sixthree));
        if (a==6 && b==4)
            tv.setText(getString (R.string.sixfour));
        if (a==6 && b==5)
            tv.setText(getString (R.string.sixfive));
        if (a==6 && b==6)
            tv.setText(getString (R.string.sixsix));
        if (a==6 && b==7)
            tv.setText(getString (R.string.sixseven));
        if (a==6 && b==8)
            tv.setText(getString (R.string.sixeight));
        if (a==6 && b==9)
            tv.setText(getString (R.string.sixnine));

        if (a==7 && b==1)
            tv.setText(getString (R.string.sevenone));
        if (a==7 && b==2)
            tv.setText(getString (R.string.seventwo));
        if (a==7 && b==3)
            tv.setText(getString (R.string.seventhree));
        if (a==7 && b==4)
            tv.setText(getString (R.string.sevenfour));
        if (a==7 && b==5)
            tv.setText(getString (R.string.sevenfive));
        if (a==7 && b==6)
            tv.setText(getString (R.string.sevensix));
        if (a==7 && b==7)
            tv.setText(getString (R.string.sevenseven));
        if (a==7 && b==8)
            tv.setText(getString (R.string.seveneight));
        if (a==7 && b==9)
            tv.setText(getString (R.string.sevennine));

        if (a==8 && b==1)
            tv.setText(getString (R.string.eightone));
        if (a==8 && b==2)
            tv.setText(getString (R.string.eighttwo));
        if (a==8 && b==3)
            tv.setText(getString (R.string.eightthree));
        if (a==8 && b==4)
            tv.setText(getString (R.string.eightfour));
        if (a==8 && b==5)
            tv.setText(getString (R.string.eightfive));
        if (a==8 && b==6)
            tv.setText(getString (R.string.eightsix));
        if (a==8 && b==7)
            tv.setText(getString (R.string.eightseven));
        if (a==8 && b==8)
            tv.setText(getString (R.string.eighteight));
        if (a==8 && b==9)
            tv.setText(getString (R.string.eightnine));

        if (a==9 && b==1)
            tv.setText(getString (R.string.nineone));
        if (a==9 && b==2)
            tv.setText(getString (R.string.ninetwo));
        if (a==9 && b==3)
            tv.setText(getString (R.string.ninethree));
        if (a==9 && b==4)
            tv.setText(getString (R.string.ninefour));
        if (a==9 && b==5)
            tv.setText(getString (R.string.ninefive));
        if (a==9 && b==6)
            tv.setText(getString (R.string.ninesix));
        if (a==9 && b==7)
            tv.setText(getString (R.string.nineseven));
        if (a==9 && b==8)
            tv.setText(getString (R.string.nineeight));
        if (a==9 && b==9)
            tv.setText(getString (R.string.ninenine));


    }
}