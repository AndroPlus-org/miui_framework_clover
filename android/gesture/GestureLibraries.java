// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import java.io.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.gesture:
//            GestureLibrary, GestureStore

public final class GestureLibraries
{
    private static class FileGestureLibrary extends GestureLibrary
    {

        public boolean isReadOnly()
        {
            return mPath.canWrite() ^ true;
        }

        public boolean load()
        {
            boolean flag;
            File file;
            boolean flag1;
            flag = false;
            file = mPath;
            flag1 = flag;
            if(!file.exists())
                break MISSING_BLOCK_LABEL_52;
            flag1 = flag;
            if(!file.canRead())
                break MISSING_BLOCK_LABEL_52;
            GestureStore gesturestore = mStore;
            FileInputStream fileinputstream = JVM INSTR new #41  <Class FileInputStream>;
            fileinputstream.FileInputStream(file);
            gesturestore.load(fileinputstream, true);
            flag1 = true;
_L2:
            return flag1;
            Object obj;
            obj;
            Log.d("Gestures", (new StringBuilder()).append("Could not load the gesture library from ").append(mPath).toString(), ((Throwable) (obj)));
            flag1 = flag;
            continue; /* Loop/switch isn't completed */
            obj;
            Log.d("Gestures", (new StringBuilder()).append("Could not load the gesture library from ").append(mPath).toString(), ((Throwable) (obj)));
            flag1 = flag;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean save()
        {
            File file;
            boolean flag;
            if(!mStore.hasChanged())
                return true;
            file = mPath;
            File file1 = file.getParentFile();
            if(!file1.exists() && !file1.mkdirs())
                return false;
            flag = false;
            file.createNewFile();
            GestureStore gesturestore = mStore;
            FileOutputStream fileoutputstream = JVM INSTR new #88  <Class FileOutputStream>;
            fileoutputstream.FileOutputStream(file);
            gesturestore.save(fileoutputstream, true);
            flag = true;
_L2:
            return flag;
            Object obj;
            obj;
            Log.d("Gestures", (new StringBuilder()).append("Could not save the gesture library in ").append(mPath).toString(), ((Throwable) (obj)));
            continue; /* Loop/switch isn't completed */
            obj;
            Log.d("Gestures", (new StringBuilder()).append("Could not save the gesture library in ").append(mPath).toString(), ((Throwable) (obj)));
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final File mPath;

        public FileGestureLibrary(File file)
        {
            mPath = file;
        }
    }

    private static class ResourceGestureLibrary extends GestureLibrary
    {

        public boolean isReadOnly()
        {
            return true;
        }

        public boolean load()
        {
            boolean flag;
            Context context;
            boolean flag1;
            java.io.InputStream inputstream;
            flag = false;
            context = (Context)mContext.get();
            flag1 = flag;
            if(context == null)
                break MISSING_BLOCK_LABEL_44;
            inputstream = context.getResources().openRawResource(mResourceId);
            mStore.load(inputstream, true);
            flag1 = true;
_L2:
            return flag1;
            IOException ioexception;
            ioexception;
            Log.d("Gestures", (new StringBuilder()).append("Could not load the gesture library from raw resource ").append(context.getResources().getResourceName(mResourceId)).toString(), ioexception);
            flag1 = flag;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean save()
        {
            return false;
        }

        private final WeakReference mContext;
        private final int mResourceId;

        public ResourceGestureLibrary(Context context, int i)
        {
            mContext = new WeakReference(context);
            mResourceId = i;
        }
    }


    private GestureLibraries()
    {
    }

    public static GestureLibrary fromFile(File file)
    {
        return new FileGestureLibrary(file);
    }

    public static GestureLibrary fromFile(String s)
    {
        return fromFile(new File(s));
    }

    public static GestureLibrary fromPrivateFile(Context context, String s)
    {
        return fromFile(context.getFileStreamPath(s));
    }

    public static GestureLibrary fromRawResource(Context context, int i)
    {
        return new ResourceGestureLibrary(context, i);
    }
}
