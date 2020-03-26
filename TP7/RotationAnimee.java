package TP7;

import TP4.Vecteur3D;

import TP6.*;

/**
 * Une rotation dont l'angle de rotation est dynamique.
 * la valur de rotation d�clar�e dans la classe parente (m_angle)
 * est appliqu�e toutes les m_intervalleEnMS millisecondes.
 * Cette classe repose sur le singleton Horloge pour g�rer sa temporalit�
 *
 * @author Alexis Heloir
 * @version 2019/03/26
 */
public class RotationAnimee extends Rotation
{

    private int m_intervalleEnMS; // m_angle est appliqu� toutes les m_intervalleEnMS sillisecondes
    private Horloge m_horloge; // singleton utilis� pour r�cup�rer le temps pass� entre deux appels � transforme()
    private float m_angleInitial; // utilis� pour sauvegarder la valeur angulaire initiale � appliquer
    
    /**
     * Le constructeur appelle celui de la classe parente avec un param�tre en plus : l'intevalle en 
     * millisecondes lors duquel l'angle m_angleInitial sera appliqu�
     * @param _intervalleEmMS l'intervalle pendant lequel l'angle m_angleInitial sera appliqu�
     */
    public RotationAnimee(Noeud _parent, Vecteur3D _axe, float _angle, int _intervalleEnMS)
    {
        super(_parent, _axe, _angle);
        m_intervalleEnMS = _intervalleEnMS;
        m_horloge = Horloge.getInstance();
        m_angleInitial = m_angle;
    }

    /**
     * Cette classe met � jour l'angle m_angle � partir de sa valeur initiale et du temps 
     * �coul� depuis le dernier tick d'horloge. Elle appelle ensuite la transformation de 
     * la classe parente
     */
    public void transforme(){
        long tempsPasse = m_horloge.getTempsPasseDepuisDernierTick();
        float tempsRelatif = (float)(tempsPasse)/(float)(m_intervalleEnMS);
        m_angle += (m_angleInitial*tempsRelatif);
        m_angle = m_angle % 360.0f; // operateur % : reste de la division enti�re
        super.transforme();
    }
    
}
