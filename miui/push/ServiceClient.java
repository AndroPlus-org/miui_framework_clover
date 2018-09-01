// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;

// Referenced classes of package miui.push:
//            PushConstants, Message, IQ, Presence

public class ServiceClient
{

    private ServiceClient(Context context)
    {
        mContext = context.getApplicationContext();
    }

    private Intent createServiceIntent()
    {
        Intent intent = new Intent();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", "com.xiaomi.xmsf.push.service.XMPushService");
        String s = mContext.getPackageName();
        intent.putExtra(PushConstants.EXTRA_PACKAGE_NAME, s);
        return intent;
    }

    public static ServiceClient getInstance(Context context)
    {
        if(sInstance == null)
            sInstance = new ServiceClient(context);
        return sInstance;
    }

    private boolean hasNetwork()
    {
        boolean flag = false;
        byte byte0 = -1;
        Object obj = (ConnectivityManager)mContext.getSystemService("connectivity");
        int i = byte0;
        if(obj != null)
        {
            obj = ((ConnectivityManager) (obj)).getActiveNetworkInfo();
            i = byte0;
            if(obj != null)
                i = ((NetworkInfo) (obj)).getType();
        }
        if(i >= 0)
            flag = true;
        return flag;
    }

    private boolean serviceInstalled()
    {
        Object obj = mContext.getPackageManager();
        try
        {
            obj = ((PackageManager) (obj)).getPackageInfo("com.xiaomi.xmsf", 4);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        return obj != null;
    }

    public boolean batchSendMessage(Message amessage[])
    {
        if(!hasNetwork())
            return false;
        Intent intent = createServiceIntent();
        Bundle abundle[] = new Bundle[amessage.length];
        for(int i = 0; i < amessage.length; i++)
            abundle[i] = amessage[i].toBundle();

        if(abundle.length > 0)
        {
            intent.setAction(PushConstants.ACTION_BATCH_SEND_MESSAGE);
            intent.putExtra("ext_packets", abundle);
            mContext.startService(intent);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean closeChannel()
    {
        if(!serviceInstalled())
        {
            return false;
        } else
        {
            Intent intent = createServiceIntent();
            intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
            mContext.startService(intent);
            return true;
        }
    }

    public boolean closeChannel(String s)
    {
        if(!serviceInstalled())
        {
            return false;
        } else
        {
            Intent intent = createServiceIntent();
            intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
            intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, s);
            mContext.startService(intent);
            return true;
        }
    }

    public boolean closeChannel(String s, String s1)
    {
        if(!serviceInstalled())
        {
            return false;
        } else
        {
            Intent intent = createServiceIntent();
            intent.setAction(PushConstants.ACTION_CLOSE_CHANNEL);
            intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, s);
            intent.putExtra(PushConstants.EXTRA_USER_ID, s1);
            mContext.startService(intent);
            return true;
        }
    }

    public boolean forceReconnection()
    {
        if(!serviceInstalled() || hasNetwork() ^ true)
        {
            return false;
        } else
        {
            Intent intent = createServiceIntent();
            intent.setAction(PushConstants.ACTION_FORCE_RECONNECT);
            mContext.startService(intent);
            return true;
        }
    }

    public int openChannel(String s, String s1, String s2, String s3, String s4, boolean flag, List list, 
            List list1)
    {
        if(!serviceInstalled())
            return 1;
        Intent intent = createServiceIntent();
        intent.setAction(PushConstants.ACTION_OPEN_CHANNEL);
        intent.putExtra(PushConstants.EXTRA_USER_ID, s);
        intent.putExtra(PushConstants.EXTRA_CHANNEL_ID, s1);
        intent.putExtra(PushConstants.EXTRA_TOKEN, s2);
        intent.putExtra(PushConstants.EXTRA_SECURITY, s4);
        intent.putExtra(PushConstants.EXTRA_AUTH_METHOD, s3);
        intent.putExtra(PushConstants.EXTRA_KICK, flag);
        if(list != null)
        {
            s1 = new StringBuilder();
            int i = 1;
            for(s = list.iterator(); s.hasNext();)
            {
                s2 = (NameValuePair)s.next();
                s1.append(s2.getName()).append(":").append(s2.getValue());
                if(i < list.size())
                    s1.append(",");
                i++;
            }

            if(!TextUtils.isEmpty(s1))
                intent.putExtra(PushConstants.EXTRA_CLIENT_ATTR, s1.toString());
        }
        if(list1 != null)
        {
            s2 = new StringBuilder();
            int j = 1;
            for(s = list1.iterator(); s.hasNext();)
            {
                s1 = (NameValuePair)s.next();
                s2.append(s1.getName()).append(":").append(s1.getValue());
                if(j < list1.size())
                    s2.append(",");
                j++;
            }

            if(!TextUtils.isEmpty(s2))
                intent.putExtra(PushConstants.EXTRA_CLOUD_ATTR, s2.toString());
        }
        mContext.startService(intent);
        return 0;
    }

    public void resetConnection()
    {
        if(!serviceInstalled())
        {
            return;
        } else
        {
            Intent intent = createServiceIntent();
            intent.setAction(PushConstants.ACTION_RESET_CONNECTION);
            mContext.startService(intent);
            return;
        }
    }

    public boolean sendIQ(IQ iq)
    {
        if(!serviceInstalled() || hasNetwork() ^ true)
            return false;
        Intent intent = createServiceIntent();
        iq = iq.toBundle();
        if(iq != null)
        {
            intent.setAction(PushConstants.ACTION_SEND_IQ);
            intent.putExtra("ext_packet", iq);
            mContext.startService(intent);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean sendMessage(Message message)
    {
        if(!serviceInstalled() || hasNetwork() ^ true)
            return false;
        Intent intent = createServiceIntent();
        message = message.toBundle();
        if(message != null)
        {
            intent.setAction(PushConstants.ACTION_SEND_MESSAGE);
            intent.putExtra("ext_packet", message);
            mContext.startService(intent);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean sendPresence(Presence presence)
    {
        if(!serviceInstalled() || hasNetwork() ^ true)
            return false;
        Intent intent = createServiceIntent();
        presence = presence.toBundle();
        if(presence != null)
        {
            intent.setAction(PushConstants.ACTION_SEND_PRESENCE);
            intent.putExtra("ext_packet", presence);
            mContext.startService(intent);
            return true;
        } else
        {
            return false;
        }
    }

    private static ServiceClient sInstance;
    private Context mContext;
}
