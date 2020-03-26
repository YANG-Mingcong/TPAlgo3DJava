package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Une lumière directionelle représente une source de lumière 
 * située à l'infini : tous les rayons émis par cette lumière sont parallèles
 * et leur direction est donnée par un vecteur de dimension trois.
 *
 * @author Alexis Heloir
 * @version 19/03/2019
 */
public class LumiereDirectionelle extends Lumiere
{

    private float[] m_direction = {0.0f,0.0f,0.0f,0.0f};
    
    /**
     * Constructeur de la classe LumiereDirectionelle, il prend quatre arguments
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire composante speculaire de la lumière
     * @param _direction vecteur donnant la direction des rayons émis
     */
    public LumiereDirectionelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction)
    {
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        m_direction[0] = _direction.getX();
        m_direction[1] = _direction.getY();
        m_direction[2] = _direction.getZ();
        m_direction[3] = 0.0f; // la dernière valeur du tableau de réel représentant la position est
                               // égale à 0 pounr indiquer que la lumière est directionelle 
                               // (https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/)
    }


    /**
     * Constructeur par défaut de la classe LumiereDirectionelle, il ne prend pas d'argument.
     * Il place une lumière directionelle qui simule la lumière d'un soleil au zenith
     */
    public LumiereDirectionelle()
    {
        super();
        m_direction[0] = 0.0f;
        m_direction[1] = -1.0f; // les rayons "tombent" verticalement
        m_direction[2] = 0.0f;
        m_direction[3] = -1.0f;
    }    
    
    /**
     * On initialise la valeur du vecteur de direction pour cette instance de classe 
     * de lumière directionelle après avoir appelé la méthode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {
        super.initialise();
        
        FloatBuffer buffDirection = BufferUtils.createFloatBuffer(4).put(m_direction);
        buffDirection.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffDirection);
        
    }

}
