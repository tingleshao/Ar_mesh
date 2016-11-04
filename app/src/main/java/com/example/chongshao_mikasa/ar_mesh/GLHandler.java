package com.example.chongshao_mikasa.ar_mesh;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chongshao-mikasa on 11/2/16.
 */


public class GLHandler extends GLSurfaceView
{
    GLSurfaceView mainsurface;
    MyGLRenderer scene_renderer;

    public GLHandler(Context ctx, AttributeSet attr){
        super(ctx,attr);
      //  mainsurface = new MySurfaceView(ctx);

        Log.d("T", "GL Handler Init!!!!");
     //   scene_renderer = new MyGLRenderer();

     //   mainsurface.setRenderer(scene_renderer);
    }
    public void onResume(){
        mainsurface.onResume();
    }
    public void onPause(){
        mainsurface.onPause();
    }
}