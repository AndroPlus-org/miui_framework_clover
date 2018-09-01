// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            Activity, PendingIntent

public class TaskStackBuilder
{

    private TaskStackBuilder(Context context)
    {
        mSourceContext = context;
    }

    public static TaskStackBuilder create(Context context)
    {
        return new TaskStackBuilder(context);
    }

    public TaskStackBuilder addNextIntent(Intent intent)
    {
        mIntents.add(intent);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent intent)
    {
        ComponentName componentname = intent.getComponent();
        ComponentName componentname1 = componentname;
        if(componentname == null)
            componentname1 = intent.resolveActivity(mSourceContext.getPackageManager());
        if(componentname1 != null)
            addParentStack(componentname1);
        addNextIntent(intent);
        return this;
    }

    public TaskStackBuilder addParentStack(Activity activity)
    {
        Intent intent = activity.getParentActivityIntent();
        if(intent != null)
        {
            ComponentName componentname = intent.getComponent();
            activity = componentname;
            if(componentname == null)
                activity = intent.resolveActivity(mSourceContext.getPackageManager());
            addParentStack(((ComponentName) (activity)));
            addNextIntent(intent);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName componentname)
    {
        ComponentName componentname1;
        int i = mIntents.size();
        PackageManager packagemanager = mSourceContext.getPackageManager();
        ActivityInfo activityinfo;
        String s;
        try
        {
            activityinfo = packagemanager.getActivityInfo(componentname, 0);
            componentname = activityinfo.parentActivityName;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(componentname);
        }
_L3:
        if(componentname == null)
            break MISSING_BLOCK_LABEL_132;
        componentname1 = JVM INSTR new #85  <Class ComponentName>;
        componentname1.ComponentName(activityinfo.packageName, componentname);
        activityinfo = packagemanager.getActivityInfo(componentname1, 0);
        s = activityinfo.parentActivityName;
        if(s != null || i != 0)
            break MISSING_BLOCK_LABEL_114;
        componentname = Intent.makeMainActivity(componentname1);
_L1:
        mIntents.add(i, componentname);
        componentname = s;
        continue; /* Loop/switch isn't completed */
        componentname = JVM INSTR new #39  <Class Intent>;
        componentname.Intent();
        componentname = componentname.setComponent(componentname1);
          goto _L1
        return this;
        if(true) goto _L3; else goto _L2
_L2:
    }

    public TaskStackBuilder addParentStack(Class class1)
    {
        return addParentStack(new ComponentName(mSourceContext, class1));
    }

    public Intent editIntentAt(int i)
    {
        return (Intent)mIntents.get(i);
    }

    public int getIntentCount()
    {
        return mIntents.size();
    }

    public Intent[] getIntents()
    {
        Intent aintent[] = new Intent[mIntents.size()];
        if(aintent.length == 0)
            return aintent;
        aintent[0] = (new Intent((Intent)mIntents.get(0))).addFlags(0x1000c000);
        for(int i = 1; i < aintent.length; i++)
            aintent[i] = new Intent((Intent)mIntents.get(i));

        return aintent;
    }

    public PendingIntent getPendingIntent(int i, int j)
    {
        return getPendingIntent(i, j, null);
    }

    public PendingIntent getPendingIntent(int i, int j, Bundle bundle)
    {
        if(mIntents.isEmpty())
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        else
            return PendingIntent.getActivities(mSourceContext, i, getIntents(), j, bundle);
    }

    public PendingIntent getPendingIntent(int i, int j, Bundle bundle, UserHandle userhandle)
    {
        if(mIntents.isEmpty())
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        else
            return PendingIntent.getActivitiesAsUser(mSourceContext, i, getIntents(), j, bundle, userhandle);
    }

    public void startActivities()
    {
        startActivities(null);
    }

    public void startActivities(Bundle bundle)
    {
        startActivities(bundle, new UserHandle(UserHandle.myUserId()));
    }

    public void startActivities(Bundle bundle, UserHandle userhandle)
    {
        if(mIntents.isEmpty())
        {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        } else
        {
            mSourceContext.startActivitiesAsUser(getIntents(), bundle, userhandle);
            return;
        }
    }

    private static final String TAG = "TaskStackBuilder";
    private final ArrayList mIntents = new ArrayList();
    private final Context mSourceContext;
}
