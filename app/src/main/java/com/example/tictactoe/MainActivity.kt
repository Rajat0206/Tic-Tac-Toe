package com.example.tictactoe

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board : Array<Array<Button>>
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3) {IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )

        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }

        initialiseBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER = true;
            TURN_COUNT = 0
            updateDisplay("Player X Turn")
            initialiseBoardStatus()
        }

    }

    private fun initialiseBoardStatus() {
        for (i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j] = -1
                board[i][j].text = ""
                board[i][j].isEnabled = true
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.btn1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }

            R.id.btn2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }

            R.id.btn3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }

            R.id.btn4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }

            R.id.btn5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }

            R.id.btn6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }

            R.id.btn7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }

            R.id.btn8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }

            R.id.btn9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }

        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER)
        {
            updateDisplay("Player X Turn")
        }
        else
        {
            updateDisplay("Player O Turn")
        }

        if(TURN_COUNT == 9)
        {
            updateDisplay("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal --- rows
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

        //Vertical --- columns
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[0][i] == 0) {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0]== boardStatus[2][2])

        //First diagonal
            if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
                if (boardStatus[0][0] == 1) {
                    updateDisplay("Player X Winner")
                } else if (boardStatus[0][0] == 0) {
                    updateDisplay("Player 0 Winner")
                }
            }

        //Second diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player 0 Winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        displayTV.text = text

        if(text.contains("Winner"))
        {
            disableButton()
        }
    }

    private fun disableButton() {
        for(i in board)
        {
            for(button in i)
                button.isEnabled = false
        }
    }


    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(PLAYER) "X" else "O"
        val value = if(PLAYER) 1 else 0

        board[row][col].apply {
            setText(text)
            isEnabled = false
        }

        boardStatus[row][col] = value
    }
}