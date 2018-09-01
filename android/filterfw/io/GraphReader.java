// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.io;

import android.content.Context;
import android.content.res.Resources;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.KeyValueMap;
import java.io.*;

// Referenced classes of package android.filterfw.io:
//            GraphIOException

public abstract class GraphReader
{

    public GraphReader()
    {
        mReferences = new KeyValueMap();
    }

    public void addReference(String s, Object obj)
    {
        mReferences.put(s, obj);
    }

    public transient void addReferencesByKeysAndValues(Object aobj[])
    {
        mReferences.setKeyValues(aobj);
    }

    public void addReferencesByMap(KeyValueMap keyvaluemap)
    {
        mReferences.putAll(keyvaluemap);
    }

    public FilterGraph readGraphResource(Context context, int i)
        throws GraphIOException
    {
        StringWriter stringwriter;
        char ac[];
        context = new InputStreamReader(context.getResources().openRawResource(i));
        stringwriter = new StringWriter();
        ac = new char[1024];
_L1:
        try
        {
            i = context.read(ac, 0, 1024);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Could not read specified resource file!");
        }
        if(i <= 0)
            break MISSING_BLOCK_LABEL_68;
        stringwriter.write(ac, 0, i);
          goto _L1
        return readGraphString(stringwriter.toString());
    }

    public abstract FilterGraph readGraphString(String s)
        throws GraphIOException;

    public abstract KeyValueMap readKeyValueAssignments(String s)
        throws GraphIOException;

    protected KeyValueMap mReferences;
}
