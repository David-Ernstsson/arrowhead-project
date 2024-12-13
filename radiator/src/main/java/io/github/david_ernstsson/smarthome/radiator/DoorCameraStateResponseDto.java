package io.github.david_ernstsson.smarthome.radiator;

import java.io.Serializable;

public class DoorCameraStateResponseDto implements Serializable{
    private static final long serialVersionUID = -8371510478751740542L;
    private boolean homeownerIsHome = false;

    public DoorCameraStateResponseDto() {}

    //-------------------------------------------------------------------------------------------------
    public DoorCameraStateResponseDto(boolean homeownerIsHome) {
        this.homeownerIsHome = homeownerIsHome;
    }

    public boolean getHomeownerIsHome() { return homeownerIsHome; }
}
