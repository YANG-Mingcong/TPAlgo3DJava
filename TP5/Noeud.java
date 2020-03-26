package TP5;

import java.util.Vector;

/**
 * La classe noeud est une classe abstraite en amont de la 
 * hiérarchie de classes de notre graphe de scene. Sa fonction
 * principale est de porter la strucutre d'arbre du raphe de 
 * scène. Un noeud peut avoir plusieurs enfant et a un seul parent.
 * Seul le noeud d'origine de la Scène n'a pas de parent.
 *
 * @author Alexis Heloir
 * @version (2019/03/20
 */
public abstract class Noeud
{
    
    // Un noeud peut avoir plusieurs enfants
    protected Vector<Noeud> m_enfants;
    
    // Un noeud a un seul parent
    protected Noeud m_parent;

    /**
     * Le constructeur par défaut de la classe Noeud prend le noeud parent en paramètre
     */
    
    public Noeud(Noeud _parent){
        m_parent = _parent;
        _parent.ajouteEnfant(this); // le parent m'ajoute à sa liste d'enfants
    }

    /**
     * Ajoute le Noeud _enfant passé en paramètre à la liste d'enfant en attribut
     * @param _enfant le noeud enfant à rajouter à la liste d'enfants m_enfants
     */
    public void ajouteEnfant(Noeud _enfant){
        m_enfants.add(_enfant);
    }
    
    
    /**
     * Méthode abstraite en charge dÄappeler les instruction openGL
     * permettant d'afficher le noeud (même si on ne sait pas encore ce qu'il 
     * représente)
     */
    public abstract void affiche();
    
}
