package TP3;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * Classe lumière. Cette classe est abstraite, elle sert à 
 * factoriser les attibuts communs à toutes les lumières :
 * la composante ambiante, la composante spéculaire et la 
 * composante diffuse
 *
 * @author (Alexis Heloir)
 * @version (06/02/2018)
 */
public abstract class Lumiere
{
    private float[] m_ambientComponent = {0.0f,0.0f,0.0f,0.0f};
    private float[] m_diffuseComponent = {0.0f,0.0f,0.0f,0.0f};
    private float[] m_specularComponent = {0.0f,0.0f,0.0f,0.0f};

    protected int m_currentLight = -1;
    private static int m_nextLight = GL11.GL_LIGHT0;

    /**
     * Ce constructeur assigne une parmi les 8 lumières 
     * disponibles en OpenGL1.1. Le compteur m_currentLight 
     * est statique, c'est à dire que toutes les instances de 
     * la classe lumière partagent la valeur de m_currentLight.
     * Chaque instance connait donc le nombre de lumières déjà 
     * instanciées.
     */ 
    public Lumiere(){
        m_currentLight = m_nextLight;
        m_nextLight ++;
    }

    /**
     * Constructeur de la classe lumière prenant en paramètres trois 
     * instances de Vecteur3D pour les composantes ambiantes, diffuses 
     * et spéculaire de la lumière
     * 
     * @param _vecteurAmbiant la composante ambiante de la lumière (Vecteur3D)
     * @param _vecteurDiffus la composante diffuse de la lumière (Vecteur3D)
     * @param _vecteurSpeculaire la composante speculaire de la lumière (Vecteur3D)
     */
    public Lumiere(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, Vecteur3D _vecteurSpeculaire)
    {
        this();
        
        m_ambientComponent[0] = _vecteurAmbiant.getX(); 
        m_ambientComponent[1] = _vecteurAmbiant.getY(); 
        m_ambientComponent[2] = _vecteurAmbiant.getZ(); 
        m_ambientComponent[3] = 1.0f;

        m_diffuseComponent[0] = _vecteurDiffus.getX(); 
        m_diffuseComponent[1] = _vecteurDiffus.getY(); 
        m_diffuseComponent[2] = _vecteurDiffus.getZ(); 
        m_diffuseComponent[3] = 1.0f;

        m_specularComponent[0] = _vecteurSpeculaire.getX(); 
        m_specularComponent[1] = _vecteurSpeculaire.getY(); 
        m_specularComponent[2] = _vecteurSpeculaire.getZ(); 
        m_specularComponent[3] = 1.0f;
   
    }
    
    
    /**
     * initialisation de la lumière courante.
     */
    public void initialise()
    {
        FloatBuffer buffAmbient = BufferUtils.createFloatBuffer(4).put(m_ambientComponent);
        buffAmbient.position(0);

        FloatBuffer buffDiffuse = BufferUtils.createFloatBuffer(4).put(m_diffuseComponent);
        buffDiffuse.position(0);

        FloatBuffer buffSpecular = BufferUtils.createFloatBuffer(4).put(m_specularComponent);
        buffSpecular.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffAmbient);
        GL11.glLight(m_currentLight, GL11.GL_DIFFUSE, buffDiffuse);
        GL11.glLight(m_currentLight, GL11.GL_SPECULAR, buffSpecular);        
    }
    
    /**
     * active la lumière courante (on suppose que GL_LIGHTING)
     * est également activé
     */    
    public void allumer()
    {
        GL11.glEnable(m_currentLight);
    }

    /**
     * desactive la lumière courante (on suppose que GL_LIGHTING)
     * est également activé
     */    
    public void eteindre()
    {
        GL11.glDisable(m_currentLight);
    }

    
}
