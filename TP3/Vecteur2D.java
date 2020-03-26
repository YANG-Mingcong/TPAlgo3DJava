package TP3;



/**
 * Cette classe représente un vecteur à deux dimentions.
 * Elle est surtout utilisée pour définir les coordonnées
 * (u,v) de texture
 * @author (Alexis Heloir)
 * @version (0.0.0,1 alpha)
 */
public class Vecteur2D
{
    // attributs de la classe
    private float m_u=0.0f,m_v=0.0f;

    /**
     * Le constructeur par défaut initialise 
     * les deux composants du vecteur à zéro
     */
    public Vecteur2D()
    {
        // initialisation des attributs à l'appel du constructeur
        m_u = 0.0f;
        m_v = 0.0f;
    }

    /**
     * Ce constructeur prend deux paramètre :
     * les valeurs qui seront assignées aux composantes
     * m_u et m_v de l'instance de Vecteur2D
     * 
     * @param _u la valeur de la composante u du vecteur 
     * @param _v la valeur de la composante v du vecteur
     */
    public Vecteur2D(float _u, float _v)
    {
        m_u = _u;
        m_v =_v;
    }
    
    /**
     * Cette méthode ajoute au vecteur courant le 
     * vecteur passé en parmètre. Les composantes du
     * vecteur passé en paramètre sont ajoutées aux 
     * composantes du vecteur représenté par l'instance 
     * courante
     * 
     * @param _vec vecteur à ajouter à l'instance courante 
     * 
     */
    
     public void add(Vecteur2D _vec)
     {
         m_u += _vec.getU();
         m_v += _vec.getV();
         
     }
     
     /**
      * accesseur pour la composante u
      * @return la valeur de la composante u
      */
      public float getU()
      {
          return m_u;
      }

     /**
      * accesseur pour la composante v
      * @return la valeur de la composante v
      */
      public float getV()
      {
          return m_v;
      }

      /**
       * mutateur pour la composante u
       * @param _u la valeur de la composante u
       */
      public void setU( float _u)
      {
          this.m_u = _u;
      }

      /**
       * mutateur pour la composante v
       * @param _v la valeur de la composante u
       */
      public void setV( float _v)
      {
          this.m_v = _v;
      }

      
    /**
     * Cette méthode retourne la norme du vecteur, c'est à 
     * dire la recine carrée de la somme de carrés des deux 
     * composantes du vecteur
     * @return    la norme du vecteur
     */
    public float getMagnitude()
    {
        // la méthode <b>sqrt</b> du package Math retourne un
        // double : on le transtype en float
        return (float)Math.sqrt(m_u*m_u + m_v*m_v);
    }
}
