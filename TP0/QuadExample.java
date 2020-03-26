package TP0;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import java.io.File;

import java.io.File;
import java.io.*;


/**
 * Cette classe constitue une entr�e en mati�re pour les modules 
 * ALGO 3D et Programmation JAVA, elle va vous permettre de tester
 * votre environnment de d�veloppement bas� sur BlueJ et lwjgl et poser
 * les premi�res briques logiciels permettant la mise en place d'une 
 * fen�tre d'affichage, d'un contexte OpenGL. Elle expose enfin, de 
 * mani�re succinte comment tracer et remplir un polygone (ici un carr�)
 * en OpenGL.
 */  
public class QuadExample {

    /**
     * Cette m�thode est appel�e par la m�thode main (d�clar�e plus bas).
     * La m�thode start initialise une fen�tre d'affichage puis un contexte OpenGL
     * enfin, elle invoque une boucle infinie en charge de d�ssiner le polygone �
     * afficher.
     * <p>
     * Pour ce faire, la classe <b>Display</b> est utilis�e.
     * La documentation de la classe Display est disponible � cette adresse :
     * <a href="http://legacy.lwjgl.org/javadoc/org/lwjgl/opengl/Display.html">opengl/Display.html</a>
     * 
     */
    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
  
        // init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
  
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
         
            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0.5f,0.5f,1.0f);
             
            // draw quad
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2i(100,100);
            GL11.glVertex2i(100+200,100);
            GL11.glVertex2i(100+200,100+200);
            GL11.glVertex2i(100,100+200);
            GL11.glEnd();
  
            Display.update();
        }
  
        Display.destroy();
    }

    /**
     * Cette m�thode est appel�e au lancement de votre programme, elle a pour 
     * fonction principale d'instancier la classe <b>QuadExample</b> et de la 
     * "d�marrer" en invoquant la m�thode <b>start()</b> sur l'instance de <b>QuadExample</b>
     * nouvellement cr��e. Les lignes pr�c�dant l'instanciation de la classe <b>QuadExample</b>
     * sont li�es � des contraintes de portabiblit� entre les diff�rentes architectures 
     * mat�rielles et logicielles (mac, pc, linux) utilis�es et d�passent le cadre de ce module
     *
     *@param argv un tableau de chaines de caract�res contenant les arguments du programmes � appeler.
     *            pour l'instant, seul l'argument "fullscreen" est disponible. Plus de d�tails � 
     *            propos de cet argument sont disponibles en commentaires dans le corps de la fonction
     */    
    public static void main(String[] argv) {
        String OS = System.getProperty("os.name").toLowerCase();
        String path = "";
        try{
            if(OS.indexOf("win") >= 0){
                path = QuadExample.class.getResource("../native/windows").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            if(OS.indexOf("linux") >= 0){
                path = QuadExample.class.getResource("../native/linux").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");                
            }
            if(OS.indexOf("mac") >= 0){
                path = QuadExample.class.getResource("../native/macosx").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            System.setProperty("org.lwjgl.librarypath", path);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // il est possible de passer l'argument "fullscreen" lors de l'invocation de la fonction 
        //<b>main()</b> pour que la fen�tre soit affich�e en mode plein �cran
        boolean fullscreen = false;
        if(argv.length>0) {
            if(argv[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }        
        
        QuadExample quadExample = new QuadExample();
        quadExample.start();
    }
}