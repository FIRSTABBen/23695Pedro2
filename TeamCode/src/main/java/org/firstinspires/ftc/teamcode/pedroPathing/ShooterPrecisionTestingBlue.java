package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.util.List;

@TeleOp
public class ShooterPrecisionTestingBlue extends OpMode
{
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotorEx shooter = null;
    private DcMotor intakeForward = null;
    private DcMotor intakeBack = null;
    private DcMotor turret = null;

    private Servo hood = null;
    private Servo blocker = null;

    private Limelight3A limelight = null;
    private IMU imu; //emu

    double shooterVelocity = 0;
    boolean shooterPowerControl = true;
    boolean hoodController = true;
    double hoodPos;
    int tagID = 0;
    double turningPower = 0;
    boolean gamepadImput = false;


    @Override
    public void init() {
        //Declare variables for phone to recognise//

        //names on the config

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back");
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        intakeForward = hardwareMap.get(DcMotor.class, "intakeForward");
        intakeBack = hardwareMap.get(DcMotor.class, "intakeBack");
        turret = hardwareMap.get(DcMotor.class, "turret");
        hood = hardwareMap.get(Servo.class, "hood");
        blocker = hardwareMap.get(Servo.class, "blocker");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(2);
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

        //other motor initializing

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeForward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotor.Direction.REVERSE);
        intakeForward.setDirection(DcMotor.Direction.FORWARD);
        intakeBack.setDirection(DcMotor.Direction.FORWARD);
        turret.setDirection(DcMotor.Direction.FORWARD);
        hood.setDirection(Servo.Direction.FORWARD);
        blocker.setDirection(Servo.Direction.FORWARD);


        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeForward.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        hood.setPosition(0.27);
        blocker.setPosition(0.4);


        telemetry.addData("status", "Initialized");
    }


    //Set variables//
    @Override
    public void loop() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw());
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            Pose3D botPose =llResult.getBotpose_MT2();
        }

        // drive variables
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;

        LLResult result = limelight.getLatestResult();
        limelight.start();
        limelight.setPollRateHz(90);
        double tx = result.getTx();
        double ta = result.getTa();
        String limelight_telemetry = "Limelight Data";
        int pipeline = result.getPipelineIndex();

        if (llResult.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                tagID = fr.getFiducialId();
            }
        }
        else {
            tagID = 0;
        }


        boolean priorityImput = false;
        boolean anyImput = false;


//      y
//   x     b   << controller button layout
//      a
        // joystick controls
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        // making sure power doesnt exceed 100% and adding and subtracting variables to
        // get individual motor power levels
        leftFrontPower = Range.clip(drive + turn + strafe, -1, 1);
        rightFrontPower = Range.clip(drive - turn - strafe, -1, 1);
        leftBackPower = Range.clip(drive + turn - strafe, -1, 1);
        rightBackPower = Range.clip(drive - turn + strafe, -1, 1);
        // dividing power if right bumper is pressed
        if(gamepad1.right_trigger > 0.5){
            leftFrontPower /= 2;
            leftBackPower /= 2;
            rightFrontPower /= 2;
            rightBackPower /= 2;
        } else if(gamepad1.left_trigger > 0.5){
            leftFrontPower /= 4;
            leftBackPower /= 4;
            rightFrontPower /= 4;
            rightBackPower /= 4;
        }


        // shooter controls
        if (gamepad2.right_trigger > 0.5) {
            blocker.setPosition(0.2);
            hood.setPosition(0.24);
            shooterVelocity = 1850;
        } else if (gamepad2.right_bumper) {
            hood.setPosition(0.1);
            blocker.setPosition(0.2);
            shooterVelocity = 1100;
        }
        else {
            blocker.setPosition(0.4);
            hood.setPosition(0.05);
            shooterVelocity = 0;
        }

        // intake and transfer controls
        if (gamepad2.left_trigger > 0.5) {
            intakeForward.setPower(1);
            priorityImput = true;
            anyImput = true;
        }
        if (gamepad2.left_bumper) {
            intakeBack.setPower(1);
            priorityImput = true;
            anyImput = true;
        }
        if (gamepad2.dpad_down && !priorityImput) {
            intakeForward.setPower(-1);
            intakeBack.setPower(-1);
            anyImput = true;
        }
        if (!anyImput) {
            intakeForward.setPower(0);
            intakeBack.setPower(0);
        }

        // turret code
        if (gamepad2.left_stick_x > 0.05 || gamepad2.left_stick_x < -0.05){
            turningPower = (gamepad2.left_stick_x / 2);
            gamepadImput = true;
        }
        else {
            gamepadImput = false;
        }

        if ((result.getStaleness() < 100) && ((llResult != null && llResult.isValid())) && !gamepadImput) {
            if (ta < 0.5){
              if (tx < -6 || tx > 0){
                  turningPower = (tx / 23);
              }
            }
            else if (ta > 0.5){
                if (tx < -3 || tx > 3){
                turningPower = (tx / 23);
                }
            }
        }






//        // old shooter controls, keep commented out for now
//            if (shooterPowerControl && gamepad2.y && shooterVelocity != 2800) {
//                shooterVelocity += 100;
//                shooterPowerControl = false;
//            } else if (shooterPowerControl && gamepad2.x && shooterVelocity != 0) {
//                shooterVelocity -= 100;
//                shooterPowerControl = false;
//            } else if (!gamepad2.x && !gamepad2.y) {
//                shooterPowerControl = true;
//            }
        // self destruct button
        if ((gamepad1.a && gamepad1.b && gamepad1.x && gamepad1.y) || (gamepad2.a && gamepad2.b && gamepad2.x && gamepad2.y)) {
            leftFrontDrive.setPower(0);
            rightFrontDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightBackDrive.setPower(0);
            shooter.setVelocity(0);
            intakeForward.setPower(0);
            intakeBack.setPower(0);
            limelight.stop();
            terminateOpModeNow();

        }
        //limelight stuff
        long staleness = result.getStaleness();
        if (staleness < 100) {
            telemetry.addData("data", "good");
        } else {
            telemetry.addData("data", "bad (" + staleness + " ms)");
        }

//        if (gamepad2.a) {
//            hood.setPosition(0.05);
//        } else if (gamepad2.b) {
//            hood.setPosition(0.27);
//        }

        //setting final power levels
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
        shooter.setVelocity(shooterVelocity);
        turret.setPower(turningPower);


        //Claw Code: Opens with GP2 X and opens less when past vertical position
        // BIGGER CLOSES MORE*********************
        String tagseen = "n/a";

        // telemetry
        telemetry.addData("left front motor expected", "power = %.2f", leftFrontPower);
        telemetry.addData("right front motor expected", "power = %.2f", rightFrontPower);
        telemetry.addData("left rear motor expected", "power = %.2f", leftBackPower);
        telemetry.addData("right rear motor expected", "power = %.2f", rightBackPower);
        telemetry.addData("shooter velocity", "velocity = %.2f", shooter.getVelocity());
        //telemetry.addData("turret pos", "pos= %.2f", turret.getCurrentPosition());
        telemetry.addData("", limelight_telemetry);
        if (llResult != null && llResult.isValid()) {
            tagseen = "true";
        }
        else {
            tagseen = "false";
        }
        telemetry.addData("tagSeen ", tagseen);
        telemetry.addData("limelight x = ", tx);
        telemetry.addData("limelight a = ", ta);
        telemetry.addData("limelight pipeline = ", pipeline);
        telemetry.addData("tag ", "ID: %d", tagID);
    }


    @Override
    public void stop() {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
        shooter.setPower(0);
        intakeForward.setPower(0);
        limelight.stop();


    }

}

