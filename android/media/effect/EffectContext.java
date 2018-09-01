// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import android.filterfw.core.*;
import android.opengl.GLES20;

// Referenced classes of package android.media.effect:
//            EffectFactory

public class EffectContext
{

    private EffectContext()
    {
        mOldState = new int[3];
        mFilterContext = new FilterContext();
        mFilterContext.setFrameManager(new CachedFrameManager());
        mFactory = new EffectFactory(this);
    }

    public static EffectContext createWithCurrentGlContext()
    {
        EffectContext effectcontext = new EffectContext();
        effectcontext.initInCurrentGlContext();
        return effectcontext;
    }

    private void initInCurrentGlContext()
    {
        if(!GLEnvironment.isAnyContextActive())
        {
            throw new RuntimeException("Attempting to initialize EffectContext with no active GL context!");
        } else
        {
            GLEnvironment glenvironment = new GLEnvironment();
            glenvironment.initWithCurrentContext();
            mFilterContext.initGLEnvironment(glenvironment);
            return;
        }
    }

    final void assertValidGLState()
    {
        GLEnvironment glenvironment = mFilterContext.getGLEnvironment();
        if(glenvironment == null || glenvironment.isContextActive() ^ true)
        {
            if(GLEnvironment.isAnyContextActive())
                throw new RuntimeException("Applying effect in wrong GL context!");
            else
                throw new RuntimeException("Attempting to apply effect without valid GL context!");
        } else
        {
            return;
        }
    }

    public EffectFactory getFactory()
    {
        return mFactory;
    }

    public void release()
    {
        mFilterContext.tearDown();
        mFilterContext = null;
    }

    final void restoreGLState()
    {
        GLES20.glBindFramebuffer(36160, mOldState[0]);
        GLES20.glUseProgram(mOldState[1]);
        GLES20.glBindBuffer(34962, mOldState[2]);
    }

    final void saveGLState()
    {
        GLES20.glGetIntegerv(36006, mOldState, 0);
        GLES20.glGetIntegerv(35725, mOldState, 1);
        GLES20.glGetIntegerv(34964, mOldState, 2);
    }

    private final int GL_STATE_ARRAYBUFFER = 2;
    private final int GL_STATE_COUNT = 3;
    private final int GL_STATE_FBO = 0;
    private final int GL_STATE_PROGRAM = 1;
    private EffectFactory mFactory;
    FilterContext mFilterContext;
    private int mOldState[];
}
