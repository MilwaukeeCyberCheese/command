package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {

    /**
     * The maximum voltage that will be delivered to the drive motors.
     * <p>
     * This can be reduced to cap the robot's maximum speed. Typically, this is
     * useful during initial testing of the robot.
     */
    public static final double MAX_VOLTAGE = Constants.subsystems.drive.MAX_VOLTAGE;

    // The formula for calculating the theoretical maximum velocity is:
    // <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> *
    // pi
    // By default this value is setup for a Mk3 standard module using Falcon500s to
    // drive.
    // An example of this constant for a Mk4 L2 module with NEOs to drive is:
    // 5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() *
    // SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
    /**
     * The maximum velocity of the robot in meters per second.
     * 
     * This is a measure of how fast the robot should be able to drive in a straight
     * line.
     */
    public static final double MAX_VELOCITY_METERS_PER_SECOND = Constants.subsystems.drive.MAX_VEL_METERS;

    /**
     * The maximum angular velocity of the robot in radians per second.
     * 
     * This is a measure of how fast the robot can rotate in place.
     */
    public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = Constants.subsystems.drive.MAX_ANG_VEL_RAD;

    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    private static final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
            // Front left
            new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
            // Front right
            new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0),
            // Back left
            new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
            // Back right
            new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0));

    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function drives the robot based on the stick
     * parameters
     */
    @Override
    public void periodic() {
        // this is only used for when planning auto paths
        if (RobotContainer.readAuto)
            RobotContainer.m_autoSubsystem.addSpeed(m_chassisSpeeds);

        // get the wheel speeds
        MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);

        // get the individual wheel speeds
        double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
        double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
        double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
        double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;

        // set the speeds to each controller
        Constants.controllers.rightRearSpark.set(rightRearSpeed);
        Constants.controllers.leftRearSpark.set(-leftRearSpeed);
        Constants.controllers.rightFrontSpark.set(rightFrontSpeed);
        Constants.controllers.leftFrontSpark.set(-leftFrontSpeed);

    }

    /**
     * This function is called when the button on the controller to operate this
     * subsystem is activated
     * 
     * @param speed The speed to move this subsystem at
     */
    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return m_chassisSpeeds;
    }
}
