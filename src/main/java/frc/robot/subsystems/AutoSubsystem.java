package frc.robot.subsystems;

import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.other.Stopwatch;

public class AutoSubsystem extends SubsystemBase {

    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    private final Stopwatch stopwatch;
    private List<ChassisSpeeds> speeds = new LinkedList();

    private static final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
                        // Front left
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Front right
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0),
                        // Back left
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Back right
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0));

    public AutoSubsystem() {
        stopwatch = new Stopwatch();
        System.out.println(speeds);
    }

    private int stopwatchCounter = -1;
    @Override
    public void periodic() {
        if (DriverStation.isAutonomousEnabled() && stopwatchCounter < speeds.size()) { 
            // set m_chassisSpeeds
            stopwatchCounter++;
            m_chassisSpeeds = speeds.get(stopwatchCounter);

            // drive using m_chassisSpeeds
            // get the wheel speeds
            MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);
        
            // get the individual wheel speeds
            double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
            double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
            double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
            double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;
            
            Constants.controllers.leftFrontSpark.set(-leftFrontSpeed);
            Constants.controllers.leftRearSpark.set(-leftRearSpeed);
            Constants.controllers.rightFrontSpark.set(rightFrontSpeed);
            Constants.controllers.rightRearSpark.set(rightRearSpeed);
        }
    } 

    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }

    public void startStopwatch() {
        stopwatch.start();
    }

    public void stopStopwatch() {
        stopwatch.stop();
    }

    public void addSpeed(ChassisSpeeds speed) {
        speeds.add(speed);
    }

    public List<ChassisSpeeds> getSpeeds() {
        return speeds;
    }

    public void printSpeeds() {
        String toPrint = "";

        toPrint += "{";
        for (int i = 0; i < speeds.size(); i++) {
            ChassisSpeeds speed = speeds.get(i);
            toPrint += "new ChassisSpeeds(";
            toPrint += speed.vxMetersPerSecond + ",";
            toPrint += speed.vyMetersPerSecond + ",";;
            toPrint += speed.omegaRadiansPerSecond + ")";

            if (i != speeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        System.out.println(toPrint);
    }
}
