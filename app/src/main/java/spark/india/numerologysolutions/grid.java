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
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.india.numerologysolutions.R;

import java.util.Objects;

public class grid extends AppCompatActivity {


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
                .setPositiveButton("Yes", (dialog, id) -> grid.this.finish())
                .setNegativeButton("No", null)
                .setNeutralButton("Change Values", (dialogInterface, i) -> {
                    Intent na= new Intent(grid.this,info.class);
                    startActivity(na);
                    finish();
                })

                .show();

    }

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);


        TabLayout tl;
        tl=findViewById(R.id.tablay2);

        Intent i1= new Intent(grid.this,kua.class);
        Intent i2= new Intent(grid.this,overall.class);
        Intent personal= new Intent(grid.this,personal.class);
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

        personal.putExtra("nn", nn);
        personal.putExtra("driver", driver);
        personal.putExtra("cond", cond);
        personal.putExtra("py", py);
        personal.putExtra("grid",gri);
        personal.putExtra("day",day);
        personal.putExtra("month",month);
        personal.putExtra("gender",gender);
        personal.putExtra("kua",Kua);

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

        ScrollView s=findViewById(R.id.swipe2);

        s.setOnTouchListener(new OnSwipeTouchListener(grid.this) {

            public void onSwipeRight() {
                startActivity(i2);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
            public void onSwipeLeft() {
                startActivity(i1);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            }

        });


        Objects.requireNonNull(tl.getTabAt(1)).select();

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        startActivity(i2);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        finish();
                        break;
                     case 2:
                         startActivity(i1);
                         overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
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


        TextView mp =findViewById(R.id.textView22);
        TextView ep =findViewById(R.id.textView23);
        TextView pp =findViewById(R.id.textView24);
        TextView tp =findViewById(R.id.textView25);
        TextView wpp =findViewById(R.id.textView26);
        TextView ap =findViewById(R.id.textView27);
        TextView sp1 =findViewById(R.id.textView28);
        TextView sp2 =findViewById(R.id.textView29);
        int on=gri[1],tw=gri[2],th=gri[3],fo=gri[4],fi=gri[5],si=gri[6],se=gri[7],ei=gri[8],ni=gri[9];

        //Memory Plane
        if(tw!=0 && fo!=0 && ni!=0) mp.setText("100%");
        if((tw!=0 && fo!=0 && ni==0) || (tw!=0 && fo==0 && ni!=0) || (tw==0 && fo!=0 && ni!=0))  mp.setText("66%");
        if((tw==0 && fo==0 && ni!=0) || (tw==0 && fo!=0 && ni==0) || (tw!=0 && fo==0 && ni==0)) mp.setText("33%");
        if(tw==0 && fo==0 && ni==0) mp.setText("0%");
        //Emotional Plane
        if(th!=0 && fi!=0 && se!=0) ep.setText("100%");
        if((th!=0 && fi!=0 && se==0) || (th!=0 && fi==0 && se!=0) || (th==0 && fi!=0 && se!=0))  ep.setText("66%");
        if((th==0 && fi==0 && se!=0) || (th==0 && fi!=0 && se==0) || (th!=0 && fi==0 && se==0)) ep.setText("33%");
        if(th==0 && fi==0 && se==0) ep.setText("0%");
        //Practical Plane
        if(ei!=0 && on!=0 && si!=0) pp.setText("100%");
        if((ei!=0 && on!=0 && si==0) || (ei!=0 && on==0 && si!=0) || (ei==0 && on!=0 && si!=0))  pp.setText("66%");
        if((ei==0 && on==0 && si!=0) || (ei==0 && on!=0 && si==0) || (ei!=0 && on==0 && si==0)) pp.setText("33%");
        if(ei==0 && on==0 && si==0) pp.setText("0%");
        //Thought Plane
        if(ei!=0 && th!=0 && fo!=0) tp.setText("100%");
        if((ei!=0 && th!=0 && fo==0) || (ei!=0 && th==0 && fo!=0) || (ei==0 && th!=0 && fo!=0))  tp.setText("66%");
        if((ei==0 && th==0 && fo!=0) || (ei==0 && th!=0 && fo==0) || (ei!=0 && th==0 && fo==0)) tp.setText("33%");
        if(ei==0 && th==0 && fo==0) tp.setText("0%");
        //Will Power Plane
        if(on!=0 && fi!=0 && ni!=0) wpp.setText("100%");
        if((on!=0 && fi!=0 && ni==0) || (on!=0 && fi==0 && ni!=0) || (on==0 && fi!=0 && ni!=0))  wpp.setText("66%");
        if((on==0 && fi==0 && ni!=0) || (on==0 && fi!=0 && ni==0) || (on!=0 && fi==0 && ni==0)) wpp.setText("33%");
        if(on==0 && fi==0 && ni==0) wpp.setText("0%");
        //Action Plane
        if(si!=0 && se!=0 && tw!=0) ap.setText("100%");
        if((si!=0 && se!=0 && tw==0) || (si!=0 && se==0 && tw!=0) || (si==0 && se!=0 && tw!=0))  ap.setText("66%");
        if((si==0 && se==0 && tw!=0) || (si==0 && se!=0 && tw==0) || (si!=0 && se==0 && tw==0)) ap.setText("33%");
        if(si==0 && se==0 && tw==0) ap.setText("0%");
        //Success Plane I
        if(si!=0 && fi!=0 && fo!=0) sp1.setText("100%");
        if((si!=0 && fi!=0 && fo==0) || (si!=0 && fi==0 && fo!=0) || (si==0 && fi!=0 && fo!=0))  sp1.setText("66%");
        if((si==0 && fi==0 && fo!=0) || (si==0 && fi!=0 && fo==0) || (si!=0 && fi==0 && fo==0)) sp1.setText("33%");
        if(si==0 && fi==0 && fo==0) sp1.setText("0%");
        //Success Plane II
        if(ei!=0 && fi!=0 && tw!=0) sp2.setText("100%");
        if((ei!=0 && fi!=0 && tw==0) || (ei!=0 && fi==0 && tw!=0) || (ei==0 && fi!=0 && tw!=0))  sp2.setText("66%");
        if((ei==0 && fi==0 && tw!=0) || (ei==0 && fi!=0 && tw==0) || (ei!=0 && fi==0 && tw==0)) sp2.setText("33%");
        if(ei==0 && fi==0 && tw==0) sp2.setText("0%");


        TextView one=findViewById(R.id.one);
        TextView two=findViewById(R.id.two);
        TextView three=findViewById(R.id.three);
        TextView four=findViewById(R.id.four);
        TextView five=findViewById(R.id.five);
        TextView six=findViewById(R.id.six);
        TextView seven=findViewById(R.id.seven);
        TextView eight=findViewById(R.id.eight);
        TextView nine=findViewById(R.id.nine);

            int k=gri[1],
                m=gri[2],
               n=gri[3],
               num4=gri[4],
               num5=gri[5],
               j=gri[6],
               i3=gri[7],
               num8=gri[8],
               i4=gri[9];

        String str1="",str2="",str3="",str4="",str5="",str6="",str7="",str8="",str9="";

        if (k > 0) {
            if (k == 1) {
                one.setText("1");
                str1 = "\n*It is difficult for you to express your feelings.\n";
            }
            if (k == 2) {
                one.setText("11");
                str1 = "\n*You are able express yourself easily and fluently.\n";
            }
            if (k == 3) {
                one.setText("111");
                str1 = "\n*You are very good in communication and you are an entertainer.\n";
            }
            if (k == 4) {
                one.setText("1111");
                str1 = "\n*You are professionally successful, sometimes it is difficult for you to express yourself.\n";
            }
            if (k == 5) {
                one.setText("11111");
                str1 = "\n*You are a well knows person, you will find yourself in a controversy most of the time, you are easily misunderstood by others, you express your feelings in writing, painting and dancing.\n";
            }
            if (k == 6) {
                one.setText("111111");
                str1 = "\n*You are a well knows person, you will find yourself in a controversy most of the time, you are easily misunderstood by others, you express your feelings in writing, painting and dancing.\n";
            } }


        if (m > 0) {
            if (m == 1) {
                two.setText("2");
                str2 = "\n*You are sensitive and you have intuition powers. You are a moody person.\n";
            }
            if (m == 2) {
                two.setText("22");
                str2 = "\n*You have strong intuition power you are very sensitive and intelligent. You are a moody person.\n";
            }
            if (m == 3) {
                two.setText("222");
                str2 = "\n*You have extraordinary intuition power, you are sensitive and moody. You are easily by others, you prefer some alone time.\n";
            }
            if (m == 4) {
                two.setText("2222");
                str2 = "\n*You are impatient and inclined to over react to small problems. You are extremly sensitive, you like to live alone.\n";
            }
            if (m == 5) {
                two.setText("22222");
                str2 = "\n*You are impatient and inclined to over react to small problems. You are extremly sensitive, you like to live alone.\n";
            }
            if (m == 6) {
                two.setText("222222");
                str2 = "\n*You are impatient and inclined to over react to small problems. You are extremly sensitive, you like to live alone.\n";
            }
        }

        if (n > 0) {
            if (n == 1) {
                three.setText("3");
                str3 = "\n*You are creative, imaginative, optimistic, inspiring and you have excellent memory.\n";
            }
            if (n == 2) {
                three.setText("33");
                str3 = "\n*You are creative, imaginative, mentally alert but slightly eccentric. You are a good writer, painter,actor or politician.\n";
            }
            if (n == 3) {
                three.setText("333");
                str4 = "\n*You live in your imaginary life. You are a good actor.\n";
            }
            if (n == 4) {
                three.setText("3333");
                str3 = "\n*You are impractical, overly imaginative and fearful.\n";
            }
            if (n == 5) {
                three.setText("33333");
                str3 = "\n*You are impractical, overly imaginative and fearful.\n";
            }
            if (n == 6) {
                three.setText("333333");
                str3 = "\n*You are impractical, overly imaginative and fearful.\n";
            }
        }

        if (num4 > 0) {
            if (num4 == 1) {
                four.setText("4");
                str4 = "\n*You are practical, hardworking and balanced. You love music and handicrafts.\n";
            }
            if (num4 == 2) {
                four.setText("44");
                str4 = "\n*You involve in physical and materialistic activities, you have excellent organising skills.\n";
            }
            if (num4 == 3) {
                four.setText("444");
                str4 = "\n*You are extensively involved with physical activities. You are self disciplined, you are very hardworking. Sometimes you waste your time (years) working in the wrong field.\n";
            }
            if (num4 == 4) {
                four.setText("4444");
                str4 = "\n**Rarely found number* You are extensively involved in physical activities.\n";
            }
            if (num4 == 5) {
                four.setText("44444");
                str4 = "\n**Rarely found number* You are extensively involved in physical activities.\n";
            }
            if (num4 == 6) {
                four.setText("444444");
                str4 = "\n**Rarely found number* You are extensively involved in physical activities.\n";
            }
        }

        if (num5 > 0) {
            if (num5 == 1) {
                five.setText("5");
                str5 = "\n*You are emotionally balanced, compassionate, understanding and caring. You motivate and inspire others.\n";
            }
            if (num5 == 2) {
                five.setText("55");
                str5 = "\n*You are intensively determined, enthusiastic. You sometimes fail to control your emotions which causes strife, tension.\n";
            }
            if (num5 == 3) {
                five.setText("555");
                str5 = "\n*You speak without thinking and hurt others easily. You have nature to take unnecessary risks and adventures.\n";
            }
            if (num5 == 4) {
                five.setText("5555");
                str5 = "\n*This is a rare and dangerous combination as it provides the potential for accidents. You need to think before acting and you need to slow down.\n";
            }
            if (num5 == 5) {
                five.setText("55555");
                str5 = "\n*This is a rare and dangerous combination as it provides the potential for accidents. You need to think before acting and you need to slow down.\n";
            }
            if (num5 == 6) {
                five.setText("555555");
                str5 = "\n*This is a rare and dangerous combination as it provides the potential for accidents. You need to think before acting and you need to slow down.\n";
            }
        }

        if (j > 0) {
            if (j == 1) {
                six.setText("6");
                str6 = "\n*You have great love for domesticity. You are a good partner and enjoy domestic responsibilities.\n";
            }
            if (j == 2) {
                six.setText("66");
                str6 = "\n*You worry about home and family. You are very protective about children, you love to enjoy creative activities.\n";
            }
            if (j == 3) {
                six.setText("666");
                str6 = "\n*You are overprotective and possessive of loved ones, stress/tension prove to be a great problem for you.\n";
            }
            if (j == 4) {
                six.setText("6666");
                str6 = "\n*You are highly creative but it is hard to channelise your energy.\n";
            }
            if (j == 5) {
                six.setText("66666");
                str6 = "\n*You are highly creative but it is hard to channelise your energy.\n";
            }
            if (j == 6) {
                six.setText("666666");
                str6 = "\n*You are highly creative but it is hard to channelise your energy.\n";
            }
        }
        if (i3 > 0) {
            if (i3 == 1) {
                seven.setText("7");
                str7 = "\n*You may face losses of love, possessions to health.\n";
            }
            if (i3 == 2) {
                seven.setText("77");
                str7 = "\n*You will grow in knowledge and wisdom by losing either love, health or money. You have interest in occult or spiritual world.\n";
            }
            if (i3 == 3) {
                seven.setText("777");
                str7 = "\n*You may live a sad life caused by major disappointments or set backs in areas of love health and money.\n";
            }
            if (i3 == 4) {
                seven.setText("7777");
                str7 = "\n*You learn through major losses in areas of love, health and finances.\n";
            }
            if (i3 == 5) {
                seven.setText("77777");
                str7 = "\n*You learn through major losses in areas of love, health and finances.\n";
            }
            if (i3 == 6) {
                seven.setText("777777");
                str7 = "\n*You learn through major losses in areas of love, health and finances.\n";
            }
        }
        if (num8 > 0) {
            if (num8 == 1) {
                eight.setText("8");
                str8 = "\n*You are methodical, conscientious and good with details. You are restless, have active mind and need constant mental challenges.\n";
            }
            if (num8 == 2) {
                eight.setText("88");
                str8 = "\n*You are extremely perceptive and conscientious, your opinions and views are fixed and you don't change easily.\n";
            }
            if (num8 == 3) {
                eight.setText("888");
                str8 = "\n*You are restless, rigid and conscientious you fix certain goals in life and achieve them rapidly. You do good in business and finances, you are very materialistic.\n";
            }
            if (num8 == 4) {
                eight.setText("8888");
                str8 = "\n*You are restless and like changes and variety in life you are able to achieve extraordinary success.\n";
            }
            if (num8 == 5) {
                eight.setText("88888");
                str8 = "\n*You are restless and like changes and variety in life you are able to achieve extraordinary success.\n";
            }
            if (num8 == 6) {
                eight.setText("888888");
                str8 = "\n*You are restless and like changes and variety in life you are able to achieve extraordinary success.\n";
            }
        }
        if (i4 > 0) {
            if (i4 == 1) {
                nine.setText("9");
                str9 = "\n*You are ambitious, charitable and you have strong urge to improve yourself.\n";
            }
            if (i4 == 2) {
                nine.setText("99");
                str9 = "\n*You are idealistic, intelligent and critical to others.\n";
            }
            if (i4 == 3) {
                nine.setText("999");
                str9 = "\n*You are idealistic, intelligent and caring. You are happy and positive, you use your mental capabilities. Sometimes you are frustrated and aggressive when in difficult situations.\n";
            }
            if (i4 == 4) {
                nine.setText("9999");
                str9 = "\n*You are highly intelligent, it is difficult for you to cope up with everyday world. You are extremely successful.\n";
            }
            if (i4 == 5) {
                nine.setText("99999");
                str9 = "\n*You are highly intelligent, it is difficult for you to cope up with everyday world. You are extremely successful.\n";
            }
            if (i4 == 6) {
                nine.setText("999999");
                str9 = "\n*You are highly intelligent, it is difficult for you to cope up with everyday world. You are extremely successful.\n";
            }
        }

        String str=str1+str2+str3+str4+str5+str6+str7+str8+str9;
        TextView sh=findViewById(R.id.textView2);
        sh.setText(str);

        Button gr= findViewById(R.id.button3);
        gr.setOnClickListener(view -> {
            Intent abc = new Intent(grid.this,remedy.class);
            abc.putExtra("one",on);
            abc.putExtra("two",tw);
            abc.putExtra("three",th);
            abc.putExtra("four",fo);
            abc.putExtra("five",fi);
            abc.putExtra("six",si);
            abc.putExtra("seven",se);
            abc.putExtra("eight",ei);
            abc.putExtra("nine",ni);
            startActivity(abc);
        });


    }
}