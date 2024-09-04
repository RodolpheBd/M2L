package com.example.m2l;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private TextField text_field_1; // Nom Competition
    @FXML
    private TextField text_field_2; // Type de sport Competition
    @FXML
    private TextField text_field_3; // Adresse Competition
    @FXML
    private TextField text_field_4; // Date Competition
    @FXML
    private TextField text_field_5; // Nom Equipe
    @FXML
    private TextField text_field_6; // Type de sport Equipe
    @FXML
    private TextField text_field_7; // Competition
    @FXML
    private TextField text_field_8; // Nom Membre
    @FXML
    private TextField text_field_9; // Prenom Membre
    @FXML
    private TextField text_field_10; // Sexe Membre
    @FXML
    private TextField text_field_11; // Date de Naissance Membre
    @FXML
    private TextField text_field_12; // Nom Modification Competition
    @FXML
    private TextField text_field_13; // Nom Modification Equipe
    @FXML
    private TextField text_field_14;
    @FXML
    private TextField text_field_15;
    @FXML
    private TextField text_field_16;
    @FXML
    private TextField text_field_17;
    @FXML
    private TextField text_field_18;
    @FXML
    private TextField text_field_19;
    @FXML
    private TextField text_field_20;
    @FXML
    private TextField text_field_21;
    @FXML
    private TextField text_field_22;

    // Common Methods
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void navigateToView(String viewName) throws IOException {
        Parent sc1 = FXMLLoader.load(getClass().getResource(viewName));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void homeButtonClick() throws IOException {
        navigateToView("hello-view.fxml");
    }

    @FXML
    protected void logoutBouton() {
        Platform.exit();
    }

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ MESSAGE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ COMPETITION @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    // Boutons COMPETITION
    @FXML
    protected void CompetitionBouton() throws IOException {
        navigateToView("Competition-view.fxml");
    }

    @FXML
    protected void CompetitionBouton1() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Competition-view1.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void CompetitionBouton2() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Competition-view2.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void CompetitionBouton3() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Competition-view3.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void returnButtonClickCompetition() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Competition-view.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    // Fonctions COMPETITION
    @FXML
    public void AjoutCompetitionBouton(ActionEvent actionEvent) {
        String nom = text_field_1.getText();
        String typeSport = text_field_2.getText();
        String adresse = text_field_3.getText();
        String date = text_field_4.getText();

        String insertQuery = "INSERT INTO competition(NOM_COMPETITION, TYPE_SPORT, LIEU_COMPETITION, DATE_COMPETITION) VALUES (?,?,?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, typeSport);
            preparedStatement.setString(3, adresse);
            preparedStatement.setString(4, date);

            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Information", "Compétition ajoutée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de la compétition.");
        }
    }

    @FXML
    public void SupprimerCompetitionBouton(ActionEvent actionEvent) {
        String nom = text_field_12.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer un nom de compétition.");
            return;
        }

        if (!competitionExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune compétition trouvée avec ce nom.");
            return;
        }

        String deleteQuery = "DELETE FROM competition WHERE NOM_COMPETITION=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Compétition supprimée avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune compétition trouvée avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression de la compétition.");
        }
    }

    @FXML
    public void ModifierCompetitionBouton(ActionEvent actionEvent) {
        String nom = text_field_12.getText();
        String type_sport = text_field_13.getText();
        String adresse = text_field_14.getText();
        String date = text_field_15.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer le nom de la compétition à modifier.");
            return;
        }

        if (!competitionExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune compétition trouvée avec ce nom.");
            return;
        }

        String updateQuery = "UPDATE competition SET TYPE_SPORT = ?, LIEU_COMPETITION = ?, DATE_COMPETITION = ? WHERE NOM_COMPETITION = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, type_sport);
            preparedStatement.setString(2, adresse);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Compétition modifiée avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune compétition trouvée avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification de la compétition.");
        }
    }

    private boolean competitionExists(String nom) {
        String selectQuery = "SELECT NOM_COMPETITION FROM competition WHERE NOM_COMPETITION=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ EQUIPE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    // Boutons EQUIPE
    @FXML
    protected void EquipeBouton() throws IOException {
        navigateToView("Equipe-view.fxml");
    }

    @FXML
    protected void EquipeBouton1() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Equipe-view1.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void EquipeBouton2() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Equipe-view2.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void EquipeBouton3() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Equipe-view3.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void returnButtonClickEquipe() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Equipe-view.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    // Fonctions EQUIPE
    @FXML
    public void AjoutEquipeBouton(ActionEvent actionEvent) {
        int competition = Integer.parseInt(text_field_7.getText());
        String nom = text_field_5.getText();
        String typeSport = text_field_6.getText();

        String insertQuery = "INSERT INTO equipe (ID_COMPETITION, NOM_EQUIPE, TYPE_SPORT) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, competition);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, typeSport);

            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Information", "Équipe ajoutée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout de l'équipe.");
        }
    }

    @FXML
    public void SupprimerEquipeBouton(ActionEvent actionEvent) {
        String nom = text_field_16.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer un nom d'une équipe.");
            return;
        }

        if (!equipeExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune équipe trouvée avec ce nom");
            return;
        }

        String deleteQuery = "DELETE FROM equipe WHERE NOM_EQUIPE=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Equipe supprimée avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune Equipe trouvée avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression de l'équipe.");
        }
    }

    @FXML
    public void ModifierEquipeBouton(ActionEvent actionEvent) {
        String nom = text_field_16.getText();
        String nouveauTypeSport = text_field_17.getText();
        String idCompetition = text_field_18.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer le nom de l'équipe à modifier.");
            return;
        }

        if (!equipeExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune équipe trouvée avec ce nom.");
            return;
        }

        String updateQuery = "UPDATE equipe SET ID_COMPETITION = ?, TYPE_SPORT = ? WHERE NOM_EQUIPE = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, idCompetition);
            preparedStatement.setString(2, nouveauTypeSport);
            preparedStatement.setString(3, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Équipe modifiée avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucune équipe trouvée avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification de l'équipe.");
        }
    }

    private boolean equipeExists(String nom) {
        String selectQuery = "SELECT NOM_EQUIPE FROM equipe WHERE NOM_EQUIPE=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ MEMBRE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    // Boutons MEMBRE
    @FXML
    protected void MembreBouton() throws IOException {
        navigateToView("Membre-view.fxml");
    }

    @FXML
    protected void MembreBouton1() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Membre-view1.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void MembreBouton2() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Membre-view2.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void MembreBouton3() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Membre-view3.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    @FXML
    protected void returnButtonClickMembre() throws IOException {
        Parent Sc1 = FXMLLoader.load(getClass().getResource("Membre-view.fxml"));
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(Sc1);
        stage.setScene(scene);
    }

    // Fonctions MEMBRE
    @FXML
    public void AjoutMembreBouton(ActionEvent actionEvent) {
        String nom = text_field_8.getText();
        String prenom = text_field_9.getText();
        String sexe = text_field_10.getText();
        String date_naissance = text_field_11.getText();

        String insertQuery = "INSERT INTO membre (NOM_MEMBRE,PRENOM_MEMBRE,SEXE_MEMBRE,DATE_NAISSANCE_MEMBRE) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);
            preparedStatement.setString(4, date_naissance);

            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Information", "Membre ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de l'ajout du membre.");
        }
    }

    @FXML
    public void SupprimerMembreBouton(ActionEvent actionEvent) {
        String nom = text_field_19.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer un nom d'un membre.");
            return;
        }

        if (!membreExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucun membre trouvé avec ce nom.");
            return;
        }

        String deleteQuery = "DELETE FROM membre WHERE NOM_MEMBRE=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Membre supprimé avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucun membre trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la suppression du membre.");
        }
    }

    @FXML
    public void ModifierMembreBouton(ActionEvent actionEvent) {
        String nom = text_field_19.getText();
        String prenom = text_field_20.getText();
        String sexe = text_field_21.getText();
        String date_naissance = text_field_22.getText();

        if (nom == null || nom.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Veuillez entrer le nom du membre à modifier.");
            return;
        }

        if (!membreExists(nom)) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "Aucun membre trouvé avec ce nom.");
            return;
        }

        String updateQuery = "UPDATE membre SET PRENOM_MEMBRE = ?, SEXE_MEMBRE = ?, DATE_NAISSANCE_MEMBRE = ? WHERE NOM_MEMBRE = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, prenom);
            preparedStatement.setString(2, sexe);
            preparedStatement.setString(3, date_naissance);
            preparedStatement.setString(4, nom);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Membre modifié avec succès !");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information", "Aucun membre trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la modification du membre.");
        }
    }

    private boolean membreExists(String nom) {
        String selectQuery = "SELECT NOM_MEMBRE FROM membre WHERE NOM_MEMBRE=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}