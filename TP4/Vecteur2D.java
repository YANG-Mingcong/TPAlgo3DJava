package TP3;



/**
 * Cette classe repr�sente un vecteur � deux dimentions.
 * Elle est surtout utilis�e pour d�finir les coordonn�es
 * (u,v) de texture
 * @author (Alexis Heloir)
 * @version (0.0.0,1 alpha)
 */
public class Vecteur2D
{
    // attributs de la classe
    private float m_u=0.0f,m_v=0.0f;

    /**
     * Le constructeur par d�faut initialise 
     * les deux composants du vecteur � z�ro
     */
    public Vecteur2D()
    {
        // initialisation des attributs � l'appel du constructeur
        m_u = 0.0f;
        m_v = 0.0f;
    }

    /**
     * Ce constructeur prend deux param�tre :
     * les valeurs qui seront assign�es aux composantes
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
     * Cette m�thode ajoute au vecteur courant le 
     * vecteur pass� en parm�tre. Les composantes du
     * vecteur pass� en param�tre sont ajout�es aux 
     * composantes du vecteur repr�sent� par l'instance 
     * courante
     * 
     * @param _vec vecteur � ajouter � l'instance courante 
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
     * Cette m�thode retourne la norme du vecteur, c'est � 
     * dire la recine carr�e de la somme de carr�s des deux 
     * composantes du vecteur
     * @return    la norme du vecteur
     */
    public float getMagnitude()
    {
        // la m�thode <b>sqrt</b> du package Math retourne un
        // double : on le transtype en float
        return (float)Math.sqrt(m_u*m_u + m_v*m_v);
    }
}
