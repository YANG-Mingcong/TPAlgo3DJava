package TP6;

import java.util.Vector;

/**
 * La classe noeud est une classe abstraite en amont de la 
 * hi�rarchie de classes de notre graphe de scene. Sa fonction
 * principale est de porter la strucutre d'arbre du raphe de 
 * sc�ne. Un noeud peut avoir plusieurs enfant et a un seul parent.
 * Seul le noeud d'origine de la Sc�ne n'a pas de parent.
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
     * Le constructeur par d�faut de la classe Noeud prend le noeud parent en param�tre
     */
    
    public Noeud(Noeud _parent){
        m_parent = _parent;
        m_enfants = new Vector<Noeud>();
        if (_parent != null)
        {
            _parent.ajouteEnfant(this);
        }
    }

    /**
     * Ajoute le Noeud _enfant pass� en param�tre � la liste d'enfant en attribut
     * @param _enfant le noeud enfant � rajouter � la liste d'enfants m_enfants
     */
    public void ajouteEnfant(Noeud _enfant){
        m_enfants.add(_enfant);
    }
    
    
    /**
     * M�thode abstraite en charge d�appeler les instruction openGL
     * permettant d'afficher le noeud (m�me si on ne sait pas encore ce qu'il 
     * repr�sente)
     */
    public abstract void affiche();
    
}
