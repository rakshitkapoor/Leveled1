package com.example.leveled1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leveled1.Bottomfragments.ProfileFragment;
import com.example.leveled1.R;
import com.example.leveled1.Bottomfragments.ToDoFragment;
import com.example.leveled1.Bottomfragments.financeFragment;
import com.example.leveled1.Bottomfragments.homeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Dashboard extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private  int selectedTab=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GoogleSignInAccount acc=GoogleSignIn.getLastSignedInAccount(this);


        final LinearLayout homeLayout=findViewById(R.id.HomeLayout);
        final LinearLayout todoLayout=findViewById(R.id.ToDoLayout);
        final LinearLayout financeLayout=findViewById(R.id.FinanceLayout);
        final LinearLayout profileLayout=findViewById(R.id.ProfileLayout);

        final ImageView homeImage=findViewById(R.id.HomeImage);
        final ImageView todoImage=findViewById(R.id.ToDoImage);
        final ImageView financeImage=findViewById(R.id.FinanceImage);
        final ImageView profileImage=findViewById(R.id.ProfileImage);

        final TextView homeTxt=findViewById(R.id.HomeText);
        final TextView todoTxt=findViewById(R.id.ToDoText);
        final TextView financeTxt=findViewById(R.id.FinanceText);
        final TextView profileTxt=findViewById(R.id.ProfileText);

        //set home fragment as default
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, homeFragment.class,null)
                .commit();

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if home is already selected or not
                if (selectedTab !=1){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,homeFragment.class,null)
                            .commit();

                    todoTxt.setVisibility(View.GONE);
                    financeTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    todoImage.setImageResource(R.drawable.todo_icon);
                    financeImage.setImageResource(R.drawable.finance_icon);
                    profileImage.setImageResource(R.drawable.profile_icon);

                    todoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    financeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_icon_selected);
                    homeLayout.setBackgroundResource(R.drawable.round_bg_home);

                    //animation
                    ScaleAnimation scaleAnim=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnim.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnim);

                    //set 1st tab as selected tab
                    selectedTab=1;
                }
            }
        });

        todoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if to do is already selected or not
                if (selectedTab !=2){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, ToDoFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    financeTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    financeImage.setImageResource(R.drawable.finance_icon);
                    profileImage.setImageResource(R.drawable.profile_icon);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    financeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    todoTxt.setVisibility(View.VISIBLE);
                    todoImage.setImageResource(R.drawable.todo_icon_selected);
                    todoLayout.setBackgroundResource(R.drawable.round_bg_todo);

                    //animation
                    ScaleAnimation scaleAnim=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnim.setFillAfter(true);
                    todoLayout.startAnimation(scaleAnim);

                    //set 1st tab as selected tab
                    selectedTab=2;
                }

            }
        });

        financeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if finance is already selected or not
                if (selectedTab !=3){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, financeFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    todoTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    todoImage.setImageResource(R.drawable.todo_icon);
                    profileImage.setImageResource(R.drawable.profile_icon);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    todoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    financeTxt.setVisibility(View.VISIBLE);
                    financeImage.setImageResource(R.drawable.finance_icon_selected);
                    financeLayout.setBackgroundResource(R.drawable.round_bg_finance);

                    //animation
                    ScaleAnimation scaleAnim=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnim.setFillAfter(true);
                    financeLayout.startAnimation(scaleAnim);

                    //set 1st tab as selected tab
                    selectedTab=3;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if profile is already selected or not
                if (selectedTab !=4){

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, ProfileFragment.class,null)
                            .commit();

                    homeTxt.setVisibility(View.GONE);
                    financeTxt.setVisibility(View.GONE);
                    todoTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.home_icon);
                    financeImage.setImageResource(R.drawable.finance_icon);
                    todoImage.setImageResource(R.drawable.todo_icon);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    financeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    todoLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    profileTxt.setVisibility(View.VISIBLE);
                    profileImage.setImageResource(R.drawable.profile_icon_selected);
                    profileImage.setBackgroundResource(R.drawable.round_bg_profile);

                    //animation
                    ScaleAnimation scaleAnim=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnim.setFillAfter(true);
                    profileLayout.startAnimation(scaleAnim);

                    //set 1st tab as selected tab
                    selectedTab= 4;
                }

            }
        });

    }

}