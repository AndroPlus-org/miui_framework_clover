// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.gesture;

import java.util.ArrayList;
import java.util.Set;

// Referenced classes of package android.gesture:
//            GestureStore, Gesture, Learner

public abstract class GestureLibrary
{

    protected GestureLibrary()
    {
    }

    public void addGesture(String s, Gesture gesture)
    {
        mStore.addGesture(s, gesture);
    }

    public Set getGestureEntries()
    {
        return mStore.getGestureEntries();
    }

    public ArrayList getGestures(String s)
    {
        return mStore.getGestures(s);
    }

    public Learner getLearner()
    {
        return mStore.getLearner();
    }

    public int getOrientationStyle()
    {
        return mStore.getOrientationStyle();
    }

    public int getSequenceType()
    {
        return mStore.getSequenceType();
    }

    public boolean isReadOnly()
    {
        return false;
    }

    public abstract boolean load();

    public ArrayList recognize(Gesture gesture)
    {
        return mStore.recognize(gesture);
    }

    public void removeEntry(String s)
    {
        mStore.removeEntry(s);
    }

    public void removeGesture(String s, Gesture gesture)
    {
        mStore.removeGesture(s, gesture);
    }

    public abstract boolean save();

    public void setOrientationStyle(int i)
    {
        mStore.setOrientationStyle(i);
    }

    public void setSequenceType(int i)
    {
        mStore.setSequenceType(i);
    }

    protected final GestureStore mStore = new GestureStore();
}
