package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous
public class autoPractise extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private enum Pathstate{
        //SPOS EPOS
    }

    Pathstate pathstate;

    //private final Pose startpose = new Pose()

    @Override
    public void init() {



    }
    public void loop(){

    }
}
