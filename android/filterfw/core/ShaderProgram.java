// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;
import android.opengl.GLES20;

// Referenced classes of package android.filterfw.core:
//            Program, FilterContext, StopWatchMap, GLFrame, 
//            Frame, FrameFormat, GLEnvironment, NativeAllocatorTag, 
//            VertexFrame

public class ShaderProgram extends Program
{

    private ShaderProgram()
    {
        mMaxTileSize = 0;
        mTimer = null;
    }

    public ShaderProgram(FilterContext filtercontext, String s)
    {
        mMaxTileSize = 0;
        mTimer = null;
        mGLEnvironment = getGLEnvironment(filtercontext);
        allocate(mGLEnvironment, null, s);
        if(!compileAndLink())
        {
            throw new RuntimeException("Could not compile and link shader!");
        } else
        {
            setTimer();
            return;
        }
    }

    public ShaderProgram(FilterContext filtercontext, String s, String s1)
    {
        mMaxTileSize = 0;
        mTimer = null;
        mGLEnvironment = getGLEnvironment(filtercontext);
        allocate(mGLEnvironment, s, s1);
        if(!compileAndLink())
        {
            throw new RuntimeException("Could not compile and link shader!");
        } else
        {
            setTimer();
            return;
        }
    }

    private ShaderProgram(NativeAllocatorTag nativeallocatortag)
    {
        mMaxTileSize = 0;
        mTimer = null;
    }

    private native boolean allocate(GLEnvironment glenvironment, String s, String s1);

    private native boolean beginShaderDrawing();

    private native boolean compileAndLink();

    public static ShaderProgram createIdentity(FilterContext filtercontext)
    {
        filtercontext = nativeCreateIdentity(getGLEnvironment(filtercontext));
        filtercontext.setTimer();
        return filtercontext;
    }

    private native boolean deallocate();

    private static GLEnvironment getGLEnvironment(FilterContext filtercontext)
    {
        if(filtercontext != null)
            filtercontext = filtercontext.getGLEnvironment();
        else
            filtercontext = null;
        if(filtercontext == null)
            throw new NullPointerException("Attempting to create ShaderProgram with no GL environment in place!");
        else
            return filtercontext;
    }

    private native Object getUniformValue(String s);

    private static native ShaderProgram nativeCreateIdentity(GLEnvironment glenvironment);

    private native boolean setShaderAttributeValues(String s, float af[], int i);

    private native boolean setShaderAttributeVertexFrame(String s, VertexFrame vertexframe, int i, int j, int k, int l, boolean flag);

    private native boolean setShaderBlendEnabled(boolean flag);

    private native boolean setShaderBlendFunc(int i, int j);

    private native boolean setShaderClearColor(float f, float f1, float f2);

    private native boolean setShaderClearsOutput(boolean flag);

    private native boolean setShaderDrawMode(int i);

    private native boolean setShaderTileCounts(int i, int j);

    private native boolean setShaderVertexCount(int i);

    private native boolean setTargetRegion(float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7);

    private void setTimer()
    {
        mTimer = new StopWatchMap();
    }

    private native boolean setUniformValue(String s, Object obj);

    private native boolean shaderProcess(GLFrame aglframe[], GLFrame glframe);

    public void beginDrawing()
    {
        if(!beginShaderDrawing())
            throw new RuntimeException("Could not prepare shader-program for drawing!");
        else
            return;
    }

    protected void finalize()
        throws Throwable
    {
        deallocate();
    }

    public GLEnvironment getGLEnvironment()
    {
        return mGLEnvironment;
    }

    public Object getHostValue(String s)
    {
        return getUniformValue(s);
    }

    public void process(Frame aframe[], Frame frame)
    {
        if(mTimer.LOG_MFF_RUNNING_TIMES)
        {
            mTimer.start("glFinish");
            GLES20.glFinish();
            mTimer.stop("glFinish");
        }
        GLFrame aglframe[] = new GLFrame[aframe.length];
        for(int i = 0; i < aframe.length;)
            if(aframe[i] instanceof GLFrame)
            {
                aglframe[i] = (GLFrame)aframe[i];
                i++;
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("ShaderProgram got non-GL frame as input ").append(i).append("!").toString());
            }

