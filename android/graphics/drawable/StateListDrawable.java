// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableContainer, Drawable

public class StateListDrawable extends DrawableContainer
{
    static class StateListState extends DrawableContainer.DrawableContainerState
    {

        int addStateSet(int ai[], Drawable drawable)
        {
            int i = addChild(drawable);
            mStateSets[i] = ai;
            return i;
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null)
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        public void growArray(int i, int j)
        {
            super.growArray(i, j);
            int ai[][] = new int[j][];
            System.arraycopy(mStateSets, 0, ai, 0, i);
            mStateSets = ai;
        }

        boolean hasFocusStateSpecified()
        {
            return StateSet.containsAttribute(mStateSets, 0x101009c);
        }

        int indexOfStateSet(int ai[])
        {
            int ai1[][] = mStateSets;
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                if(StateSet.stateSetMatches(ai1[j], ai))
                    return j;

            return -1;
        }

        void mutate()
        {
            int ai[];
            int ai1[][];
            int i;
            if(mThemeAttrs != null)
                ai = (int[])mThemeAttrs.clone();
            else
                ai = null;
            mThemeAttrs = ai;
            ai1 = new int[mStateSets.length][];
            i = mStateSets.length - 1;
            while(i >= 0) 
            {
                if(mStateSets[i] != null)
                    ai = (int[])mStateSets[i].clone();
                else
                    ai = null;
                ai1[i] = ai;
                i--;
            }
            mStateSets = ai1;
        }

        public Drawable newDrawable()
        {
            return new StateListDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new StateListDrawable(this, resources, null);
        }

        int mStateSets[][];
        int mThemeAttrs[];

        StateListState(StateListState stateliststate, StateListDrawable statelistdrawable, Resources resources)
        {
            super(stateliststate, statelistdrawable, resources);
            if(stateliststate != null)
            {
                mThemeAttrs = stateliststate.mThemeAttrs;
                mStateSets = stateliststate.mStateSets;
            } else
            {
                mThemeAttrs = null;
                mStateSets = new int[getCapacity()][];
            }
        }
    }


    public StateListDrawable()
    {
        this(null, null);
    }

    StateListDrawable(StateListState stateliststate)
    {
        if(stateliststate != null)
            setConstantState(stateliststate);
    }

    private StateListDrawable(StateListState stateliststate, Resources resources)
    {
        setConstantState(new StateListState(stateliststate, this, resources));
        onStateChange(getState());
    }

    StateListDrawable(StateListState stateliststate, Resources resources, StateListDrawable statelistdrawable)
    {
        this(stateliststate, resources);
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        StateListState stateliststate = mStateListState;
        int i = xmlpullparser.getDepth() + 1;
        do
        {
            int j = xmlpullparser.next();
            if(j == 1)
                break;
            int k = xmlpullparser.getDepth();
            if(k < i && j == 3)
                break;
            if(j == 2 && k <= i && !(xmlpullparser.getName().equals("item") ^ true))
            {
                Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.StateListDrawableItem);
                Drawable drawable = ((TypedArray) (obj)).getDrawable(0);
                ((TypedArray) (obj)).recycle();
                int ai[] = extractStateSet(attributeset);
                obj = drawable;
                if(drawable == null)
                {
                    int l;
                    do
                        l = xmlpullparser.next();
                    while(l == 4);
                    if(l != 2)
                        throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
                    obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
                }
                stateliststate.addStateSet(ai, ((Drawable) (obj)));
            }
        } while(true);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        StateListState stateliststate = mStateListState;
        stateliststate.mChangingConfigurations = stateliststate.mChangingConfigurations | typedarray.getChangingConfigurations();
        stateliststate.mThemeAttrs = typedarray.extractThemeAttrs();
        stateliststate.mVariablePadding = typedarray.getBoolean(2, stateliststate.mVariablePadding);
        stateliststate.mConstantSize = typedarray.getBoolean(3, stateliststate.mConstantSize);
        stateliststate.mEnterFadeDuration = typedarray.getInt(4, stateliststate.mEnterFadeDuration);
        stateliststate.mExitFadeDuration = typedarray.getInt(5, stateliststate.mExitFadeDuration);
        stateliststate.mDither = typedarray.getBoolean(0, stateliststate.mDither);
        stateliststate.mAutoMirrored = typedarray.getBoolean(6, stateliststate.mAutoMirrored);
    }

    public void addState(int ai[], Drawable drawable)
    {
        if(drawable != null)
        {
            mStateListState.addStateSet(ai, drawable);
            onStateChange(getState());
        }
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    volatile DrawableContainer.DrawableContainerState cloneConstantState()
    {
        return cloneConstantState();
    }

    StateListState cloneConstantState()
    {
        return new StateListState(mStateListState, this, null);
    }

    int[] extractStateSet(AttributeSet attributeset)
    {
        int i = attributeset.getAttributeCount();
        int ai[] = new int[i];
        int j = 0;
        int k = 0;
        do
            if(j < i)
            {
                int l = attributeset.getAttributeNameResource(j);
                switch(l)
                {
                default:
                    int i1 = k + 1;
                    if(!attributeset.getAttributeBooleanValue(j, false))
                        l = -l;
                    ai[k] = l;
                    k = i1;
                    // fall through

                case 0: // '\0'
                case 16842960: 
                case 16843161: 
                    j++;
                    break;
                }
            } else
            {
                return StateSet.trimStateSet(ai, k);
            }
        while(true);
    }

    public int getStateCount()
    {
        return mStateListState.getChildCount();
    }

    public Drawable getStateDrawable(int i)
    {
        return mStateListState.getChild(i);
    }

    public int getStateDrawableIndex(int ai[])
    {
        return mStateListState.indexOfStateSet(ai);
    }

    StateListState getStateListState()
    {
        return mStateListState;
    }

    public int[] getStateSet(int i)
    {
        return mStateListState.mStateSets[i];
    }

    public boolean hasFocusStateSpecified()
    {
        return mStateListState.hasFocusStateSpecified();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.StateListDrawable);
        super.inflateWithAttributes(resources, xmlpullparser, typedarray, 1);
        updateStateFromTypedArray(typedarray);
        updateDensity(resources);
        typedarray.recycle();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        onStateChange(getState());
    }

    public boolean isStateful()
    {
        return true;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mStateListState.mutate();
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = super.onStateChange(ai);
        int i = mStateListState.indexOfStateSet(ai);
        int j = i;
        if(i < 0)
            j = mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        if(selectDrawable(j))
            flag = true;
        return flag;
    }

    protected void setConstantState(DrawableContainer.DrawableContainerState drawablecontainerstate)
    {
        super.setConstantState(drawablecontainerstate);
        if(drawablecontainerstate instanceof StateListState)
            mStateListState = (StateListState)drawablecontainerstate;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;
}
