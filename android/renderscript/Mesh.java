// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.renderscript;

import dalvik.system.CloseGuard;
import java.util.Vector;

// Referenced classes of package android.renderscript:
//            BaseObj, RenderScript, Allocation, Element, 
//            Type

public class Mesh extends BaseObj
{
    public static class AllocationBuilder
    {

        public AllocationBuilder addIndexSetAllocation(Allocation allocation, Primitive primitive)
        {
            Entry entry = new Entry();
            entry.a = allocation;
            entry.prim = primitive;
            mIndexTypes.addElement(entry);
            return this;
        }

        public AllocationBuilder addIndexSetType(Primitive primitive)
        {
            Entry entry = new Entry();
            entry.a = null;
            entry.prim = primitive;
            mIndexTypes.addElement(entry);
            return this;
        }

        public AllocationBuilder addVertexAllocation(Allocation allocation)
            throws IllegalStateException
        {
            if(mVertexTypeCount >= mVertexTypes.length)
            {
                throw new IllegalStateException("Max vertex types exceeded.");
            } else
            {
                mVertexTypes[mVertexTypeCount] = new Entry();
                mVertexTypes[mVertexTypeCount].a = allocation;
                mVertexTypeCount = mVertexTypeCount + 1;
                return this;
            }
        }

        public Mesh create()
        {
            mRS.validate();
            long al[] = new long[mVertexTypeCount];
            long al1[] = new long[mIndexTypes.size()];
            int ai[] = new int[mIndexTypes.size()];
            Allocation aallocation[] = new Allocation[mIndexTypes.size()];
            Primitive aprimitive[] = new Primitive[mIndexTypes.size()];
            Allocation aallocation1[] = new Allocation[mVertexTypeCount];
            for(int i = 0; i < mVertexTypeCount; i++)
            {
                Entry entry = mVertexTypes[i];
                aallocation1[i] = entry.a;
                al[i] = entry.a.getID(mRS);
            }

            int j = 0;
            while(j < mIndexTypes.size()) 
            {
                Entry entry1 = (Entry)mIndexTypes.elementAt(j);
                long l;
                if(entry1.a == null)
                    l = 0L;
                else
                    l = entry1.a.getID(mRS);
                aallocation[j] = entry1.a;
                aprimitive[j] = entry1.prim;
                al1[j] = l;
                ai[j] = entry1.prim.mID;
                j++;
            }
            Mesh mesh = new Mesh(mRS.nMeshCreate(al, al1, ai), mRS);
            mesh.mVertexBuffers = aallocation1;
            mesh.mIndexBuffers = aallocation;
            mesh.mPrimitives = aprimitive;
            return mesh;
        }

        public int getCurrentIndexSetIndex()
        {
            return mIndexTypes.size() - 1;
        }

        public int getCurrentVertexTypeIndex()
        {
            return mVertexTypeCount - 1;
        }

        Vector mIndexTypes;
        RenderScript mRS;
        int mVertexTypeCount;
        Entry mVertexTypes[];

        public AllocationBuilder(RenderScript renderscript)
        {
            mRS = renderscript;
            mVertexTypeCount = 0;
            mVertexTypes = new Entry[16];
            mIndexTypes = new Vector();
        }
    }

    class AllocationBuilder.Entry
    {

        Allocation a;
        Primitive prim;
        final AllocationBuilder this$1;

        AllocationBuilder.Entry()
        {
            this$1 = AllocationBuilder.this;
            super();
        }
    }

    public static class Builder
    {

        public Builder addIndexSetType(Element element, int i, Primitive primitive)
        {
            Entry entry = new Entry();
            entry.t = null;
            entry.e = element;
            entry.size = i;
            entry.prim = primitive;
            mIndexTypes.addElement(entry);
            return this;
        }

        public Builder addIndexSetType(Primitive primitive)
        {
            Entry entry = new Entry();
            entry.t = null;
            entry.e = null;
            entry.size = 0;
            entry.prim = primitive;
            mIndexTypes.addElement(entry);
            return this;
        }

        public Builder addIndexSetType(Type type, Primitive primitive)
        {
            Entry entry = new Entry();
            entry.t = type;
            entry.e = null;
            entry.size = 0;
            entry.prim = primitive;
            mIndexTypes.addElement(entry);
            return this;
        }

        public Builder addVertexType(Element element, int i)
            throws IllegalStateException
        {
            if(mVertexTypeCount >= mVertexTypes.length)
            {
                throw new IllegalStateException("Max vertex types exceeded.");
            } else
            {
                mVertexTypes[mVertexTypeCount] = new Entry();
                mVertexTypes[mVertexTypeCount].t = null;
                mVertexTypes[mVertexTypeCount].e = element;
                mVertexTypes[mVertexTypeCount].size = i;
                mVertexTypeCount = mVertexTypeCount + 1;
                return this;
            }
        }

