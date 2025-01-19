package com.mtest.tictactoe

import android.os.Bundle
import android.text.format.Time
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text

class TicTacToe : AppCompatActivity() {
    var turn : String = "X"
    var userX : MutableList<Int> = mutableListOf()
    var userO : MutableList<Int> = mutableListOf()
    var isBoardButtonsClickable = true

    lateinit var textCommentator : TextView
    lateinit var buttonRestart : Button
    lateinit var button_1 : TextView
    lateinit var button_2 : TextView
    lateinit var button_3 : TextView
    lateinit var button_4 : TextView
    lateinit var button_5 : TextView
    lateinit var button_6 : TextView
    lateinit var button_7 : TextView
    lateinit var button_8 : TextView
    lateinit var button_9 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tic_tac_toe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textCommentator = findViewById(R.id.game_commentator)
        buttonRestart = findViewById(R.id.button_restart)

        button_1 = findViewById(R.id.button_1); button_2 = findViewById(R.id.button_2);button_3  = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);button_5  = findViewById(R.id.button_5);button_6  = findViewById(R.id.button_6)
        button_7 = findViewById(R.id.button_7);button_8  = findViewById(R.id.button_8);button_9  = findViewById(R.id.button_9)

        textCommentator.text = "Turn: $turn"
        buttonRestart.isVisible = false

        buttonRestart.setOnClickListener {
            restartTheGame()
        }

        button_1.setOnClickListener {
            handleButtonFromBoard(button_1, 1)
        }
        button_2.setOnClickListener {
            handleButtonFromBoard(button_2, 2)
        }
        button_3.setOnClickListener {
            handleButtonFromBoard(button_3, 3)
        }
        button_4.setOnClickListener {
            handleButtonFromBoard(button_4, 4)
        }
        button_5.setOnClickListener {
            handleButtonFromBoard(button_5, 5)
        }
        button_6.setOnClickListener {
            handleButtonFromBoard(button_6, 6)
        }
        button_7.setOnClickListener {
            handleButtonFromBoard(button_7, 7)
        }
        button_8.setOnClickListener {
            handleButtonFromBoard(button_8, 8)
        }
        button_9.setOnClickListener {
            handleButtonFromBoard(button_9, 9)
        }
    }

    fun handleButtonFromBoard(button: TextView, num: Int) {
        button.text = turn
        button.isClickable = false
        val player = currentPlayerMoves(num)
        if (isWinner(player)) {
            textCommentator.text = "Player $turn won the round!"
            Toast.makeText(this, "player $turn won", Toast.LENGTH_SHORT).show()
            changeBoardButtonsStatuses()
        } else {
            isTheGameDraw()
        }
    }

    fun isTheGameDraw() {
        if (userX.count() + userO.count() == 9) {
            textCommentator.text = "Draw!"
            changeBoardButtonsStatuses()
        } else {
            changeCurrentPlayer()
        }
    }

    fun changeBoardButtonsStatuses() {
        if (isBoardButtonsClickable) {
            isBoardButtonsClickable = false
        } else {
            isBoardButtonsClickable = true
        }
        button_1.isClickable = isBoardButtonsClickable
        button_2.isClickable = isBoardButtonsClickable
        button_3.isClickable = isBoardButtonsClickable
        button_4.isClickable = isBoardButtonsClickable
        button_5.isClickable = isBoardButtonsClickable
        button_6.isClickable = isBoardButtonsClickable
        button_7.isClickable = isBoardButtonsClickable
        button_8.isClickable = isBoardButtonsClickable
        button_9.isClickable = isBoardButtonsClickable

        buttonRestart.isVisible = true
    }

    fun restartTheGame() {
        changeBoardButtonsStatuses()
        button_1.text = "   "
        button_2.text = "   "
        button_3.text = "   "
        button_4.text = "   "
        button_5.text = "   "
        button_6.text = "   "
        button_7.text = "   "
        button_8.text = "   "
        button_9.text = "   "
        turn = "X"
        textCommentator.text = "Turn: $turn"
        buttonRestart.isVisible = false
        userX = mutableListOf()
        userO = mutableListOf()
    }


    fun currentPlayerMoves(num: Int) : MutableList<Int> {
        if (turn == "X") {
            userX.add(num)
            return userX
        } else {
            userO.add(num)
            return userO
        }
    }
    fun changeCurrentPlayer()  {
        if (turn == "X") {
            turn = "O"
            textCommentator.text = "Turn: O"
        } else {
            turn = "X"
            textCommentator.text = "Turn: X"
        }
    }
}
val winCombinations = arrayOf(
    // horizontals
    arrayOf(1, 2, 3),
    arrayOf(4, 5, 6),
    arrayOf(7, 8, 9),
    // Cross
    arrayOf(1, 5, 9),
    arrayOf(3, 5, 7),
    // Verticals
    arrayOf(1, 4, 7),
    arrayOf(2, 5, 8),
    arrayOf(3, 6, 9),
)

private fun isWinner(userMoves: List<Int>): Boolean {
    winCombinations.forEach { row ->
        if (row.all { it in userMoves }) {
            return true
        }
    }
    return false
}