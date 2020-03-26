package TP5;

import org.lwjgl.opengl.GL11;
import TP4.Vecteur3D;


/**
 * La classe Rotation applique une translation définie par
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
     * Ce constructeur fixe les coordonnées de transaltion en même temps qu'une réfe?ence
     * vers l'instance de noeud parent
     * @param _parent le noeud déclaré comme parent de l'instance courante (this)
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
     * implémentation de la méthode abstraite transfome() déclarée initialement dans la
     * classe abstraire Transformation. Elle consiste à appeler l'instruction OpenGL 
     * glRotatef avec une coordonnée représentant l'angle de rotation et trois coordonnées 
     * (float) représentant l'axe de rotation.
     */    
    public void transforme(){
        GL11.glRotatef(m_angle, m_axe.getX(), m_axe.getY(), m_axe.getZ());
    }
    
}
