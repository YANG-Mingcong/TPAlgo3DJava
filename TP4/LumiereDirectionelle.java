package TP3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Write a description of class LumierePonctuelle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LumiereDirectionelle extends Lumiere
{

    private float[] m_direction = {0.0f,0.0f,0.0f,0.0f};
    
    /**
     * Constructor for objects of class LumierePonctuelle
     */
    public LumiereDirectionelle(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _direction)
    {
        super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire);
        m_direction[0] = _direction.getX();
        m_direction[0] = _direction.getY();
        m_direction[0] = _direction.getZ();
        m_direction[0] = 1.0f;        
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
