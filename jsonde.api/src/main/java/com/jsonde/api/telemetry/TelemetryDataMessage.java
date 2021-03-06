package com.jsonde.api.telemetry;

import com.jsonde.api.Message;

/**
 * Commenti Javadoc
 * @author gabriele
 *
 */
public class TelemetryDataMessage extends Message {

    private TelemetryDataDto telemetryDataDto;

    public TelemetryDataMessage(TelemetryDataDto telemetryDataDto) {
        this.telemetryDataDto = telemetryDataDto;
    }

    public TelemetryDataDto getTelemetryData() {
        return telemetryDataDto;
    }

}
