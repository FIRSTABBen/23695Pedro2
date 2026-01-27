package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class JoystickTesting extends OpMode{
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        String gp1 = "gamepad 1";
        String gp1j = "gamepad 1 joysticks";
        String gp1t = "gamepad 1 triggers/bumpers";
        String gp2 = "gamepad 2";
        String gp2j = "gamepad 2 joysticks";
        String gp2t = "gamepad 2 triggers";
        String blank = " ";

        telemetry.addData("", gp1);
        telemetry.addData("gamepad 1 DL ", gamepad1.dpad_left);
        telemetry.addData("gamepad 1 DU ", gamepad1.dpad_up);
        telemetry.addData("gamepad 1 DR ", gamepad1.dpad_right);
        telemetry.addData("gamepad 1 DD ", gamepad1.dpad_down);

        telemetry.addData("", blank);

        telemetry.addData("gamepad 1 Y ", gamepad1.y);
        telemetry.addData("gamepad 1 B ", gamepad1.b);
        telemetry.addData("gamepad 1 x ", gamepad1.x);
        telemetry.addData("gamepad 1 a ", gamepad1.a);

        telemetry.addData("", blank);

        telemetry.addData("", gp1j);
        telemetry.addData("gamepad 1 LJX ", gamepad1.left_stick_x);
        telemetry.addData("gamepad 1 LJY ", gamepad1.left_stick_y);
        telemetry.addData("gamepad 1 RJX ", gamepad1.right_stick_x);
        telemetry.addData("gamepad 1 RJY ", gamepad1.right_stick_y);

        telemetry.addData("", blank);

        telemetry.addData("", gp1t);
        telemetry.addData("gamepad 1 LT", gamepad1.left_trigger);
        telemetry.addData("gamepad 1 LB", gamepad1.left_bumper);
        telemetry.addData("gamepad 1 RT", gamepad1.right_trigger);
        telemetry.addData("gamepad 1 RB", gamepad1.right_bumper);


        

        telemetry.addData("", blank);
        telemetry.addData("", blank);
        telemetry.addData("", blank);




        telemetry.addData("", gp2);
        telemetry.addData("gamepad 2 DL ", gamepad2.dpad_left);
        telemetry.addData("gamepad 2 DU ", gamepad2.dpad_up);
        telemetry.addData("gamepad 2 DR ", gamepad2.dpad_right);
        telemetry.addData("gamepad 2 DD ", gamepad2.dpad_down);

        telemetry.addData("", blank);

        telemetry.addData("gamepad 2 Y ", gamepad2.y);
        telemetry.addData("gamepad 2 B ", gamepad2.b);
        telemetry.addData("gamepad 2 x ", gamepad2.x);
        telemetry.addData("gamepad 2 a ", gamepad2.a);

        telemetry.addData("", blank);

        telemetry.addData("", gp2j);
        telemetry.addData("gamepad 2 LJX ", gamepad2.left_stick_x);
        telemetry.addData("gamepad 2 LJY ", gamepad2.left_stick_y);
        telemetry.addData("gamepad 2 RJX ", gamepad2.right_stick_x);
        telemetry.addData("gamepad 2 RJY ", gamepad2.right_stick_y);

        telemetry.addData("", blank);

        telemetry.addData("", gp2t);
        telemetry.addData("gamepad 2 LT", gamepad2.left_trigger);
        telemetry.addData("gamepad 2 LB", gamepad2.left_bumper);
        telemetry.addData("gamepad 2 RT", gamepad2.right_trigger);
        telemetry.addData("gamepad 2 RB", gamepad2.right_bumper);


    }
}