        public Builder addVertexType(Type type)
            throws IllegalStateException
        {
            if(mVertexTypeCount >= mVertexTypes.length)
            {
                throw new IllegalStateException("Max vertex types exceeded.");
            } else
            {
                mVertexTypes[mVertexTypeCount] = new Entry();
                mVertexTypes[mVertexTypeCount].t = type;
                mVertexTypes[mVertexTypeCount].e = null;
                mVertexTypeCount = mVertexTypeCount + 1;
                return this;
            }
        }

        public Mesh create()
        {
            mRS.validate();
            long al[] = new long[mVertexTypeCount];
            long al1[] = new long[mIndexTypes.size()];
            int ai[] = new int[mIndexTypes.size()];
            Allocation aallocation[] = new Allocation[mVertexTypeCount];
            Allocation aallocation1[] = new Allocation[mIndexTypes.size()];
            Primitive aprimitive[] = new Primitive[mIndexTypes.size()];
            int i = 0;
            while(i < mVertexTypeCount) 
            {
                Object obj = mVertexTypes[i];
                if(((Entry) (obj)).t != null)
                    obj = Allocation.createTyped(mRS, ((Entry) (obj)).t, mUsage);
                else
                if(((Entry) (obj)).e != null)
                    obj = Allocation.createSized(mRS, ((Entry) (obj)).e, ((Entry) (obj)).size, mUsage);
                else
                    throw new IllegalStateException("Builder corrupt, no valid element in entry.");
                aallocation[i] = ((Allocation) (obj));
                al[i] = ((Allocation) (obj)).getID(mRS);
                i++;
            }
            i = 0;
            while(i < mIndexTypes.size()) 
            {
                Entry entry = (Entry)mIndexTypes.elementAt(i);
                Allocation allocation;
                long l;
                if(entry.t != null)
                    allocation = Allocation.createTyped(mRS, entry.t, mUsage);
                else
                if(entry.e != null)
                    allocation = Allocation.createSized(mRS, entry.e, entry.size, mUsage);
                else
                    throw new IllegalStateException("Builder corrupt, no valid element in entry.");
                if(allocation == null)
                    l = 0L;
                else
                    l = allocation.getID(mRS);
                aallocation1[i] = allocation;
                aprimitive[i] = entry.prim;
                al1[i] = l;
                ai[i] = entry.prim.mID;
                i++;
            }
            Mesh mesh = new Mesh(mRS.nMeshCreate(al, al1, ai), mRS);
            mesh.mVertexBuffers = aallocation;
            mesh.mIndexBuffers = aallocation1;
            mesh.mPrimitives = aprimitive;
            return mesh;
        }

        public int getCurrentIndexSetIndex()
        {
            return mIndexTypes.size() - 1;
        }

        public int getCurrentVertexTypeIndex()
        {
            return mVertexTypeCount - 1;
        }

        Type newType(Element element, int i)
        {
            element = new Type.Builder(mRS, element);
            element.setX(i);
            return element.create();
        }

        Vector mIndexTypes;
        RenderScript mRS;
        int mUsage;
        int mVertexTypeCount;
        Entry mVertexTypes[];

        public Builder(RenderScript renderscript, int i)
        {
            mRS = renderscript;
            mUsage = i;
            mVertexTypeCount = 0;
            mVertexTypes = new Entry[16];
            mIndexTypes = new Vector();
        }
    }

    class Builder.Entry
    {

        Element e;
        Primitive prim;
        int size;
        Type t;
        final Builder this$1;
        int usage;

        Builder.Entry()
        {
            this$1 = Builder.this;
            super();
        }
    }

    public static final class Primitive extends Enum
    {

        public static Primitive valueOf(String s)
        {
            return (Primitive)Enum.valueOf(android/renderscript/Mesh$Primitive, s);
        }

        public static Primitive[] values()
        {
            return $VALUES;
        }

        private static final Primitive $VALUES[];
        public static final Primitive LINE;
        public static final Primitive LINE_STRIP;
        public static final Primitive POINT;
        public static final Primitive TRIANGLE;
        public static final Primitive TRIANGLE_FAN;
        public static final Primitive TRIANGLE_STRIP;
        int mID;

