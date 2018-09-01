// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.io.File;
import miui.maml.util.ZipResourceLoader;

// Referenced classes of package miui.maml:
//            ScreenElementRoot, ScreenContext, LifecycleResourceManager, ResourceLoader

public class ScreenElementRootFactory
{
    public static class Parameter
    {

        static Context _2D_get0(Parameter parameter)
        {
            return parameter.mContext;
        }

        static String _2D_get1(Parameter parameter)
        {
            return parameter.mPath;
        }

        static ResourceLoader _2D_get2(Parameter parameter)
        {
            return parameter.mResourceLoader;
        }

        private Context mContext;
        private String mPath;
        private ResourceLoader mResourceLoader;

        public Parameter(Context context, String s)
        {
            if(context != null)
                mContext = context.getApplicationContext();
            mPath = s;
        }

        public Parameter(Context context, ResourceLoader resourceloader)
        {
            if(context != null)
                mContext = context.getApplicationContext();
            mResourceLoader = resourceloader;
        }
    }


    public ScreenElementRootFactory()
    {
    }

    public static ScreenElementRoot create(Parameter parameter)
    {
        Context context = Parameter._2D_get0(parameter);
        if(context == null)
            throw new NullPointerException();
        ResourceLoader resourceloader = Parameter._2D_get2(parameter);
        String s = Parameter._2D_get1(parameter);
        parameter = resourceloader;
        if(resourceloader == null)
        {
            parameter = resourceloader;
            if(s != null)
            {
                parameter = resourceloader;
                if((new File(s)).exists())
                    parameter = (new ZipResourceLoader(s)).setLocal(context.getResources().getConfiguration().locale);
            }
        }
        if(parameter == null)
            return null;
        else
            return new ScreenElementRoot(new ScreenContext(context, new LifecycleResourceManager(parameter, 0x36ee80L, 0x57e40L)));
    }
}
