// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.graphics.Rect;
import android.text.TextUtils;

public final class DisplayViewport
{

    public DisplayViewport()
    {
    }

    public void copyFrom(DisplayViewport displayviewport)
    {
        valid = displayviewport.valid;
        displayId = displayviewport.displayId;
        orientation = displayviewport.orientation;
        logicalFrame.set(displayviewport.logicalFrame);
        physicalFrame.set(displayviewport.physicalFrame);
        deviceWidth = displayviewport.deviceWidth;
        deviceHeight = displayviewport.deviceHeight;
        uniqueId = displayviewport.uniqueId;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof DisplayViewport))
            return false;
        obj = (DisplayViewport)obj;
        boolean flag1 = flag;
        if(valid == ((DisplayViewport) (obj)).valid)
        {
            flag1 = flag;
            if(displayId == ((DisplayViewport) (obj)).displayId)
            {
                flag1 = flag;
                if(orientation == ((DisplayViewport) (obj)).orientation)
                {
                    flag1 = flag;
                    if(logicalFrame.equals(((DisplayViewport) (obj)).logicalFrame))
                    {
                        flag1 = flag;
                        if(physicalFrame.equals(((DisplayViewport) (obj)).physicalFrame))
                        {
                            flag1 = flag;
                            if(deviceWidth == ((DisplayViewport) (obj)).deviceWidth)
                            {
                                flag1 = flag;
                                if(deviceHeight == ((DisplayViewport) (obj)).deviceHeight)
                                    flag1 = TextUtils.equals(uniqueId, ((DisplayViewport) (obj)).uniqueId);
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        int i = 1;
        if(!valid)
            i = 0;
        i = i + 31 + 1;
        i += i * 31 + displayId;
        i += i * 31 + orientation;
        i += i * 31 + logicalFrame.hashCode();
        i += i * 31 + physicalFrame.hashCode();
        i += i * 31 + deviceWidth;
        i += i * 31 + deviceHeight;
        return i + (i * 31 + uniqueId.hashCode());
    }

    public DisplayViewport makeCopy()
    {
        DisplayViewport displayviewport = new DisplayViewport();
        displayviewport.copyFrom(this);
        return displayviewport;
    }

    public String toString()
    {
        return (new StringBuilder()).append("DisplayViewport{valid=").append(valid).append(", displayId=").append(displayId).append(", uniqueId='").append(uniqueId).append("'").append(", orientation=").append(orientation).append(", logicalFrame=").append(logicalFrame).append(", physicalFrame=").append(physicalFrame).append(", deviceWidth=").append(deviceWidth).append(", deviceHeight=").append(deviceHeight).append("}").toString();
    }

    public int deviceHeight;
    public int deviceWidth;
    public int displayId;
    public final Rect logicalFrame = new Rect();
    public int orientation;
    public final Rect physicalFrame = new Rect();
    public String uniqueId;
    public boolean valid;
}
