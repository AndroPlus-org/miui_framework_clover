// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.RemoteException;

public abstract class TaskStackListener extends ITaskStackListener.Stub
{

    public TaskStackListener()
    {
    }

    public void onActivityDismissingDockedStack()
        throws RemoteException
    {
    }

    public void onActivityForcedResizable(String s, int i, int j)
        throws RemoteException
    {
    }

    public void onActivityLaunchOnSecondaryDisplayFailed()
        throws RemoteException
    {
    }

    public void onActivityPinned(String s, int i, int j)
        throws RemoteException
    {
    }

    public void onActivityRequestedOrientationChanged(int i, int j)
        throws RemoteException
    {
    }

    public void onActivityUnpinned()
        throws RemoteException
    {
    }

    public void onPinnedActivityRestartAttempt(boolean flag)
        throws RemoteException
    {
    }

    public void onPinnedStackAnimationEnded()
        throws RemoteException
    {
    }

    public void onPinnedStackAnimationStarted()
        throws RemoteException
    {
    }

    public void onTaskCreated(int i, ComponentName componentname)
        throws RemoteException
    {
    }

    public void onTaskDescriptionChanged(int i, ActivityManager.TaskDescription taskdescription)
        throws RemoteException
    {
    }

    public void onTaskMovedToFront(int i)
        throws RemoteException
    {
    }

    public void onTaskProfileLocked(int i, int j)
    {
    }

    public void onTaskRemovalStarted(int i)
    {
    }

    public void onTaskRemoved(int i)
        throws RemoteException
    {
    }

    public void onTaskSnapshotChanged(int i, ActivityManager.TaskSnapshot tasksnapshot)
        throws RemoteException
    {
    }

    public void onTaskStackChanged()
        throws RemoteException
    {
    }
}
