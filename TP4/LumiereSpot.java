package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe spot repr�sente une lumi�re ponctuelle qui 
 * diffuse un faisceau de lumi�re selon une direction 
 * et un angle de diffusion (GL_SPOT_CUTOFF). Un troisi�me
 * param�tre (GL_SPOT_EXPONENT) d�termine l'att�nuation de
 * l'intensit� lumineuse lorsque la direction des rayons 
 * s'�carte de la direction principale (GL_SPOT_DIRECTION)
 *
 * @author Alexis Heloir
 * @version 2019/03/19
 */
public class LumiereSpot extends LumierePonctuelle
{

    // la direction principale de la lumi�re spot (tableau de r�els, dmension 3)
    float[] m_spotDirection = {0.0f,0.0f,-1.0f};
    
    // l'angle de diffusion de la lumi�re spot 
    // (r�el compris entre 0 (distribution uniforme) et 128 (focus maximal) )
    float m_spotCutoff = 0;
    
    // l'attenuation par rapport � la direction principale 
    // (r�el entre 0 et 90 -> C�ne de lumi�re ou 180 -> distribution uniforme)
    float m_spotExponent = 90;
    
    /**
     * Constructeur par d�faut de la classe LumiereSpot, il ne prend pas d'argument.
     * Il place une lumi�re spot au centre du syst�me de coordonn�es local 
     * orient�e selon les z n�egatifs (droit devant)
     */
    public LumiereSpot()
    {
        super();
        m_spotDirection[0] = 0.0f;
        m_spotDirection[1] = 0.0f; // les rayons "tombent" verticalement
        m_spotDirection[2] = -1.0f;
        
        m_spotCutoff = 0.0f;
        m_spotExponent = 90.0f;
    } 

    /**
     * Constructeur de la classe LumierePonctuelle, il prend sept arguments
     * @param _vecteurAmbiant composante ambiante de la lumi�re
     * @param _vecteurDiffus composante diffuse de la lumi�re
     * @param _vecteurSpeculaire composante speculaire de la lumi�re
     * @param _position vecteur donnant la direction des rayons �mis
     * @param _spotDirection vecteur donnant la direction du faisceau du spot
     * @param _spotCutoff r�el donnant l'angle d'ouverture du spot
     * @param _spotExponent r�el donnant l'att�nuation des rayons s'�cartant de la direction principale
     */
    public LumiereSpot(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _position, Vecteur3D _spotDirection, float _cutoff, float _spotExponent)
    {
       //On apelle le ]constructeur de la classe parente avec ses param�tres
       super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire, _position);
       
       // Avec le dernier param�tre, on initialise l'attribut m_spotDirection (il s'agit d'un tableau de r�els de 
       // dimension trois)
       
       m_spotDirection[0] = _spotDirection.getX();
       m_spotDirection[1] = _spotDirection.getY();
       m_spotDirection[2] = _spotDirection.getZ();

       m_spotCutoff = m_spotCutoff;
       m_spotExponent = m_spotExponent;
       
    }

    
    /**
     * On initialise les valeurs du vecteur de direction, de cutoff et d'exponent pour cette instance 
     * de classe de lumi�re ponctuelle apr�s avoir appel� la m�thode d'initialisation de la 
     * classe parente
     */
    public void initialise()
    {
        super.initialise();
 
        FloatBuffer buffDirection = BufferUtils.createFloatBuffer(4).put(m_spotDirection);
        buffDirection.position(0);

        GL11.glLight(m_currentLight, GL11.GL_AMBIENT, buffDirection);
        GL11.glLightf(m_currentLight, GL11.GL_SPOT_CUTOFF, m_spotCutoff);
        GL11.glLightf(m_currentLight, GL11.GL_SPOT_EXPONENT, m_spotExponent);        
    }
    
}
