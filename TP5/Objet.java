package TP5;


/**
 * Abstract class Objet - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Objet extends Noeud
{

    public Objet(Noeud _parent)
    {
        super(_parent);
    }

    public abstract void dessine();
    
    public void affiche()
    {
        // ici on dessine l'objet, m�me si on ne sait pas encore comment le dessiner
        dessine();
        
        // ici on fait quelque chose mais quoi???
        // On dessine le contenu de tous les elements de cette classe
        // plus le contenu des noeuds enfants
        // il s'agit de parcourir le contenu du Vecteur contenant les enfants 
        // et d'appeler la m�thode afficher() pour chaque enfant r�f�renc� dans le
        // vecteur
        
        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }
        
    }
    
}
