// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.split;

import android.util.IntArray;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.BitSet;
import libcore.util.EmptyArray;

public abstract class SplitDependencyLoader
{
    public static class IllegalDependencyException extends Exception
    {

        private IllegalDependencyException(String s)
        {
            super(s);
        }

        IllegalDependencyException(String s, IllegalDependencyException illegaldependencyexception)
        {
            this(s);
        }
    }


    protected SplitDependencyLoader(SparseArray sparsearray)
    {
        mDependencies = sparsearray;
    }

    private static int[] append(int ai[], int i)
    {
        if(ai == null)
        {
            return (new int[] {
                i
            });
        } else
        {
            int ai1[] = Arrays.copyOf(ai, ai.length + 1);
            ai1[ai.length] = i;
            return ai1;
        }
    }

    private int[] collectConfigSplitIndices(int i)
    {
        int ai[] = (int[])mDependencies.get(i);
        if(ai == null || ai.length <= 1)
            return EmptyArray.INT;
        else
            return Arrays.copyOfRange(ai, 1, ai.length);
    }

    public static SparseArray createDependenciesFromPackage(android.content.pm.PackageParser.PackageLite packagelite)
        throws IllegalDependencyException
    {
        SparseArray sparsearray = new SparseArray();
        sparsearray.put(0, new int[] {
            -1
        });
        int i = 0;
        while(i < packagelite.splitNames.length) 
        {
            if(packagelite.isFeatureSplits[i])
            {
                String s = packagelite.usesSplitNames[i];
                int k;
                if(s != null)
                {
                    k = Arrays.binarySearch(packagelite.splitNames, s);
                    if(k < 0)
                        throw new IllegalDependencyException((new StringBuilder()).append("Split '").append(packagelite.splitNames[i]).append("' requires split '").append(s).append("', which is missing.").toString(), null);
                    k++;
                } else
                {
                    k = 0;
                }
                sparsearray.put(i + 1, new int[] {
                    k
                });
            }
            i++;
        }
        i = 0;
        while(i < packagelite.splitNames.length) 
        {
            if(!packagelite.isFeatureSplits[i])
            {
                String s1 = packagelite.configForSplit[i];
                int l;
                if(s1 != null)
                {
                    l = Arrays.binarySearch(packagelite.splitNames, s1);
                    if(l < 0)
                        throw new IllegalDependencyException((new StringBuilder()).append("Split '").append(packagelite.splitNames[i]).append("' targets split '").append(s1).append("', which is missing.").toString(), null);
                    if(!packagelite.isFeatureSplits[l])
                        throw new IllegalDependencyException((new StringBuilder()).append("Split '").append(packagelite.splitNames[i]).append("' declares itself as configuration split for a non-feature split '").append(packagelite.splitNames[l]).append("'").toString(), null);
                    l++;
                } else
                {
                    l = 0;
                }
                sparsearray.put(l, append((int[])sparsearray.get(l), i + 1));
            }
            i++;
        }
        packagelite = new BitSet();
        int i1 = 0;
        for(int j1 = sparsearray.size(); i1 < j1; i1++)
        {
            int j = sparsearray.keyAt(i1);
            packagelite.clear();
            while(j != -1) 
            {
                if(packagelite.get(j))
                    throw new IllegalDependencyException("Cycle detected in split dependencies.", null);
                packagelite.set(j);
                int ai[] = (int[])sparsearray.get(j);
                if(ai != null)
                    j = ai[0];
                else
                    j = -1;
            }
        }

        return sparsearray;
    }

    protected abstract void constructSplit(int i, int ai[], int j)
        throws Exception;

    protected abstract boolean isSplitCached(int i);

    protected void loadDependenciesForSplit(int i)
        throws Exception
    {
        if(isSplitCached(i))
            return;
        if(i == 0)
        {
            constructSplit(0, collectConfigSplitIndices(0), -1);
            return;
        }
        IntArray intarray = new IntArray();
        intarray.add(i);
        do
        {
            int ai[] = (int[])mDependencies.get(i);
            if(ai != null && ai.length > 0)
                i = ai[0];
            else
                i = -1;
            if(i < 0 || isSplitCached(i))
            {
                for(int j = intarray.size() - 1; j >= 0; j--)
                {
                    int k = intarray.get(j);
                    constructSplit(k, collectConfigSplitIndices(k), i);
                    i = k;
                }

                break;
            }
            intarray.add(i);
        } while(true);
    }

    private final SparseArray mDependencies;
}
