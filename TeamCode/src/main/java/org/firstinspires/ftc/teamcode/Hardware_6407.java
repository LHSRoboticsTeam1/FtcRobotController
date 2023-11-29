package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class Hardware_6407 {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor LFront = null;
    public DcMotor LBack = null;
    public DcMotor RFront = null;
    public DcMotor RBack = null;
    public Servo Drone = null;




    public WebcamName WebCam6407 = null;
    //public DcMotor IntakeLeft = null;
    //public DcMotor IntakeRight = null;
    public BNO055IMU imu    =null;
    //  public DcMotor TuckerCMotor       = null;


    //public DcMotor IntakeSpinner = null;
    //public DcMotor IntakeArm = null;
    //   public DcMotor ArmJoint       = null;
    //   public DcMotor ArmBottom       = null;
    //   public DcMotor ArmTop       = null;



    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    private TFObjectDetector tfod;

    /* Constructor */
    public Hardware_6407(){
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        LFront  = hwMap.get(DcMotor.class, "LFront");
        LBack  = hwMap.get(DcMotor.class, "LBack");
        RFront = hwMap.get(DcMotor.class, "RFront");
        RBack = hwMap.get(DcMotor.class, "RBack");
        Drone = hwMap.get(Servo.class, "Drone");


        // Set drone  to starting position power
        Drone.setPosition(.3);










        //Define and initialize ALL installed sensors
        BNO055IMU.Parameters parameters             = new BNO055IMU.Parameters();
        parameters.angleUnit                        = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit                        = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile              = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled                   = true;
        parameters.loggingTag                       = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu                                         =hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        LFront.setDirection(DcMotor.Direction.FORWARD);
        LBack.setDirection(DcMotor.Direction.FORWARD);
        RFront.setDirection(DcMotor.Direction.FORWARD);
        RBack.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        LFront.setPower(0);
        RFront.setPower(0);
        RBack.setPower(0);
        LBack.setPower(0);

        //Intake.setPower(0);

        // May want to use RUN_USING_ENCODERS if encoders are installed.
        LFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





        // Set motor ZeroPower Behavior
        LFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        LBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        RBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }
}