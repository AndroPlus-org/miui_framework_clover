// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import java.util.HashMap;

class TreeNode
{

    TreeNode()
    {
        mString = null;
    }

    private void insertToChild(String s, int i)
    {
        if(mChildNodes == null)
            mChildNodes = new HashMap(0);
        if(i < s.length())
        {
            char c = s.charAt(i);
            TreeNode treenode = (TreeNode)mChildNodes.get(Character.valueOf(c));
            TreeNode treenode1 = treenode;
            if(treenode == null)
            {
                treenode1 = new TreeNode();
                mChildNodes.put(Character.valueOf(c), treenode1);
            }
            treenode1.insert(s, i + 1);
        }
    }

    boolean find(String s, int i)
    {
        boolean flag = false;
        if(isTerminal() && mString != null)
            return s.startsWith(mString);
        if(mChildNodes != null && s != null && i < s.length())
        {
            TreeNode treenode = (TreeNode)mChildNodes.get(Character.valueOf(s.charAt(i)));
            if(treenode != null)
                flag = treenode.find(s, i + 1);
            return flag;
        } else
        {
            return false;
        }
    }

    void insert(String s, int i)
    {
        if(i != s.length() && (mChildNodes != null || mString != null)) goto _L2; else goto _L1
_L1:
        mString = s;
_L4:
        return;
_L2:
        insertToChild(s, i);
        if(mString != null && i < mString.length())
        {
            insertToChild(mString, i);
            mString = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    boolean isTerminal()
    {
        boolean flag;
        if(mChildNodes == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private HashMap mChildNodes;
    private String mString;
}
