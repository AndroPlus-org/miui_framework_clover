// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server;

import java.util.List;

public interface WidgetBackupProvider
{

    public abstract List getWidgetParticipants(int i);

    public abstract byte[] getWidgetState(String s, int i);

    public abstract void restoreFinished(int i);

    public abstract void restoreStarting(int i);

    public abstract void restoreWidgetState(String s, byte abyte0[], int i);
}
