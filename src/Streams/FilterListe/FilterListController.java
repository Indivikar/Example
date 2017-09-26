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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * JavaFX Controller referenced in FilterList.fxml; the main screen
 *
 * @author carl
 */
public class FilterListController {

    @FXML
    private ListView<Player> lvPlayers;

    @FXML
    private HBox hboxFilters;

    private final PlayersModel playersModel = new PlayersModel();

    private ToggleGroup filtersGroup = new ToggleGroup();

    private EventHandler<ActionEvent> toggleHandler = event -> {
        ToggleButton tb = (ToggleButton)event.getSource();
        Predicate<Player> filter = (Predicate<Player>)tb.getUserData();
        playersModel.filterProperty().set( filter );
    };

    public void initialize() {

        playersModel.loadTestData();

        lvPlayers.setCellFactory(
                (lv) ->
                    new PlayerListCell()
        );

        lvPlayers.itemsProperty().bind( playersModel.viewablePlayersProperty() );

        lvPlayers.setContextMenu( createContextMenu() );

        List<String> teams = playersModel.playersProperty().get()
                .stream()
                .map( (p) -> p.getTeam() )
                .distinct()
                .collect(Collectors.toList());

        //
        // Clear out the existing ToggleButtons from Scene Builder and replace with the real data plus a ToggleButton
        // for "ShowAll"
        //
        hboxFilters.getChildren().clear();
        hboxFilters.getChildren().add( createToggleButton("Show All", new TeamMatcher("*")) );

        teams
                .stream()
                .forEach( (t) -> hboxFilters.getChildren().add( createToggleButton(t, new TeamMatcher(t) )));
    }

    private ToggleButton createToggleButton(String label, TeamMatcher matcher) {
        ToggleButton tb = new ToggleButton(label);
        tb.setUserData( matcher );
        tb.setOnAction( toggleHandler );
        tb.setToggleGroup( filtersGroup );
        return tb;
    }

    private ContextMenu createContextMenu() {
        ContextMenu cm = new ContextMenu();
        MenuItem mi = new MenuItem("Delete");
        mi.setOnAction( (evt) -> {
            Player selectedP = lvPlayers.getSelectionModel().getSelectedItem();
            if( selectedP != null ) {
                playersModel.remove(selectedP);
            }
        });
        cm.getItems().add( mi );
        return cm;
    }

    static class TeamMatcher implements Predicate<Player> {

        private final String team;
        public TeamMatcher(String team) {
            this.team = team;
        }
        @Override
        public boolean test(Player player) {
            if( player == null || team == null ) return false;
            if( team.equals("*") ) {  // for show all
                return true;
            }
            return team.equalsIgnoreCase(player.getTeam());
        }
    }

    static class PlayerListCell extends ListCell<Player> {

        @Override
        protected void updateItem(Player p, boolean empty) {
            super.updateItem(p, empty);

            if( p == null || empty ) {
                setText( null );
            } else {
                setText( p.getPlayerName() + " (" + p.getTeam() + ")" );
            }
        }
    }

    @FXML
    public void showAddPlayer() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(FilterListController.class.getResource("Player.fxml"));

            Parent p = fxmlLoader.load();

            PlayerController c = fxmlLoader.getController();

            Scene scene = new Scene(p);

            Stage stage = new Stage();
            stage.setScene( scene );
            stage.setTitle("Add Player");
            stage.setOnShown( (evt) -> {
                c.setModel( playersModel );
            });
            stage.show();

        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }
}
