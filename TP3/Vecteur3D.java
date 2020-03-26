package TP3;

/**
 * Write a description of class Vecteur3D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Vecteur3D
{
    // instance variables - replace the example below with your own
    float m_x,m_y,m_z;

    /**
     * Constructor for objects of class Vecteur3D
     */
    public Vecteur3D()
    {
        m_x = 0.0f;
        m_y = 0.0f;
        m_z = 0.0f; 
    }

    /**
     * Constructor for objects of class Vecteur3D
     */
    public Vecteur3D(float _x, float _y, float _z)
    {
        m_x = _x;
        m_y = _y;
        m_z = _z; 
    }

    public float getX(){
        return m_x;
    }

    public float getY(){
        return m_y;
    }

    public float getZ(){
        return m_z;
    }

}