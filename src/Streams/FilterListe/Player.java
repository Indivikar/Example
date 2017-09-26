/*
 * Copyright 2016 Bekwam, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Streams.FilterListe;

/**
 * Domain object representing a Player
 *
 * @author carl
 */
public class Player {

    private static int counter = 1;

    private final Integer playerId;
    private final String playerName;
    private final String team;

    public Player(String team, String playerName) {
        this.team = team;
        this.playerName = playerName;
        this.playerId = nextId();
    }

    private int nextId() { return counter++; }

    public static int getCounter() {
        return counter;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeam() {
        return team;
    }
}
