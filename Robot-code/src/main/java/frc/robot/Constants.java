/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */



public final class Constants {
    //TODO: Put FRC Characterization values here
    public final class RobotProperties {
        public static final double K_RamseteB = 0;
        public static final double K_RamseteZeta = 0;
        public static final double K_sVolts = 0;
        public static final double K_vVoltSecondsPerMeter = 0;
        public static final double K_aVoltSecondsSquaredPerMeter = 0;
        public static final double K_trackWidthMeters = 0;
        //TODO: Test if accurate
        public static final double K_encoderToMeters = 0.1524;
        public static final double K_armFeedForward = 0.15;
        public static final double K_armPotentiometerHighest = 18;
        public static final double K_armPotentiometerLowest = 1;
        public static final double K_armRaiseSignal = 1.0;
        public static final double K_armLowerSignal = -1.0;
    }
    //PORTS
    public final class Ports {
        //Joystick ports
        public static final int PORT_Joystick_Control = 1;
        public static final int PORT_Joystick_Chassis = 0;
        //CAN Talon SRX
        public static final int PORT_Chassis_FrontLeft = 2;
        public static final int PORT_Chassis_FrontRight = 7;
        public static final int PORT_Chassis_BackLeft = 6;
        public static final int PORT_Chassis_BackRight = 8;
        //CAN Victor SPX
        public static final int PORT_Hook_Motor = 5;

        public static final int PORT_Elevator_LiftMotorOne = 3;
        public static final int PORT_Elevator_LiftMotorTwo = 4;
        public static final int PORT_Intake_Motor = 1;
        //Analog ports
        public static final int PORT_Arm_Potentiometer = 0;
        //PWM Victor SP
        public static final int PORT_Arm_Motor = 9;
    }
    public final class OIConstants {
        //This is here to make vscode not warn us that these values are unused.
        @SuppressWarnings("unused")
        /**
         * Joystick Mappings
         */
        private final class JM {
            public final class Axes {
                public static final int Left_X = 0;
                public static final int Left_Y = 1;
                //L2
                public static final int Left_Trigger = 2;
                //R2
                public static final int Right_Trigger = 3;
                public static final int Right_X = 4;
                public static final int Right_Y = 5;
            }
            public final class Buttons {
                public static final int A = 1;
                public static final int B = 2;
                public static final int X = 3;
                public static final int Y = 4;
                public static final int L1 = 5;
                public static final int R1 = 6;
                public static final int Back = 7;
                public static final int Start = 8;
                public static final int L3 = 9;
                public static final int R3 = 10;
            }
        }
        public final class Driver {
            public static final int Axis_X = JM.Axes.Left_X;
            public static final int Axis_Y = JM.Axes.Left_Y;
            public static final int Button_Turbo = JM.Buttons.A;
        }
        public final class Controller {
            public static final int Button_Intake = JM.Buttons.B;
            public static final int Button_Outtake = JM.Buttons.A;
            public static final int Button_Lift = JM.Buttons.L1;
            public static final int Button_Pull = JM.Buttons.R1;
            public static final int Button_Lift_Green = JM.Buttons.Back;
            public static final int Button_Pull_Green = JM.Buttons.Start;
            public static final int Button_Raise_Arm = JM.Buttons.X;
            public static final int Button_Lower_Arm = JM.Buttons.Y;
        }
    }
}
