package TP4;

import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

/**
 * La classe spot représente une lumière ponctuelle qui 
 * diffuse un faisceau de lumière selon une direction 
 * et un angle de diffusion (GL_SPOT_CUTOFF). Un troisième
 * paramètre (GL_SPOT_EXPONENT) détermine l'atténuation de
 * l'intensité lumineuse lorsque la direction des rayons 
 * s'écarte de la direction principale (GL_SPOT_DIRECTION)
 *
 * @author Alexis Heloir
 * @version 2019/03/19
 */
public class LumiereSpot extends LumierePonctuelle
{

    // la direction principale de la lumière spot (tableau de réels, dmension 3)
    float[] m_spotDirection = {0.0f,0.0f,-1.0f};
    
    // l'angle de diffusion de la lumière spot 
    // (réel compris entre 0 (distribution uniforme) et 128 (focus maximal) )
    float m_spotCutoff = 0;
    
    // l'attenuation par rapport à la direction principale 
    // (réel entre 0 et 90 -> Cône de lumière ou 180 -> distribution uniforme)
    float m_spotExponent = 90;
    
    /**
     * Constructeur par défaut de la classe LumiereSpot, il ne prend pas d'argument.
     * Il place une lumière spot au centre du système de coordonnées local 
     * orientée selon les z nßegatifs (droit devant)
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
     * @param _vecteurAmbiant composante ambiante de la lumière
     * @param _vecteurDiffus composante diffuse de la lumière
     * @param _vecteurSpeculaire composante speculaire de la lumière
     * @param _position vecteur donnant la direction des rayons émis
     * @param _spotDirection vecteur donnant la direction du faisceau du spot
     * @param _spotCutoff réel donnant l'angle d'ouverture du spot
     * @param _spotExponent réel donnant l'atténuation des rayons s'écartant de la direction principale
     */
    public LumiereSpot(Vecteur3D _vecteurAmbiant, Vecteur3D _vecteurDiffus, 
                             Vecteur3D _vecteurSpeculaire, Vecteur3D _position, Vecteur3D _spotDirection, float _cutoff, float _spotExponent)
    {
       //On apelle le ]constructeur de la classe parente avec ses paramètres
       super(_vecteurAmbiant, _vecteurDiffus, _vecteurSpeculaire, _position);
       
       // Avec le dernier paramètre, on initialise l'attribut m_spotDirection (il s'agit d'un tableau de réels de 
       // dimension trois)
       
       m_spotDirection[0] = _spotDirection.getX();
       m_spotDirection[1] = _spotDirection.getY();
       m_spotDirection[2] = _spotDirection.getZ();

       m_spotCutoff = m_spotCutoff;
       m_spotExponent = m_spotExponent;
       
    }

    
    /**
     * On initialise les valeurs du vecteur de direction, de cutoff et d'exponent pour cette instance 
     * de classe de lumière ponctuelle après avoir appelé la méthode d'initialisation de la 
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
