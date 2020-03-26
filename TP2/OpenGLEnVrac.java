package TP2;


import java.awt.image.BufferedImage;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import org.lwjgl.util.glu.GLU;


import java.io.*;


/**
 * Cette classe rassemble la plupart des instructions de base
 * couramment utilisées avec la version 1.1 d'OpenGL. à savoir :
 * <ul>
 *   <li> la création de polygones,
 *   <li> l'application de texture,
 *   <li> les transformations affines dans le mode <b>MODELVIEW</b>,
 *   <li> la définition des paramètres de caméra dans le mode <b>PROJECTION</b>,
 *   <li> les transformations affines dans le <b>MODELVIEW</b>,
 *   <li> l'empilement et le dépilement de matrices.
 * </ul>
 */

public class OpenGLEnVrac {
    private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "OpenGL en vrac";
    private boolean f1 = false;
    private DisplayMode displayMode;

    private float xrot;            // X Rotation ( NEW )
    private float yrot;            // Y Rotation ( NEW )
    private float zrot;            // Z Rotation ( NEW )
    private int textureID;         // Storage For One Texture ( NEW )    

    private boolean light;         // Lighting ON/OFF    
    
    private float[] lightAmbient = {0.2f,0.2f,0.2f,0.2f};
    private float[] lightDiffuse = {0.5f,0.5f,0.5f,0.0f};
    private float[] lightSpecularComponent = {1.0f,1.0f,1.0f,0.0f};
    
    private float quadratic_attenuation = 0.0f;
    private float linear_attenuation = 0.0f;
    private float constant_attenuation = 1.0f;

    // le dernier composant de ce vecteur indique 
    // le type de lumière : si la valeur est 1.0f, la lumière
    // est ponctuelle, si sa valeur est 0.0f, la lumière est directionnelle 
    // et sa direction est donnée par les trois premières composantes du vecteur
    // une lumière directionelle n'est pas soumise à l'atténuation
    private float[] lightPosition = {0.0f,0.0f,-3.0f,1.0f}; 
    
    
    private float[] no_mat = {0.0f, 0.0f, 0.0f, 1.0f};
    private float[] mat_ambient = {0.3f, 0.3f, 0.3f, 1.0f};
    private float[] mat_diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
    private float[] mat_specular = {1.0f, 0.0f, 0.0f, 1.0f};
    private float no_shininess = 0.0f;
    private float low_shininess = 5.0f;
    private float high_shininess = 100.0f;
    private float[] mat_emission = {0.3f, 0.2f, 0.2f, 0.0f};    
 
    private float[] spotDirection = {1.5f,0.0f,-5.0f,1.0f};
    private float spotCutoff = 20.0f;
    private float spotExponent = 1.0f;
    

    private boolean filter = false;
    