        if(frame instanceof GLFrame)
        {
            aframe = (GLFrame)frame;
            if(mMaxTileSize > 0)
                setShaderTileCounts(((frame.getFormat().getWidth() + mMaxTileSize) - 1) / mMaxTileSize, ((frame.getFormat().getHeight() + mMaxTileSize) - 1) / mMaxTileSize);
            if(!shaderProcess(aglframe, aframe))
                throw new RuntimeException("Error executing ShaderProgram!");
        } else
        {
            throw new RuntimeException("ShaderProgram got non-GL output frame!");
        }
        if(mTimer.LOG_MFF_RUNNING_TIMES)
            GLES20.glFinish();
    }

    public void setAttributeValues(String s, VertexFrame vertexframe, int i, int j, int k, int l, boolean flag)
    {
        if(!setShaderAttributeVertexFrame(s, vertexframe, i, j, k, l, flag))
            throw new RuntimeException((new StringBuilder()).append("Error setting attribute value for attribute '").append(s).append("'!").toString());
        else
            return;
    }

    public void setAttributeValues(String s, float af[], int i)
    {
        if(!setShaderAttributeValues(s, af, i))
            throw new RuntimeException((new StringBuilder()).append("Error setting attribute value for attribute '").append(s).append("'!").toString());
        else
            return;
    }

    public void setBlendEnabled(boolean flag)
    {
        if(!setShaderBlendEnabled(flag))
            throw new RuntimeException((new StringBuilder()).append("Could not set Blending ").append(flag).append("!").toString());
        else
            return;
    }

    public void setBlendFunc(int i, int j)
    {
        if(!setShaderBlendFunc(i, j))
            throw new RuntimeException((new StringBuilder()).append("Could not set BlendFunc ").append(i).append(",").append(j).append("!").toString());
        else
            return;
    }

    public void setClearColor(float f, float f1, float f2)
    {
        if(!setShaderClearColor(f, f1, f2))
            throw new RuntimeException((new StringBuilder()).append("Could not set clear color to ").append(f).append(",").append(f1).append(",").append(f2).append("!").toString());
        else
            return;
    }

    public void setClearsOutput(boolean flag)
    {
        if(!setShaderClearsOutput(flag))
            throw new RuntimeException((new StringBuilder()).append("Could not set clears-output flag to ").append(flag).append("!").toString());
        else
            return;
    }

    public void setDrawMode(int i)
    {
        if(!setShaderDrawMode(i))
            throw new RuntimeException((new StringBuilder()).append("Could not set GL draw-mode to ").append(i).append("!").toString());
        else
            return;
    }

    public void setHostValue(String s, Object obj)
    {
        if(!setUniformValue(s, obj))
            throw new RuntimeException((new StringBuilder()).append("Error setting uniform value for variable '").append(s).append("'!").toString());
        else
            return;
    }

    public void setMaximumTileSize(int i)
    {
        mMaxTileSize = i;
    }

    public void setSourceRect(float f, float f1, float f2, float f3)
    {
        setSourceRegion(f, f1, f + f2, f1, f, f1 + f3, f + f2, f1 + f3);
    }

    public void setSourceRegion(Quad quad)
    {
        setSourceRegion(quad.p0.x, quad.p0.y, quad.p1.x, quad.p1.y, quad.p2.x, quad.p2.y, quad.p3.x, quad.p3.y);
    }

    public native boolean setSourceRegion(float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7);

    public void setTargetRect(float f, float f1, float f2, float f3)
    {
        setTargetRegion(f, f1, f + f2, f1, f, f1 + f3, f + f2, f1 + f3);
    }

    public void setTargetRegion(Quad quad)
    {
        setTargetRegion(quad.p0.x, quad.p0.y, quad.p1.x, quad.p1.y, quad.p2.x, quad.p2.y, quad.p3.x, quad.p3.y);
    }

    public void setVertexCount(int i)
    {
        if(!setShaderVertexCount(i))
            throw new RuntimeException((new StringBuilder()).append("Could not set GL vertex count to ").append(i).append("!").toString());
        else
            return;
    }

    private GLEnvironment mGLEnvironment;
    private int mMaxTileSize;
    private StopWatchMap mTimer;
    private int shaderProgramId;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
