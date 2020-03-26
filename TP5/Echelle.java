package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe Echelle applique une translation définie par
 * l'attribut m_vecteurEchelle
 *
 * @author Alexis Heloir
 * @version 2019/03/20
 */
public class Echelle extends Transformation
{

    private Vecteur3D m_vecteurEchelle;

    /**
     * Ce constructeur fixe les coordonnées de mise à l'échelle en même temps qu'une réfe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instance courante (this)
     * @param _vecteurEchelle les coordonnées de mise à l'echelle à appliquer
     */
    public Echelle(Noeud _parent, Vecteur3D _vecteurEchelle)
    {
        super(_parent);
        m_vecteurEchelle = _vecteurEchelle;
    }

    /**
     * implémentation de la méthode abstraite transfome() déclarée initialement dans la
     * classe abstraire Transformation. Elle consiste à appeler l'instruction OpenGL 
     * glScalef avec trois coordonnées (float)
     */
    public void transforme()
    {
        GL11.glScalef(m_vecteurEchelle.getX(),m_vecteurEchelle.getY(),m_vecteurEchelle.getZ());
    }    
    
}
