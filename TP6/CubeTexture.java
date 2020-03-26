package TP6;

import org.lwjgl.opengl.GL11;

import java.awt.image.BufferedImage;


/**
 * Write a description of class CubeTexture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CubeTexture extends Objet
{

    private int m_textureID;         // Storage For One Texture ( NEW )            

    /**
     * Constructor for objects of class CubeTexture
     */
    public CubeTexture(Noeud _parent)
    {
        super(_parent);
        
        TextureLoader myTextureLoader;
        BufferedImage image = TextureLoader.loadImage("/TP1/res/logo-uvhc.bmp");//The path is inside the jar file
        m_textureID = TextureLoader.loadTexture(image);

        
    }

    /**
     * Cette m�thode utilise une suite d'instructions OpenGL perettant d'afficher un cube.
     * Vous trouverez ces instructions dans la clase OpenGL en vrac du package TP2.
     */
    public void dessine(){
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, m_textureID); // Select Our Texture        
        
        GL11.glBegin(GL11.GL_QUADS);
        // Front Face
        //GL11.glColor3f(0.5f,0.5f,0.5f);
        GL11.glNormal3f( 0.0f, 0.0f, 1.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        // Back Face
        GL11.glNormal3f( 0.0f, 0.0f, -1.0f);        
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        // Top Face
        GL11.glNormal3f( 0.0f, 1.0f, 0.0f);        
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        // Bottom Face
        GL11.glNormal3f( 0.0f, -1.0f, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        // Right face
        GL11.glNormal3f( 1.0f, 0.0f, 0.0f);        
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        // Left Face
        GL11.glNormal3f( -1.0f, 0.0f, 0.0f);        
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glEnd();        
    }
    
}
