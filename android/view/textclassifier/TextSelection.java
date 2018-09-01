// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import com.android.internal.util.Preconditions;
import java.util.List;

// Referenced classes of package android.view.textclassifier:
//            EntityConfidence

public final class TextSelection
{
    public static final class Builder
    {

        public TextSelection build()
        {
            return new TextSelection(mStartIndex, mEndIndex, mEntityConfidence, mLogSource, mVersionInfo, null);
        }

        public Builder setEntityType(String s, float f)
        {
            mEntityConfidence.setEntityType(s, f);
            return this;
        }

        Builder setLogSource(String s)
        {
            mLogSource = (String)Preconditions.checkNotNull(s);
            return this;
        }

        Builder setVersionInfo(String s)
        {
            mVersionInfo = (String)Preconditions.checkNotNull(s);
            return this;
        }

        private final int mEndIndex;
        private final EntityConfidence mEntityConfidence = new EntityConfidence();
        private String mLogSource;
        private final int mStartIndex;
        private String mVersionInfo;

        public Builder(int i, int j)
        {
            boolean flag = true;
            super();
            mLogSource = "";
            mVersionInfo = "";
            boolean flag1;
            if(i >= 0)
                flag1 = true;
            else
                flag1 = false;
            Preconditions.checkArgument(flag1);
            if(j > i)
                flag1 = flag;
            else
                flag1 = false;
            Preconditions.checkArgument(flag1);
            mStartIndex = i;
            mEndIndex = j;
        }
    }


    private TextSelection(int i, int j, EntityConfidence entityconfidence, String s, String s1)
    {
        mStartIndex = i;
        mEndIndex = j;
        mEntityConfidence = new EntityConfidence(entityconfidence);
        mEntities = mEntityConfidence.getEntities();
        mLogSource = s;
        mVersionInfo = s1;
    }

    TextSelection(int i, int j, EntityConfidence entityconfidence, String s, String s1, TextSelection textselection)
    {
        this(i, j, entityconfidence, s, s1);
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

    public int getSelectionEndIndex()
    {
        return mEndIndex;
    }

    public int getSelectionStartIndex()
    {
        return mStartIndex;
    }

    public String getSourceClassifier()
    {
        return mLogSource;
    }

    public String getVersionInfo()
    {
        return mVersionInfo;
    }

    public String toString()
    {
        return String.format("TextSelection {%d, %d, %s}", new Object[] {
            Integer.valueOf(mStartIndex), Integer.valueOf(mEndIndex), mEntityConfidence
        });
    }

    private final int mEndIndex;
    private final List mEntities;
    private final EntityConfidence mEntityConfidence;
    private final String mLogSource;
    private final int mStartIndex;
    private final String mVersionInfo;
}
