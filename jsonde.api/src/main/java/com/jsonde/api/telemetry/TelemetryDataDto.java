package com.jsonde.api.telemetry;

import java.io.Serializable;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class TelemetryDataDto implements Serializable {

    private static final long serialVersionUID = -4872627251132927940L;

    /**
     * Variabile time
     */
    public long time;
    
    /**
     * freeMemory
     */
    public long freeMemory;

    /**
     * maxMemory
     */
    public long maxMemory;
    public long totalMemory;

    public int loadedClassCount;
    public long classCount;
    public long unloadedClassCount;

    public long totalCompilationTime;

}