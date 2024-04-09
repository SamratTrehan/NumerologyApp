package spark.india.numerologysolutions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import com.india.numerologysolutions.R;
import java.util.Objects;

public class personal extends AppCompatActivity {


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
                .setPositiveButton("Yes", (dialog, id) -> personal.this.finish())
                .setNeutralButton("Change Values", (dialogInterface, i) -> {
                    Intent na= new Intent(personal.this,info.class);
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
        setContentView(R.layout.activity_personal);

        Button b123=findViewById(R.id.pybutton);
        EditText e123=findViewById(R.id.editTextNumber);
        TextView tv2=findViewById(R.id.textView33);

        Intent grid= new Intent(personal.this,grid.class);
        Intent kua= new Intent(personal.this,kua.class);
        Intent overall= new Intent(personal.this,overall.class);
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

        kua.putExtra("nn", nn);
        kua.putExtra("driver", driver);
        kua.putExtra("cond", cond);
        kua.putExtra("py", py);
        kua.putExtra("grid",gri);
        kua.putExtra("day",day);
        kua.putExtra("month",month);
        kua.putExtra("gender",gender);
        kua.putExtra("kua",Kua);

        ScrollView s=findViewById(R.id.swipe3);

        s.setOnTouchListener(new personal.OnSwipeTouchListener(personal.this) {

            public void onSwipeRight() {
                startActivity(kua);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }

        });


        TabLayout tl;
        tl=findViewById(R.id.tablay3);
        Objects.requireNonNull(tl.getTabAt(3)).select();
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
                    case 2:
                        startActivity(kua);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
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


        b123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(e123.getText())) {
                    Toast.makeText(getApplicationContext(), "Please Enter All the Year.", Toast.LENGTH_SHORT).show();
                }
                else{
                    e123.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    int year=Integer.parseInt(e123.getText().toString()),sum;
                    int npy=day+month+year;

                    for(int i4 = 0; i4 <4; i4++)
                    {
                        for(sum=0 ;npy!=0 ;npy/=10){
                            sum+=npy %10;
                        }
                        npy=sum;
                    }
                    switch (npy) {
                        default:
                            return;
                        case 9:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, take precautions in your financial, social and personal life. Take extra care of your health 50% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face struggle to maintain balance in social, financial and personal life. Health may be effected, be cautious while driving a vehicle 20 fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face bad health, depression, loss in businesss/ job, social respect can be in danger. Be cautious while driving 30% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face bad health, depression, loss in businesss/ job, betrayals from loved ones. Be cautious while driving 30% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, be alert while driving a vehicle and dealing with people. Beware of backstabbers 50% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, business/ job may be affected stay away from controversies try to maintain balance of your emotions 20% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, if you have done good in past 8 years you'll get good results 50% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, business/ job may be affected stay away from controversies try to maintain balance of your emotions 20% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, you may face bad health no new change in business/ job are seen 60% fate is in your favour");
                            return;
                        case 8:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 8 this is a year for academic gains you will get good results in business/ work field. You may face bad health this year, your relations need more of your attention this is not a good year for health and relations, this is a good year for materialistic gains 75% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 8 this is an average year for business/ job but it is a great year for students/ learners, you will get academic gains you will have spiritual experiences this year 60% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business. You can buy new house/ shop/ car 70% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business 70% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business 80% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 8 this is an average year for business/ job you may face health issues this year. You will expand your property or will get good results in real estate business. You need to take extra care of your relations 50% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                            return;
                        case 7:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 7 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 7 this is not a good year for your health take precautions while driving. This is not a year for change in business/ jobs 55% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 7 this is an average year for you, you may experience inclination towards spirituality. This is a very good year for education, take precautions while deciding something for your business/ job 70% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 7 this is a very good year for you, you may enjoy growth in business/ job. You may get a new house/ car 80% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 7 this is a year of disappointments you may take wrong decisions, you may face financial losses. This is not the year to start anything new 50% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 7 this is a very good year for you, you will get financial gains and will get very good result in business/ job but be alert for every aspect of your life this year 70% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 7 this is not a good year for growth in business/ job you may face betrayals, you'll be disappointed with your work. This is a good year for students or to learn something new, academic gains will be seen. This is a good year for spiritual experiences 55% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 7 this is not a good year for your health/ business/ job, you'll be disappointed with your work you may face betrayals. Stay away from controversies, you might feel helpless at some moments 25% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 7 this is not a good year for growth in business/ job you may face betrayals, you'll be disappointed with your work. This is a good year for students or to learn something new, academic gains will be seen 40% fate is in your favour.");
                            return;
                        case 6:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 6 this is not a good year for you, you may face betrayals from your dear ones. It will be difficult for you to handle your business/ job take extra care while driving a vehicle. Stay away from controversies 65% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. You may get married this year, it is a very good year for materialistic gains 90% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. It is a very good year for materialistic gains 90% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. You may get married this year, it is a very good year for materialistic gains 90% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. It is a very good year for materialistic gains 90% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 6 this is a very good year for growth of business or job this is the year of financial gains you will achieve what you desire. Foreign travel, new relations/ shop/ car are on the cards  85% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 6 this is not a good year for you, you may face betrayals from your dear ones. It will be difficult for you to handle your business/ job take extra care while driving a vehicle. Stay away from controversies 40% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 6 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 80% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 6 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 90% fate is in your favour.");
                            return;
                        case 5:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 5 this is a good year you will do good in your business/ job/ academics. This year will pass like a breeze for you, you'll get joy and happiness this year 75% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains. People who are in real estate business will do very good and get financial gains 80% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 5 this is a good year you will do good in your business/ job/ academics. This year will pass like a breeze for you, you'll get joy and happiness this year 70% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. New relations are on the card 90% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire 90% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire 90% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 5 this is a good year no new changes or growth in business/ job is seen, you will do good in academics. This year will pass like a breeze for you, you'll get joy and happiness this year 70% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 80% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 90% fate is in your favour.");
                            return;
                        case 4:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 4 this year you may face controversies, betrayal in your relationships, emotional set backs. Be cautious while driving, you may be blamed for something you never did 50% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 4 this is a very good time to start a new business or to get in a new job. You will enjoy growth in business,you will do a lot of hard work this year. You can buy new house/ shop/ car but health and relations needs more attention from you 80% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 4 this is a very good time to start a new business or to get in a new job. You will enjoy growth in business, you can buy new house/ shop/ car but health and relations needs more attention from you 75% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your persoanl year 4 this is a veru good year for you, you will rise in business/ job, foreign trips are on the cards. You can buy new house/ car it is a very good year for materialistic gains 90% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 4 this is a very good year for you new business ventures are on the cards. You may get promotions in your job/ work field, this is a good year for tie up for new ventures. Overall it is a good year 80% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 4 this is a good year for you, you can make new changes in business/ job you need to do hard work this year. This is a good year for financial/ health matters but relations can be a reason for depression 80% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 4 this is an average year for you, you will get average results in job/ business you need to do hard work to get results 65% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 4 this is not a good year for you, loss in business/ job you may face betrayals from your dear ones/ known/ unknown. Be alert this year 40% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 4 this is a very good year for you new house/ shop/ car are on the cards. You will get success in your work field, you will enjoy your relations this year 90% fate is in your favour.");
                            return;
                        case 3:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 3 this is a good year for you, you will be recognised in your work field. You can make new changes in job/ business 60% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 3 this year there is no change in job/ business, people connected to insurance/ property sector will get good results 55% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 3 this is a great year for spiritual people, people connected to education sector will do good. Business/ job will be average 50% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 3 this is not a good year for your business/ job/ personal life/ health, you need to stay alert while driving a vehicle 25% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 3 this is an average year for your business but you will do very good in education sector. Overall this is and average year 60% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 3 you will be very spiritual this year, you may acquire a lot of knowledge this year but business growth is average. You need to take extra care for your health 45% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 3 this is a very good year for starting anything new, you will receive respect. You will do good in education/ job business, you will get recognition in your work field 65% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 3 this is an average year for you, you will be able to balance in your financial status 65% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 3 this is a very good year for new business venture or growth in your running business, you may get recognition in your work field. Overall it is a very good year for you 70% fate is in your favour.");
                            return;
                        case 2:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 2 you need to take extra care of your health, beware while driving a vehicle. You may face losses in your business, stay away from controversies. This is not a good year for change, betrayal from your dear ones are on the card 25% fate is in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 2 this year you may face fall/loss in business, health needs your extra care. This is a depressive year for you, be alert while driving a vehicle 20% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 2 you need to take extra care for your health, you may have to face betrayals from your near and dear ones. Financial loses are shown on the cards 40% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 2 this year you may face controversies, bad name in society, no new growth can be seen in business. Court cases could be there 50% fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("You are in your personal year 2 you need to take care of your health this year, if we talk about business or job no new changes can be seen, don't plan new things to do or change this year 60% fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 2 this is a depressive year for you, you need to take extra care of your health and while driving a vehicle. No new change is shown on the card 35% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 2 this year no such change in your business or job, slow growth can be seen in financial matter. Education wise this is a very good year for you 55% fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("You are in your personal year 2 this year you have to take care of your health you should be alert in your relations. Beware of backstabbers, this year could be depressive for you, you will face emotional instability this year 50% fate is in your favour.");
                                    return;
                                case 1:
                                    break;
                            }
                            tv2.setText("You are in your personal year 2 you will get good results in job/ business, you will be emotionally unstable this year. But overall this is a good year 70% fate is in your favour.");
                            return;
                        case 1:
                            switch (i) {
                                default:
                                    return;
                                case 9:
                                    tv2.setText("You are in your personal year 1 this is a very good year for you, this year you may buy a new house/ new car. New business ventures are also seen on the card, new relationships are also seen on the cards. This year will be full of happiness. You will be recognised in your work field 90% of fate will be in your favour.");
                                    return;
                                case 8:
                                    tv2.setText("You are in your personal year 1 you may face bad health in this year, fall in business and be cautious while driving. Stay away from controversies or your reputation may be damaged 40-50% fate is in your favour.");
                                    return;
                                case 7:
                                    tv2.setText("You are in your personal year 1 this is an average year for you if we talk about job/ business but you may enjoy recognition in your work field, academic gains are on the cards 70% fate is in your favour.");
                                    return;
                                case 6:
                                    tv2.setText("You are in your personal year 1 this is a very good year for you, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                                    return;
                                case 5:
                                    tv2.setText("This year you are in your personal year 1, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                                    return;
                                case 4:
                                    tv2.setText("You are in your personal year 1, new business ventures are on the cards, new house, new job/ promotion in job 85% fate is in your favour.");
                                    return;
                                case 3:
                                    tv2.setText("You are in your personal year 1, new house / new shop, promotions in jobs are on the cards. You will learn something new in this year 75% of fate is in your favour.");
                                    return;
                                case 2:
                                    tv2.setText("This year you are in your personal year 1, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                                    return;
                                case 1:

                                    tv2.setText("You are in your personal year 1, this year you may buy a new house/ new car. New business ventures are also seen on the card, new relationships are also seen on the cards. This year will be full of happiness. 90% of fate will be in your favour.");
                            }
            }}}
        });


        TextView ter= findViewById(R.id.textView12);
        ter.setText(py);
        TextView textView = findViewById(R.id.textView);

        switch (j) {
            default:
                return;
            case 9:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, take precautions in your financial, social and personal life. Take extra care of your health 50% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face struggle to maintain balance in social, financial and personal life. Health may be effected, be cautious while driving a vehicle 20 fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face bad health, depression, loss in businesss/ job, social respect can be in danger. Be cautious while driving 30% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, you may face bad health, depression, loss in businesss/ job, betrayals from loved ones. Be cautious while driving 30% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, be alert while driving a vehicle and dealing with people. Beware of backstabbers 50% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, business/ job may be affected stay away from controversies try to maintain balance of your emotions 20% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, if you have done good in past 8 years you'll get good results 50% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 9 this is not a good year this is a year to audit your karmas, business/ job may be affected stay away from controversies try to maintain balance of your emotions 20% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 9 this is an average year this is a year to audit your karmas, you may face bad health no new change in business/ job are seen 60% fate is in your favour");
                return;
            case 8:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 8 this is a year for academic gains you will get good results in business/ work field. You may face bad health this year, your relations need more of your attention this is not a good year for health and relations, this is a good year for materialistic gains 75% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 8 this is an average year for business/ job but it is a great year for students/ learners, you will get academic gains you will have spiritual experiences this year 60% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business. You can buy new house/ shop/ car 70% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business 70% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 8 this is a very good year for growth in business/ job this is the year of financial gains you will also do good in real estate business 80% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 8 this is an average year for business/ job you may face health issues this year. You will expand your property or will get good results in real estate business. You need to take extra care of your relations 50% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 8 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                return;
            case 7:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 7 this is not a good year you may face health issues, physical problems, depression, bad relations, court cases. You may face betrayals it is not a good year for business/ job 30% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 7 this is not a good year for your health take precautions while driving. This is not a year for change in business/ jobs 55% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 7 this is an average year for you, you may experience inclination towards spirituality. This is a very good year for education, take precautions while deciding something for your business/ job 70% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 7 this is a very good year for you, you may enjoy growth in business/ job. You may get a new house/ car 80% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 7 this is a year of disappointments you may take wrong decisions, you may face financial losses. This is not the year to start anything new 50% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 7 this is a very good year for you, you will get financial gains and will get very good result in business/ job but be alert for every aspect of your life this year 70% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 7 this is not a good year for growth in business/ job you may face betrayals, you'll be disappointed with your work. This is a good year for students or to learn something new, academic gains will be seen. This is a good year for spiritual experiences 55% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 7 this is not a good year for your health/ business/ job, you'll be disappointed with your work you may face betrayals. Stay away from controversies, you might feel helpless at some moments 25% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 7 this is not a good year for growth in business/ job you may face betrayals, you'll be disappointed with your work. This is a good year for students or to learn something new, academic gains will be seen 40% fate is in your favour.");
                return;
            case 6:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 6 this is not a good year for you, you may face betrayals from your dear ones. It will be difficult for you to handle your business/ job take extra care while driving a vehicle. Stay away from controversies 65% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. You may get married this year, it is a very good year for materialistic gains 90% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. It is a very good year for materialistic gains 90% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. You may get married this year, it is a very good year for materialistic gains 90% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 6 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. It is a very good year for materialistic gains 90% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 6 this is a very good year for growth of business or job this is the year of financial gains you will achieve what you desire. Foreign travel, new relations/ shop/ car are on the cards  85% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 6 this is not a good year for you, you may face betrayals from your dear ones. It will be difficult for you to handle your business/ job take extra care while driving a vehicle. Stay away from controversies 40% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 6 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 80% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 6 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 90% fate is in your favour.");
                return;
            case 5:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 5 this is a good year you will do good in your business/ job/ academics. This year will pass like a breeze for you, you'll get joy and happiness this year 75% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains. People who are in real estate business will do very good and get financial gains 80% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 5 this is a good year you will do good in your business/ job/ academics. This year will pass like a breeze for you, you'll get joy and happiness this year 70% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire. New relations are on the card 90% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire 90% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 5 this is a very good year in growth of business or job this is the year of financial gains you will achieve what you desire 90% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 5 this is a good year no new changes or growth in business/ job is seen, you will do good in academics. This year will pass like a breeze for you, you'll get joy and happiness this year 70% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 80% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 5 this is a very good year you will get all you desire new house/ shop/ relationships are on the cards. You may get a chance to travel abroad, business/ job will be great this year you'll have financial gains 90% fate is in your favour.");
                return;
            case 4:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 4 this year you may face controversies, betrayal in your relationships, emotional set backs. Be cautious while driving, you may be blamed for something you never did 50% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 4 this is a very good time to start a new business or to get in a new job. You will enjoy growth in business,you will do a lot of hard work this year. You can buy new house/ shop/ car but health and relations needs more attention from you 80% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 4 this is a very good time to start a new business or to get in a new job. You will enjoy growth in business, you can buy new house/ shop/ car but health and relations needs more attention from you 75% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your persoanl year 4 this is a veru good year for you, you will rise in business/ job, foreign trips are on the cards. You can buy new house/ car it is a very good year for materialistic gains 90% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 4 this is a very good year for you new business ventures are on the cards. You may get promotions in your job/ work field, this is a good year for tie up for new ventures. Overall it is a good year 80% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 4 this is a good year for you, you can make new changes in business/ job you need to do hard work this year. This is a good year for financial/ health matters but relations can be a reason for depression 80% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 4 this is an average year for you, you will get average results in job/ business you need to do hard work to get results 65% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 4 this is not a good year for you, loss in business/ job you may face betrayals from your dear ones/ known/ unknown. Be alert this year 40% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 4 this is a very good year for you new house/ shop/ car are on the cards. You will get success in your work field, you will enjoy your relations this year 90% fate is in your favour.");
                return;
            case 3:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 3 this is a good year for you, you will be recognised in your work field. You can make new changes in job/ business 60% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 3 this year there is no change in job/ business, people connected to insurance/ property sector will get good results 55% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 3 this is a great year for spiritual people, people connected to education sector will do good. Business/ job will be average 50% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 3 this is not a good year for your business/ job/ personal life/ health, you need to stay alert while driving a vehicle 25% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 3 this is an average year for your business but you will do very good in education sector. Overall this is and average year 60% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 3 you will be very spiritual this year, you may acquire a lot of knowledge this year but business growth is average. You need to take extra care for your health 45% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 3 this is a very good year for starting anything new, you will receive respect. You will do good in education/ job business, you will get recognition in your work field 65% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 3 this is an average year for you, you will be able to balance in your financial status 65% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 3 this is a very good year for new business venture or growth in your running business, you may get recognition in your work field. Overall it is a very good year for you 70% fate is in your favour.");
                return;
            case 2:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 2 you need to take extra care of your health, beware while driving a vehicle. You may face losses in your business, stay away from controversies. This is not a good year for change, betrayal from your dear ones are on the card 25% fate is in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 2 this year you may face fall/loss in business, health needs your extra care. This is a depressive year for you, be alert while driving a vehicle 20% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 2 you need to take extra care for your health, you may have to face betrayals from your near and dear ones. Financial loses are shown on the cards 40% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 2 this year you may face controversies, bad name in society, no new growth can be seen in business. Court cases could be there 50% fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("You are in your personal year 2 you need to take care of your health this year, if we talk about business or job no new changes can be seen, don't plan new things to do or change this year 60% fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 2 this is a depressive year for you, you need to take extra care of your health and while driving a vehicle. No new change is shown on the card 35% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 2 this year no such change in your business or job, slow growth can be seen in financial matter. Education wise this is a very good year for you 55% fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("You are in your personal year 2 this year you have to take care of your health you should be alert in your relations. Beware of backstabbers, this year could be depressive for you, you will face emotional instability this year 50% fate is in your favour.");
                        return;
                    case 1:
                        break;
                }
                textView.setText("You are in your personal year 2 you will get good results in job/ business, you will be emotionally unstable this year. But overall this is a good year 70% fate is in your favour.");
                return;
            case 1:
                switch (i) {
                    default:
                        return;
                    case 9:
                        textView.setText("You are in your personal year 1 this is a very good year for you, this year you may buy a new house/ new car. New business ventures are also seen on the card, new relationships are also seen on the cards. This year will be full of happiness. You will be recognised in your work field 90% of fate will be in your favour.");
                        return;
                    case 8:
                        textView.setText("You are in your personal year 1 you may face bad health in this year, fall in business and be cautious while driving. Stay away from controversies or your reputation may be damaged 40-50% fate is in your favour.");
                        return;
                    case 7:
                        textView.setText("You are in your personal year 1 this is an average year for you if we talk about job/ business but you may enjoy recognition in your work field, academic gains are on the cards 70% fate is in your favour.");
                        return;
                    case 6:
                        textView.setText("You are in your personal year 1 this is a very good year for you, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                        return;
                    case 5:
                        textView.setText("This year you are in your personal year 1, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                        return;
                    case 4:
                        textView.setText("You are in your personal year 1, new business ventures are on the cards, new house, new job/ promotion in job 85% fate is in your favour.");
                        return;
                    case 3:
                        textView.setText("You are in your personal year 1, new house / new shop, promotions in jobs are on the cards. You will learn something new in this year 75% of fate is in your favour.");
                        return;
                    case 2:
                        textView.setText("This year you are in your personal year 1, new house / new car, foreign travels are on the cards. You can expect marriage proposals this year. Do hard work 90% of fate is in your favour.");
                        return;
                    case 1:
                       textView.setText("You are in your personal year 1, this year you may buy a new house/ new car. New business ventures are also seen on the card, new relationships are also seen on the cards. This year will be full of happiness. 90% of fate will be in your favour.");
        }}


    }}

