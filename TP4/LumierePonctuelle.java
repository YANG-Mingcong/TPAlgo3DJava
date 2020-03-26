package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe LumierePonctuelle représente une source de lumière ponctuelle.
 * Elle est définie par un point dans l'espace à partir duquel vont irradier
 * les rayons émis par cette source lumineuse ponctuelle
 *
 * @author Alexis Heloir
 * @version 2019/03/19
 */
public class LumierePonctuelle extends Lumiere
{
    // tableau de floats de dimension 4 représentant l'origine de la source lumineus (appelé m_position)
    // la quatrième valeur de ce vecteur vaut 1 : cela signifie que ce tableau de réels sera interprété 
    // en tant que paramètre de lumière ponctuelle lors de la phase d'initialisation.
    private float[] m_position = {0.0f,0.0f,0.0f,1.0f};

    /**
     * Constructeur de la classe LumierePonctuelle, il prend quatre arguments
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire composante speculaire de la lumière
     * @param _position vecteur donnant la direction des rayons émis
     */
    public LumierePonctuelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _position)
    {
       //On apelle le ]constructeur de la classe parente avec ses paramètres
       super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
       
       // Avec le dernier paremètre, on initialise l'attribut m_position (il s'agit d'un tableau de réels de 
       // dimansion quatre)
       
       m_position[0] = _vecteurSpeculaire.getX();
       m_position[1] = _vecteurSpeculaire.getY();
       m_position[2] = _vecteurSpeculaire.getZ();
       m_position[3] = 1.0f; //la dernière valeur du tableau de réel est égale à 1 pour indiquer 
                             //qu'il s'agit d'une lumière ponctuelle
                             //(https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/)
       
    }

    /**
     * Constructeur par défaut de la classe LumierePonctuelle, il ne prend pas d'argument.
     * Il place une lumière ponctuelle à l'origine du système de coordonnées (0.0f,0.0f,0.0f)
     */
    public LumierePonctuelle()
    {
        super();
        m_position[0] = 0.0f;
        m_position[1] = 0.0f;
        m_position[2] = 0.0f;
        m_position[3] = 1.0f;
    }


    /**
     * On initialise la valeur du vecteur de position pour cette instance de classe 
     * de lumière ponctuelle après avoir appelé la méthode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {
        super.initialise();
        
        FloatBuffer buffPosition = BufferUtils.createFloatBuffer(4).put(m_position);
        buffPosition.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffPosition);
        
    }
}
