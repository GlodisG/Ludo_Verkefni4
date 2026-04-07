/******************************************************************************
 *  Nafn    : Ebba Þóra Hvannberg
 *  T-póstur: ebba@hi.is
 *  Lýsing  : er module skrá sem skilgreinir hvaða forritasöfn eru nauðsynleg og hver eru
 *  aðgengileg forritasöfnum  *
 *
 *****************************************************************************/
module Ludo {
    requires javafx.fxml;
    requires javafx.controls;
	requires javafx.base;
    opens is.vidmot to javafx.fxml;
    opens is.vidmot.controller to javafx.fxml;

    exports is.vidmot.controller;
    exports is.vidmot;
    exports is.vinnsla;
}