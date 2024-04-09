package spark.india.numerologysolutions;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.india.numerologysolutions.R;

public class remedy extends AppCompatActivity {

    @Override
    public void onBackPressed() {
       finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedy);

        Intent abc= getIntent();
        int on,tw,th,fo,fi,si,se,ei,ni;
        on=abc.getIntExtra("one",0);
        tw=abc.getIntExtra("two",0);
        th=abc.getIntExtra("three",0);
        fo=abc.getIntExtra("four",0);
        fi=abc.getIntExtra("five",0);
        si=abc.getIntExtra("six",0);
        se=abc.getIntExtra("seven",0);
        ei=abc.getIntExtra("eight",0);
        ni=abc.getIntExtra("nine",0);

        TextView tv=findViewById(R.id.textView31);
        String str1,str2;

        str2 = "";
        if (on == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 1\n* Tie a red thread on wrist.\n* Wear a surya yantra.\n* Enchant Aadityahridya Stotram(Google) Daily.\n\n");
            str2 = stringBuilder.toString();
        }
        str1 = str2;
        if (tw == 0) {
            String stringBuilder = str2 +
                    "\t\t\t\t\tRemedies For Missing Number 2\n* Wear a crystal in the form of bracelet or pendant.\n* Worship Lord Shiva.\n\n";
            str1 = stringBuilder;
        }
        str2 = str1;
        if (th == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 3\n* Wear a Rudraksh or Tulsi Maala/Bracelet.\n\n");
            str2 = stringBuilder.toString();
        }
        str1 = str2;
        if (fo == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 4\n* Wear a Rudraksh or Tulsi Malla/Bracelet.\n\n");
            str1 = stringBuilder.toString();
        }
        str2 = str1;
        if (fi == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 5\n* Wear a crystal pendant or bracelet.\n* Feed cows.\n\n");
            str2 = stringBuilder.toString();
        }
        str1 = str2;
        if (si == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 6\n* Wear a golden bracelet or watch.\n\n");
            str1 = stringBuilder.toString();
        }
        str2 = str1;
        if (se == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 7\n* Wear a silver watch or bracelet.\n\n");
            str2 = stringBuilder.toString();
        }
        str1 = str2;
        if (ei == 0) {
            String stringBuilder = str2 +
                    "\t\t\t\t\tRemedies For Missing Number 8\n* Wear a crystal pendant or bracelet.\n* Worship Lord Shani.\n\n";
            str1 = stringBuilder;
        }
        str2 = str1;
        if (ni == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append("\t\t\t\t\tRemedies For Missing Number 9\n* Tie a red thread on wrist.\n* Wroship Lord Hanumaan.\n\n");
            str2 = stringBuilder.toString();
        }
        tv.setText(str2);
    }
}