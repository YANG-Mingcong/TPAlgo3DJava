package TP6;

import org.lwjgl.opengl.GL11;

/**
 * La classe transformation permet de factoriser le code commun aux classes
 * filles Translation, Rotation et Echelle.
 * Les fonctions cummunes à ces transformations sont principalement l'empilement et 
 * le dépilement de matrices
 *
 * @author Alexis Heloir
 * @version 2019/20/03
 */
public abstract class Transformation extends Noeud
{

    public Transformation(Noeud _parent){
        super(_parent);
    }

    public abstract void transforme();
    
    public void affiche(){
        //On empile la matrice de transformation sur la matrice courante
        GL11.glPushMatrix();

        // ici on exécute la transformation de la classe. Même si on ne sait 
        // pas encore de quelle transformation il s'agit
        transforme();
        
        // ici on fait quelque chose mais quoi???
        // On dessine le contenu de tous les elements de cette classe
        // plus le contenu des noeuds enfants
        // il s'agit de parcourir le contenu du Vecteur contenant les enfants 
        // et d'appeler la méthode afficher() pour chaque enfant référencé dans le
        // vecteur
        
        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }
        
        //On dépile la matrice de transformation pour revenir à la matrice courante
        GL11.glPopMatrix();
    }
    
}
