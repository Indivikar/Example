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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.function.Predicate;

/**
 * The RAM-based datastore for the FitlerListApp
 *
 * Users bind to the properties players, viewablePlayers, and filter
 *
 * Manipulate the model via the add() and remove() methods
 *
 * @author carl
 */
public class PlayersModel {

    private final ObservableList<Player> players = FXCollections.observableArrayList();

    private final FilteredList<Player> viewablePlayers = new FilteredList<>(players);

    /**
     * Add a Player to the model
     *
     * Will also affect viewablePlayers if the Player being added adheres to the filter
     *
     * @param p
     */
    public void add( Player p ) {
        players.add(p);
    }

    /**
     * Remove a Player from the model
     *
     * Will also affect viewablePlayers if the Player being removed adheres to the filter
     *
     * @param p
     */
    public void remove( Player p ) {
        players.remove( p );
    }

    /**
     * Bind to this property for the comprehensive list of Players in this model
     *
     * @return
     */
    public ReadOnlyObjectProperty<ObservableList<Player>> playersProperty() {
        return new SimpleObjectProperty<>(players);
    }

    /**
     * Bind to this property for the list of Players in the model that meets the filter criteria
     *
     * Can be the same as playersProperty if there is no filter defined or the filter is set to "Show All"
     *
     * @return
     */
    public ReadOnlyObjectProperty<ObservableList<Player>> viewablePlayersProperty() {
        return new SimpleObjectProperty<>(viewablePlayers);
    }

    /**
     * A Predicate that limits the number of viewablePlayers
     *
     * @return
     */
    public ObjectProperty<Predicate<? super Player>> filterProperty() {
        return viewablePlayers.predicateProperty();
    }

    /**
     * Initializes the model with test data
     */
    public void loadTestData() {

        players.clear();

        add(new Player("BOS", "David Ortiz"));
        add(new Player("BOS", "Jackie Bradley Jr."));
        add(new Player("BOS", "Xander Bogarts"));
        add(new Player("BOS", "Mookie Betts"));
        add(new Player("HOU", "Jose Altuve"));
        add(new Player("HOU", "Will Harris"));
        add(new Player("WSH", "Max Scherzer"));
        add(new Player("WSH", "Bryce Harper"));
        add(new Player("WSH", "Daniel Murphy"));
        add(new Player("WSH", "Wilson Ramos"));
    }
}
