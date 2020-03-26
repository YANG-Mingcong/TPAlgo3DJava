package TP6;


/**
 * Cette classe abstraite factorise le code commun � tous les
 * �l�ments "dessinables" du projet : il s'agit de l'ensemble 
 * des variations des cubes
 *
 * @author Alexis Heloir
 * @version 2019/21/03
 */
public abstract class Objet extends Noeud
{

    /**
     * Le constucteur de la classe Objet prend un noeud en param�tre.
     * se r�f�rer � la classe parente (Noeud) pour plus de d�tails.
     * @param _parent le noud parent au noeud courant pour la construction
     *                du graphe de sc�ne.
     */
    public Objet(Noeud _parent)
    {
        super(_parent);
    }

    /**
     * La m�thode dessine prend en charge les appels OpenGL participant � 
     * l'affichage de l'objet
     */
    public abstract void dessine();
    
    /**
     * La m�thode affiche appelle successivement la m�thode de repr�sentation de
     * l'objet puis appelle de mani�re r�cursive la m�thode affiche pour l'ensemble 
     * des noeuds enfants. Le graphe de scene est ainsi parcouru.
     */
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
        
        // TODO : factoriser ce code (le passer dans la classe parente)
        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }
        
    }
    
}
