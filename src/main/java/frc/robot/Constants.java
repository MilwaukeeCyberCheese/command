// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class controllers {
        public static final int DRIVETRAIN_LEFT_FRONT_SPARK = 7; // front = closest to intake, left = facing intake
        public static final int DRIVETRAIN_LEFT_REAR_SPARK = 2;
        public static final int DRIVETRAIN_RIGHT_FRONT_SPARK = 4;
        public static final int DRIVETRAIN_RIGHT_REAR_SPARK = 5;
        public static final int INTAKE_SPARK = 3;
        public static final int SHOOTER_TOP_SPARK = 6;
        public static final int SHOOTER_BOTTOM_SPARK = 8;
        public static final int CLIMBER_SPARK = 1; // this is likely unused 
    }

    public static final class dimensions {
        public static final double TRACKWIDTH = Units.inchesToMeters(24);
        public static final double WHEELBASE = Units.inchesToMeters(20.25);
        public static final double DRIVE_REDUCTION = (1/10.71);
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(6);
    }

    public static final class subsystems {
        public static final class drive {
            // <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
            // public static final double MAX_VEL_METERS = (5310 / 60) * (1/10.71) * 0.1524 * Math.PI;
            public static final double MAX_VEL_METERS = 5310.0 / 60.0
                    * Constants.dimensions.DRIVE_REDUCTION
                    * Constants.dimensions.WHEEL_DIAMETER * Math.PI;
            public static final double MAX_ANG_VEL_RAD = MAX_VEL_METERS
                / Math.hypot(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0);
            public static final double MAX_ANG_ACCEL = 8 * Math.PI;
            public static final double MAX_VOLTAGE = 12.0;   
        }

        public final class intake {
            public static final double INTAKE_SPEED = 1.0;
        }
    }

    public static final class outputs {
        public static final double strafe = .1;
        public static final double turnRate = .1;
    }
}