    public static void main(String[] argv) {
        String OS = System.getProperty("os.name").toLowerCase();
        String path = "";
        try{
            if(OS.indexOf("win") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/windows").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            if(OS.indexOf("linux") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/linux").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");                
            }
            if(OS.indexOf("mac") >= 0){
                path = OpenGLEnVrac.class.getResource("../native/macosx").getPath();
                path = java.net.URLDecoder.decode(path, "UTF-8");
            }
            System.setProperty("org.lwjgl.librarypath", path);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // il est possible de passer l'argument "fullscreen" lors de l'invocation de la fonction 
        //<b>main()</b> pour que la fenètre soit affichée en mode plein écran
        boolean fullscreen = false;
        if(argv.length>0) {
            if(argv[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }
        
        OpenGLEnVrac monOpenGLEnVrac = new OpenGLEnVrac();
        monOpenGLEnVrac.start(fullscreen);
    }
    public void start(boolean fullscreen) {
        this.fullscreen = fullscreen;
        try {
            init();
            while (!done) {
                mainloop();
                render();
                Display.update();
            }
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void mainloop() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            done = true;
        }
        if(Display.isCloseRequested()) {                     // Exit if window is closed
            done = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !f1) {    // Is F1 Being Pressed?
            f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            f1 = false;
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F)) {          // Is F Being Pressed?
            filter = true;
        }        
        if(!Keyboard.isKeyDown(Keyboard.KEY_D)) {          // Is F Being Pressed?
            filter = false;
        }        

    }

    private void switchMode() {
        fullscreen = !fullscreen;
        try {
            Display.setFullscreen(fullscreen);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);          // Clear The Screen And The Depth Buffer

        GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix

        GL11.glTranslatef(0.0f, 0.0f, -8.0f); // Move Into The Screen 5 Units        
        
        GL11.glPushMatrix();

        GL11.glTranslatef(1.5f, 0.0f, 0.0f); // Move Into The Screen 5 Units        
        GL11.glRotatef(xrot, 1.0f, 0.0f, 0.0f); // Rotate On The X Axis
        GL11.glRotatef(yrot, 0.0f, 1.0f, 0.0f); // Rotate On The Y Axis
        GL11.glRotatef(zrot, 0.0f, 0.0f, 1.0f); // Rotate On The Z Axis        
        
        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); // Select Our Texture
      
        if (filter){
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_LINEAR); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR); // contre l'aliasage lointain
        }else{
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MAG_FILTER,GL11.GL_NEAREST); // contre l'aliasage proche
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_NEAREST); // contre l'aliasage lointain
        }
        
        
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

        xrot += 0.001f; // X Axis Rotation (delta d'animation)
        yrot += 0.02f; // Y Axis Rotation (delta d'animation)
        zrot += 0.0f; // Z Axis Rotation (delta d'animation)

        GL11.glPopMatrix();        
        
        // On applique une translation selon l'axe des X pour que le
        // second cube soit visible
        GL11.glTranslatef(-1.5f, 0.0f, 0.0f);
        
        // On désactive le mode d'éclairage pour afficher des lignes
        GL11.glDisable(GL11.GL_LIGHTING);
        // On affiche le second cube avec des lignes
        GL11.glBegin(GL11.GL_LINES);

        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad        
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad        
        
        // Back Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        // Top Face      
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        // Bottom Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Right Of The Texture and Quad
        // Right face      
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Texture and Quad
        // Left Face
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Texture and Quad
        GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Texture and Quad
        GL11.glEnd();
        
        // On résactive le mode d'éclairage pour afficher ke cube
        GL11.glEnable(GL11.GL_LIGHTING);        
        
        return true;
    }
    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }
    private void init() throws Exception {
        createWindow();
        TextureLoader myTextureLoader;
        BufferedImage image = TextureLoader.loadImage("/TP1/res/logo-uvhc.bmp");//The path is inside the jar file
        textureID = TextureLoader.loadTexture(image);        
        initGL();
    }

    private void initGL() {
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
          (float) displayMode.getWidth() / (float) displayMode.getHeight(),
          0.1f,
          100.0f);

        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
        
        GL11.glEnable(GL11.GL_LIGHTING);

        FloatBuffer buffAmbient = BufferUtils.createFloatBuffer(4).put(lightAmbient);
        buffAmbient.position(0);
                
        FloatBuffer buffDiffuse = BufferUtils.createFloatBuffer(4).put(lightDiffuse);
        buffDiffuse.position(0);

        FloatBuffer buffPosition = BufferUtils.createFloatBuffer(4).put(lightPosition);
        buffPosition.position(0);
        
        FloatBuffer buffSpecular = BufferUtils.createFloatBuffer(4).put(lightSpecularComponent);
        buffSpecular.position(0);
        
        FloatBuffer buffSpotDirection = BufferUtils.createFloatBuffer(4).put(spotDirection);
        buffSpotDirection.position(0);
        
                
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, buffAmbient);
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, buffDiffuse);
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, buffSpecular);
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, buffPosition);
        GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_CONSTANT_ATTENUATION, constant_attenuation);
        GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_LINEAR_ATTENUATION, linear_attenuation);
        GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_QUADRATIC_ATTENUATION, quadratic_attenuation);

        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPOT_DIRECTION, buffSpotDirection);
        GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_SPOT_CUTOFF, spotCutoff);
        GL11.glLightf(GL11.GL_LIGHT1, GL11.GL_SPOT_EXPONENT, spotExponent);
        
        GL11.glEnable(GL11.GL_LIGHT1);
        
        //GL11.glEnable(GL11.GL_LIGHT0);

        FloatBuffer buffNoMat = BufferUtils.createFloatBuffer(4).put(no_mat);
        buffNoMat.position(0);
        
        FloatBuffer buffMatAmbient = BufferUtils.createFloatBuffer(4).put(mat_ambient);
        buffMatAmbient.position(0);

        FloatBuffer buffMatSpecular = BufferUtils.createFloatBuffer(4).put(mat_specular);
        buffMatSpecular.position(0);
        
        
        FloatBuffer buffMatDiffuse = BufferUtils.createFloatBuffer(4).put(mat_diffuse);
        buffMatDiffuse.position(0);

        
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, buffMatAmbient);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, buffMatDiffuse);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, buffMatSpecular);
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, high_shininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_EMISSION, buffNoMat);        
        

        
        // Really Nice Perspective Calculations
        //GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }
    private static void cleanup() {
        Display.destroy();
    }    
}
