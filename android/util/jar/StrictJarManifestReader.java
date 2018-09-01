// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.jar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

class StrictJarManifestReader
{

    public StrictJarManifestReader(byte abyte0[], Attributes attributes)
        throws IOException
    {
        consecutiveLineBreaks = 0;
        buf = abyte0;
        for(; readHeader(); attributes.put(name, value));
        endOfMainSection = pos;
    }

    private boolean readHeader()
        throws IOException
    {
        boolean flag = true;
        if(consecutiveLineBreaks > 1)
        {
            consecutiveLineBreaks = 0;
            return false;
        }
        readName();
        consecutiveLineBreaks = 0;
        readValue();
        if(consecutiveLineBreaks <= 0)
            flag = false;
        return flag;
    }

    private void readName()
        throws IOException
    {
        int i = pos;
        while(pos < buf.length) 
        {
            byte abyte0[] = buf;
            int j = pos;
            pos = j + 1;
            if(abyte0[j] == 58)
            {
                String s = new String(buf, i, pos - i - 1, StandardCharsets.US_ASCII);
                byte abyte1[] = buf;
                int k = pos;
                pos = k + 1;
                if(abyte1[k] != 32)
                    throw new IOException(String.format("Invalid value for attribute '%s'", new Object[] {
                        s
                    }));
                try
                {
                    name = (java.util.jar.Attributes.Name)attributeNameCache.get(s);
                    if(name == null)
                    {
                        java.util.jar.Attributes.Name name1 = JVM INSTR new #96  <Class java.util.jar.Attributes$Name>;
                        name1.java.util.jar.Attributes.Name(s);
                        name = name1;
                        attributeNameCache.put(s, name);
                    }
                    return;
                }
                catch(IllegalArgumentException illegalargumentexception)
                {
                    throw new IOException(illegalargumentexception.getMessage());
                }
            }
        }
    }

    private void readValue()
        throws IOException
    {
        boolean flag;
        int i;
        int j;
        flag = false;
        i = pos;
        j = pos;
        valueBuffer.reset();
_L10:
        if(pos >= buf.length) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int k;
        abyte0 = buf;
        k = pos;
        pos = k + 1;
        abyte0[k];
        JVM INSTR lookupswitch 4: default 100
    //                   0: 151
    //                   10: 161
    //                   13: 183
    //                   32: 198;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        if(consecutiveLineBreaks < 1)
            break; /* Loop/switch isn't completed */
        pos = pos - 1;
_L2:
        valueBuffer.write(buf, i, j - i);
        value = valueBuffer.toString(StandardCharsets.UTF_8.name());
        return;
_L4:
        throw new IOException("NUL character in a manifest");
_L5:
        if(flag)
            flag = false;
        else
            consecutiveLineBreaks = consecutiveLineBreaks + 1;
        continue; /* Loop/switch isn't completed */
_L6:
        flag = true;
        consecutiveLineBreaks = consecutiveLineBreaks + 1;
        continue; /* Loop/switch isn't completed */
_L7:
        if(consecutiveLineBreaks == 1)
        {
            valueBuffer.write(buf, i, j - i);
            i = pos;
            consecutiveLineBreaks = 0;
            continue; /* Loop/switch isn't completed */
        }
        if(true) goto _L3; else goto _L8
_L8:
        j = pos;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public int getEndOfMainSection()
    {
        return endOfMainSection;
    }

    public void readEntries(Map map, Map map1)
        throws IOException
    {
        int j;
        for(int i = pos; readHeader(); i = j)
        {
            if(!java.util.jar.Attributes.Name.NAME.equals(name))
                throw new IOException("Entry is not named");
            String s = value;
            Attributes attributes = (Attributes)map.get(s);
            Attributes attributes1 = attributes;
            if(attributes == null)
                attributes1 = new Attributes(12);
            for(; readHeader(); attributes1.put(name, value));
            j = i;
            if(map1 != null)
            {
                if(map1.get(s) != null)
                    throw new IOException("A jar verifier does not support more than one entry with the same name");
                map1.put(s, new StrictJarManifest.Chunk(i, pos));
                j = pos;
            }
            map.put(s, attributes1);
        }

    }

    private final HashMap attributeNameCache = new HashMap();
    private final byte buf[];
    private int consecutiveLineBreaks;
    private final int endOfMainSection;
    private java.util.jar.Attributes.Name name;
    private int pos;
    private String value;
    private final ByteArrayOutputStream valueBuffer = new ByteArrayOutputStream(80);
}
