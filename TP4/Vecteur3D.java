package TP4;

/**
 * Vecteur de dimension 3
 *
 * @Alexis Heloir
 * @2019/03/19
 */
public class Vecteur3D
{
    // les trois dimensions du vecteur
    float m_x,m_y,m_z;

    /**
     * Constructor sans param�tre pour le vecteur
     */
    public Vecteur3D()
    {
        m_x = 0.0f;
        m_y = 0.0f;
        m_z = 0.0f; 
    }

    /**
     * Constructur qvec trois param�tres
     * @param _x la composante X
     * @param _y la composante Y
     * @param _z la composante Z
     */
    public Vecteur3D(float _x, float _y, float _z)
    {
        m_x = _x;
        m_y = _y;
        m_z = _z; 
    }

    /**
     * Accesseur pour la composante X
     * @return la composante X
     */
    public float getX(){
        return m_x;
    }

    /**
     * Accesseur pour la composante X
     * @return la composante X
     */
    public float getY(){
        return m_y;
    }

    /**
     * Accesseur pour la composante X
     * @return la composante X
     */
    public float getZ(){
        return m_z;
    }

}