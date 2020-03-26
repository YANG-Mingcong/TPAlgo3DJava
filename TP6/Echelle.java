package TP6;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;

/**
 * La classe Echelle applique une translation d�finie par
 * l'attribut m_vecteurEchelle
 *
 * @author Alexis Heloir
 * @version 2019/03/20
 */
public class Echelle extends Transformation
{

    private Vecteur3D m_vecteurEchelle;

    /**
     * Ce constructeur fixe les coordonn�es de mise � l'�chelle en m�me temps qu'une r�fe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud d�clar� comme parent de l'instance courante (this)
     * @param _vecteurEchelle les coordonn�es de mise � l'echelle � appliquer
     */
    public Echelle(Noeud _parent, Vecteur3D _vecteurEchelle)
    {
        super(_parent);
        m_vecteurEchelle = _vecteurEchelle;
    }

    /**
     * impl�mentation de la m�thode abstraite transfome() d�clar�e initialement dans la
     * classe abstraire Transformation. Elle consiste � appeler l'instruction OpenGL 
     * glScalef avec trois coordonn�es (float)
     */
    public void transforme()
    {
        GL11.glScalef(m_vecteurEchelle.getX(),m_vecteurEchelle.getY(),m_vecteurEchelle.getZ());
    }    
    
}
