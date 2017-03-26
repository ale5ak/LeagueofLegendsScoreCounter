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
package com.example.android.leagueoflegendsscorecounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This activity keeps track of the score for blue and red team.
 */
public class MainActivity extends AppCompatActivity {

    static final String STATE_BLUE_TEAM_SCORE = "blueTeamScore";
    static final String STATE_RED_TEAM_SCORE = "redTeamScore";
    Team blueTeam = new Team(R.id.blue_team_score);
    Team redTeam = new Team(R.id.red_team_score);

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_BLUE_TEAM_SCORE, blueTeam.getScore());
        savedInstanceState.putInt(STATE_RED_TEAM_SCORE, redTeam.getScore());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            blueTeam.addScore(savedInstanceState.getInt(STATE_BLUE_TEAM_SCORE));
            redTeam.addScore(savedInstanceState.getInt(STATE_RED_TEAM_SCORE));
        }

        Button[] buttons = new Button[10];
        buttons[0] = (Button) findViewById(R.id.add5forBlue);
        buttons[1] = (Button) findViewById(R.id.add4forBlue);
        buttons[2] = (Button) findViewById(R.id.add3forBlue);
        buttons[3] = (Button) findViewById(R.id.add2forBlue);
        buttons[4] = (Button) findViewById(R.id.add1forBlue);
        buttons[5] = (Button) findViewById(R.id.add5forRed);
        buttons[6] = (Button) findViewById(R.id.add4forRed);
        buttons[7] = (Button) findViewById(R.id.add3forRed);
        buttons[8] = (Button) findViewById(R.id.add2forRed);
        buttons[9] = (Button) findViewById(R.id.add1forRed);

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonName = getResources().getResourceEntryName(v.getId());
                    int points = Integer.parseInt(buttonName.substring(3, 4));

                    if (buttonName.substring(7, buttonName.length()).equals("Blue")) {
                        blueTeam.addScore(points);
                    } else {
                        redTeam.addScore(points);
                    }
                }
            });
        }
    }

    /**
     * Resets the score for both teams back to 0.
     */
    public void resetScore(View v) {
        blueTeam.resetScore();
        redTeam.resetScore();
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
}
