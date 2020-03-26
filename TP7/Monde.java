package TP7;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;

import org.lwjgl.input.Keyboard;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import TP4.*;
import TP6.*;

import java.io.*;


/**
 * La classe Monde est le noeud primordial � l'origine du graphe
 * de sc�ne. Elle prend en charge les interactions ainsi que l'initialisation
 * du contexte OpenGL
 *
 * @author Alexis Heloir
 * @version 2019/03/21
 */
public class Monde extends Noeud
{
    static final int MS_ENTRE_DEUX_AFFICHAGES = 40; // 25 affichages par secondes

    private boolean m_done = false; // Est ce que l'application doit se terminer?
    private boolean m_fullscreen = false; // Est-ce que l'application doit �tre plein �cran?
    private final String m_windowTitle = "OpenGL et Graphe de sc�ne"; // Titre de l'application
    private boolean m_f1 = false; // A t-on appuy� sur la touche F1?
    private DisplayMode m_displayMode; // propri�t�s de la fen�tre d'affichage

    private boolean m_filter = false; // Est-ce  que l'on applique le mipmapping de texture 

    
    /**
     * Le constructuer de la classe Monde ne prend pas de parm�tre : la classe Monde 
     * n'a pas de parent car son instance (souvent unique) est � l'origine du graphe de sc�ne.
     */
    public Monde()
    {
        super(null);
    }
    
    /**
     * La m�thode affiche vides les buffers du contexte OpenGL puis appelle de mani�re 
     * r�cursive la m�thode affiche pour l'ensemble des noeuds enfants. Le graphe de scene est ainsi parcouru.
     */
    public void affiche(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

        for (int i=0; i<m_enfants.size(); i++ )
        {
            m_enfants.get(i).affiche();
        }        
    }

