package com.example.chongshao_mikasa.ar_mesh;

import android.opengl.Matrix;
import android.util.Log;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.Cube;

import java.util.Arrays;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by chongshao-mikasa on 11/2/16.
 */

public class SimpleRenderer extends ARRenderer{
    private int markerID = -1;

    private Cube cube = new Cube(40.0f, 0.0f, 0.0f, 20.0f);
    private float angle = 0.0f;
    private boolean spinning = false;
    private int drawCount = 0;
   // private float angle = 0.f;
    private float rangle = 0.0f;

    public void setRAngle(float angle) {
        Log.d("T", "in set R angle: " + String.valueOf(this.rangle));

        this.rangle = angle;
    }

    @Override
    public boolean configureARScene() {

        markerID = ARToolKit.getInstance().addMarker("single;Data/patt.hiro;80");
        if (markerID < 0) return false;

        return true;
    }

    public void click() {
        spinning = !spinning;
    }

    public void draw(GL10 gl) {
        Log.d("T", "drawCount: " + String.valueOf(this.drawCount));
        this.drawCount += 1;
        Log.d("T", "thisRangle: " + String.valueOf(this.rangle));

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        if (ARToolKit.getInstance().queryMarkerVisible(markerID)) {
            float[] m = ARToolKit.getInstance().queryMarkerTransformation(markerID);
   //         Log.d("T", "in rotate angle: " + String.valueOf(this.rangle));

            Matrix.rotateM(m, 0, m, 0, this.rangle, 0, 0, 1);
            gl.glLoadMatrixf(m, 0);
      //      Log.d("T", Arrays.toString(m));
            float m20 = m[2];
            float m00 = m[0];
            double heading = Math.atan2(-m20, m00);
   //         Log.d("T", String.valueOf(heading));
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();

            if (spinning) angle += 5.0f;
        }
    }
}
