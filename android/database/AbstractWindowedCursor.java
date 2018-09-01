// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;


// Referenced classes of package android.database:
//            AbstractCursor, StaleDataException, CursorWindow, CharArrayBuffer

public abstract class AbstractWindowedCursor extends AbstractCursor
{

    public AbstractWindowedCursor()
    {
    }

    protected void checkPosition()
    {
        super.checkPosition();
        if(mWindow == null)
            throw new StaleDataException("Attempting to access a closed CursorWindow.Most probable cause: cursor is deactivated prior to calling this method.");
        else
            return;
    }

    protected void clearOrCreateWindow(String s)
    {
        if(mWindow == null)
            mWindow = new CursorWindow(s);
        else
            mWindow.clear();
    }

    protected void closeWindow()
    {
        if(mWindow != null)
        {
            mWindow.close();
            mWindow = null;
        }
    }

    public void copyStringToBuffer(int i, CharArrayBuffer chararraybuffer)
    {
        checkPosition();
        mWindow.copyStringToBuffer(mPos, i, chararraybuffer);
    }

    public byte[] getBlob(int i)
    {
        checkPosition();
        return mWindow.getBlob(mPos, i);
    }

    public double getDouble(int i)
    {
        checkPosition();
        return mWindow.getDouble(mPos, i);
    }

    public float getFloat(int i)
    {
        checkPosition();
        return mWindow.getFloat(mPos, i);
    }

    public int getInt(int i)
    {
        checkPosition();
        return mWindow.getInt(mPos, i);
    }

    public long getLong(int i)
    {
        checkPosition();
        return mWindow.getLong(mPos, i);
    }

    public short getShort(int i)
    {
        checkPosition();
        return mWindow.getShort(mPos, i);
    }

    public String getString(int i)
    {
        checkPosition();
        return mWindow.getString(mPos, i);
    }

    public int getType(int i)
    {
        checkPosition();
        return mWindow.getType(mPos, i);
    }

    public CursorWindow getWindow()
    {
        return mWindow;
    }

    public boolean hasWindow()
    {
        boolean flag;
        if(mWindow != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isBlob(int i)
    {
        boolean flag;
        if(getType(i) == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFloat(int i)
    {
        boolean flag;
        if(getType(i) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLong(int i)
    {
        boolean flag = true;
        if(getType(i) != 1)
            flag = false;
        return flag;
    }

    public boolean isNull(int i)
    {
        boolean flag = false;
        checkPosition();
        if(mWindow.getType(mPos, i) == 0)
            flag = true;
        return flag;
    }

    public boolean isString(int i)
    {
        boolean flag;
        if(getType(i) == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void onDeactivateOrClose()
    {
        super.onDeactivateOrClose();
        closeWindow();
    }

    public void setWindow(CursorWindow cursorwindow)
    {
        if(cursorwindow != mWindow)
        {
            closeWindow();
            mWindow = cursorwindow;
        }
    }

    protected CursorWindow mWindow;
}