    /**
     * M�thode responsable de la gestion des interactions avec l'utilisateur --
     * elle g�re principalement les interruptions clavier
     */
    private void interactionManagement() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            m_done = true;
        }
        if(Display.isCloseRequested()) {                     // Exit if window is closed
            m_done = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !m_f1) {    // Is F1 Being Pressed?
            m_f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            m_f1 = false;
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F)) {          // Is F Being Pressed?
            m_filter = true;
        }        
        if(!Keyboard.isKeyDown(Keyboard.KEY_D)) {          // Is F Being Pressed?
            m_filter = false;
        }        

    }

    /**
     *  Appel� par la m�thode interactionManagement() en charge de capturer les
     *  �v�nments clavier. Cette m�thode g�re l'attribut sanctionnant le mode plein
     *  �cran
     */
    private void switchMode() {
        m_fullscreen = !m_fullscreen;
        try {
            Display.setFullscreen(m_fullscreen);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void start(boolean fullscreen) {
        this.m_fullscreen = fullscreen;
        try {
            initGL();
            prepareScene();
            while (!m_done) {
                Thread.sleep(MS_ENTRE_DEUX_AFFICHAGES);
                interactionManagement();
                affiche();
                Display.update();
            }
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
        
    private void initGL() throws Exception {
        
        createWindow();
      
        if (m_filter){
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_LINEAR); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR); // contre l'aliasage lointain
        }else{
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST); // contre l'aliasage lointain
        }
        
        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        GL11.glClearDepth(1.0); // Depth Buffer Setup
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

        GL11.glEnable(GL11.GL_CULL_FACE); // Back face culling 
        
        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
        GL11.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(
          45.0f,
          (float) m_displayMode.getWidth() / (float) m_displayMode.getHeight(),
          0.1f,
          100.0f);

        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
        
        GL11.glEnable(GL11.GL_LIGHTING);
        
        LumiereDirectionelle maLumiere = new LumiereDirectionelle();
        maLumiere.allumer();
        
    }

    private void prepareScene(){
        Vecteur3D vecteur0 = new Vecteur3D(0.0f,-5.0f,-16.0f);
        Transformation translation0 = new Translation(this, vecteur0);
        CubeTexture cube0 = new CubeTexture(translation0);
        
        Vecteur3D vecteur1 = new Vecteur3D(-4.0f,0.0f,0.0f);
        Transformation translation1 = new Translation(translation0, vecteur1);        
        CubeTexture cube1 = new CubeTexture(translation1);
        
        Vecteur3D vecteur2 = new Vecteur3D(-2.0f,0.0f,0.0f);
        Transformation translation2 = new Translation(translation0, vecteur2);        
        CubeTexture cube2 = new CubeTexture(translation2);

        Vecteur3D vecteur3 = new Vecteur3D(2.0f,0.0f,0.0f);
        Transformation translation3 = new Translation(translation0, vecteur3);        
        CubeTexture cube3 = new CubeTexture(translation3);        

        Vecteur3D vecteur4 = new Vecteur3D(4.0f,0.0f,0.0f);
        Transformation translation4 = new Translation(translation0, vecteur4);        
        CubeTexture cube4 = new CubeTexture(translation4);
        
        Vecteur3D vecteur5 = new Vecteur3D(-2.0f,2.0f,0.0f);
        Transformation translation5 = new Translation(translation0, vecteur5);        
        CubeTexture cube5 = new CubeTexture(translation5);
        
        Vecteur3D vecteur6 = new Vecteur3D(0.0f,2.0f,0.0f);
        Transformation translation6 = new Translation(translation0, vecteur6);        
        CubeTexture cube6 = new CubeTexture(translation6); 

        Vecteur3D vecteur7 = new Vecteur3D(2.0f,2.0f,0.0f);
        Transformation translation7 = new Translation(translation0, vecteur7);        
        CubeTexture cube7 = new CubeTexture(translation7); 

        Vecteur3D vecteur8 = new Vecteur3D(0.0f,4.0f,0.0f);
        Transformation translation8 = new Translation(translation0, vecteur8);        
        CubeTexture cube8 = new CubeTexture(translation8);
        
        Vecteur3D vecteur9 = new Vecteur3D(0.0f,5.5f,0.0f);
        Transformation translation9 = new Translation(translation0, vecteur9);
        Vecteur3D vecteurEchelle9 = new Vecteur3D(0.5f,0.5f,0.5f);        
        Echelle echelle9 = new Echelle(translation9,vecteurEchelle9);
        CubeTexture cube9 = new CubeTexture(echelle9);
        
        Vecteur3D vecteur10 = new Vecteur3D(0.0f,6.25f,0.0f);
        Transformation translation10 = new Translation(translation0, vecteur10);
        Vecteur3D vecteurEchelle10 = new Vecteur3D(0.25f,0.25f,0.25f);        
        Echelle echelle10 = new Echelle(translation10,vecteurEchelle10);
        CubeTexture cube10 = new CubeTexture(echelle10);
        
        
        Vecteur3D vecteur11 = new Vecteur3D(0.0f,8.0f,0.0f);

        Transformation translation11 = new Translation(translation0, vecteur11);        
        RotationAnimee rotation11 = new RotationAnimee(translation11, new Vecteur3D(0.0f,1.0f,0.0f), 90.0f, 5000);
        CubeTextureParFace cube11 = new CubeTextureParFace(rotation11);        
    }
    
    private void createWindow() throws Exception {
        Display.setFullscreen(m_fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                m_displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(m_displayMode);
        Display.setTitle(m_windowTitle);
        Display.create();
    }

    
    private static void cleanup() {
        Display.destroy();
    }    

    /**
     * M�thode statique permettant de lancer l'application depuis la ligne de commande
     */
    public static void main(String[] argv) {
        String OS = System.getProperty("os.name").toLowerCase();
        String path = "";
        try{
            if(OS.indexOf("win") >= 0){
                path = Monde.class.getResource("../native/windows").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            if(OS.indexOf("linux") >= 0){
                path = Monde.class.getResource("../native/linux").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");                
            }
            if(OS.indexOf("mac") >= 0){
                path = Monde.class.getResource("../native/macosx").getPath();
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
        
        Monde monMonde = new Monde();
        monMonde.start(fullscreen);
    }
    
}


