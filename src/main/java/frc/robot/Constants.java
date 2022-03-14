// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Servo;

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
        public static final int SHOOTER_TOP_SPARK = 8;
        public static final int SHOOTER_BOTTOM_SPARK = 6;
        public static final int CLIMBER_SPARK = 1; // this is likely unused 


        public static final CANSparkMax leftFrontSpark = new CANSparkMax(DRIVETRAIN_LEFT_FRONT_SPARK, MotorType.kBrushed);
        public static final CANSparkMax leftRearSpark = new CANSparkMax(DRIVETRAIN_LEFT_REAR_SPARK, MotorType.kBrushed);
        public static final CANSparkMax rightFrontSpark = new CANSparkMax(DRIVETRAIN_RIGHT_FRONT_SPARK, MotorType.kBrushed);
        public static final CANSparkMax rightRearSpark = new CANSparkMax(DRIVETRAIN_RIGHT_REAR_SPARK, MotorType.kBrushed);
        public static final CANSparkMax intakeSpark = new CANSparkMax(INTAKE_SPARK, MotorType.kBrushless);
        public static final CANSparkMax shooterTopSpark = new CANSparkMax(SHOOTER_TOP_SPARK, MotorType.kBrushed);
        public static final CANSparkMax shooterBottomSpark = new CANSparkMax(SHOOTER_BOTTOM_SPARK, MotorType.kBrushed);
        public static final Servo servo = new Servo(0);
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

        public final class shooter {
            public static final double TOP_SPEED = -0.75;
            public static final double BOTTOM_SPEED = 1.1;
            public static final double TOP_WRONG_BALL_SPEED = 0.1;
            public static final double BOTTOM_WRONG_BALL_SPEED = -0.1;
        }
    }

    public static final class outputs {
        public static final double strafe = 0.4;
        public static final double turnRate = 0.4;
    }
}