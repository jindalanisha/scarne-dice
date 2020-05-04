package com.example.scarnesdice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int user_total;
    int user_turn=0;
    int computer_total;
    int computer_turn;
    Button roll,reset,hold;
    ImageView i;
    TextView yourturnscore,computerturnscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_total=0;
        user_turn=0;
        computer_total=0;
        computer_turn=0;

        i =(ImageView)findViewById(R.id.dice) ;
        yourturnscore=(TextView)findViewById(R.id.yourturnscore);
        computerturnscore=(TextView)findViewById(R.id.computerturnscore);

        roll=(Button)findViewById(R.id.roll);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomNumber = random.nextInt(5) + 1;
                if(randomNumber==1) {
                    user_turn =0;
                    yourturnscore.setText(user_turn+"");
                    i.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                    computerturn();
                }
                else {
                    user_turn += randomNumber;
                    yourturnscore.setText(user_turn+"");
                    if (randomNumber == 2)
                        i.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                    else if (randomNumber == 3)
                        i.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                    else if (randomNumber == 4)
                        i.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                    else if (randomNumber == 5)
                        i.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                    else if (randomNumber == 6)
                        i.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                }

            }
        });
        reset=(Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_total=0;
                user_turn=0;
                computer_total=0;
                computer_turn=0;
                ((TextView)findViewById(R.id.your)).setText(user_total+"");
                ((TextView)findViewById(R.id.computer)).setText(computer_total+"");
                ((TextView)findViewById(R.id.yourturnscore)).setText(user_turn+"");
            }
        });

        hold=(Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_total+=user_turn;
                user_turn=0;
                ((TextView)findViewById(R.id.your)).setText(user_total+"");
                ((TextView)findViewById(R.id.yourturnscore)).setText(user_turn+"");
                computerturn();

            }
        });
    }
    public void computerturn()
    {
        computer_turn=0;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           roll.setEnabled(false);
                                           hold.setEnabled(false);
                                           ((TextView)findViewById(R.id.status)).setText("Its Computers turn . Wait....");
                                           Random random = new Random();
                                           int randomNumber = random.nextInt(5) + 1;
                                           Log.e("hey",randomNumber+"");
                                           if(randomNumber==1) {
                                               computer_turn =0;
                                               computerturnscore.setText(computer_turn+"");
                                               i.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
                                               computerfinish();
                                           }
                                           else {
                                               computer_turn += randomNumber;
                                               computerturnscore.setText(computer_turn+"");
                                               if (randomNumber == 2)
                                                   i.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
                                               else if (randomNumber == 3)
                                                   i.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
                                               else if (randomNumber == 4)
                                                   i.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
                                               else if (randomNumber == 5)
                                                   i.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
                                               else if (randomNumber == 6)
                                                   i.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
                                               if(computer_turn<20)
                                                   handler.postDelayed(this, 2000);
                                               else computerfinish();
                                           }

                                       }

           }, 2000);

    }
    public void computerfinish()
    {
        computer_total+=computer_turn;
        ((TextView)findViewById(R.id.computer)).setText(computer_total+"");
        computer_turn=0;
        ((TextView)findViewById(R.id.computerturnscore)).setText(computer_turn+"");
        Toast.makeText(getApplicationContext(),"Make your move now",Toast.LENGTH_LONG).show();
        ((TextView)findViewById(R.id.status)).setText("");
        roll.setEnabled(true);
        hold.setEnabled(true);

    }
}
