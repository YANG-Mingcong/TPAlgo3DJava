package TP7;


/**
 * Classe singleton qui repr�sente une Horlge dont l'instance unique 
 * garantie que la base de temps sera partag�e par toutes les instances
 * uitlisatrices du Singleton
 *
 * @author Alexis Heloir
 * @version 2019/03/26
 */
public class Horloge
{
    // instance variables - replace the example below with your own
    private static Horloge m_instanceUnique = null;
    
    private long m_startTime = 0;
    private long m_lastTick = 0;

    
    /**
     * Le constructeur du singleton est priv� : il n'est pas accessible depuis l'ext�rieur 
     * puisque l'instance unique du singleton est retourn�e par la m�thode getInstance()
     */
    private Horloge()
    {
        m_startTime = System.currentTimeMillis();
        m_lastTick = m_startTime;
    }
    
    /**
     * Cette m�thode retourne l'instance unique de l'horloge
     */
    public static Horloge getInstance()
    {
        if (m_instanceUnique == null) {
            m_instanceUnique = new Horloge();
        }
        return m_instanceUnique;
    }

    /**
     * Conbien de temps se sont �coul�s depuis le dernier tick()?
     * @return le temps �coul� depuis le dernier tick (em millisecondes)
     */
    public long getTempsPasseDepuisDernierTick(){
        long tempsPasse = System.currentTimeMillis()-m_lastTick;
        m_lastTick += tempsPasse;
        return tempsPasse;
    }

    
    
}
