package TP6;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;


/**
 * La classe translation applique une translation d�finie par
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
     * Ce constructeur fixe les coordonn�es de transaltion en m�me temps qu'une r�fe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud d�clar� comme parent de l'instance courante (this)
     * @param _coordonneees les coordonn�es de translation � appliquer
     */
    public Translation(Noeud _parent, Vecteur3D _coordonnees)
    {
        super(_parent);
        m_coordonnees = _coordonnees;
    }
    
    /**
     * Ce constructeur fixe les coordonn�es de transaltion en m�me temps qu'une r�fe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud d�clar� comme parent de l'instance courante (this)
     * @param _coordonneees les coordonn�es de translation � appliquer
     */
    public Translation(Noeud _parent)
    {
        super(_parent);
        m_coordonnees = new Vecteur3D(0.0f,0.0f,0.0f);
    }

    /**
     * impl�mentation de la m�thode abstraite transfome() d�clar�e initialement dans la
     * classe abstraire Transformation. Elle consiste � appeler l'instruction OpenGL 
     * glTranslatef avec trois coordonn�es (float)
     */
    public void transforme()
    {
        GL11.glTranslatef(m_coordonnees.getX(),m_coordonnees.getY(),m_coordonnees.getZ());
    }
}
