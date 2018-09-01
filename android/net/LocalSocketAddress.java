// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;


public class LocalSocketAddress
{
    public static final class Namespace extends Enum
    {

        public static Namespace valueOf(String s)
        {
            return (Namespace)Enum.valueOf(android/net/LocalSocketAddress$Namespace, s);
        }

        public static Namespace[] values()
        {
            return $VALUES;
        }

        int getId()
        {
            return id;
        }

        private static final Namespace $VALUES[];
        public static final Namespace ABSTRACT;
        public static final Namespace FILESYSTEM;
        public static final Namespace RESERVED;
        private int id;

        static 
        {
            ABSTRACT = new Namespace("ABSTRACT", 0, 0);
            RESERVED = new Namespace("RESERVED", 1, 1);
            FILESYSTEM = new Namespace("FILESYSTEM", 2, 2);
            $VALUES = (new Namespace[] {
                ABSTRACT, RESERVED, FILESYSTEM
            });
        }

        private Namespace(String s, int i, int j)
        {
            super(s, i);
            id = j;
        }
    }


    public LocalSocketAddress(String s)
    {
        this(s, Namespace.ABSTRACT);
    }

    public LocalSocketAddress(String s, Namespace namespace1)
    {
        name = s;
        namespace = namespace1;
    }

    public String getName()
    {
        return name;
    }

    public Namespace getNamespace()
    {
        return namespace;
    }

    private final String name;
    private final Namespace namespace;
}
