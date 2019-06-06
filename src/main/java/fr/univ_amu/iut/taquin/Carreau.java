package fr.univ_amu.iut.taquin;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class Carreau extends Button
{
    private Integer value;
    private Position position;
    private Position positionAttendue;


    public Carreau (Integer numero, Position position)
    {
        this.value = numero;
        this.position = position;
        this.positionAttendue = position;

        this.setMinSize(TaquinBoard.TAILLE_CASE,TaquinBoard.TAILLE_CASE);
        this.setPrefSize(TaquinBoard.TAILLE_CASE,TaquinBoard.TAILLE_CASE);
        this.setMaxSize(TaquinBoard.TAILLE_CASE, TaquinBoard.TAILLE_CASE);

        this.setAlignment(Pos.CENTER);
        this.setText(numero.toString());
    }


    public Integer getValue()
    {
        return value;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition (Position position)
    {
        this.position = position;
    }

    public boolean estBienPlace()
    {
        return this.position == this.positionAttendue;
    }


}