package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.List;


@Autonomous
public class autoPractise extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private DcMotorEx shooter = null;
    private DcMotor intakeForward = null;
    private DcMotor intakeBack =null;
    private DcMotor turret = null;
    private Servo hood = null;
    private Servo blocker = null;

    private Limelight3A limelight = null;


    double turningPower = 0;
    int tagID = 0;
    boolean target = false;

    private enum Pathstate{
        PATH1, PATH2, PATH3, PATH4, PATH5, PATH6, PATH7, PATH8, PATH9, PATH10, PATH11
        //SPOS EPOS
    }

    Pathstate pathState;

    private final Pose startPose = new Pose(56,8, Math.toRadians(180));
    private final Pose movePose1 = new Pose(45, 32, Math.toRadians(180));
    private final Pose movePose2 = new Pose(15,32, Math.toRadians(180));
    private final Pose movePose3 = new Pose(56,27, Math.toRadians(270));
    private final Pose movePose4 = new Pose(8, 28, Math.toRadians(270));
    private final Pose movePose5 = new Pose(8, 10, Math.toRadians(270));
    private final Pose movePose6 = new Pose(56, 20, Math.toRadians(90));
    private final Pose movePose7 = new Pose(56,8, Math.toRadians(90));
    private final Pose finalPose = new Pose(56, 20, Math.toRadians(90));


    //alice in pathChains\/

    private PathChain path1;
    private PathChain path2;
    private PathChain path3;
    private PathChain path4;
    private PathChain path5;
    private PathChain path6;
    private PathChain path7;
    private PathChain path8;
    private PathChain path9;
    private PathChain path10;
    private PathChain path11;

private void shoot(){
    blocker.setPosition(0.6);
    sleep(500);
    intakeBack.setPower(1);
    intakeForward.setPower(1);
    sleep(2000);
    intakeBack.setPower(0);
    intakeForward.setPower(0);
    blocker.setPosition(0.05);
}

public void statePathUpdate() {
    shooter.setVelocity(-1850);
    target = false;

    //1100 for close
    hood.setPosition(0.25);
    //0.12 for close
        switch (pathState) {
            case PATH1:
                sleep(3500);
                shoot();
                follower.followPath(path1, true);
                setPathState(Pathstate.PATH2);
                break;
            case PATH2:
                if (!follower.isBusy()) {
                    intakeBack.setPower(1);
                    intakeForward.setPower(1);
                    telemetry.addLine("done Path1");
                    follower.followPath(path2, true);
                    setPathState(Pathstate.PATH3);
                }
                break;
            case PATH3:
                if (!follower.isBusy()) {
                    intakeBack.setPower(0);
                    intakeForward.setPower(0);
                    telemetry.addLine("done Path2");
                    follower.followPath(path3, true);
                    setPathState(Pathstate.PATH4);
                }
                break;
                //shoot
            case PATH4:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path3");
                    follower.followPath(path4, true);
                    setPathState(Pathstate.PATH5);
                }
                break;
            case PATH5:
                if (!follower.isBusy()) {

                    telemetry.addLine("done Path4");
                    follower.followPath(path5, true);
                    setPathState(Pathstate.PATH6);
                }
                break;
            case PATH6:
                if (!follower.isBusy()) {
                    intakeBack.setPower(1);
                    intakeForward.setPower(1);
                    telemetry.addLine("done Path5");
                    follower.followPath(path6, 0.5,true);
                    setPathState(Pathstate.PATH7);
                }
                break;
            case PATH7:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path6");
                    follower.followPath(path7, true);
                    setPathState(Pathstate.PATH8);
                }
                break;
            case PATH8:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path7");
                    follower.followPath(path8, true);
                    setPathState(Pathstate.PATH9);
                }
                break;
            default:
                telemetry.addLine("no state");
                terminateOpModeNow();
                break;

        }

}

