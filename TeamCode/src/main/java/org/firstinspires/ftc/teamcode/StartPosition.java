package org.firstinspires.ftc.teamcode;

public enum StartPosition {
    FRONT_WALL,
    GOAL_SIDE;

    public static StartPosition toggle(boolean pressed, StartPosition current) {
        if (!pressed) return current;
        return (current == FRONT_WALL) ? GOAL_SIDE : FRONT_WALL;
    }
}
