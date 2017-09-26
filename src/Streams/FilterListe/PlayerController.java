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

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaFX Controller referenced in Player.fxml
 *
 * A dialog that is displayed to add a Player
 *
 * @author carl
 */
public class PlayerController {

    @FXML
    private TextField tfPlayerName;

    @FXML
    private ComboBox<String> cmbTeam;

    @FXML
    private Label lblErrPlayerName, lblErrTeam;

    private PlayersModel model;

    public void initialize() {

        tfPlayerName.focusedProperty().addListener(
                (obs,ov,nv) -> {
                    if (nv) {
                        if (lblErrPlayerName.getOpacity() == 1.0d) {
                            fadeOut(lblErrPlayerName);
                        }
                    }
                }
        );

        cmbTeam.focusedProperty().addListener(
                (obs,ov,nv) -> {
                    if( nv ) {
                        if( lblErrTeam.getOpacity() == 1.0d ) {
                            fadeOut(lblErrTeam);
                        }
                    }
                }
        );
    }

    @FXML
    public void addPlayer(ActionEvent evt) {

        List<String> validationErrors = validate();

        if( validationErrors.isEmpty() ) {
            model.add( new Player( cmbTeam.getSelectionModel().getSelectedItem(), tfPlayerName.getText()));
            hide( evt );
        }
    }

    @FXML
    public void cancel(ActionEvent evt) {
        hide(evt);
    }

    private void hide(ActionEvent evt) {
        ((Button)evt.getSource()).getScene().getWindow().hide();
    }

    public void setModel( PlayersModel model ) {

        this.model = model;

        List<String> teams = this.model.playersProperty().get()
                .stream()
                .map( (p) -> p.getTeam() )
                .distinct()
                .collect(Collectors.toList());

        cmbTeam.setItems(FXCollections.observableArrayList( teams ) );
    }

    private List<String> validate() {

        List<String> validationErrors = new ArrayList<>();

        if( tfPlayerName.getText() == null || tfPlayerName.getText().isEmpty() ) {
            fadeIn( lblErrPlayerName );
            validationErrors.add("Player Name is required.");
        }

        if( cmbTeam.getSelectionModel().getSelectedItem() == null ) {
            fadeIn( lblErrTeam );
            validationErrors.add("Team is required.");
        }

        return validationErrors;
    }

    private void fadeIn(Label lbl) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), lbl);
        ft.setFromValue( 0.0d );
        ft.setToValue( 1.0d );
        ft.play();
    }

    private void fadeOut(Label lbl) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), lbl);
        ft.setFromValue( 1.0d );
        ft.setToValue( 0.0d );
        ft.play();
    }
}
