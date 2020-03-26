package TP5;


/**
 * Write a description of class Monde here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Monde extends Noeud
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Monde
     */
    public Monde(Noeud _parent)
    {
        super(_parent);
    }
    
    public void affiche(){
        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }        
    }

}
