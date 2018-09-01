// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location.gnssmetrics;

import android.os.SystemClock;
import android.util.Base64;
import android.util.TimeUtils;
import java.util.Arrays;

public class GnssMetrics
{
    private class Statistics
    {

        public void addItem(double d)
        {
            count = count + 1;
            sum = sum + d;
            sumSquare = sumSquare + d * d;
        }

        public int getCount()
        {
            return count;
        }

        public double getMean()
        {
            return sum / (double)count;
        }

        public double getStandardDeviation()
        {
            double d = sum / (double)count;
            d *= d;
            double d1 = sumSquare / (double)count;
            if(d1 > d)
                return Math.sqrt(d1 - d);
            else
                return 0.0D;
        }

        public void reset()
        {
            count = 0;
            sum = 0.0D;
            sumSquare = 0.0D;
        }

        private int count;
        private double sum;
        private double sumSquare;
        final GnssMetrics this$0;

        private Statistics()
        {
            this$0 = GnssMetrics.this;
            super();
        }

        Statistics(Statistics statistics)
        {
            this();
        }
    }


    public GnssMetrics()
    {
        locationFailureStatistics = new Statistics(null);
        timeToFirstFixSecStatistics = new Statistics(null);
        positionAccuracyMeterStatistics = new Statistics(null);
        topFourAverageCn0Statistics = new Statistics(null);
        reset();
    }

    private void reset()
    {
        StringBuilder stringbuilder = new StringBuilder();
        TimeUtils.formatDuration(SystemClock.elapsedRealtimeNanos() / 0xf4240L, stringbuilder);
        logStartInElapsedRealTime = stringbuilder.toString();
        locationFailureStatistics.reset();
        timeToFirstFixSecStatistics.reset();
        positionAccuracyMeterStatistics.reset();
        topFourAverageCn0Statistics.reset();
    }

    public String dumpGnssMetricsAsProtoString()
    {
        Object obj = new com.android.internal.location.nano.GnssLogsProto.GnssLog();
        if(locationFailureStatistics.getCount() > 0)
        {
            obj.numLocationReportProcessed = locationFailureStatistics.getCount();
            obj.percentageLocationFailure = (int)(locationFailureStatistics.getMean() * 100D);
        }
        if(timeToFirstFixSecStatistics.getCount() > 0)
        {
            obj.numTimeToFirstFixProcessed = timeToFirstFixSecStatistics.getCount();
            obj.meanTimeToFirstFixSecs = (int)timeToFirstFixSecStatistics.getMean();
            obj.standardDeviationTimeToFirstFixSecs = (int)timeToFirstFixSecStatistics.getStandardDeviation();
        }
        if(positionAccuracyMeterStatistics.getCount() > 0)
        {
            obj.numPositionAccuracyProcessed = positionAccuracyMeterStatistics.getCount();
            obj.meanPositionAccuracyMeters = (int)positionAccuracyMeterStatistics.getMean();
            obj.standardDeviationPositionAccuracyMeters = (int)positionAccuracyMeterStatistics.getStandardDeviation();
        }
        if(topFourAverageCn0Statistics.getCount() > 0)
        {
            obj.numTopFourAverageCn0Processed = topFourAverageCn0Statistics.getCount();
            obj.meanTopFourAverageCn0DbHz = topFourAverageCn0Statistics.getMean();
            obj.standardDeviationTopFourAverageCn0DbHz = topFourAverageCn0Statistics.getStandardDeviation();
        }
        obj = Base64.encodeToString(com.android.internal.location.nano.GnssLogsProto.GnssLog.toByteArray(((com.android.framework.protobuf.nano.MessageNano) (obj))), 0);
        reset();
        return ((String) (obj));
    }

    public String dumpGnssMetricsAsText()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("GNSS_KPI_START").append('\n');
        stringbuilder.append("  KPI logging start time: ").append(logStartInElapsedRealTime).append("\n");
        stringbuilder.append("  KPI logging end time: ");
        TimeUtils.formatDuration(SystemClock.elapsedRealtimeNanos() / 0xf4240L, stringbuilder);
        stringbuilder.append("\n");
        stringbuilder.append("  Number of location reports: ").append(locationFailureStatistics.getCount()).append("\n");
        if(locationFailureStatistics.getCount() > 0)
            stringbuilder.append("  Percentage location failure: ").append(locationFailureStatistics.getMean() * 100D).append("\n");
        stringbuilder.append("  Number of TTFF reports: ").append(timeToFirstFixSecStatistics.getCount()).append("\n");
        if(timeToFirstFixSecStatistics.getCount() > 0)
        {
            stringbuilder.append("  TTFF mean (sec): ").append(timeToFirstFixSecStatistics.getMean()).append("\n");
            stringbuilder.append("  TTFF standard deviation (sec): ").append(timeToFirstFixSecStatistics.getStandardDeviation()).append("\n");
        }
        stringbuilder.append("  Number of position accuracy reports: ").append(positionAccuracyMeterStatistics.getCount()).append("\n");
        if(positionAccuracyMeterStatistics.getCount() > 0)
        {
            stringbuilder.append("  Position accuracy mean (m): ").append(positionAccuracyMeterStatistics.getMean()).append("\n");
            stringbuilder.append("  Position accuracy standard deviation (m): ").append(positionAccuracyMeterStatistics.getStandardDeviation()).append("\n");
        }
        stringbuilder.append("  Number of CN0 reports: ").append(topFourAverageCn0Statistics.getCount()).append("\n");
        if(topFourAverageCn0Statistics.getCount() > 0)
        {
            stringbuilder.append("  Top 4 Avg CN0 mean (dB-Hz): ").append(topFourAverageCn0Statistics.getMean()).append("\n");
            stringbuilder.append("  Top 4 Avg CN0 standard deviation (dB-Hz): ").append(topFourAverageCn0Statistics.getStandardDeviation()).append("\n");
        }
        stringbuilder.append("GNSS_KPI_END").append("\n");
        return stringbuilder.toString();
    }

    public void logCn0(float af[], int i)
    {
        if(i < 4)
            return;
        af = Arrays.copyOf(af, i);
        Arrays.sort(af);
        if((double)af[i - 4] > 0.0D)
        {
            double d = 0.0D;
            for(int j = i - 4; j < i; j++)
                d += af[j];

            d /= 4D;
            topFourAverageCn0Statistics.addItem(d);
        }
    }

    public void logMissedReports(int i, int j)
    {
        j = j / Math.max(1000, i) - 1;
        if(j > 0)
            for(i = 0; i < j; i++)
                locationFailureStatistics.addItem(1.0D);

    }

    public void logPositionAccuracyMeters(float f)
    {
        positionAccuracyMeterStatistics.addItem(f);
    }

    public void logReceivedLocationStatus(boolean flag)
    {
        if(!flag)
        {
            locationFailureStatistics.addItem(1.0D);
            return;
        } else
        {
            locationFailureStatistics.addItem(0.0D);
            return;
        }
    }

    public void logTimeToFirstFixMilliSecs(int i)
    {
        timeToFirstFixSecStatistics.addItem(i / 1000);
    }

    private static final int DEFAULT_TIME_BETWEEN_FIXES_MILLISECS = 1000;
    private Statistics locationFailureStatistics;
    private String logStartInElapsedRealTime;
    private Statistics positionAccuracyMeterStatistics;
    private Statistics timeToFirstFixSecStatistics;
    private Statistics topFourAverageCn0Statistics;
}
