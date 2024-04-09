package spark.india.numerologysolutions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.india.numerologysolutions.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    UserData[] userData;
    Context context;



    public UserListAdapter(UserData[] userData,Context context) {
        this.userData = userData;
        this.context = context;
    }
    private void removeItem(int position) {

        UserData userToDelete = userData[position];

        // Filter and rewrite the file without the deleted user
        try {
            FileInputStream fis = context.openFileInput("UserProfiles.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.contains(userToDelete.getUserName() + "," + userToDelete.getDateOfBirth())) {
                    sb.append(line).append("\n");
                }
            }
            fis.close();

            // Rewrite the file without the deleted user
            FileOutputStream fos = context.openFileOutput("UserProfiles.txt", Context.MODE_PRIVATE);
            fos.write(sb.toString().getBytes());
            fos.close();

            // Update the RecyclerView
            List<UserData> tempList = new ArrayList<>(Arrays.asList(userData));
            tempList.remove(position);
            userData = tempList.toArray(new UserData[0]);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int fixedPosition = position;

        final UserData userDataList = userData[position];
        holder.textViewName.setText(userDataList.getUserName());
        holder.textViewDate.setText(userDataList.getDateOfBirth());
        holder.userImage.setImageResource(userDataList.getUserImage());
        holder.textViewGender.setText(userDataList.getGender());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(fixedPosition);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, info.class);
                intent.putExtra("userName", userDataList.getUserName());
                intent.putExtra("dateOfBirth", userDataList.getDateOfBirth());
                intent.putExtra("gender", userDataList.getGender());
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

    }



    @Override
    public int getItemCount() {
        return userData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userImage;
        TextView textViewName;
        TextView textViewDate;
        TextView textViewGender;
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);
            textViewGender = itemView.findViewById(R.id.textGender);
            deleteButton = itemView.findViewById(R.id.delButton);
        }

    }
}
