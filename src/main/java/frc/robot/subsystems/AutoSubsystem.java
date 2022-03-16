package frc.robot.subsystems;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AutoSubsystem extends SubsystemBase {

    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    // the following lists store data relating to the preprogrammed auto paths
    private List<ChassisSpeeds> speeds = new LinkedList<ChassisSpeeds>();
    private List<Boolean> intaking = new LinkedList<Boolean>();
    private List<Boolean> shooting = new LinkedList<Boolean>();
    private List<Boolean> servos = new LinkedList<Boolean>();

    private static final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
                        // Front left
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Front right
                        new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0),
                        // Back left
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
                        // Back right
                        new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0));

    private int stopwatchCounter = -1;

    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function drives all auto paths
     */
    @Override
    public void periodic() {
        if (DriverStation.isAutonomousEnabled() && stopwatchCounter < speeds.size() - 1) { 
            stopwatchCounter++;
            
            // shoot
            boolean shoot = shooting.get(stopwatchCounter);
                
            if (shoot) {
                Constants.controllers.shooterTopSpark.set(Constants.subsystems.shooter.HIGH_TOP_SPEED);
                Constants.controllers.shooterBottomSpark.set(Constants.subsystems.shooter.HIGH_BOTTOM_SPEED);
            } else {
                Constants.controllers.shooterTopSpark.set(0.0);
                Constants.controllers.shooterBottomSpark.set(0.0);
            }

            // intake
            boolean intake = intaking.get(stopwatchCounter);
                
            if (intake) {
                Constants.controllers.intakeSpark.set(Constants.subsystems.intake.INTAKE_SPEED);
            } else {
                Constants.controllers.intakeSpark.set(0.0);
            }

            // servos
            if (stopwatchCounter < servos.size() - 1) {
                boolean servo = servos.get(stopwatchCounter);

                if (servo) {
                    Constants.controllers.servo.set(0.5);
                } else {
                    Constants.controllers.servo.set(0.0);
                }
            }
            
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
        } else if (DriverStation.isAutonomousEnabled() && stopwatchCounter >= speeds.size() - 1) {
            // if we run out of code to run in auto, make sure everything is not moving
            Constants.controllers.shooterTopSpark.set(0.0);
            Constants.controllers.shooterBottomSpark.set(0.0);
            Constants.controllers.intakeSpark.set(0.0);
            Constants.controllers.servo.set(0.0);
            Constants.controllers.leftFrontSpark.set(0.0);
            Constants.controllers.leftRearSpark.set(0.0);
            Constants.controllers.rightFrontSpark.set(0.0);
            Constants.controllers.rightRearSpark.set(0.0);
        }
    } 

    /**
     * This function is called when the button on the controller to operate this subsystem is activated
     * 
     * @param speed The speed to move this subsystem at
     */
    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }

    /**
     * The following functions all are used to preprogram the auto paths
     */
    public void addSpeed(ChassisSpeeds speed) {
        speeds.add(speed);
    }

    public void addIntaking(boolean intake) {
        intaking.add(intake);
    }

    public void addShooting(boolean shoot) {
        shooting.add(shoot);
    }

    public void addServo(boolean servo) {
        servos.add(servo);
    }

    public void printSpeeds() {
        String toPrint = "";

        toPrint += speeds.size();
        toPrint += "\n" + intaking.size();
        toPrint += "\n" + shooting.size();
        toPrint += "\n" + servos.size();

        // append the drive speeds
        toPrint += "private List<ChassisSpeeds> speeds = {";
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

        // append the intake speeds
        toPrint += "\n\nprivate List<Boolean> intakeing = {";
        for (int i = 0; i < intaking.size(); i++) {
            boolean intake = intaking.get(i);
            toPrint += intake;

            if (i != intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // append the shooter speeds
        toPrint += "\n\nprivate List<Boolean> shooting = {";
        for (int i = 0; i < shooting.size(); i++) {
            boolean shoot = shooting.get(i);
            toPrint += shoot;

            if (i != shooting.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // append the servo speeds
        toPrint += "\n\nprivate List<Boolean> servos = {";
        for (int i = 0; i < servos.size(); i++) {
            boolean servo = servos.get(i);
            toPrint += servo;

            if (i != servos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // print the full string to file for analysis
        try {
            PrintWriter writer = new PrintWriter("auto-output.txt", "UTF-8");
            writer.println(toPrint);
            writer.close();
        } catch (Exception ignored) {}
    }
}