        static 
        {
            POINT = new Primitive("POINT", 0, 0);
            LINE = new Primitive("LINE", 1, 1);
            LINE_STRIP = new Primitive("LINE_STRIP", 2, 2);
            TRIANGLE = new Primitive("TRIANGLE", 3, 3);
            TRIANGLE_STRIP = new Primitive("TRIANGLE_STRIP", 4, 4);
            TRIANGLE_FAN = new Primitive("TRIANGLE_FAN", 5, 5);
            $VALUES = (new Primitive[] {
                POINT, LINE, LINE_STRIP, TRIANGLE, TRIANGLE_STRIP, TRIANGLE_FAN
            });
        }

        private Primitive(String s, int i, int j)
        {
            super(s, i);
            mID = j;
        }
    }

    public static class TriangleMeshBuilder
    {

        private void latch()
        {
            if((mFlags & 1) != 0)
            {
                makeSpace(4);
                float af[] = mVtxData;
                int i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = mR;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = mG;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = mB;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = mA;
            }
            if((mFlags & 0x100) != 0)
            {
                makeSpace(2);
                float af1[] = mVtxData;
                int j = mVtxCount;
                mVtxCount = j + 1;
                af1[j] = mS0;
                af1 = mVtxData;
                j = mVtxCount;
                mVtxCount = j + 1;
                af1[j] = mT0;
            }
            if((mFlags & 2) != 0)
            {
                makeSpace(4);
                float af2[] = mVtxData;
                int k = mVtxCount;
                mVtxCount = k + 1;
                af2[k] = mNX;
                af2 = mVtxData;
                k = mVtxCount;
                mVtxCount = k + 1;
                af2[k] = mNY;
                af2 = mVtxData;
                k = mVtxCount;
                mVtxCount = k + 1;
                af2[k] = mNZ;
                af2 = mVtxData;
                k = mVtxCount;
                mVtxCount = k + 1;
                af2[k] = 0.0F;
            }
            mMaxIndex = mMaxIndex + 1;
        }

        private void makeSpace(int i)
        {
            if(mVtxCount + i >= mVtxData.length)
            {
                float af[] = new float[mVtxData.length * 2];
                System.arraycopy(mVtxData, 0, af, 0, mVtxData.length);
                mVtxData = af;
            }
        }

        public TriangleMeshBuilder addTriangle(int i, int j, int k)
        {
            while(i >= mMaxIndex || i < 0 || j >= mMaxIndex || j < 0 || k >= mMaxIndex || k < 0) 
                throw new IllegalStateException("Index provided greater than vertex count.");
            if(mIndexCount + 3 >= mIndexData.length)
            {
                short aword0[] = new short[mIndexData.length * 2];
                System.arraycopy(mIndexData, 0, aword0, 0, mIndexData.length);
                mIndexData = aword0;
            }
            short aword1[] = mIndexData;
            int l = mIndexCount;
            mIndexCount = l + 1;
            aword1[l] = (short)i;
            aword1 = mIndexData;
            i = mIndexCount;
            mIndexCount = i + 1;
            aword1[i] = (short)j;
            aword1 = mIndexData;
            i = mIndexCount;
            mIndexCount = i + 1;
            aword1[i] = (short)k;
            return this;
        }

        public TriangleMeshBuilder addVertex(float f, float f1)
        {
            if(mVtxSize != 2)
            {
                throw new IllegalStateException("add mistmatch with declared components.");
            } else
            {
                makeSpace(2);
                float af[] = mVtxData;
                int i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = f;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = f1;
                latch();
                return this;
            }
        }

        public TriangleMeshBuilder addVertex(float f, float f1, float f2)
        {
            if(mVtxSize != 3)
            {
                throw new IllegalStateException("add mistmatch with declared components.");
            } else
            {
                makeSpace(4);
                float af[] = mVtxData;
                int i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = f;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = f1;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = f2;
                af = mVtxData;
                i = mVtxCount;
                mVtxCount = i + 1;
                af[i] = 1.0F;
                latch();
                return this;
            }
        }

        public Mesh create(boolean flag)
        {
            Object obj = new Element.Builder(mRS);
            ((Element.Builder) (obj)).add(Element.createVector(mRS, Element.DataType.FLOAT_32, mVtxSize), "position");
            if((mFlags & 1) != 0)
                ((Element.Builder) (obj)).add(Element.F32_4(mRS), "color");
            if((mFlags & 0x100) != 0)
                ((Element.Builder) (obj)).add(Element.F32_2(mRS), "texture0");
            if((mFlags & 2) != 0)
                ((Element.Builder) (obj)).add(Element.F32_3(mRS), "normal");
            mElement = ((Element.Builder) (obj)).create();
            byte byte0 = 1;
            if(flag)
                byte0 = 5;
            obj = new Builder(mRS, byte0);
            ((Builder) (obj)).addVertexType(mElement, mMaxIndex);
            ((Builder) (obj)).addIndexSetType(Element.U16(mRS), mIndexCount, Primitive.TRIANGLE);
            obj = ((Builder) (obj)).create();
            ((Mesh) (obj)).getVertexAllocation(0).copy1DRangeFromUnchecked(0, mMaxIndex, mVtxData);
            if(flag)
                ((Mesh) (obj)).getVertexAllocation(0).syncAll(1);
            ((Mesh) (obj)).getIndexSetAllocation(0).copy1DRangeFromUnchecked(0, mIndexCount, mIndexData);
            if(flag)
                ((Mesh) (obj)).getIndexSetAllocation(0).syncAll(1);
            return ((Mesh) (obj));
        }

