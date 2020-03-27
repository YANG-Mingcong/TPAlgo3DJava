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
 
    
    /**
     * Constructor for objects of class Pyramide
     */
    public Pyramide(Noeud _parent)
    {
        super(_parent);
        m_Cube_initial = new Cube(this);
        
        Vecteur3D pos2_1 = new Vecteur3D(-1.0f,2.0f,0.0f);
        Transformation trans2_1 = new Translation(this, pos2_1);
        Cube cube_2_1 = new Cube(trans2_1);
        
        Vecteur3D pos2_2 = new Vecteur3D(1.0f,2.0f,0.0f);
        Transformation trans2_2 = new Translation(this, pos2_2);
        Cube cube_2_2 = new Cube(trans2_2);
        
        Vecteur3D pos3 = new Vecteur3D(0.0f,4.0f,0.0f);
        Transformation trans3 = new Translation(this, pos3);
        Cube cube_3 = new Cube(trans3);
        
        Vecteur3D pos1_1 = new Vecteur3D(-2.0f,0.0f,0.0f);
        Transformation trans1_1 = new Translation(this, pos1_1);
        Cube cube_1_1 = new Cube(trans1_1);
        
        Vecteur3D pos1_3 = new Vecteur3D(2.0f,0.0f,0.0f);
        Transformation trans1_3 = new Translation(this, pos1_3);
        Cube cube_1_3 = new Cube(trans1_3);
        
        Vecteur3D posTop = new Vecteur3D(0.0f,8.0f,0.0f);
        Transformation transTop = new Translation(this, posTop);
        Vecteur3D zRot = new Vecteur3D(0.0f,0.0f,-1.0f);
        Transformation transTopWithRot = new Rotation(transTop, zRot, 45);
        Cube cubeTop = new Cube(transTopWithRot);
        
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
