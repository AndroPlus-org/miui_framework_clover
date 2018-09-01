// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableContainer, Drawable

public class LevelListDrawable extends DrawableContainer
{
    private static final class LevelListState extends DrawableContainer.DrawableContainerState
    {

        static void _2D_wrap0(LevelListState levelliststate)
        {
            levelliststate.mutate();
        }

        private void mutate()
        {
            mLows = (int[])mLows.clone();
            mHighs = (int[])mHighs.clone();
        }

        public void addLevel(int i, int j, Drawable drawable)
        {
            int k = addChild(drawable);
            mLows[k] = i;
            mHighs[k] = j;
        }

        public void growArray(int i, int j)
        {
            super.growArray(i, j);
            int ai[] = new int[j];
            System.arraycopy(mLows, 0, ai, 0, i);
            mLows = ai;
            ai = new int[j];
            System.arraycopy(mHighs, 0, ai, 0, i);
            mHighs = ai;
        }

        public int indexOfLevel(int i)
        {
            int ai[] = mLows;
            int ai1[] = mHighs;
            int j = getChildCount();
            for(int k = 0; k < j; k++)
                if(i >= ai[k] && i <= ai1[k])
                    return k;

            return -1;
        }

        public Drawable newDrawable()
        {
            return new LevelListDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new LevelListDrawable(this, resources, null);
        }

        private int mHighs[];
        private int mLows[];

        LevelListState(LevelListState levelliststate, LevelListDrawable levellistdrawable, Resources resources)
        {
            super(levelliststate, levellistdrawable, resources);
            if(levelliststate != null)
            {
                mLows = levelliststate.mLows;
                mHighs = levelliststate.mHighs;
            } else
            {
                mLows = new int[getCapacity()];
                mHighs = new int[getCapacity()];
            }
        }
    }


    public LevelListDrawable()
    {
        this(null, null);
    }

    private LevelListDrawable(LevelListState levelliststate, Resources resources)
    {
        setConstantState(new LevelListState(levelliststate, this, resources));
        onLevelChange(getLevel());
    }

    LevelListDrawable(LevelListState levelliststate, Resources resources, LevelListDrawable levellistdrawable)
    {
        this(levelliststate, resources);
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth() + 1;
        do
        {
            int j = xmlpullparser.next();
            if(j == 1)
                break;
            int l = xmlpullparser.getDepth();
            if(l < i && j == 3)
                break;
            if(j == 2 && l <= i && !(xmlpullparser.getName().equals("item") ^ true))
            {
                Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.LevelListDrawableItem);
                int i1 = ((TypedArray) (obj)).getInt(1, 0);
                int k = ((TypedArray) (obj)).getInt(2, 0);
                int j1 = ((TypedArray) (obj)).getResourceId(0, 0);
                ((TypedArray) (obj)).recycle();
                if(k < 0)
                    throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'maxLevel' attribute").toString());
                if(j1 != 0)
                {
                    obj = resources.getDrawable(j1, theme);
                } else
                {
                    int k1;
                    do
                        k1 = xmlpullparser.next();
                    while(k1 == 4);
                    if(k1 != 2)
                        throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
                    obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
                }
                mLevelListState.addLevel(i1, k, ((Drawable) (obj)));
            }
        } while(true);
        onLevelChange(getLevel());
    }

    public void addLevel(int i, int j, Drawable drawable)
    {
        if(drawable != null)
        {
            mLevelListState.addLevel(i, j, drawable);
            onLevelChange(getLevel());
        }
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

    LevelListState cloneConstantState()
    {
        return new LevelListState(mLevelListState, this, null);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateDensity(resources);
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            LevelListState._2D_wrap0(mLevelListState);
            mMutated = true;
        }
        return this;
    }

    protected boolean onLevelChange(int i)
    {
        if(selectDrawable(mLevelListState.indexOfLevel(i)))
            return true;
        else
            return super.onLevelChange(i);
    }

    protected void setConstantState(DrawableContainer.DrawableContainerState drawablecontainerstate)
    {
        super.setConstantState(drawablecontainerstate);
        if(drawablecontainerstate instanceof LevelListState)
            mLevelListState = (LevelListState)drawablecontainerstate;
    }

    private LevelListState mLevelListState;
    private boolean mMutated;
}
