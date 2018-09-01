// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.SparseArray;
import dalvik.system.CloseGuard;
import java.io.UnsupportedEncodingException;

// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, Allocation, Type, 
//            RSIllegalArgumentException, RSDriverException, FieldPacker, Element

public class Script extends BaseObj
{
    public static class Builder
    {

        RenderScript mRS;

        Builder(RenderScript renderscript)
        {
            mRS = renderscript;
        }
    }

    public static class FieldBase
    {

        public Allocation getAllocation()
        {
            return mAllocation;
        }

        public Element getElement()
        {
            return mElement;
        }

        public Type getType()
        {
            return mAllocation.getType();
        }

        protected void init(RenderScript renderscript, int i)
        {
            mAllocation = Allocation.createSized(renderscript, mElement, i, 1);
        }

        protected void init(RenderScript renderscript, int i, int j)
        {
            mAllocation = Allocation.createSized(renderscript, mElement, i, j | 1);
        }

        public void updateAllocation()
        {
        }

        protected Allocation mAllocation;
        protected Element mElement;

        protected FieldBase()
        {
        }
    }

    public static final class FieldID extends BaseObj
    {

        Script mScript;
        int mSlot;

        FieldID(long l, RenderScript renderscript, Script script, int i)
        {
            super(l, renderscript);
            mScript = script;
            mSlot = i;
        }
    }

    public static final class InvokeID extends BaseObj
    {

        Script mScript;
        int mSlot;

        InvokeID(long l, RenderScript renderscript, Script script, int i)
        {
            super(l, renderscript);
            mScript = script;
            mSlot = i;
        }
    }

    public static final class KernelID extends BaseObj
    {

        Script mScript;
        int mSig;
        int mSlot;

        KernelID(long l, RenderScript renderscript, Script script, int i, int j)
        {
            super(l, renderscript);
            mScript = script;
            mSlot = i;
            mSig = j;
        }
    }

    public static final class LaunchOptions
    {

        static int _2D_get0(LaunchOptions launchoptions)
        {
            return launchoptions.xend;
        }

        static int _2D_get1(LaunchOptions launchoptions)
        {
            return launchoptions.xstart;
        }

        static int _2D_get2(LaunchOptions launchoptions)
        {
            return launchoptions.yend;
        }

        static int _2D_get3(LaunchOptions launchoptions)
        {
            return launchoptions.ystart;
        }

        static int _2D_get4(LaunchOptions launchoptions)
        {
            return launchoptions.zend;
        }

        static int _2D_get5(LaunchOptions launchoptions)
        {
            return launchoptions.zstart;
        }

        public int getXEnd()
        {
            return xend;
        }

        public int getXStart()
        {
            return xstart;
        }

        public int getYEnd()
        {
            return yend;
        }

        public int getYStart()
        {
            return ystart;
        }

        public int getZEnd()
        {
            return zend;
        }

        public int getZStart()
        {
            return zstart;
        }

        public LaunchOptions setX(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                xstart = i;
                xend = j;
                return this;
            }
        }

