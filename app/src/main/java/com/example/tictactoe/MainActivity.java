package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity
{
    private int currentPlayer = 0;
    private int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    private int[][] winningPositions = {{0, 1, 2},
                                        {3, 4, 5},
                                        {6, 7, 8},
                                        {0, 3, 6},
                                        {1, 4, 7},
                                        {2, 5, 8},
                                        {0, 4, 8},
                                        {2, 4, 6}};
    private String winner = "";

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == -1 && winner.compareTo("") == 0)
        {
            counter.setTranslationY(-1500);
            gameState[tappedCounter] = currentPlayer;

            if (currentPlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                currentPlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                currentPlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(500);

            for (int[] array : winningPositions)
            {
                if (gameState[array[0]] == gameState[array[1]] && gameState[array[2]] == gameState[array[1]] && gameState[array[0]] != -1)
                {
                    if (currentPlayer == 0)
                    {
                        winner = "Red";
                    }
                    else
                    {
                        winner = "Yellow";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgain);

                    TextView winnerText = (TextView) findViewById(R.id.winnerText);

                    winnerText.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerText.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.playAgain);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerText);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i < grid.getChildCount(); i++)
        {

            ImageView counter = (ImageView) grid.getChildAt(i);

            counter.setImageDrawable(null);

        }

        currentPlayer = 0;

        for(int i=0; i<9; i++)
        {
            gameState[i] = -1;
        }

        winner = "";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}