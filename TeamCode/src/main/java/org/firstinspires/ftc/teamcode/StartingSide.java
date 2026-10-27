package org.firstinspires.ftc.teamcode;

public enum StartingSide {
    HIVEUP("hiveup"),
    HIVEDOWN("hivedown"),

    INSTANCE("instance");

    private final String name;

    StartingSide(String name) {
        this.name = name;
    }

    public StartingSide toggleSide(boolean buttonPressed, StartingSide startingSide) {
        if (buttonPressed) {
            if (startingSide == HIVEUP) {
                startingSide = HIVEDOWN;
            }
            else {
                startingSide = HIVEUP;
            }
        }

        return startingSide;
    }

}
