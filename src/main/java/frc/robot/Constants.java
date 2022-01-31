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

    public static final class dimensions {
        public static final double TRACKWIDTH = Units.inchesToMeters(24);
        public static final double WHEELBASE = Units.inchesToMeters(20.25);
    }

    public final class subsystems {
        public final class drive {
            //todo find out how to calculate these constants
            public static final double MAX_VEL_METERS = 0;
            public static final double MAX_ANG_VEL_RAD = 0;
            public static final double MAX_VOLTAGE = 12.0;
            public static final double MAX_ANG_ACCEL = 8 * Math.PI;
            
        }
    }

    public static final class outputs {
        public static final double strafe = .7;
        public static final double turnRate = 1;
    }
}
