package TP6;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;


/**
 * La classe Rotation applique une translation d�finie par
 * le attributs m_angle et m_axe
 *
 * @author Alexis Heloir
 * @version 2019/03/20
 */
public class Rotation extends Transformation
{

    private float m_angle;
    private Vecteur3D m_axe;
    

    /**
     * Ce constructeur fixe les coordonn�es de transaltion en m�me temps qu'une r�fe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud d�clar� comme parent de l'instance courante (this)
     * @param _coordonneees l'axe autour duquel effectuer la rotation
     * @param _angle l'angle de rotation autour de l'axe
     */
    public Rotation(Noeud _parent, Vecteur3D _axe, float _angle)
    {
        super(_parent);
        m_axe = _axe;
        m_angle = _angle;
    }

    /**
     * impl�mentation de la m�thode abstraite transfome() d�clar�e initialement dans la
     * classe abstraire Transformation. Elle consiste � appeler l'instruction OpenGL 
     * glRotatef avec une coordonn�e repr�sentant l'angle de rotation et trois coordonn�es 
     * (float) repr�sentant l'axe de rotation.
     */    
    public void transforme(){
        GL11.glRotatef(m_angle, m_axe.getX(), m_axe.getY(), m_axe.getZ());
    }
    
}
