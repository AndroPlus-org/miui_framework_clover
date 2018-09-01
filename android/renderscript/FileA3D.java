// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.CloseGuard;
import java.io.File;
import java.io.InputStream;

// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, RSRuntimeException, Mesh

public class FileA3D extends BaseObj
{
    public static final class EntryType extends Enum
    {

        static EntryType toEntryType(int i)
        {
            return values()[i];
        }

        public static EntryType valueOf(String s)
        {
            return (EntryType)Enum.valueOf(android/renderscript/FileA3D$EntryType, s);
        }

        public static EntryType[] values()
        {
            return $VALUES;
        }

        private static final EntryType $VALUES[];
        public static final EntryType MESH;
        public static final EntryType UNKNOWN;
        int mID;

        static 
        {
            UNKNOWN = new EntryType("UNKNOWN", 0, 0);
            MESH = new EntryType("MESH", 1, 1);
            $VALUES = (new EntryType[] {
                UNKNOWN, MESH
            });
        }

        private EntryType(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static class IndexEntry
    {

        private static int[] _2D_getandroid_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues()
        {
            if(_2D_android_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues != null)
                return _2D_android_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues;
            int ai[] = new int[EntryType.values().length];
            try
            {
                ai[EntryType.MESH.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[EntryType.UNKNOWN.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_android_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues = ai;
            return ai;
        }

        static BaseObj internalCreate(RenderScript renderscript, IndexEntry indexentry)
        {
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorenter ;
            if(indexentry.mLoadedObj == null)
                break MISSING_BLOCK_LABEL_20;
            renderscript = indexentry.mLoadedObj;
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorexit ;
            return renderscript;
            EntryType entrytype;
            EntryType entrytype1;
            entrytype = indexentry.mEntryType;
            entrytype1 = EntryType.UNKNOWN;
            if(entrytype != entrytype1)
                break MISSING_BLOCK_LABEL_39;
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorexit ;
            return null;
            long l = renderscript.nFileA3DGetEntryByIndex(indexentry.mID, indexentry.mIndex);
            if(l != 0L)
                break MISSING_BLOCK_LABEL_65;
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorexit ;
            return null;
            switch(_2D_getandroid_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues()[indexentry.mEntryType.ordinal()])
            {
            default:
                renderscript = JVM INSTR new #73  <Class RSRuntimeException>;
                renderscript.RSRuntimeException("Unrecognized object type in file.");
                throw renderscript;

            case 1: // '\001'
                break;
            }
            break MISSING_BLOCK_LABEL_114;
            renderscript;
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorexit ;
            throw renderscript;
            Mesh mesh = JVM INSTR new #80  <Class Mesh>;
            mesh.Mesh(l, renderscript);
            indexentry.mLoadedObj = mesh;
            indexentry.mLoadedObj.updateFromNative();
            renderscript = indexentry.mLoadedObj;
            android/renderscript/FileA3D$IndexEntry;
            JVM INSTR monitorexit ;
            return renderscript;
        }

        public EntryType getEntryType()
        {
            return mEntryType;
        }

        public Mesh getMesh()
        {
            return (Mesh)getObject();
        }

        public String getName()
        {
            return mName;
        }

        public BaseObj getObject()
        {
            mRS.validate();
            return internalCreate(mRS, this);
        }

        private static final int _2D_android_2D_renderscript_2D_FileA3D$EntryTypeSwitchesValues[];
        EntryType mEntryType;
        long mID;
        int mIndex;
        BaseObj mLoadedObj;
        String mName;
        RenderScript mRS;

        IndexEntry(RenderScript renderscript, int i, long l, String s, EntryType entrytype)
        {
            mRS = renderscript;
            mIndex = i;
            mID = l;
            mName = s;
            mEntryType = entrytype;
            mLoadedObj = null;
        }
    }


    FileA3D(long l, RenderScript renderscript, InputStream inputstream)
    {
        super(l, renderscript);
        mInputStream = inputstream;
        guard.open("destroy");
    }

    public static FileA3D createFromAsset(RenderScript renderscript, AssetManager assetmanager, String s)
    {
        renderscript.validate();
        long l = renderscript.nFileA3DCreateFromAsset(assetmanager, s);
        if(l == 0L)
        {
            throw new RSRuntimeException((new StringBuilder()).append("Unable to create a3d file from asset ").append(s).toString());
        } else
        {
            renderscript = new FileA3D(l, renderscript, null);
            renderscript.initEntries();
            return renderscript;
        }
    }

    public static FileA3D createFromFile(RenderScript renderscript, File file)
    {
        return createFromFile(renderscript, file.getAbsolutePath());
    }

    public static FileA3D createFromFile(RenderScript renderscript, String s)
    {
        long l = renderscript.nFileA3DCreateFromFile(s);
        if(l == 0L)
        {
            throw new RSRuntimeException((new StringBuilder()).append("Unable to create a3d file from ").append(s).toString());
        } else
        {
            renderscript = new FileA3D(l, renderscript, null);
            renderscript.initEntries();
            return renderscript;
        }
    }

    public static FileA3D createFromResource(RenderScript renderscript, Resources resources, int i)
    {
        renderscript.validate();
        try
        {
            resources = resources.openRawResource(i);
        }
        // Misplaced declaration of an exception variable
        catch(RenderScript renderscript)
        {
            throw new RSRuntimeException((new StringBuilder()).append("Unable to open resource ").append(i).toString());
        }
        if(resources instanceof android.content.res.AssetManager.AssetInputStream)
        {
            long l = renderscript.nFileA3DCreateFromAssetStream(((android.content.res.AssetManager.AssetInputStream)resources).getNativeAsset());
            if(l == 0L)
            {
                throw new RSRuntimeException((new StringBuilder()).append("Unable to create a3d file from resource ").append(i).toString());
            } else
            {
                renderscript = new FileA3D(l, renderscript, resources);
                renderscript.initEntries();
                return renderscript;
            }
        } else
        {
            throw new RSRuntimeException("Unsupported asset stream");
        }
    }

    private void initEntries()
    {
        int i = mRS.nFileA3DGetNumIndexEntries(getID(mRS));
        if(i <= 0)
            return;
        mFileEntries = new IndexEntry[i];
        int ai[] = new int[i];
        String as[] = new String[i];
        mRS.nFileA3DGetIndexEntries(getID(mRS), i, ai, as);
        for(int j = 0; j < i; j++)
            mFileEntries[j] = new IndexEntry(mRS, j, getID(mRS), as[j], EntryType.toEntryType(ai[j]));

    }

    public IndexEntry getIndexEntry(int i)
    {
        while(getIndexEntryCount() == 0 || i < 0 || i >= mFileEntries.length) 
            return null;
        return mFileEntries[i];
    }

    public int getIndexEntryCount()
    {
        if(mFileEntries == null)
            return 0;
        else
            return mFileEntries.length;
    }

    IndexEntry mFileEntries[];
    InputStream mInputStream;
}
