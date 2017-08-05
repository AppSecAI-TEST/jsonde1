package com.jsonde.api.telemetry;

import java.io.Serializable;

public class TelemetryDataDto implements Serializable {

    private static final long serialVersionUID = -4872627251132927940L;

    /**
     * Variabile time
     */
    public long time;
    public long freeMemory;
    public long maxMemory;
    public long totalMemory;

    public int loadedClassCount;
    public long classCount;
    public long unloadedClassCount;

    public long totalCompilationTime;

}