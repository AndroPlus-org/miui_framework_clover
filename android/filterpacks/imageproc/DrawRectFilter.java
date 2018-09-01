// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.format.ObjectFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.opengl.GLES20;

public class DrawRectFilter extends Filter
{

    public DrawRectFilter(String s)
    {
        super(s);
        mColorRed = 0.8F;
        mColorGreen = 0.8F;
        mColorBlue = 0.0F;
    }

    private void renderBox(Quad quad)
    {
        float f = mColorRed;
        float f1 = mColorGreen;
        float f2 = mColorBlue;
        float f3 = quad.p0.x;
        float f4 = quad.p0.y;
        float f5 = quad.p1.x;
        float f6 = quad.p1.y;
        float f7 = quad.p3.x;
        float f8 = quad.p3.y;
        float f9 = quad.p2.x;
        float f10 = quad.p2.y;
        mProgram.setHostValue("color", new float[] {
            f, f1, f2, 1.0F
        });
        mProgram.setAttributeValues("aPosition", new float[] {
            f3, f4, f5, f6, f7, f8, f9, f10
        }, 2);
        mProgram.setVertexCount(4);
        mProgram.beginDrawing();
        GLES20.glLineWidth(1.0F);
        GLES20.glDrawArrays(2, 0, 4);
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void prepare(FilterContext filtercontext)
    {
        mProgram = new ShaderProgram(filtercontext, "attribute vec4 aPosition;\nvoid main() {\n  gl_Position = aPosition;\n}\n", "precision mediump float;\nuniform vec4 color;\nvoid main() {\n  gl_FragColor = color;\n}\n");
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        Quad quad = ((Quad)pullInput("box").getObjectValue()).scaled(2.0F).translated(-1F, -1F);
        filtercontext = (GLFrame)filtercontext.getFrameManager().duplicateFrame(frame);
        filtercontext.focus();
        renderBox(quad);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addMaskedInputPort("box", ObjectFormat.fromClass(android/filterfw/geometry/Quad, 1));
        addOutputBasedOnInput("image", "image");
    }

    private float mColorBlue;
    private float mColorGreen;
    private float mColorRed;
    private final String mFixedColorFragmentShader = "precision mediump float;\nuniform vec4 color;\nvoid main() {\n  gl_FragColor = color;\n}\n";
    private ShaderProgram mProgram;
    private final String mVertexShader = "attribute vec4 aPosition;\nvoid main() {\n  gl_Position = aPosition;\n}\n";
}
