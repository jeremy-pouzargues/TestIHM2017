package fr.univ_amu.iut.taquin;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaquinBoard extends GridPane
{
    private int taille;
    private List<Carreau> carreaux;
    private Carreau carreauvide;
    private boolean estPartieTerminee;
    private int nombreDeMouvement;

    private final EventHandler<ActionEvent> ecouteurDeCarreau = event -> {
        Carreau carreauTemp = (Carreau) event.getSource();
        this.deplacer(carreauTemp);
        verifierFinDePartie();
    };


    public TaquinBoard (int taille)
    {
        this.taille = taille;
        estPartieTerminee = false;
        nombreDeMouvement = 0;
        carreaux = new ArrayList<Carreau>(taille * taille);
        initCarreaux();
    }

    private void initCarreaux ()
    {
        int numeroCarreauCourant = 0;
        for (int i = 0; i < taille; ++i)
        {
            for (int j = 0; j < taille; ++j)
            {
                ++ numeroCarreauCourant;
                Carreau carreauCourant = new Carreau (numeroCarreauCourant, new Position(i,j));
                this.add(carreauCourant,i,j);
                carreauCourant.setOnAction(ecouteurDeCarreau);
                carreaux.add(carreauCourant);
            }
        }

        initCarreauVide();
    }

    private void initCarreauVide ()
    {
        carreauvide = carreaux.get(carreaux.size() - 1);
        carreauvide.setText("");
        carreauvide.setDisable(true);
    }

    private void placer (Carreau carreau, Position position)
    {
        carreau.setPosition(position);
        this.setConstraints(carreau, position.getX(), position.getY());
    }

    private void permuter (Carreau carreau1, Carreau carreau2)
    {
        this.placer(carreau1, carreau2.getPosition());
        this.placer(carreau2, carreau1.getPosition());
    }

    private void deplacer (Carreau carreau)
    {
        Position positionCarreau = carreau.getPosition();
        Position positionVide = carreauvide.getPosition();

        if (positionVide == new Position(positionCarreau.getX() + 1, positionCarreau.getY()) ||
                positionVide == new Position(positionCarreau.getX(), positionCarreau.getY() + 1) ||
                positionVide == new Position(positionCarreau.getX() - 1, positionCarreau.getY()) ||
                positionVide == new Position(positionCarreau.getX(), positionCarreau.getY() - 1))
            permuter(carreau, carreauvide);
    }

    private void verifierFinDePartie()
    {
        boolean tempPartieTerminee = true;
        for (int i = 0; i < carreaux.size(); ++i)
        {
            if (! carreaux.get(i).equals(i))
                tempPartieTerminee = false;
        }

        if (tempPartieTerminee == true)
            estPartieTerminee = true;
    }



}