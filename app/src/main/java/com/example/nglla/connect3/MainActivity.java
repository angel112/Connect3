package com.example.nglla.connect3;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0:Yellow .. 1:Red .. 2:Empty
    int activePlayer = 0;
    boolean active = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winner = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void chance(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && active){

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            TextView wTextView = (TextView) findViewById(R.id.Info);
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1500).setDuration(100);

                wTextView.setText("Red's Chance");
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1500).setDuration(100);
                wTextView.setText("Yellow's Chance");
                activePlayer = 0;
            }

            for(int[] winner: winner) {
                if (gameState[winner[0]] == gameState[winner[1]] && gameState[winner[1]] == gameState[winner[2]] && gameState[winner[0]]!=2) {
                    active = false;
                    String win = "";
                    GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
                    ImageView c1 = (ImageView) gridLayout.getChildAt(winner[0]);
                    ImageView c2 = (ImageView) gridLayout.getChildAt(winner[1]);
                    ImageView c3 = (ImageView) gridLayout.getChildAt(winner[2]);
                    c1.animate().rotationBy(36000).setDuration(100000);
                    c2.animate().rotationBy(36000).setDuration(100000);
                    //SystemClock.sleep(900);
                    //c3.animate().rotationBy(36000).setDuration(100000);

                    if(activePlayer == 0) {
                        win = "Red";
                    }
                    else {
                        win = "Yellow";
                    }
                    Button playAgain = (Button) findViewById(R.id.play);
                    c3.animate().rotationBy(36000).setDuration(100000);
                    TextView winnerTextView = (TextView)findViewById(R.id.Info);
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setText(win+" has Won!");
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view){
        TextView winnerTextView = (TextView)findViewById(R.id.Info);
        winnerTextView.setText("Start the Game");
        Button playAgain = (Button) findViewById(R.id.play);
        playAgain.setVisibility(View.INVISIBLE);
        for(int i =0;i<9;i++) {
            GridLayout gridLayout1 = (GridLayout) findViewById(R.id.grid);
            ImageView c;
            c = (ImageView) gridLayout1.getChildAt(i);
            c.setImageDrawable(null);
            gameState[i] = 2;
            activePlayer = 0;
            active = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
