// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.DualSim;

import android.content.Context;
import java.util.*;
import miui.telephony.SubscriptionInfo;
import miui.telephony.SubscriptionManager;

public class DualSimInfoManagerWrapper
{
    public static interface ISimInfoChangeWrapperListener
    {

        public abstract void onSubscriptionsChanged();
    }

    private static class SimInfoChangeImpl
        implements miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener
    {

        public void onSubscriptionsChanged()
        {
            DualSimInfoManagerWrapper._2D_wrap0();
        }

        private SimInfoChangeImpl()
        {
        }

        SimInfoChangeImpl(SimInfoChangeImpl siminfochangeimpl)
        {
            this();
        }
    }

    private static final class SubscriptionInfoComparable
        implements Comparator
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((SubscriptionInfo)obj, (SubscriptionInfo)obj1);
        }

        public int compare(SubscriptionInfo subscriptioninfo, SubscriptionInfo subscriptioninfo1)
        {
            return subscriptioninfo.getSlotId() - subscriptioninfo1.getSlotId();
        }

        private SubscriptionInfoComparable()
        {
        }

        SubscriptionInfoComparable(SubscriptionInfoComparable subscriptioninfocomparable)
        {
            this();
        }
    }


    static void _2D_wrap0()
    {
        broadcastSubscriptionsChanged();
    }

    private DualSimInfoManagerWrapper()
    {
    }

    private static void broadcastSubscriptionsChanged()
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((ISimInfoChangeWrapperListener)iterator.next()).onSubscriptionsChanged());
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
    }

    public static List getSimInfoList(Context context)
    {
        Object obj;
        Iterator iterator;
        SubscriptionInfo subscriptioninfo;
        try
        {
            obj = SubscriptionManager.getDefault().getSubscriptionInfoList();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context.printStackTrace();
            return null;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_20;
        if(((List) (obj)).size() != 0)
            break MISSING_BLOCK_LABEL_22;
        return null;
        if(((List) (obj)).size() > 0)
        {
            context = JVM INSTR new #12  <Class DualSimInfoManagerWrapper$SubscriptionInfoComparable>;
            context.SubscriptionInfoComparable(null);
            Collections.sort(((List) (obj)), context);
        }
        context = JVM INSTR new #27  <Class ArrayList>;
        context.ArrayList();
        iterator = ((Iterable) (obj)).iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            subscriptioninfo = (SubscriptionInfo)iterator.next();
            if(subscriptioninfo.isActivated())
            {
                obj = JVM INSTR new #93  <Class HashMap>;
                ((HashMap) (obj)).HashMap();
                ((Map) (obj)).put("simId", String.valueOf(subscriptioninfo.getSubscriptionId()));
                ((Map) (obj)).put("slotNum", String.valueOf(subscriptioninfo.getSlotId()));
                ((Map) (obj)).put("simName", subscriptioninfo.getDisplayName().toString());
                ((Map) (obj)).put("iccId", subscriptioninfo.getIccId());
                context.add(obj);
            }
        } while(true);
        return context;
    }

    private static void registerChangeListener(SimInfoChangeImpl siminfochangeimpl)
    {
        SubscriptionManager.getDefault().addOnSubscriptionsChangedListener(siminfochangeimpl);
    }

    public static void registerSimInfoChangeListener(Context context, ISimInfoChangeWrapperListener isiminfochangewrapperlistener)
    {
        context = mListeners;
        context;
        JVM INSTR monitorenter ;
        if(isiminfochangewrapperlistener == null)
            break MISSING_BLOCK_LABEL_36;
        if(mListeners.contains(isiminfochangewrapperlistener) ^ true)
        {
            mListeners.add(isiminfochangewrapperlistener);
            registerChangeListener(mSimInfoChangeImpl);
        }
        context;
        JVM INSTR monitorexit ;
        return;
        isiminfochangewrapperlistener;
        throw isiminfochangewrapperlistener;
    }

    private static void unRegisterChangeListener(SimInfoChangeImpl siminfochangeimpl)
    {
        SubscriptionManager.getDefault().removeOnSubscriptionsChangedListener(siminfochangeimpl);
    }

    public static void unRegisterSimInfoChangeListener(Context context, ISimInfoChangeWrapperListener isiminfochangewrapperlistener)
    {
        context = mListeners;
        context;
        JVM INSTR monitorenter ;
        if(isiminfochangewrapperlistener == null)
            break MISSING_BLOCK_LABEL_34;
        if(mListeners.contains(isiminfochangewrapperlistener))
        {
            mListeners.remove(isiminfochangewrapperlistener);
            unRegisterChangeListener(mSimInfoChangeImpl);
        }
        context;
        JVM INSTR monitorexit ;
        return;
        isiminfochangewrapperlistener;
        throw isiminfochangewrapperlistener;
    }

    private static ArrayList mListeners = new ArrayList();
    private static SimInfoChangeImpl mSimInfoChangeImpl = new SimInfoChangeImpl(null);

}
