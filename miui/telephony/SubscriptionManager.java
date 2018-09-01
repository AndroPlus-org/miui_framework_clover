// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.content.Intent;
import android.os.*;
import android.telephony.Rlog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package miui.telephony:
//            PhoneDebug, TelephonyManager, SubscriptionInfo, TelephonyUtils, 
//            SubscriptionManagerEx

public abstract class SubscriptionManager
{
    private static class Holder
    {

        static final SubscriptionManagerEx INSTANCE = SubscriptionManagerEx.getDefault();


        private Holder()
        {
        }
    }

    public static interface OnSubscriptionsChangedListener
    {

        public abstract void onSubscriptionsChanged();
    }


    static void _2D_wrap0(SubscriptionManager subscriptionmanager, boolean flag)
    {
        subscriptionmanager.ensureSubscriptionInfoCache(flag);
    }

    static void _2D_wrap1(SubscriptionManager subscriptionmanager)
    {
        subscriptionmanager.notifyOnSubscriptionsChangedListeners();
    }

    public SubscriptionManager()
    {
        mListeners = null;
        mLock = new Object();
        mInsertedSubscriptionInfos = null;
        mSubscriptionsCacheEnabled = false;
    }

    private void ensureSubscriptionInfoCache(boolean flag)
    {
        boolean flag1 = false;
        if(flag || mInsertedSubscriptionInfos == null)
        {
            mInsertedSubscriptionInfos = getSubscriptionInfoListInternal();
            if(mInsertedSubscriptionInfos == null)
                mInsertedSubscriptionInfos = new ArrayList();
            flag1 = true;
        }
        if(flag1 && PhoneDebug.VDBG)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("ensureSubscriptionInfoCache ");
            String s;
            if(flag)
                s = "";
            else
                s = "false";
            Rlog.i("SubMgr", stringbuilder.append(s).append(" insert=").append(Arrays.toString(mInsertedSubscriptionInfos.toArray())).toString());
        }
    }

    public static SubscriptionManager getDefault()
    {
        return Holder.INSTANCE;
    }

    public static int getPhoneId(Bundle bundle, int i)
    {
        return bundle.getInt(PHONE_KEY, i);
    }

    public static int getPhoneIdExtra(Intent intent, int i)
    {
        return intent.getIntExtra(PHONE_KEY, i);
    }

    public static int getSlotId(Bundle bundle, int i)
    {
        return bundle.getInt(SLOT_KEY, i);
    }

    public static int getSlotIdExtra(Intent intent, int i)
    {
        return intent.getIntExtra(SLOT_KEY, i);
    }

    public static int getSubscriptionId(Bundle bundle, int i)
    {
        return bundle.getInt(SUBSCRIPTION_KEY, i);
    }

    public static int getSubscriptionIdExtra(Intent intent, int i)
    {
        return intent.getIntExtra(SUBSCRIPTION_KEY, i);
    }

    public static boolean isRealSlotId(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i < TelephonyManager.getDefault().getPhoneCount())
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isValidPhoneId(int i)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((i < 0 || i >= TelephonyManager.getDefault().getPhoneCount()) && i != DEFAULT_PHONE_ID)
            flag = false;
        return flag;
    }

    public static boolean isValidSlotId(int i)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((i < 0 || i >= TelephonyManager.getDefault().getPhoneCount()) && i != DEFAULT_SLOT_ID)
            flag = false;
        return flag;
    }

    public static boolean isValidSubscriptionId(int i)
    {
        boolean flag;
        if(i > INVALID_SUBSCRIPTION_ID)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void notifyOnSubscriptionsChangedListeners()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mListeners != null)
        {
            if(PhoneDebug.VDBG)
            {
                StringBuilder stringbuilder = JVM INSTR new #128 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Rlog.i("SubMgr", stringbuilder.append("notify OnSubscriptionsChangedListener size=").append(mListeners.size()).toString());
            }
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((OnSubscriptionsChangedListener)iterator.next()).onSubscriptionsChanged());
        }
        break MISSING_BLOCK_LABEL_94;
        Exception exception;
        exception;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }

    public static void putPhoneId(Bundle bundle, int i)
    {
        int j = getDefault().getSlotIdForPhone(i);
        putSlotIdPhoneIdAndSubId(bundle, j, i, getDefault().getSubscriptionIdForSlot(j));
    }

    public static void putPhoneIdExtra(Intent intent, int i)
    {
        int j = getDefault().getSlotIdForPhone(i);
        putSlotIdPhoneIdAndSubIdExtra(intent, j, i, getDefault().getSubscriptionIdForSlot(j));
    }

    public static void putSlotId(Bundle bundle, int i)
    {
        putSlotIdPhoneIdAndSubId(bundle, i, getDefault().getPhoneIdForSlot(i), getDefault().getSubscriptionIdForSlot(i));
    }

    public static void putSlotIdExtra(Intent intent, int i)
    {
        putSlotIdPhoneIdAndSubIdExtra(intent, i, getDefault().getPhoneIdForSlot(i), getDefault().getSubscriptionIdForSlot(i));
    }

    public static void putSlotIdPhoneIdAndSubId(Bundle bundle, int i, int j, int k)
    {
        bundle.putInt(SUBSCRIPTION_KEY, k);
        bundle.putInt(PHONE_KEY, j);
        bundle.putInt(SLOT_KEY, i);
    }

    public static void putSlotIdPhoneIdAndSubIdExtra(Intent intent, int i, int j, int k)
    {
        intent.putExtra(SUBSCRIPTION_KEY, k);
        intent.putExtra(PHONE_KEY, j);
        intent.putExtra(SLOT_KEY, i);
    }

    public static void putSubscriptionId(Bundle bundle, int i)
    {
        putSlotIdPhoneIdAndSubId(bundle, getDefault().getSlotIdForSubscription(i), getDefault().getPhoneIdForSubscription(i), i);
    }

    public static void putSubscriptionIdExtra(Intent intent, int i)
    {
        putSlotIdPhoneIdAndSubIdExtra(intent, getDefault().getSlotIdForSubscription(i), getDefault().getPhoneIdForSubscription(i), i);
    }

    public static String toSimpleString(List list)
    {
        int i;
        if(list == null)
            i = 0;
        else
            i = list.size();
        if(i > 0)
        {
            SubscriptionInfo asubscriptioninfo[] = new SubscriptionInfo[i];
            list.toArray(asubscriptioninfo);
            StringBuilder stringbuilder = (new StringBuilder(i * 64)).append("[ size=").append(i);
            int j = asubscriptioninfo.length;
            i = 0;
            while(i < j) 
            {
                SubscriptionInfo subscriptioninfo = asubscriptioninfo[i];
                if(subscriptioninfo == null)
                {
                    Rlog.i("SubMgr", "toSimpleString SubscriptionInfo size was changed");
                } else
                {
                    StringBuilder stringbuilder1 = stringbuilder.append(" {id=").append(subscriptioninfo.getSubscriptionId()).append(" iccid=");
                    if(PhoneDebug.VDBG)
                        list = subscriptioninfo.getIccId();
                    else
                        list = TelephonyUtils.pii(subscriptioninfo.getIccId());
                    stringbuilder1.append(list).append(" slot=").append(subscriptioninfo.getSlotId()).append(" active=").append(subscriptioninfo.isActivated()).append('}');
                }
                i++;
            }
            stringbuilder.append(']');
            return stringbuilder.toString();
        } else
        {
            return "[]";
        }
    }

    public void addOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onsubscriptionschangedlistener)
    {
        if(PhoneDebug.VDBG)
            Rlog.i("SubMgr", (new StringBuilder()).append("addOnSubscriptionsChangedListener listener=").append(onsubscriptionschangedlistener.getClass().getName()).toString());
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mListeners == null)
        {
            ArrayList arraylist = JVM INSTR new #120 <Class ArrayList>;
            arraylist.ArrayList();
            mListeners = arraylist;
        }
        if(!mListeners.contains(onsubscriptionschangedlistener))
        {
            mListeners.add(onsubscriptionschangedlistener);
            addOnSubscriptionsChangedListenerInternal();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        onsubscriptionschangedlistener;
        throw onsubscriptionschangedlistener;
    }

    protected abstract void addOnSubscriptionsChangedListenerInternal();

    public void disableSubscriptionsCache()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mSubscriptionsCacheEnabled = false;
        mInsertedSubscriptionInfos = null;
        if(mListeners == null || mListeners.size() == 0)
            removeOnSubscriptionsChangedListenerInternal();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("SubscriptionManager:");
        filedescriptor = JVM INSTR new #128 <Class StringBuilder>;
        filedescriptor.StringBuilder(512);
        as = filedescriptor.append("mListeners=").append('[');
        if(mListeners != null)
        {
            OnSubscriptionsChangedListener onsubscriptionschangedlistener;
            for(filedescriptor = mListeners.iterator(); filedescriptor.hasNext(); as.append('{').append(onsubscriptionschangedlistener.getClass().getName()).append('}'))
                onsubscriptionschangedlistener = (OnSubscriptionsChangedListener)filedescriptor.next();

        }
          goto _L1