        public LaunchOptions setY(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                ystart = i;
                yend = j;
                return this;
            }
        }

        public LaunchOptions setZ(int i, int j)
        {
            if(i < 0 || j <= i)
            {
                throw new RSIllegalArgumentException("Invalid dimensions");
            } else
            {
                zstart = i;
                zend = j;
                return this;
            }
        }

        private int strategy;
        private int xend;
        private int xstart;
        private int yend;
        private int ystart;
        private int zend;
        private int zstart;

        public LaunchOptions()
        {
            xstart = 0;
            ystart = 0;
            xend = 0;
            yend = 0;
            zstart = 0;
            zend = 0;
        }
    }


    Script(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        mInIdsBuffer = new long[1];
        guard.open("destroy");
    }

    public void bindAllocation(Allocation allocation, int i)
    {
        mRS.validate();
        mRS.validateObject(allocation);
        if(allocation != null)
        {
            if(mRS.getApplicationContext().getApplicationInfo().targetSdkVersion >= 20)
            {
                for(Type type = allocation.mType; type.hasMipmaps() || type.hasFaces() || type.getY() != 0 || type.getZ() != 0;)
                    throw new RSIllegalArgumentException("API 20+ only allows simple 1D allocations to be used with bind.");

            }
            mRS.nScriptBindAllocation(getID(mRS), allocation.getID(mRS), i);
        } else
        {
            mRS.nScriptBindAllocation(getID(mRS), 0L, i);
        }
    }

    protected FieldID createFieldID(int i, Element element)
    {
        element = (FieldID)mFIDs.get(i);
        if(element != null)
            return element;
        long l = mRS.nScriptFieldIDCreate(getID(mRS), i);
        if(l == 0L)
        {
            throw new RSDriverException("Failed to create FieldID");
        } else
        {
            element = new FieldID(l, mRS, this, i);
            mFIDs.put(i, element);
            return element;
        }
    }

    protected InvokeID createInvokeID(int i)
    {
        InvokeID invokeid = (InvokeID)mIIDs.get(i);
        if(invokeid != null)
            return invokeid;
        long l = mRS.nScriptInvokeIDCreate(getID(mRS), i);
        if(l == 0L)
        {
            throw new RSDriverException("Failed to create KernelID");
        } else
        {
            InvokeID invokeid1 = new InvokeID(l, mRS, this, i);
            mIIDs.put(i, invokeid1);
            return invokeid1;
        }
    }

    protected KernelID createKernelID(int i, int j, Element element, Element element1)
    {
        element = (KernelID)mKIDs.get(i);
        if(element != null)
            return element;
        long l = mRS.nScriptKernelIDCreate(getID(mRS), i, j);
        if(l == 0L)
        {
            throw new RSDriverException("Failed to create KernelID");
        } else
        {
            element = new KernelID(l, mRS, this, i, j);
            mKIDs.put(i, element);
            return element;
        }
    }

    protected void forEach(int i, Allocation allocation, Allocation allocation1, FieldPacker fieldpacker)
    {
        forEach(i, allocation, allocation1, fieldpacker, null);
    }

    protected void forEach(int i, Allocation allocation, Allocation allocation1, FieldPacker fieldpacker, LaunchOptions launchoptions)
    {
        mRS.validate();
        mRS.validateObject(allocation);
        mRS.validateObject(allocation1);
        if(allocation == null && allocation1 == null && launchoptions == null)
            throw new RSIllegalArgumentException("At least one of input allocation, output allocation, or LaunchOptions is required to be non-null.");
        long al[] = null;
        if(allocation != null)
        {
            al = mInIdsBuffer;
            al[0] = allocation.getID(mRS);
        }
        long l = 0L;
        if(allocation1 != null)
            l = allocation1.getID(mRS);
        allocation = null;
        if(fieldpacker != null)
            allocation = fieldpacker.getData();
        allocation1 = null;
        if(launchoptions != null)
        {
            allocation1 = new int[6];
            allocation1[0] = LaunchOptions._2D_get1(launchoptions);
            allocation1[1] = LaunchOptions._2D_get0(launchoptions);
            allocation1[2] = LaunchOptions._2D_get3(launchoptions);
            allocation1[3] = LaunchOptions._2D_get2(launchoptions);
            allocation1[4] = LaunchOptions._2D_get5(launchoptions);
            allocation1[5] = LaunchOptions._2D_get4(launchoptions);
        }
        mRS.nScriptForEach(getID(mRS), i, al, l, allocation, allocation1);
    }

    protected void forEach(int i, Allocation aallocation[], Allocation allocation, FieldPacker fieldpacker)
    {
        forEach(i, aallocation, allocation, fieldpacker, null);
    }

    protected void forEach(int i, Allocation aallocation[], Allocation allocation, FieldPacker fieldpacker, LaunchOptions launchoptions)
    {
        mRS.validate();
        if(aallocation != null)
        {
            int j = 0;
            for(int l = aallocation.length; j < l; j++)
            {
                Allocation allocation1 = aallocation[j];
                mRS.validateObject(allocation1);
            }

        }
        mRS.validateObject(allocation);
        if(aallocation == null && allocation == null)
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        long al[];
        if(aallocation != null)
        {
            long al1[] = new long[aallocation.length];
            int k = 0;
            do
            {
                al = al1;
                if(k >= aallocation.length)
                    break;
                al1[k] = aallocation[k].getID(mRS);
                k++;
            } while(true);
        } else
        {
            al = null;
        }
        long l1 = 0L;
        if(allocation != null)
            l1 = allocation.getID(mRS);
        aallocation = null;
        if(fieldpacker != null)
            aallocation = fieldpacker.getData();
        allocation = null;
        if(launchoptions != null)
        {
            allocation = new int[6];
            allocation[0] = LaunchOptions._2D_get1(launchoptions);
            allocation[1] = LaunchOptions._2D_get0(launchoptions);
            allocation[2] = LaunchOptions._2D_get3(launchoptions);
            allocation[3] = LaunchOptions._2D_get2(launchoptions);
            allocation[4] = LaunchOptions._2D_get5(launchoptions);
            allocation[5] = LaunchOptions._2D_get4(launchoptions);
        }
        mRS.nScriptForEach(getID(mRS), i, al, l1, aallocation, allocation);
    }

    public boolean getVarB(int i)
    {
        boolean flag = false;
        if(mRS.nScriptGetVarI(getID(mRS), i) > 0)
            flag = true;
        return flag;
    }

    public double getVarD(int i)
    {
        return mRS.nScriptGetVarD(getID(mRS), i);
    }

    public float getVarF(int i)
    {
        return mRS.nScriptGetVarF(getID(mRS), i);
    }

    public int getVarI(int i)
    {
        return mRS.nScriptGetVarI(getID(mRS), i);
    }

    public long getVarJ(int i)
    {
        return mRS.nScriptGetVarJ(getID(mRS), i);
    }

    public void getVarV(int i, FieldPacker fieldpacker)
    {
        mRS.nScriptGetVarV(getID(mRS), i, fieldpacker.getData());
    }

    protected void invoke(int i)
    {
        mRS.nScriptInvoke(getID(mRS), i);
    }

    protected void invoke(int i, FieldPacker fieldpacker)
    {
        if(fieldpacker != null)
            mRS.nScriptInvokeV(getID(mRS), i, fieldpacker.getData());
        else
            mRS.nScriptInvoke(getID(mRS), i);
    }

    protected void reduce(int i, Allocation aallocation[], Allocation allocation, LaunchOptions launchoptions)
    {
        mRS.validate();
        if(aallocation == null || aallocation.length < 1)
            throw new RSIllegalArgumentException("At least one input is required.");
        if(allocation == null)
            throw new RSIllegalArgumentException("aout is required to be non-null.");
        int j = 0;
        for(int l = aallocation.length; j < l; j++)
        {
            Allocation allocation1 = aallocation[j];
            mRS.validateObject(allocation1);
        }

        long al[] = new long[aallocation.length];
        for(int k = 0; k < aallocation.length; k++)
            al[k] = aallocation[k].getID(mRS);

        long l1 = allocation.getID(mRS);
        aallocation = null;
        if(launchoptions != null)
        {
            aallocation = new int[6];
            aallocation[0] = LaunchOptions._2D_get1(launchoptions);
            aallocation[1] = LaunchOptions._2D_get0(launchoptions);
            aallocation[2] = LaunchOptions._2D_get3(launchoptions);
            aallocation[3] = LaunchOptions._2D_get2(launchoptions);
            aallocation[4] = LaunchOptions._2D_get5(launchoptions);
            aallocation[5] = LaunchOptions._2D_get4(launchoptions);
        }
        mRS.nScriptReduce(getID(mRS), i, al, l1, aallocation);
    }

    public void setTimeZone(String s)
    {
        mRS.validate();
        try
        {
            mRS.nScriptSetTimeZone(getID(mRS), s.getBytes("UTF-8"));
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
    }

    public void setVar(int i, double d)
    {
        mRS.nScriptSetVarD(getID(mRS), i, d);
    }

    public void setVar(int i, float f)
    {
        mRS.nScriptSetVarF(getID(mRS), i, f);
    }

    public void setVar(int i, int j)
    {
        mRS.nScriptSetVarI(getID(mRS), i, j);
    }

    public void setVar(int i, long l)
    {
        mRS.nScriptSetVarJ(getID(mRS), i, l);
    }

    public void setVar(int i, BaseObj baseobj)
    {
        mRS.validate();
        mRS.validateObject(baseobj);
        RenderScript renderscript = mRS;
        long l = getID(mRS);
        long l1;
        if(baseobj == null)
            l1 = 0L;
        else
            l1 = baseobj.getID(mRS);
        renderscript.nScriptSetVarObj(l, i, l1);
    }

    public void setVar(int i, FieldPacker fieldpacker)
    {
        mRS.nScriptSetVarV(getID(mRS), i, fieldpacker.getData());
    }

    public void setVar(int i, FieldPacker fieldpacker, Element element, int ai[])
    {
        mRS.nScriptSetVarVE(getID(mRS), i, fieldpacker.getData(), element.getID(mRS), ai);
    }

    public void setVar(int i, boolean flag)
    {
        RenderScript renderscript = mRS;
        long l = getID(mRS);
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        renderscript.nScriptSetVarI(l, i, j);
    }

    private final SparseArray mFIDs = new SparseArray();
    private final SparseArray mIIDs = new SparseArray();
    long mInIdsBuffer[];
    private final SparseArray mKIDs = new SparseArray();
}
