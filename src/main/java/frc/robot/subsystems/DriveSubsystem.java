package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

    public static final int MAX_VELOCITY_METERS_PER_SECOND = 0;

    public static final int MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = 0;

    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    private final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
                        // Front left
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Front right
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0),
                        // Back left
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Back right
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0));


    public DriveSubsystem() {}

    @Override
    public void periodic() {
        // Get my wheel speeds
        MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);
      
        // Get the individual wheel speeds
        double frontLeft = wheelSpeeds.frontLeftMetersPerSecond;
        double frontRight = wheelSpeeds.frontRightMetersPerSecond;
        double backLeft = wheelSpeeds.rearLeftMetersPerSecond;
        double backRight = wheelSpeeds.rearRightMetersPerSecond;
        
        //todo set motors to speed
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }

    public ChassisSpeeds getChassisSpeeds() {
        return m_chassisSpeeds;
    }
}
