package com.example.okay;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NO
    }

    Player currentPlayer = Player.ONE;
    Player[] playerChoice = new Player[9];

    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8},
            {2, 4, 6}};
    private boolean isGameOver=false;
    private Button btnReset;
    private GridLayout grid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoice[0] = Player.NO;
        playerChoice[1] = Player.NO;
        playerChoice[2] = Player.NO;
        playerChoice[3] = Player.NO;
        playerChoice[4] = Player.NO;
        playerChoice[5] = Player.NO;
        playerChoice[6] = Player.NO;
        playerChoice[7] = Player.NO;
        playerChoice[8] = Player.NO;
        btnReset = findViewById(R.id.btnreset);
        grid=findViewById(R.id.gridlayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resetTheGame();
             // btnReset.setVisibility(View.GONE);
            }
        });

    }

    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int tag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoice[tag]==Player.NO && !isGameOver) {
            tappedImageView.setTranslationX(-2000);

            playerChoice[tag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
               // tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(500);
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }
            //tappedImageView.setImageResource(R.drawable.tiger);
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(500);
            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoice[winnerColumns[0]] == playerChoice[winnerColumns[1]]
                        && playerChoice[winnerColumns[1]] == playerChoice[winnerColumns[2]]
                        && playerChoice[winnerColumns[0]] != Player.NO) {

                    btnReset.setVisibility(View.VISIBLE);
                    isGameOver=true;
                    String winner = " ";
                    if (currentPlayer == Player.ONE) {
                        winner = "player two is winner";

                    } else if (currentPlayer == Player.TWO) {
                        winner = "Player one is winner";

                    }
                    Toast.makeText(this, winner, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //reset method
    private void resetTheGame(){
        for(int index =0;index<grid.getChildCount();index++ ){
            ImageView img= (ImageView) grid.getChildAt(index);
            img.setImageDrawable(null);
            img.setAlpha(0.2f);

        }
        currentPlayer = Player.ONE;
        playerChoice[0] = Player.NO;
        playerChoice[1] = Player.NO;
        playerChoice[2] = Player.NO;
        playerChoice[3] = Player.NO;
        playerChoice[4] = Player.NO;
        playerChoice[5] = Player.NO;
        playerChoice[6] = Player.NO;
        playerChoice[7] = Player.NO;
        playerChoice[8] = Player.NO;
        isGameOver=false;


    }


}