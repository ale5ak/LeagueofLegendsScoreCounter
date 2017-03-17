/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity keeps track of the basketball score for 2 teams.
 */
public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Team teamA = new Team(R.id.team_a_score);
    Team teamB = new Team(R.id.team_b_score);

    static final String STATE_A_SCORE = "teamAScore";
    static final String STATE_B_SCORE = "teamBScore";

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_A_SCORE, teamA.getScore());
        savedInstanceState.putInt(STATE_B_SCORE, teamB.getScore());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            teamA.addScore(savedInstanceState.getInt(STATE_A_SCORE));
            teamB.addScore(savedInstanceState.getInt(STATE_B_SCORE));
        }

        Button add3forA = (Button) findViewById(R.id.add3forA);
        add3forA.setOnClickListener(this); // calling onClick() method

        Button add2forA = (Button) findViewById(R.id.add2forA);
        add2forA.setOnClickListener(this); // calling onClick() method

        Button add1forA = (Button) findViewById(R.id.add1forA);
        add1forA.setOnClickListener(this); // calling onClick() method


        Button add3forB = (Button) findViewById(R.id.add3forB);
        add3forB.setOnClickListener(this); // calling onClick() method

        Button add2forB = (Button) findViewById(R.id.add2forB);
        add2forB.setOnClickListener(this); // calling onClick() method

        Button add1forB = (Button) findViewById(R.id.add1forB);
        add1forB.setOnClickListener(this); // calling onClick() method


    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        teamA.resetScore();
        teamB.resetScore();
    }


    @Override
    public void onClick(View v) {
        String buttonName = getResources().getResourceEntryName(v.getId());
        int points = Integer.parseInt(buttonName.substring(3, 4));

        if (buttonName.substring(7, 8).equals("A")) {
            teamA.addScore(points);
        } else {
            teamB.addScore(points);
        }
    }

    public class Team {

        private int mScore = 0;
        private int mTextView;

        public Team(int textView) {
            mTextView = textView;
        }

        public void showScore() {
            TextView scoreView = (TextView) findViewById(mTextView);
            scoreView.setText(String.valueOf(mScore));
        }

        public void addScore(int points) {
            mScore += points;
            showScore();
        }

        public void resetScore() {
            mScore = 0;
            showScore();
        }

        public int getScore() {
            return mScore;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimpSlifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
