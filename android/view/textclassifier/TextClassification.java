// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.internal.util.Preconditions;
import java.util.List;

// Referenced classes of package android.view.textclassifier:
//            EntityConfidence

public final class TextClassification
{
    public static final class Builder
    {

        public TextClassification build()
        {
            return new TextClassification(mText, mIcon, mLabel, mIntent, mOnClickListener, mEntityConfidence, mLogType, mVersionInfo, null);
        }

        public Builder setEntityType(String s, float f)
        {
            mEntityConfidence.setEntityType(s, f);
            return this;
        }

        public Builder setIcon(Drawable drawable)
        {
            mIcon = drawable;
            return this;
        }

        public Builder setIntent(Intent intent)
        {
            mIntent = intent;
            return this;
        }

        public Builder setLabel(String s)
        {
            mLabel = s;
            return this;
        }

        public Builder setLogType(int i)
        {
            mLogType = i;
            return this;
        }

        public Builder setOnClickListener(android.view.View.OnClickListener onclicklistener)
        {
            mOnClickListener = onclicklistener;
            return this;
        }

        public Builder setText(String s)
        {
            mText = s;
            return this;
        }

        Builder setVersionInfo(String s)
        {
            mVersionInfo = (String)Preconditions.checkNotNull(s);
            return this;
        }

        private final EntityConfidence mEntityConfidence = new EntityConfidence();
        private Drawable mIcon;
        private Intent mIntent;
        private String mLabel;
        private int mLogType;
        private android.view.View.OnClickListener mOnClickListener;
        private String mText;
        private String mVersionInfo;

        public Builder()
        {
            mVersionInfo = "";
        }
    }


    private TextClassification(String s, Drawable drawable, String s1, Intent intent, android.view.View.OnClickListener onclicklistener, EntityConfidence entityconfidence, int i, 
            String s2)
    {
        mText = s;
        mIcon = drawable;
        mLabel = s1;
        mIntent = intent;
        mOnClickListener = onclicklistener;
        mEntityConfidence = new EntityConfidence(entityconfidence);
        mEntities = mEntityConfidence.getEntities();
        mLogType = i;
        mVersionInfo = s2;
    }

    TextClassification(String s, Drawable drawable, String s1, Intent intent, android.view.View.OnClickListener onclicklistener, EntityConfidence entityconfidence, int i, 
            String s2, TextClassification textclassification)
    {
        this(s, drawable, s1, intent, onclicklistener, entityconfidence, i, s2);
    }

    public static android.view.View.OnClickListener createStartActivityOnClickListener(Context context, Intent intent)
    {
        boolean flag = true;
        boolean flag1;
        if(context != null)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        if(intent != null)
            flag1 = flag;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        return new _.Lambda.mxr44OLodDKdoE5ddAZvMdsFssQ(context, intent);
    }

    static void lambda$_2D_android_view_textclassifier_TextClassification_5391(Context context, Intent intent, View view)
    {
        context.startActivity(intent);
    }

    public float getConfidenceScore(String s)
    {
        return mEntityConfidence.getConfidenceScore(s);
    }

    public String getEntity(int i)
    {
        return (String)mEntities.get(i);
    }

    public int getEntityCount()
    {
        return mEntities.size();
    }

    public Drawable getIcon()
    {
        return mIcon;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public int getLogType()
    {
        return mLogType;
    }

    public android.view.View.OnClickListener getOnClickListener()
    {
        return mOnClickListener;
    }

    public String getText()
    {
        return mText;
    }

    public String getVersionInfo()
    {
        return mVersionInfo;
    }

    public String toString()
    {
        return String.format("TextClassification {text=%s, entities=%s, label=%s, intent=%s}", new Object[] {
            mText, mEntityConfidence, mLabel, mIntent
        });
    }

    static final TextClassification EMPTY = (new Builder()).build();
    private final List mEntities;
    private final EntityConfidence mEntityConfidence;
    private final Drawable mIcon;
    private final Intent mIntent;
    private final String mLabel;
    private int mLogType;
    private final android.view.View.OnClickListener mOnClickListener;
    private final String mText;
    private final String mVersionInfo;

}
