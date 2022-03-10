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

    public AutoSubsystem() {}

    private int stopwatchCounter = -1;
    @Override
    public void periodic() {
        if (DriverStation.isAutonomousEnabled() && stopwatchCounter < speeds.size() - 1) { 
            stopwatchCounter++;
            
            // shoot
            boolean shoot = shooting.get(stopwatchCounter);
                
            if (shoot) {
                Constants.controllers.shooterTopSpark.set(Constants.subsystems.shooter.TOP_SPEED);
                Constants.controllers.shooterBottomSpark.set(Constants.subsystems.shooter.BOTTOM_SPEED);
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
        }
    } 

    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }

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

        // // append the drive speeds
        // toPrint += "private List<ChassisSpeeds> speeds = {";
        // for (int i = 0; i < speeds.size(); i++) {
        //     ChassisSpeeds speed = speeds.get(i);
        //     toPrint += "new ChassisSpeeds(";
        //     toPrint += speed.vxMetersPerSecond + ",";
        //     toPrint += speed.vyMetersPerSecond + ",";;
        //     toPrint += speed.omegaRadiansPerSecond + ")";

        //     if (i != speeds.size() - 1) {
        //         toPrint += ",";
        //     }
        // }
        // toPrint += "}";

        // // append the intake speeds
        // toPrint += "\nprivate List<Boolean> intakeing = {";
        // for (int i = 0; i < intaking.size(); i++) {
        //     boolean intake = intaking.get(i);
        //     toPrint += intake;

        //     if (i != intaking.size() - 1) {
        //         toPrint += ",";
        //     }
        // }
        // toPrint += "}";

        // // append the shooter speeds
        // toPrint += "\nprivate List<Boolean> shooting = {";
        // for (int i = 0; i < shooting.size(); i++) {
        //     boolean shoot = shooting.get(i);
        //     toPrint += shoot;

        //     if (i != shooting.size() - 1) {
        //         toPrint += ",";
        //     }
        // }
        // toPrint += "}";

        // // append the servo speeds
        // toPrint += "\nprivate List<Boolean> servos = {";
        // for (int i = 0; i < servos.size(); i++) {
        //     boolean servo = servos.get(i);
        //     toPrint += servo;

        //     if (i != servos.size() - 1) {
        //         toPrint += ",";
        //     }
        // }
        // toPrint += "}";

        // print the full string to console
        System.out.println(toPrint);
    }
}
