// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.util.HashMap;
import java.util.Map;

public final class MapFactories
{
    private static class DefaultMapFactory
        implements MapFactory
    {

        public Map forMap(Map map)
        {
            if(map == null)
                return new HashMap();
            else
                return map;
        }

        private DefaultMapFactory()
        {
        }

        DefaultMapFactory(DefaultMapFactory defaultmapfactory)
        {
            this();
        }
    }

    public static interface MapFactory
    {

        public abstract Map forMap(Map map);
    }


    private MapFactories()
    {
    }

    public static MapFactory getMapFactory()
    {
        return mapFactory;
    }

    static void setMapFactory(MapFactory mapfactory)
    {
        mapFactory = mapfactory;
    }

    private static volatile MapFactory mapFactory = new DefaultMapFactory(null);

}
