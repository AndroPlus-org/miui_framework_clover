// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util.jar;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import java.util.jar.Attributes;
import libcore.io.Streams;

// Referenced classes of package android.util.jar:
//            StrictJarManifestReader

public class StrictJarManifest
    implements Cloneable
{
    static final class Chunk
    {

        final int end;
        final int start;

        Chunk(int i, int j)
        {
            start = i;
            end = j;
        }
    }


    public StrictJarManifest()
    {
        entries = new HashMap();
        mainAttributes = new Attributes();
    }

    public StrictJarManifest(StrictJarManifest strictjarmanifest)
    {
        mainAttributes = (Attributes)strictjarmanifest.mainAttributes.clone();
        entries = (HashMap)((HashMap)strictjarmanifest.getEntries()).clone();
    }

    public StrictJarManifest(InputStream inputstream)
        throws IOException
    {
        this();
        read(Streams.readFully(inputstream));
    }

    StrictJarManifest(byte abyte0[], boolean flag)
        throws IOException
    {
        this();
        if(flag)
            chunks = new HashMap();
        read(abyte0);
    }

    private void read(byte abyte0[])
        throws IOException
    {
        if(abyte0.length == 0)
        {
            return;
        } else
        {
            abyte0 = new StrictJarManifestReader(abyte0, mainAttributes);
            mainEnd = abyte0.getEndOfMainSection();
            abyte0.readEntries(entries, chunks);
            return;
        }
    }

    static void write(StrictJarManifest strictjarmanifest, OutputStream outputstream)
        throws IOException
    {
        CharsetEncoder charsetencoder = StandardCharsets.UTF_8.newEncoder();
        ByteBuffer bytebuffer = ByteBuffer.allocate(72);
        java.util.jar.Attributes.Name name = java.util.jar.Attributes.Name.MANIFEST_VERSION;
        String s = strictjarmanifest.mainAttributes.getValue(name);
        Object obj = s;
        if(s == null)
        {
            name = java.util.jar.Attributes.Name.SIGNATURE_VERSION;
            obj = strictjarmanifest.mainAttributes.getValue(name);
        }
        if(obj != null)
        {
            writeEntry(outputstream, name, ((String) (obj)), charsetencoder, bytebuffer);
            obj = strictjarmanifest.mainAttributes.keySet().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                java.util.jar.Attributes.Name name1 = (java.util.jar.Attributes.Name)((Iterator) (obj)).next();
                if(!name1.equals(name))
                    writeEntry(outputstream, name1, strictjarmanifest.mainAttributes.getValue(name1), charsetencoder, bytebuffer);
            } while(true);
        }
        outputstream.write(LINE_SEPARATOR);
        for(Iterator iterator = strictjarmanifest.getEntries().keySet().iterator(); iterator.hasNext(); outputstream.write(LINE_SEPARATOR))
        {
            String s1 = (String)iterator.next();
            writeEntry(outputstream, java.util.jar.Attributes.Name.NAME, s1, charsetencoder, bytebuffer);
            Attributes attributes = (Attributes)strictjarmanifest.entries.get(s1);
            java.util.jar.Attributes.Name name2;
            for(Iterator iterator1 = attributes.keySet().iterator(); iterator1.hasNext(); writeEntry(outputstream, name2, attributes.getValue(name2), charsetencoder, bytebuffer))
                name2 = (java.util.jar.Attributes.Name)iterator1.next();

        }

    }

    private static void writeEntry(OutputStream outputstream, java.util.jar.Attributes.Name name, String s, CharsetEncoder charsetencoder, ByteBuffer bytebuffer)
        throws IOException
    {
        name = name.toString();
        outputstream.write(name.getBytes(StandardCharsets.US_ASCII));
        outputstream.write(VALUE_SEPARATOR);
        charsetencoder.reset();
        bytebuffer.clear().limit(72 - name.length() - 2);
        CharBuffer charbuffer = CharBuffer.wrap(s);
        do
        {
            s = charsetencoder.encode(charbuffer, bytebuffer, true);
            name = s;
            if(CoderResult.UNDERFLOW == s)
                name = charsetencoder.flush(bytebuffer);
            outputstream.write(bytebuffer.array(), bytebuffer.arrayOffset(), bytebuffer.position());
            outputstream.write(LINE_SEPARATOR);
            if(CoderResult.UNDERFLOW == name)
                return;
            outputstream.write(32);
            bytebuffer.clear().limit(71);
        } while(true);
    }

    public void clear()
    {
        entries.clear();
        mainAttributes.clear();
    }

    public Object clone()
    {
        return new StrictJarManifest(this);
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(obj.getClass() != getClass())
            return false;
        if(!mainAttributes.equals(((StrictJarManifest)obj).mainAttributes))
            return false;
        else
            return getEntries().equals(((StrictJarManifest)obj).getEntries());
    }

    public Attributes getAttributes(String s)
    {
        return (Attributes)getEntries().get(s);
    }

    Chunk getChunk(String s)
    {
        return (Chunk)chunks.get(s);
    }

    public Map getEntries()
    {
        return entries;
    }

    public Attributes getMainAttributes()
    {
        return mainAttributes;
    }

    int getMainAttributesEnd()
    {
        return mainEnd;
    }

    public int hashCode()
    {
        return mainAttributes.hashCode() ^ getEntries().hashCode();
    }

    public void read(InputStream inputstream)
        throws IOException
    {
        read(Streams.readFullyNoClose(inputstream));
    }

    void removeChunks()
    {
        chunks = null;
    }

    public void write(OutputStream outputstream)
        throws IOException
    {
        write(this, outputstream);
    }

    static final int LINE_LENGTH_LIMIT = 72;
    private static final byte LINE_SEPARATOR[] = {
        13, 10
    };
    private static final byte VALUE_SEPARATOR[] = {
        58, 32
    };
    private HashMap chunks;
    private final HashMap entries;
    private final Attributes mainAttributes;
    private int mainEnd;

}
