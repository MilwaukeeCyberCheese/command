package frc.robot.subsystems;

import java.io.File;
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
    private List<Double> intaking = new LinkedList<Double>();
    private List<Double> topSpeeds = new LinkedList<Double>();
    private List<Double> bottomSpeeds = new LinkedList<Double>();
    private List<Double> servos = new LinkedList<Double>();

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
            double topSpeed = this.topSpeeds.get(stopwatchCounter);              
            double bottomSpeed = this.bottomSpeeds.get(stopwatchCounter);  
            
            Constants.controllers.shooterTopSpark.set(topSpeed);
            Constants.controllers.shooterBottomSpark.set(bottomSpeed);

            // intake
            double intake = this.intaking.get(stopwatchCounter);
                
            Constants.controllers.intakeSpark.set(intake);

            // servos
            if (stopwatchCounter < servos.size() - 1) {
                double servo = this.servos.get(stopwatchCounter);

                Constants.controllers.servo.set(servo);
            }
            
            m_chassisSpeeds = this.speeds.get(stopwatchCounter);

            // drive using m_chassisSpeeds
            // get the wheel speeds
            MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);
        
            // get the individual wheel speeds
            double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
            double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
            double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
            double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;
            
            Constants.controllers.rightRearSpark.set(rightRearSpeed);
            Constants.controllers.leftRearSpark.set(-leftRearSpeed);
            Constants.controllers.rightFrontSpark.set(rightFrontSpeed);
            Constants.controllers.leftFrontSpark.set(-leftFrontSpeed);
        } else if (DriverStation.isAutonomousEnabled() && stopwatchCounter >= speeds.size() - 1) {
            // if we run out of code to run in auto, make sure everything is not moving
            Constants.controllers.shooterTopSpark.set(0.0);
            Constants.controllers.shooterBottomSpark.set(0.0);
            Constants.controllers.intakeSpark.set(0.0);

            Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);

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
        this.speeds.add(speed);
    }

    public void addIntaking(double intake) {
        this.intaking.add(intake);
    }

    public void addShooting(double topSpeed, double bottomSpeed) {
        this.topSpeeds.add(topSpeed);
        this.bottomSpeeds.add(bottomSpeed);
    }

    public void addServo(double servo) {
        this.servos.add(servo);
    }

    public void printSpeeds() {
        String toPrint = "";

        // toPrint += speeds.size();
        // toPrint += "\n" + intaking.size();
        // toPrint += "\n" + topSpeed.size();
        // toPrint += "\n" + bottomSpeed.size();
        // toPrint += "\n" + servos.size();

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
        toPrint += "\n\nprivate List<Double> intaking = {";
        for (int i = 0; i < intaking.size(); i++) {
            double intake = intaking.get(i);
            toPrint += intake;

            if (i != intaking.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // append the shooter speeds
        toPrint += "\n\nprivate List<Double> topSpeeds = {";
        for (int i = 0; i < topSpeeds.size(); i++) {
            double shoot = topSpeeds.get(i);
            toPrint += shoot;

            if (i != topSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";
        
        // append the shooter speeds
        toPrint += "\n\nprivate List<Double> bottomSpeeds = {";
        for (int i = 0; i < bottomSpeeds.size(); i++) {
            double shoot = bottomSpeeds.get(i);
            toPrint += shoot;

            if (i != bottomSpeeds.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // append the servo speeds
        toPrint += "\n\nprivate List<Double> servos = {";
        for (int i = 0; i < servos.size(); i++) {
            double servo = servos.get(i);
            toPrint += servo;

            if (i != servos.size() - 1) {
                toPrint += ",";
            }
        }
        toPrint += "}";

        // print the full string to file for analysis
        try {
            System.out.println("C:\\Users\\christophersonc\\OneDrive - Milwaukee School of Engineering\\Desktop\\command\\auto-output.txt");
            File file = new File("C:\\Users\\christophersonc\\OneDrive - Milwaukee School of Engineering\\Desktop\\command\\auto-output.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println(toPrint);
            writer.close();
        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }
}
