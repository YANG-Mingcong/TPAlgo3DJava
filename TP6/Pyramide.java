package TP6;
import TP4.*;


/**
 * Write a description of class Pyramide here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pyramide extends Objet
{
    private Cube m_Cube_initial;  //Le Cube a lorigine de la pyramide.
    private Cube m_Cube_2;
    
    /**
     * Constructor for objects of class Pyramide
     */
    public Pyramide(Noeud _parent)
    {
        super(_parent);
        m_Cube_initial = new Cube(this);
        Vecteur3D pos2 = new Vecteur3D(0.0f, 2.0f, 0.0f);
        Transformation enHaut = new Translation(this, pos2);
        Cube cube_2 = new Cube(enHaut);
    }

    public void empilement()
    {
        Vecteur3D monVecteur = new Vecteur3D(0.0f,0.0f,-8.0f);
        Vecteur3D Pos2 = new Vecteur3D(1.0f,0.0f,0.0f);
        Vecteur3D Pos3 = new Vecteur3D(2.0f,0.0f,0.0f);
        Vecteur3D Pos4 = new Vecteur3D(0.5f,1.0f,0.0f);
        Vecteur3D Pos5 = new Vecteur3D(1.5f,1.0f,0.0f);
        Vecteur3D Pos6 = new Vecteur3D(1.0f,2.0f,0.0f);
        Vecteur3D Pos7 = new Vecteur3D(1.0f,3.0f,0.0f);
    
    }
    
    public void dessine(){}
}
