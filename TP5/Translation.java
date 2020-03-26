package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;


/**
 * La classe translation applique une translation définie par
 * l'attribut m_coordonnees
 *
 * @author Alexis Heloir
 * @version 2019/03/20
 */
public class Translation extends Transformation
{
    // ce vecteur stocke les coordonnees de translation
    private Vecteur3D m_coordonnees;

    /**
     * Ce constructeur fixe les coordonnées de transaltion en même temps qu'une réfe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instance courante (this)
     * @param _coordonneees les coordonnées de translation à appliquer
     */
    public Translation(Noeud _parent, Vecteur3D _coordonnees)
    {
        super(_parent);
        m_coordonnees = _coordonnees;
    }
    
    /**
     * Ce constructeur fixe les coordonnées de transaltion en même temps qu'une réfe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instance courante (this)
     * @param _coordonneees les coordonnées de translation à appliquer
     */
    public Translation(Noeud _parent)
    {
        super(_parent);
        m_coordonnees = new Vecteur3D(0.0f,0.0f,0.0f);
    }

    /**
     * implémentation de la méthode abstraite transfome() déclarée initialement dans la
     * classe abstraire Transformation. Elle consiste à appeler l'instruction OpenGL 
     * glTranslatef avec trois coordonnées (float)
     */
    public void transforme()
    {
        GL11.glTranslatef(m_coordonnees.getX(),m_coordonnees.getY(),m_coordonnees.getZ());
    }
}