_L3:
        printwriter.flush();
        return;
_L1:
        try
        {
            as.append(']');
            printwriter.println(as.toString());
            filedescriptor = JVM INSTR new #128 <Class StringBuilder>;
            filedescriptor.StringBuilder();
            printwriter.println(filedescriptor.append("mInsertedSubscriptionInfos=").append(mInsertedSubscriptionInfos).toString());
        }
        // Misplaced declaration of an exception variable
        catch(FileDescriptor filedescriptor)
        {
            filedescriptor.printStackTrace();
        }
        if(true) goto _L3; else goto _L2
_L2:
    }

    public void enableSubscriptionsCache()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mSubscriptionsCacheEnabled = true;
        addOnSubscriptionsChangedListenerInternal();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public List getActiveSubscriptionInfoList()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = getSubscriptionInfoList().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            SubscriptionInfo subscriptioninfo = (SubscriptionInfo)iterator.next();
            if(subscriptioninfo.isActivated())
                arraylist.add(subscriptioninfo);
        } while(true);
        return arraylist;
    }

    public int getAllSubscriptionInfoCount()
    {
        long l = Binder.clearCallingIdentity();
        int i = getAllSubscriptionInfoList().size();
        Binder.restoreCallingIdentity(l);
        return i;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public List getAllSubscriptionInfoList()
    {
        return getAllSubscriptionInfoListInternal();
    }

    protected abstract List getAllSubscriptionInfoListInternal();

    public abstract int getDefaultDataSlotId();

    public abstract int getDefaultDataSubscriptionId();

    public abstract SubscriptionInfo getDefaultDataSubscriptionInfo();

    public int getDefaultSlotId()
    {
label0:
        {
            int i = INVALID_SUBSCRIPTION_ID;
            int j;
            if(TelephonyManager.getDefault().isVoiceCapable())
                j = getDefaultVoiceSubscriptionId();
            else
                j = getDefaultDataSubscriptionId();
            i = INVALID_SLOT_ID;
            if(isValidSubscriptionId(j))
                i = getSlotIdForSubscription(j);
            if(isValidSlotId(i))
            {
                j = i;
                if(i != DEFAULT_SLOT_ID)
                    break label0;
            }
            j = getDefaultSlotIdInternal();
        }
        return j;
    }

    protected abstract int getDefaultSlotIdInternal();

    public int getDefaultSmsSlotId()
    {
        return getSlotIdForSubscription(getDefaultSmsSubscriptionId());
    }

    public abstract int getDefaultSmsSubscriptionId();

    public abstract SubscriptionInfo getDefaultSmsSubscriptionInfo();

    public int getDefaultSubscriptionId()
    {
label0:
        {
            int i = INVALID_SUBSCRIPTION_ID;
            int j;
            if(TelephonyManager.getDefault().isVoiceCapable())
                i = getDefaultVoiceSubscriptionId();
            else
                i = getDefaultDataSubscriptionId();
            if(isValidSubscriptionId(i))
            {
                j = i;
                if(i != DEFAULT_SUBSCRIPTION_ID)
                    break label0;
            }
            j = getSubscriptionIdForSlot(getDefaultSlotIdInternal());
        }
        return j;
    }

    public SubscriptionInfo getDefaultSubscriptionInfo()
    {
        return getSubscriptionInfoForSubscription(getDefaultSubscriptionId());
    }

    public abstract int getDefaultVoiceSlotId();

    public abstract int getDefaultVoiceSubscriptionId();

    public abstract SubscriptionInfo getDefaultVoiceSubscriptionInfo();

    public int getPhoneIdForSlot(int i)
    {
        return i;
    }

    public int getPhoneIdForSubscription(int i)
    {
        if(!isValidSubscriptionId(i))
            return INVALID_PHONE_ID;
        int j;
        if(i == DEFAULT_SUBSCRIPTION_ID)
            i = DEFAULT_PHONE_ID;
        else
            i = getSlotId(i);
        j = i;
        if(!isValidPhoneId(i))
            j = INVALID_PHONE_ID;
        return j;
    }

    protected int getSlotId(int i)
    {
        long l = Binder.clearCallingIdentity();
        Iterator iterator = getSubscriptionInfoList().iterator();
        SubscriptionInfo subscriptioninfo;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_58;
            subscriptioninfo = (SubscriptionInfo)iterator.next();
        } while(subscriptioninfo.getSubscriptionId() != i);
        i = subscriptioninfo.getSlotId();
        Binder.restoreCallingIdentity(l);
        return i;
        Binder.restoreCallingIdentity(l);
        return INVALID_PHONE_ID;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public int getSlotIdForPhone(int i)
    {
        return i;
    }

    public int getSlotIdForSubscription(int i)
    {
        if(!isValidSubscriptionId(i))
            return INVALID_SLOT_ID;
        int j;
        if(i == DEFAULT_SUBSCRIPTION_ID)
            i = DEFAULT_SLOT_ID;
        else
            i = getSlotId(i);
        j = i;
        if(!isValidSlotId(i))
            j = INVALID_SLOT_ID;
        return j;
    }

    public int getSubscriptionIdForSlot(int i)
    {
        long l;
        if(!isValidSlotId(i))
            return INVALID_SUBSCRIPTION_ID;
        if(i == DEFAULT_SLOT_ID)
            return DEFAULT_SUBSCRIPTION_ID;
        l = Binder.clearCallingIdentity();
        Iterator iterator = getSubscriptionInfoList().iterator();
        SubscriptionInfo subscriptioninfo;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_80;
            subscriptioninfo = (SubscriptionInfo)iterator.next();
        } while(subscriptioninfo.getSlotId() != i);
        i = subscriptioninfo.getSubscriptionId();
        Binder.restoreCallingIdentity(l);
        return i;
        Binder.restoreCallingIdentity(l);
        return INVALID_SUBSCRIPTION_ID;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public int getSubscriptionInfoCount()
    {
        int i;
        long l;
        i = 0;
        l = Binder.clearCallingIdentity();
        Iterator iterator = getSubscriptionInfoList().iterator();
_L1:
        boolean flag;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_53;
        flag = ((SubscriptionInfo)iterator.next()).isActivated();
        if(flag)
            i++;
          goto _L1
        Binder.restoreCallingIdentity(l);
        return i;
        Exception exception;
        exception;
        Binder.restoreCallingIdentity(l);
        throw exception;
    }

    public SubscriptionInfo getSubscriptionInfoForSlot(int i)
    {
        if(!isValidSlotId(i))
            return null;
        int j = i;
        if(i == DEFAULT_SLOT_ID)
            j = getDefaultSlotId();
        for(Iterator iterator = getSubscriptionInfoList().iterator(); iterator.hasNext();)
        {
            SubscriptionInfo subscriptioninfo = (SubscriptionInfo)iterator.next();
            if(subscriptioninfo.getSlotId() == j)
                return subscriptioninfo;
        }

        return null;
    }

    public SubscriptionInfo getSubscriptionInfoForSubscription(int i)
    {
        if(!isValidSubscriptionId(i))
            return null;
        if(i == DEFAULT_SUBSCRIPTION_ID)
            return getSubscriptionInfoForSlot(getDefaultSlotId());
        for(Iterator iterator = getSubscriptionInfoList().iterator(); iterator.hasNext();)
        {
            SubscriptionInfo subscriptioninfo = (SubscriptionInfo)iterator.next();
            if(subscriptioninfo.getSubscriptionId() == i)
                return subscriptioninfo;
        }

        return null;
    }

    public List getSubscriptionInfoList()
    {
        if(mSubscriptionsCacheEnabled)
        {
            ensureSubscriptionInfoCache(false);
            return mInsertedSubscriptionInfos;
        }
        List list = getSubscriptionInfoListInternal();
        Object obj = list;
        if(list == null)
            obj = new ArrayList();
        return ((List) (obj));
    }

    protected abstract List getSubscriptionInfoListInternal();

    protected void onSubscriptionInfoChanged()
    {
        if(!mSubscriptionsCacheEnabled)
            notifyOnSubscriptionsChangedListeners();
        else
            (new AsyncTask() {

                protected volatile Object doInBackground(Object aobj[])
                {
                    return doInBackground((Void[])aobj);
                }

                protected transient Void doInBackground(Void avoid[])
                {
                    SubscriptionManager._2D_wrap0(SubscriptionManager.this, true);
                    return null;
                }

                protected volatile void onPostExecute(Object obj)
                {
                    onPostExecute((Void)obj);
                }

                protected void onPostExecute(Void void1)
                {
                    SubscriptionManager._2D_wrap1(SubscriptionManager.this);
                }

                final SubscriptionManager this$0;

            
            {
                this$0 = SubscriptionManager.this;
                super();
            }
            }
).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[] {
                (Void)null
            });
    }

    public void removeOnSubscriptionsChangedListener(OnSubscriptionsChangedListener onsubscriptionschangedlistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mListeners;
        if(arraylist != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        mListeners.remove(onsubscriptionschangedlistener);
        if(mListeners.size() == 0)
        {
            mListeners = null;
            if(!mSubscriptionsCacheEnabled)
                removeOnSubscriptionsChangedListenerInternal();
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        onsubscriptionschangedlistener;
        throw onsubscriptionschangedlistener;
    }

    protected abstract void removeOnSubscriptionsChangedListenerInternal();

    public abstract void setDefaultDataSlotId(int i);

    public void setDefaultDataSubscriptionId(int i)
    {
        setDefaultDataSlotId(getSlotIdForSubscription(i));
    }

    public void setDefaultSmsSlotId(int i)
    {
        int j = i;
        if(!isValidSlotId(i))
            j = INVALID_SLOT_ID;
        if(j == DEFAULT_SLOT_ID || j == getDefaultSmsSlotId())
        {
            return;
        } else
        {
            setDefaultSmsSubscriptionId(getSubscriptionIdForSlot(j));
            return;
        }
    }

    public abstract void setDefaultSmsSubscriptionId(int i);

    public abstract void setDefaultVoiceSlotId(int i);

    public void setDefaultVoiceSubscriptionId(int i)
    {
        setDefaultVoiceSlotId(getSlotIdForSubscription(i));
    }

    public static final int DEFAULT_PHONE_ID = SubscriptionManagerEx.ConstantsDefiner.getDefaultPhoneIdConstant();
    public static final int DEFAULT_SLOT_ID = SubscriptionManagerEx.ConstantsDefiner.getDefaultSlotIdConstant();
    public static final int DEFAULT_SUBSCRIPTION_ID = SubscriptionManagerEx.ConstantsDefiner.getDefaultSubscriptionIdConstant();
    public static final int INVALID_PHONE_ID = SubscriptionManagerEx.ConstantsDefiner.getInvalidPhoneIdConstant();
    public static final int INVALID_SLOT_ID = SubscriptionManagerEx.ConstantsDefiner.getInvalidSlotIdConstant();
    public static final int INVALID_SUBSCRIPTION_ID = SubscriptionManagerEx.ConstantsDefiner.getInvalidSubscriptionIdConstant();
    protected static final String LOG_TAG = "SubMgr";
    public static final String PHONE_KEY = SubscriptionManagerEx.ConstantsDefiner.getPhoneKeyConstant();
    public static final int SLOT_ID_1 = 0;
    public static final int SLOT_ID_2 = 1;
    public static final String SLOT_KEY = SubscriptionManagerEx.ConstantsDefiner.getSlotKeyConstant();
    public static final String SUBSCRIPTION_KEY = SubscriptionManagerEx.ConstantsDefiner.getSubscriptionKeyConstant();
    private List mInsertedSubscriptionInfos;
    private ArrayList mListeners;
    private Object mLock;
    private boolean mSubscriptionsCacheEnabled;

}
