// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.util.Slog;

// Referenced classes of package android.os:
//            RemoteException, ServiceManager, IIncidentManager, IncidentReportArgs

public class IncidentManager
{

    public IncidentManager(Context context)
    {
        mContext = context;
    }

    public void reportIncident(IncidentReportArgs incidentreportargs)
    {
        IIncidentManager iincidentmanager;
        iincidentmanager = IIncidentManager.Stub.asInterface(ServiceManager.getService("incident"));
        if(iincidentmanager == null)
        {
            Slog.e("incident", "reportIncident can't find incident binder service");
            return;
        }
        iincidentmanager.reportIncident(incidentreportargs);
_L1:
        return;
        incidentreportargs;
        Slog.e("incident", "reportIncident failed", incidentreportargs);
          goto _L1
    }

    public void reportIncident(String s, byte abyte0[])
    {
        Object obj;
        obj = android.provider.Settings.System.getString(mContext.getContentResolver(), s);
        try
        {
            obj = IncidentReportArgs.parseSetting(((String) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Slog.w("incident", (new StringBuilder()).append("Bad value for incident report setting '").append(s).append("'").toString(), abyte0);
            return;
        }
        if(obj == null)
        {
            Slog.i("incident", (new StringBuilder()).append("Incident report requested but disabled: ").append(s).toString());
            return;
        }
        ((IncidentReportArgs) (obj)).addHeader(abyte0);
        abyte0 = IIncidentManager.Stub.asInterface(ServiceManager.getService("incident"));
        if(abyte0 == null)
        {
            Slog.e("incident", "reportIncident can't find incident binder service");
            return;
        }
        Slog.i("incident", (new StringBuilder()).append("Taking incident report: ").append(s).toString());
        abyte0.reportIncident(((IncidentReportArgs) (obj)));
_L1:
        return;
        s;
        Slog.e("incident", "reportIncident failed", s);
          goto _L1
    }

    private static final String TAG = "incident";
    private Context mContext;
}
