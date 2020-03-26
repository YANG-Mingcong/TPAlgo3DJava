package TP6;


/**
 * Cette classe abstraite factorise le code commun à tous les
 * éléments "dessinables" du projet : il s'agit de l'ensemble 
 * des variations des cubes
 *
 * @author Alexis Heloir
 * @version 2019/21/03
 */
public abstract class Objet extends Noeud
{

    /**
     * Le constucteur de la classe Objet prend un noeud en paramètre.
     * se référer à la classe parente (Noeud) pour plus de détails.
     * @param _parent le noud parent au noeud courant pour la construction
     *                du graphe de scène.
     */
    public Objet(Noeud _parent)
    {
        super(_parent);
    }

    /**
     * La méthode dessine prend en charge les appels OpenGL participant à 
     * l'affichage de l'objet
     */
    public abstract void dessine();
    
    /**
     * La méthode affiche appelle successivement la méthode de représentation de
     * l'objet puis appelle de manière récursive la méthode affiche pour l'ensemble 
     * des noeuds enfants. Le graphe de scene est ainsi parcouru.
     */
    public void affiche()
    {
        // ici on dessine l'objet, même si on ne sait pas encore comment le dessiner
        dessine();
        
        // ici on fait quelque chose mais quoi???
        // On dessine le contenu de tous les elements de cette classe
        // plus le contenu des noeuds enfants
        // il s'agit de parcourir le contenu du Vecteur contenant les enfants 
        // et d'appeler la méthode afficher() pour chaque enfant référencé dans le
        // vecteur
        
        // TODO : factoriser ce code (le passer dans la classe parente)
        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }
        
    }
    
}