public void setPathState(Pathstate newState) {
    pathState = newState;
    pathTimer.resetTimer();

}
    public void buildPaths() {
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose1))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose1.getHeading())
                .build();
        path2 = follower.pathBuilder()
                .addPath(new BezierLine(movePose1, movePose2))
                .setLinearHeadingInterpolation(movePose1.getHeading(), movePose2.getHeading())
                .build();
        path3 = follower.pathBuilder()
                .addPath(new BezierLine(movePose2, startPose))
                .setLinearHeadingInterpolation(movePose2.getHeading(), startPose.getHeading())
                .build();
        path4 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose3))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose3.getHeading())
                .build();
        path5 = follower.pathBuilder()
                .addPath(new BezierLine(movePose3, movePose4))
                .setLinearHeadingInterpolation(movePose3.getHeading(), movePose4.getHeading())
                .build();
        path6 = follower.pathBuilder()
                .addPath(new BezierLine(movePose4, movePose5))
                .setLinearHeadingInterpolation(movePose4.getHeading(), movePose5.getHeading())
                .build();
        path7 = follower.pathBuilder()
                .addPath(new BezierLine(movePose5, startPose))
                .setLinearHeadingInterpolation(movePose5.getHeading(), startPose.getHeading())
                .build();
        path8 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose6))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose6.getHeading())
                .build();
        path9 = follower.pathBuilder()
                .addPath(new BezierLine(movePose6, movePose7))
                .setLinearHeadingInterpolation(movePose6.getHeading(), movePose7.getHeading())
                .build();
        path10 = follower.pathBuilder()
                .addPath(new BezierLine(movePose7, startPose))
                .setLinearHeadingInterpolation(movePose7.getHeading(), startPose.getHeading())
                .build();
        path11 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, finalPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), finalPose.getHeading())
                .build();
    }

    @Override
    public void init() {
        pathState = Pathstate.PATH1;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        intakeForward = hardwareMap.get(DcMotor.class, "intakeForward");
        intakeBack = hardwareMap.get(DcMotor.class, "intakeBack");
        hood = hardwareMap.get(Servo.class, "hood");
        blocker = hardwareMap.get(Servo.class, "blocker");
        turret = hardwareMap.get(DcMotor.class, "turret");
        follower.setPose(startPose);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(2);


        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeForward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setDirection(DcMotor.Direction.FORWARD);
        intakeForward.setDirection(DcMotor.Direction.FORWARD);
        intakeBack.setDirection(DcMotor.Direction.FORWARD);
        hood.setDirection(Servo.Direction.FORWARD);
        blocker.setDirection(Servo.Direction.FORWARD);
        turret.setDirection(DcMotor.Direction.FORWARD);

    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);

    }
    public void loop(){

        limelight.start();
        limelight.setPollRateHz(90);
        LLResult result = limelight.getLatestResult();
        double tx = result.getTx();
        double ta = result.getTa();
        String tagseen = " ";
        String limelight_telemetry = "Limelight Data";
        int pipeline = result.getPipelineIndex();

        if (result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                tagID = fr.getFiducialId();
            }
        } else {
            tagID = 0;
            target = true;
        }
        if ((result.getStaleness() < 100) && ((result != null && result.isValid()))) {
            if (ta < 0.5) {
                if (tx < 0 || tx > 6) {    //red, -6 and 0 for blue
                    turningPower = (tx / 32.5);
                } else {
                    turningPower = 0;
                    target = true;
                }
            } else if (ta > 0.5) {
                if (tx < -3 || tx > 3) {
                    turningPower = (tx / 32.5);
                } else if (tx < -7 || tx > 7) {
                    turningPower = (tx / 40);
                } else {
                    turningPower = 0;
                    target = true;
                }
            }
        }


        long staleness = result.getStaleness();
        if (staleness < 100) {
            telemetry.addData("data", "good");
        } else {
            telemetry.addData("data", "bad (" + staleness + " ms)");
        }
        if (result != null && result.isValid()) {
            tagseen = "true";
        } else {
            tagseen = "false";
        }
        telemetry.addData("tagSeen ", tagseen);
        telemetry.addData("limelight x = ", tx);
        telemetry.addData("limelight a = ", ta);
        telemetry.addData("limelight pipeline = ", pipeline);
        telemetry.addData("tag ", "ID: %d", tagID);
        telemetry.update();
        turret.setPower(turningPower);


        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path time", pathTimer.getElapsedTimeSeconds());
        follower.update();
        statePathUpdate();



    }
}
