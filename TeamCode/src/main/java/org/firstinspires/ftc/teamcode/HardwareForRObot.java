package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import java.util.List;

/**
 * Instead of each Op Mode class redefining the robot's hardware resources within its implementation,
 * This RobotHardware class has a given robot's component resources defined and set up all in one place.
 * It also has convenience methods like driveRobot(), setArmPower(), setHandPosition(), etc.
 * that work for that robot.
 *
 * Where possible, the actual hardware objects are "abstracted" (or hidden) so the OpMode code just
 * makes calls into the class, rather than accessing the internal hardware directly.
 * This is why the objects are declared "private".
 */

public class HardwareForRObot {
    /* Declare OpMode members. */
    private OpMode myOpMode = null;   // gain access to methods in the calling OpMode.

    // Define all the HardwareDevices (Motors, Servos, etc.).
    // Make them private so they can't be accessed externally.
    private DcMotor leftFrontWheel;
    private DcMotor rightFrontWheel;
    private DcMotor leftRearWheel;
    private DcMotor rightRearWheel;

    private DcMotor pully1;
    private DistanceSensor leftSensor;
    private DistanceSensor rightSensor;




    private WebcamName webCam;

    // Define other HardwareDevices as needed.
    private DcMotor armMotor;
    private Servo   Drone;
    private Servo   rightHand;
    private TfodProcessor tfod;
    private VisionPortal visionPortal;

    // Define Drive constants.  Make them public so they CAN be used by the calling OpMode
    static final double COUNTS_PER_MOTOR_REV = 560;
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double DEFAULT_WHEEL_MOTOR_SPEED = .4;
    public static final double MID_SERVO       =  0.5 ;
    public static final double READY_LAUNCH_SERVO =  .8 ;
    public static final double LAUNCH_SERVO = .5;
    public static final double HAND_SPEED      =  0.02 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    // The one and only constructor requires a reference to an OpMode.
    public HardwareForRObot(OpMode opmode) {
        myOpMode = opmode;
    }

