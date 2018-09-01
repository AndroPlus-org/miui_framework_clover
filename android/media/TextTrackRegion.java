// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


class TextTrackRegion
{

    TextTrackRegion()
    {
        mId = "";
        mWidth = 100F;
        mLines = 3;
        mViewportAnchorPointX = 0.0F;
        mAnchorPointX = 0.0F;
        mViewportAnchorPointY = 100F;
        mAnchorPointY = 100F;
        mScrollValue = 300;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder(" {id:\"")).append(mId).append("\", width:").append(mWidth).append(", lines:").append(mLines).append(", anchorPoint:(").append(mAnchorPointX).append(", ").append(mAnchorPointY).append("), viewportAnchorPoints:").append(mViewportAnchorPointX).append(", ").append(mViewportAnchorPointY).append("), scrollValue:");
        String s;
        if(mScrollValue == 300)
            s = "none";
        else
        if(mScrollValue == 301)
            s = "scroll_up";
        else
            s = "INVALID";
        return stringbuilder.append(s).append("}").toString();
    }

    static final int SCROLL_VALUE_NONE = 300;
    static final int SCROLL_VALUE_SCROLL_UP = 301;
    float mAnchorPointX;
    float mAnchorPointY;
    String mId;
    int mLines;
    int mScrollValue;
    float mViewportAnchorPointX;
    float mViewportAnchorPointY;
    float mWidth;
}
