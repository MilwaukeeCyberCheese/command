package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveSubsystem extends SubsystemBase {

    //todo figure our these variables
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

    private final CANSparkMax leftFrontSpark = new CANSparkMax(Constants.controllers.DRIVETRAIN_LEFT_FRONT_SPARK, MotorType.kBrushed);
    private final CANSparkMax leftRearSpark = new CANSparkMax(Constants.controllers.DRIVETRAIN_LEFT_REAR_SPARK, MotorType.kBrushed);
    private final CANSparkMax rightFrontSpark = new CANSparkMax(Constants.controllers.DRIVETRAIN_RIGHT_FRONT_SPARK, MotorType.kBrushed);
    private final CANSparkMax rightRearSpark = new CANSparkMax(Constants.controllers.DRIVETRAIN_RIGHT_REAR_SPARK, MotorType.kBrushed);

    public DriveSubsystem() {}

    @Override
    public void periodic() {
        // Get my wheel speeds
        MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);
      
        // Get the individual wheel speeds
        double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
        double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
        double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
        double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;
        
        //todo set motors to speed
        leftFrontSpark.set(leftFrontSpeed);
        leftRearSpark.set(leftRearSpeed);
        rightFrontSpark.set(rightFrontSpeed);
        rightRearSpark.set(rightRearSpeed);
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
