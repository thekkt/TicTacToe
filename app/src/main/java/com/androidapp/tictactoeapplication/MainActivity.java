package com.androidapp.tictactoeapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    Integer countRed = 0;
    Integer countYellow = 0;
    String playBtnString;
    String  winnerMsg;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningCombinations ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    boolean isPlayable = true;


    public void animation(View view){

        ImageView counter = (ImageView)view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 2 &&  isPlayable) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-2000f);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                winnerTextView.setText("Теперь ход красного!");
            }else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                winnerTextView.setText("Теперь ход желтого!");
            }
            counter.animate().translationYBy(2000f).rotation(200).setDuration(200);


            for(int[] winningPosition: winningCombinations) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    isPlayable = false;
                    winnerMsg = "Красный победил! Дай шанс желтому,сыграй ещё раз!)";
                    playBtnString = "Начать игру!";


                    if (gameState[winningPosition[0]] == 0) {
                        winnerMsg = "Желтый победил! Дай шанс красному,сыграй ещё раз!)";
                        playBtnString = "Начать игру!";

                    }
                    if(winnerMsg.equals("Красный победил! Дай шанс желтому,сыграй ещё раз!)")){
                        countRed++;
                    }
                    if(winnerMsg.equals("Желтый победил! Дай шанс красному,сыграй ещё раз!)")){
                        countYellow++;
                    }
                    Button playBtn = findViewById(R.id.playBtn);
                    playBtn.setText(playBtnString);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winnerMsg);
                    TextView counterRed = findViewById(R.id.counterRed);
                    counterRed.setText(countRed.toString());
                    TextView counterYellow = findViewById(R.id.counterYellow);
                    counterYellow.setText(countYellow.toString());


                } else {
                    boolean isGameOver = true;
                    for (int gameIndex : gameState) {
                        if (gameIndex == 2)
                            isGameOver = false;
                        winnerMsg = "Игра началась!";
                    }

                    if (isGameOver) {
                        TextView winnerTextView = findViewById(R.id.winnerTextView);
                        winnerTextView.setText("Ничья! Хорошая игра! Сыграйте ещё раз!)");
                        Button playBtn = findViewById(R.id.playBtn);
                        playBtn.setText("Сыграть ещё раз!");
                    }
                }
            }
        }
    }
    public  void playAgain(View view){
        isPlayable = true;
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setText("Игра началась!");
        for(int i =0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
