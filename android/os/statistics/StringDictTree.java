// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;


// Referenced classes of package android.os.statistics:
//            TreeNode

class StringDictTree extends TreeNode
{

    public StringDictTree(String as[])
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            super.insert(as[j], 0);

    }

    public boolean find(String s)
    {
        return super.find(s, 0);
    }
}
