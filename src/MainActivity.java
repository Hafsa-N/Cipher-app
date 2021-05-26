
 // Student Name : Hafsa Nafaa
 // This lab was done with my lab partner Raidah Shomanti
 /*
 IMPORTANT: Please note that we have followed the book's directions where it clearly states that
 the game stops after 9 questions, so the 10th question never gets asked.
 The Moodle instruction stating that the game stops after 10 questions is inconsistent with the book.
 Please keep this in mind. Thank you.
 */

 // LINK TO VIDEO: https://youtu.be/Q2K0wjEe5tQ

package com.example.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Game game; //to hold Game object instance
    private String question; //to hold value of prev q
    private String answer; //to hold value of prev ans
    private int score = 0; //score is init to 0, value might be updated depending on user's answers
    private int qNum = 1; //qNum is init to 1, the value will increment as more questions are asked

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ask(); //ask a question when app is launched by invoking the ask() method below
    }

    private void ask()
    {
        if (qNum <= 9)
        {
            game = new Game();  //a new game object is created whenever ask() is invoked
            String qa = game.qa();  //get the question/answer string from the qa() method in Game model

            String q = qa.substring(0, qa.indexOf("\n") + 1); //this substring of qa gets the question part
            String ans = qa.substring(qa.indexOf("\n") + 1); //this substring of qa gets the answer part

            ((TextView) findViewById(R.id.question)).setText(q); //show question in view
            question = q; //update value of global attribute to hold the current question being asked
            answer = ans; //similarly..
        }

        else
        {
            ((TextView) findViewById(R.id.question)).setText(question);
        }
    }

    public void onDone(View v)
    {
        String prevUserAns = ((EditText) findViewById(R.id.answer)).getText().toString(); //get prev answer from view

        String showInLog = String.format("%s", "Q#" + qNum + ": " + question + "Your answer: "
                           + prevUserAns.toUpperCase() + "\nThe correct answer: " + answer); //concat into a string for the log

        TextView log = findViewById(R.id.log); //var log for log textview to avoid long concat below

        log.setText(showInLog + "\n\n" + log.getText()); //PREpend string to log entry

        if (prevUserAns.equalsIgnoreCase(answer))
        {
            score++;
            ((TextView) findViewById(R.id.score)).setText("SCORE = " + score); //update score in view
        }

        ((EditText) findViewById(R.id.answer)).setText(""); //erase prev answer from view

        qNum++; //increment qNum attribute value
        ((TextView) findViewById(R.id.qNum)).setText("Q# " + qNum); //update qNum in app view

        ask(); //ask another question

       if (qNum == 10)
        {
            ((TextView) findViewById(R.id.qNum)).setText("GAME OVER!");

            Button btn = findViewById(R.id.done);
            btn.setEnabled(false);  //disable done button
          //  finish();
        }
    }
}