    /**
     * Call init() to initialize all the robot's hardware.
     */
    public void init() {
        initWheelMotors();
        initServos();
        initpullys();
        initsensor();
        //initTfod();
        //initVisionPortal();
        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    /**
     * Call shutDown() to stop and close all the robot's hardware.
     */
    public void shutDown() {
        //tfod.shutdown();
        //visionPortal.close();
    }

    /**
     * Initialize all the wheel motors.
     * This method must be called ONCE when the OpMode is initialized.
     *
     * All of the hardware devices are accessed via the hardware map, and initialized.
     */
    private void initWheelMotors()    {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        leftFrontWheel = myOpMode.hardwareMap.get(DcMotor.class, "LFront");
        rightFrontWheel = myOpMode.hardwareMap.get(DcMotor.class, "RFront");
        leftRearWheel = myOpMode.hardwareMap.get(DcMotor.class, "LBack");
        rightRearWheel = myOpMode.hardwareMap.get(DcMotor.class, "RBack");


        // To drive forward, most robots need the motors on one side to be reversed,
        // because the axles point in opposite directions.
        // Note: The settings here assume direct drive on left and right wheels.
        // Gear Reduction or 90 Deg drives may require direction flips
        leftFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        leftRearWheel.setDirection(DcMotor.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotor.Direction.FORWARD);
        rightRearWheel.setDirection(DcMotor.Direction.REVERSE);

        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        leftFrontWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set wheel motors to not resist turning when motor is stopped.
        leftFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }


    /**
     * Initialize all servos.
     */
    private void initServos() {
        // Define and initialize ALL installed servos.
        Drone = myOpMode.hardwareMap.get(Servo.class, "Drone");
        Drone.setPosition(READY_LAUNCH_SERVO);
    }

    private void initpullys() {
        pully1 = myOpMode.hardwareMap.get(DcMotor.class, "leftpully");



    }
private void initsensor() {
        leftSensor = myOpMode.hardwareMap.get(DistanceSensor.class, "LSensor");
        rightSensor = myOpMode.hardwareMap.get(DistanceSensor.class, "RSensor");


}

    /**
     * Initialize the TensorFlow Object Detection processor.
     */
    private void initTfod() {
        // Create the TensorFlow processor by using its Builder.
        tfod = new TfodProcessor.Builder()
                // Call various setters on the Builder.
                // Use setModelAssetName() if the TF Model is built in as an asset.
                // Use setModelFileName() if you have downloaded a custom team model
                //                        to the Robot Controller.
                //.setModelAssetName(TFOD_MODEL_ASSET)
                //.setModelFileName(TFOD_MODEL_FILE)
                //.setModelLabels(LABELS)
                //.setIsModelTensorFlow2(true)
                //.setIsModelQuantized(true)
                //.setModelInputSize(300)
                //.setModelAspectRatio(16.0 / 9.0)

                //Finally, call build() to return a TfodProcessor.
                .build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }

    /**
     * Initialize the VisionPortal. tfod is expected to be initialized before calling this method.
     */
    private void initVisionPortal() {
        if (tfod == null) {
            throw new RuntimeException("tfod must be initialized first");
        }

        // Create the VisionPortal by using is Builder.
        visionPortal = new VisionPortal.Builder()
                // Set the camera to the webcam hardware.
                .setCamera(myOpMode.hardwareMap.get(WebcamName.class, "Webcam 1"))
                // Choose a camera resolution. Not all cameras support all resolutions.
                //.setCameraResolution(new Size(640, 480))
                // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
                //.enableCameraMonitoring(true)
                // Set the stream format; MJPEG uses less bandwidth than default YUY2.
                //.setStreamFormat(VisionPortal.StreamFormat.YUY2)

                // Choose whether or not LiveView stops if no processors are enabled.
                // If set "true", monitor shows solid orange screen if no processors enabled.
                // If set "false", monitor shows camera view without annotations.
                //.setAutoStopLiveView(false)

                // Set and enable the processor.
                .addProcessor(tfod)
                // Build the Vision Portal, using the above settings.
                .build();
    }

/*
    public void manuallyDriveRobot() {
        double r = Math.hypot(myOpMode.gamepad1.left_stick_x, myOpMode.gamepad1.left_stick_y);
        double robotAngle = Math.atan2(myOpMode.gamepad1.left_stick_y, myOpMode.gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = myOpMode.gamepad1.right_stick_x * .5;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        rightFrontWheel.setPower(v1);
        leftFrontWheel.setPower(v2);
        rightRearWheel.setPower(v3);
        leftRearWheel.setPower(v4);

        if(myOpMode.gamepad1.y)
            Drone.setPosition(.5);
    }
*/
    /**
     * Mecanum drive
     */
    public void manuallyDriveRobot(double lStickX, double lStickY, double rStickX) {
        double vectorLength = Math.hypot(lStickX, lStickY);
        double robotAngle = Math.atan2(lStickY, lStickX) - Math.PI / 4;
        double rightXscale = rStickX * .5;
        final double rightFrontVelocity = vectorLength * Math.cos(robotAngle) + rightXscale;
        final double leftFrontVelocity = vectorLength * Math.sin(robotAngle) - rightXscale;
        final double rightRearVelocity = vectorLength * Math.sin(robotAngle) + rightXscale;
        final double leftRearVelocity = vectorLength * Math.cos(robotAngle) - rightXscale;
        // Use existing function to drive both wheels.
        setDrivePower(leftFrontVelocity, rightFrontVelocity, leftRearVelocity, rightRearVelocity);
    }
    /**
     * Pass the requested wheel motor powers to the appropriate hardware drive motors.
     */
    public void setDrivePower(double leftFrontPower, double rightFrontPower, double leftRearPower, double rightRearPower) {
        // Output the values to the motor drives.
        leftFrontWheel.setPower(leftFrontPower);
        rightFrontWheel.setPower(rightFrontPower);
        leftRearWheel.setPower(leftRearPower);
        rightRearWheel.setPower(rightRearPower);
    }
    public void autoDriveRobot(int leftInches, int rightInches, double speed) {
        int leftInchesToCPI = (int) (leftInches * COUNTS_PER_INCH);
        int rightInchesToCPI = (int) (rightInches * COUNTS_PER_INCH);

        int leftFrontTarget = leftFrontWheel.getCurrentPosition() + leftInchesToCPI;
        int leftRearTarget = leftFrontWheel.getCurrentPosition() + leftInchesToCPI;
        int rightFrontTarget = leftFrontWheel.getCurrentPosition() + rightInchesToCPI;
        int rightRearTarget = leftFrontWheel.getCurrentPosition() + rightInchesToCPI;

        leftFrontWheel.setTargetPosition(leftFrontTarget);
        leftRearWheel.setTargetPosition(leftRearTarget);
        rightFrontWheel.setTargetPosition(rightFrontTarget);
        rightRearWheel.setTargetPosition(rightRearTarget);

        setRunModeForAllWheels(DcMotor.RunMode.RUN_TO_POSITION);
        //Set up telemetry
        myOpMode.telemetry.setAutoClear(false);
        myOpMode.telemetry.addData("Heading", "Current Wheel Positions");

        Telemetry.Item LFrontItem = myOpMode.telemetry.addData("LF Wheel",
                leftFrontWheel.getCurrentPosition());

        Telemetry.Item leftRearWheelItem = myOpMode.telemetry.addData("LR Wheel",
                leftRearWheel.getCurrentPosition());

        Telemetry.Item RFrontItem = myOpMode.telemetry.addData("RF Wheel",
                rightFrontWheel.getCurrentPosition());

        Telemetry.Item rightRearWheelItem = myOpMode.telemetry.addData("RR Wheel",
                rightRearWheel.getCurrentPosition());

        myOpMode.telemetry.update();

        // Power all wheels for as long as they are busy.
        setPowerAllWheels(speed);

        while (leftFrontWheel.isBusy() && leftRearWheel.isBusy() && rightFrontWheel.isBusy()
                && rightRearWheel.isBusy()) {
            LFrontItem.setValue(leftFrontWheel.getCurrentPosition());
            leftRearWheelItem.setValue(leftRearWheel.getCurrentPosition());
            RFrontItem.setValue(rightFrontWheel.getCurrentPosition());
            rightRearWheelItem.setValue(rightRearWheel.getCurrentPosition());
            myOpMode.telemetry.update();

        }

        setPowerAllWheels(0); //Whoa.
      //  myOpMode.telemetry.setAutoClear(true);
    }
    public void parkRobot() {

    }

    /**
     * autoDriveRobot using DEFAULT_WHEEL_MOTOR_SPEED.
     * @param leftInches
     * @param rightInches
     */
    public void autoDriveRobot(int leftInches, int rightInches) {
        autoDriveRobot(leftInches, rightInches, DEFAULT_WHEEL_MOTOR_SPEED);
    }

    /**
     * Set the RunMode for all wheel motors to the passed runMode.
     * @param runMode
     */
    public void setRunModeForAllWheels(DcMotor.RunMode runMode) {
        leftFrontWheel.setMode(runMode);
        leftRearWheel.setMode(runMode);
        rightFrontWheel.setMode(runMode);
        rightRearWheel.setMode(runMode);
    }

    /**
     * Set the Power for all wheels to the passed speed.
     * @param speed
     */

    public void setPowerAllWheels(double speed) {
        double absoluteSpeed = Math.abs(speed);
        leftFrontWheel.setPower(absoluteSpeed);
        leftRearWheel.setPower(absoluteSpeed);
        rightFrontWheel.setPower(absoluteSpeed);
        rightRearWheel.setPower(absoluteSpeed);
    }


    public List<Recognition> getFreshTfodRecognitions() {
        return tfod.getFreshRecognitions();
    }

    /**
     * Pass the requested arm power to the appropriate hardware drive motor
     *
     * @param power driving power (-1.0 to 1.0)
     */

    public void setArmPower(double power) {
        armMotor.setPower(power);
    }

    /**
     * Send the two hand-servos to opposing (mirrored) positions, based on the passed offset.
     *
     * @param offset
     */
    public void setHandPositions(double offset) {
        offset = Range.clip(offset, -0.5, 0.5);
        rightHand.setPosition(MID_SERVO - offset);
    }
    public double findLocationLeft() {
        double distance = leftSensor.getDistance(DistanceUnit.CM);
        //myOpMode.telemetry.addData("distance left", "%.0f", distance);
        //myOpMode.telemetry.update();

        return distance;
    }
    public double findLocationRight() {
        double distance = rightSensor.getDistance(DistanceUnit.CM);
       // myOpMode.telemetry.addData("distance right", "%.0f", distance);
        //myOpMode.telemetry.update();
        return distance;
    }



    /**
     * Move Drone servo so that drone is released.
     */
    public void releaseDrone() {
        Drone.setPosition(LAUNCH_SERVO);
    }
    public void raisePully() {
        pully1.setPower(.7);
    }
    public void lowerPully() {
        pully1.setPower(-.7);
    }
    public void stoppully() {pully1.setPower(0);}
   public void hangrobot() {pully1.setPower(-.7);}

}