        public TriangleMeshBuilder setColor(float f, float f1, float f2, float f3)
        {
            if((mFlags & 1) == 0)
            {
                throw new IllegalStateException("add mistmatch with declared components.");
            } else
            {
                mR = f;
                mG = f1;
                mB = f2;
                mA = f3;
                return this;
            }
        }

        public TriangleMeshBuilder setNormal(float f, float f1, float f2)
        {
            if((mFlags & 2) == 0)
            {
                throw new IllegalStateException("add mistmatch with declared components.");
            } else
            {
                mNX = f;
                mNY = f1;
                mNZ = f2;
                return this;
            }
        }

        public TriangleMeshBuilder setTexture(float f, float f1)
        {
            if((mFlags & 0x100) == 0)
            {
                throw new IllegalStateException("add mistmatch with declared components.");
            } else
            {
                mS0 = f;
                mT0 = f1;
                return this;
            }
        }

        public static final int COLOR = 1;
        public static final int NORMAL = 2;
        public static final int TEXTURE_0 = 256;
        float mA;
        float mB;
        Element mElement;
        int mFlags;
        float mG;
        int mIndexCount;
        short mIndexData[];
        int mMaxIndex;
        float mNX;
        float mNY;
        float mNZ;
        float mR;
        RenderScript mRS;
        float mS0;
        float mT0;
        int mVtxCount;
        float mVtxData[];
        int mVtxSize;

        public TriangleMeshBuilder(RenderScript renderscript, int i, int j)
        {
            mNX = 0.0F;
            mNY = 0.0F;
            mNZ = -1F;
            mS0 = 0.0F;
            mT0 = 0.0F;
            mR = 1.0F;
            mG = 1.0F;
            mB = 1.0F;
            mA = 1.0F;
            mRS = renderscript;
            mVtxCount = 0;
            mMaxIndex = 0;
            mIndexCount = 0;
            mVtxData = new float[128];
            mIndexData = new short[128];
            mVtxSize = i;
            mFlags = j;
            if(i < 2 || i > 3)
                throw new IllegalArgumentException("Vertex size out of range.");
            else
                return;
        }
    }


    Mesh(long l, RenderScript renderscript)
    {
        super(l, renderscript);
        guard.open("destroy");
    }

    public Allocation getIndexSetAllocation(int i)
    {
        return mIndexBuffers[i];
    }

    public Primitive getPrimitive(int i)
    {
        return mPrimitives[i];
    }

    public int getPrimitiveCount()
    {
        if(mIndexBuffers == null)
            return 0;
        else
            return mIndexBuffers.length;
    }

    public Allocation getVertexAllocation(int i)
    {
        return mVertexBuffers[i];
    }

    public int getVertexAllocationCount()
    {
        if(mVertexBuffers == null)
            return 0;
        else
            return mVertexBuffers.length;
    }

    void updateFromNative()
    {
        super.updateFromNative();
        int i = mRS.nMeshGetVertexBufferCount(getID(mRS));
        int j = mRS.nMeshGetIndexCount(getID(mRS));
        long al[] = new long[i];
        long al1[] = new long[j];
        int ai[] = new int[j];
        mRS.nMeshGetVertices(getID(mRS), al, i);
        mRS.nMeshGetIndices(getID(mRS), al1, ai, j);
        mVertexBuffers = new Allocation[i];
        mIndexBuffers = new Allocation[j];
        mPrimitives = new Primitive[j];
        for(int k = 0; k < i; k++)
            if(al[k] != 0L)
            {
                mVertexBuffers[k] = new Allocation(al[k], mRS, null, 1);
                mVertexBuffers[k].updateFromNative();
            }

        for(int l = 0; l < j; l++)
        {
            if(al1[l] != 0L)
            {
                mIndexBuffers[l] = new Allocation(al1[l], mRS, null, 1);
                mIndexBuffers[l].updateFromNative();
            }
            mPrimitives[l] = Primitive.values()[ai[l]];
        }

    }

    Allocation mIndexBuffers[];
    Primitive mPrimitives[];
    Allocation mVertexBuffers[];
}
