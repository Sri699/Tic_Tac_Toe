package com.example.log_tic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Tac extends AppCompatActivity {

    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];
    private boolean gameEnded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tac);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        Button restart= findViewById(R.id.restart);
        TextView statusTextView = findViewById(R.id.statusTextView);

        String username1=getIntent().getStringExtra("username1");
        String username2=getIntent().getStringExtra("username2");

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int index = i;
            Button button = (Button) gridLayout.getChildAt(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gameEnded) {
                        return;
                    }

                    int row = index / 3;
                    int col = index % 3;

                    if (board[row][col] == 0) {
                        board[row][col] = currentPlayer;
                        button.setText(String.valueOf(currentPlayer));

                        if (checkWin()) {
                            if (currentPlayer=='X'){
                                statusTextView.setText(username1 + " wins!");
                                Toast.makeText(Tac.this, "Player"+ currentPlayer + "Wins!!!", Toast.LENGTH_SHORT).show();
                                gameEnded = true;}
                            else{
                                statusTextView.setText(username2 + " wins!");
                                Toast.makeText(Tac.this, "Player"+ currentPlayer + "Wins!!!", Toast.LENGTH_SHORT).show();
                                gameEnded = true;

                            }
                        } else if (isBoardFull()) {
                            statusTextView.setText("It's a draw!");
                            Toast.makeText(Tac.this, "It's a draw", Toast.LENGTH_SHORT).show();
                            gameEnded = true;
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            statusTextView.setText("Player " + currentPlayer + "'s turn");
                        }
                    }
                }
            });

            restart.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    currentPlayer='X';
                    gameEnded=false;
                    for (int i=0;i<3;i++){
                        for (int j=0;j<3;j++){
                            board[i][j]=0;
                        }
                    }
                    String newButtonText = "";

                    for (int i = 0; i < gridLayout.getChildCount(); i++) {
                        View child = gridLayout.getChildAt(i);
                        ((Button) child).setText(newButtonText);

                    }
                    currentPlayer='X';

                }
            });
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
