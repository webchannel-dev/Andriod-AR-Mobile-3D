/*==============================================================================
#==============================================================================
#            Copyright (c) 2010-2011 Photint Incorporated.
#            All Rights Reserved.
#            Photint Confidential and Proprietary
#			 Devevloper : Kalaiy
#==============================================================================

            
@file 
    MultiTargetsRenderer.java

@brief
    Sample for MultiTargets

==============================================================================*/


package msc.ar.sound3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

import com.qualcomm.QCAR.QCAR;


/** The renderer class for the MultiTargets sample. */
public class MultiTargetsRenderer implements GLSurfaceView.Renderer
{
    public boolean mIsActive = false;
    
    /** Native function for initializing the renderer. */
    public native void initRendering();
    private SoundManager mSoundManager;
    public native void initNativeCallback();
    
    /** Native function to update the renderer. */
    public native void updateRendering(int width, int height);

    
    /** Called when the surface is created or recreated. */
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceCreated");
        initNativeCallback();
        // Call native function to initialize rendering:
        initRendering();
        
        // Call QCAR function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        QCAR.onSurfaceCreated();
     
    }
    
    
    /** Called when the surface changed size. */
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        DebugLog.LOGD("GLRenderer::onSurfaceChanged");
        
        // Call native function to update rendering when render surface parameters have changed:
        updateRendering(width, height);

        // Call QCAR function to handle render surface size changes:
        QCAR.onSurfaceChanged(width, height);
    }    
    
    
    /** The native render function. */    
    public native void renderFrame();
    
      /** Called to draw the current frame. */
    public void onDrawFrame(GL10 gl)
    {
        if (!mIsActive)
            return;

        // Call our native function to render content
        renderFrame();
    }
    public void playSound(int soundIndex, float volume)
    {
        mSoundManager.playSound(soundIndex, volume);
    }
    
    /** Setter for the sound manager. */
    public void setSoundManager(SoundManager soundManager)
    {
        mSoundManager = soundManager;
    }
}
