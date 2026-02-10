package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous
public class autoPractise extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private enum Pathstate{
        SHOOT_PRELOAD, DRIVE_STARTPOS_SHOOT_POS
        //SPOS EPOS
    }

    Pathstate pathState;

    //"x,y,heading in radians(yuck)"
    private final Pose startPose = new Pose();
    private final Pose shootPose1 = new Pose();
    private final Pose movePose1 = new Pose();
    private final Pose movePose2 = new Pose();

    //pathChains\/

    private PathChain path1;

    public void buildPaths() {
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose1))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose1.getHeading())
                .build();
    }

public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                follower.followPath(path1, true);
                setPathState(Pathstate.SHOOT_PRELOAD);
                break;
            case SHOOT_PRELOAD:
                if (!follower.isBusy()) {
                    // TODO add logic to flywheel shooter?
                    telemetry.addLine("done path1");
                }
                break;
            default:
                telemetry.addLine("no state");
                break;
        }
}

public void setPathState(Pathstate newState) {
    pathState = newState;
    pathTimer.resetTimer();

}

    @Override
    public void init() {
        pathState = Pathstate.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);

        follower.setPose(startPose);

    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);

    }
    public void loop(){
        follower.update();
        statePathUpdate();


        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path time", pathTimer.getElapsedTimeSeconds());
    }
}
