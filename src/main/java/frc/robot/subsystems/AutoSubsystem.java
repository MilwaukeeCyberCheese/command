// package frc.robot.subsystems;

// import edu.wpi.first.math.geometry.Translation2d;
// import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
// import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class AutoSubsystem extends SubsystemBase {

//     // 36 and 3/4 is how far from the edge of the tape we need to line up, we also
//     // need to have the left bumper slightly off the line

//     private int auto = 1;

//     private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

//     private static final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
//             // Front left
//             new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
//             // Front right
//             new Translation2d(Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0),
//             // Back left
//             new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, Constants.dimensions.WHEELBASE / 2.0),
//             // Back right
//             new Translation2d(-Constants.dimensions.TRACKWIDTH / 2.0, -Constants.dimensions.WHEELBASE / 2.0));

//     private int stopwatchCounter = -1;

//     /**
//      * This function is called every few milliseconds when the robot is enabled
//      * 
//      * For this subsystem, this function drives all auto paths
//      */
//     @Override
//     public void periodic() {


//         if (auto == 1) {
//             if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.speeds.size() - 1) {
//                 stopwatchCounter++;

//                 // shoot
//                 double topSpeed = AutoSubsystemValues.topSpeeds.get(stopwatchCounter);
//                 double bottomSpeed = AutoSubsystemValues.bottomSpeeds.get(stopwatchCounter);

//                 Constants.controllers.shooterTopSpark.set(topSpeed);
//                 Constants.controllers.shooterBottomSpark.set(bottomSpeed);

//                 // intake
//                 double intake = AutoSubsystemValues.intaking.get(stopwatchCounter);

//                 Constants.controllers.intakeTopSpark.set(intake);

//                 // servos
//                 if (stopwatchCounter < AutoSubsystemValues.servos.size() - 1) {
//                     double servo = AutoSubsystemValues.servos.get(stopwatchCounter);

//                     Constants.controllers.servo.set(servo);
//                 }

//                 m_chassisSpeeds = AutoSubsystemValues.speeds.get(stopwatchCounter);

//                 // drive using m_chassisSpeeds
//                 // get the wheel speeds
//                 MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);

//                 // get the individual wheel speeds
//                 double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
//                 double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
//                 double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
//                 double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;

//                 Constants.controllers.rightRearSpark.set(rightRearSpeed);
//                 Constants.controllers.leftRearSpark.set(-leftRearSpeed);
//                 Constants.controllers.rightFrontSpark.set(rightFrontSpeed);
//                 Constants.controllers.leftFrontSpark.set(-leftFrontSpeed);
//             } else if (DriverStation.isAutonomousEnabled()
//                     && stopwatchCounter >= AutoSubsystemValues.speeds.size() - 1) {
//                 // if we run out of code to run in auto, make sure everything is not moving
//                 Constants.controllers.shooterTopSpark.set(0.0);
//                 Constants.controllers.shooterBottomSpark.set(0.0);
//                 Constants.controllers.intakeTopSpark.set(0.0);

//                 Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);

//                 Constants.controllers.leftFrontSpark.set(0.0);
//                 Constants.controllers.leftRearSpark.set(0.0);
//                 Constants.controllers.rightFrontSpark.set(0.0);
//                 Constants.controllers.rightRearSpark.set(0.0);
//             }
//         } else if (auto == 2) {
//             if (DriverStation.isAutonomousEnabled() && stopwatchCounter < AutoSubsystemValues.auto2speeds.size() - 1) {
//                 stopwatchCounter++;

//                 m_chassisSpeeds = AutoSubsystemValues.auto2speeds.get(stopwatchCounter);

//                 // drive using m_chassisSpeeds
//                 // get the wheel speeds
//                 MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(m_chassisSpeeds);

//                 // get the individual wheel speeds
//                 double leftFrontSpeed = wheelSpeeds.frontLeftMetersPerSecond;
//                 double rightFrontSpeed = wheelSpeeds.frontRightMetersPerSecond;
//                 double leftRearSpeed = wheelSpeeds.rearLeftMetersPerSecond;
//                 double rightRearSpeed = wheelSpeeds.rearRightMetersPerSecond;

//                 Constants.controllers.rightRearSpark.set(rightRearSpeed);
//                 Constants.controllers.leftRearSpark.set(-leftRearSpeed);
//                 Constants.controllers.rightFrontSpark.set(rightFrontSpeed);
//                 Constants.controllers.leftFrontSpark.set(-leftFrontSpeed);
//             } else if (DriverStation.isAutonomousEnabled()
//                     && stopwatchCounter >= AutoSubsystemValues.speeds.size() - 1) {
//                 // if we run out of code to run in auto, make sure everything is not moving
//                 Constants.controllers.shooterTopSpark.set(0.0);
//                 Constants.controllers.shooterBottomSpark.set(0.0);
//                 Constants.controllers.intakeTopSpark.set(0.0);

//                 Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);

//                 Constants.controllers.leftFrontSpark.set(0.0);
//                 Constants.controllers.leftRearSpark.set(0.0);
//                 Constants.controllers.rightFrontSpark.set(0.0);
//                 Constants.controllers.rightRearSpark.set(0.0);
//             }
//         } else if (auto == 3) { // do nothing
//             Constants.controllers.shooterTopSpark.set(0.0);
//             Constants.controllers.shooterBottomSpark.set(0.0);
//             Constants.controllers.intakeTopSpark.set(0.0);

//             Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);

//             Constants.controllers.leftFrontSpark.set(0.0);
//             Constants.controllers.leftRearSpark.set(0.0);
//             Constants.controllers.rightFrontSpark.set(0.0);
//             Constants.controllers.rightRearSpark.set(0.0);
//         }
//     }

//     public void setAuto(int auto) {
//         this.auto = auto;
//     }

//     /**
//      * This function is called when the button on the controller to operate this
//      * subsystem is activated
//      * 
//      * @param speed The speed to move this subsystem at
//      */
//     public void drive(ChassisSpeeds chassisSpeeds) {
//         m_chassisSpeeds = chassisSpeeds;
//     }

//     /**
//      * The following functions all are used to preprogram the auto paths
//      */
//     public void addSpeed(ChassisSpeeds speed) {
//         AutoSubsystemValues.speeds.add(speed);
//     }

//     public void addIntaking(double intake) {
//         AutoSubsystemValues.intaking.add(intake);
//     }

//     public void addShooting(double topSpeed, double bottomSpeed) {
//         AutoSubsystemValues.topSpeeds.add(topSpeed);
//         AutoSubsystemValues.bottomSpeeds.add(bottomSpeed);
//     }

//     public void addServo(double servo) {
//         AutoSubsystemValues.servos.add(servo);
//     }

//     public void clearShit() {
//         AutoSubsystemValues.servos.clear();
//         AutoSubsystemValues.topSpeeds.clear();
//         AutoSubsystemValues.bottomSpeeds.clear();
//         AutoSubsystemValues.speeds.clear();
//         AutoSubsystemValues.intaking.clear();
//         stopwatchCounter = -1;
//     }

//     public void printSpeeds() {
//         String toPrint = "";

//         // append the drive speeds
//         toPrint += "List<ChassisSpeeds> speeds = new LinkedList(Arrays.asList(";
//         for (int i = 0; i < AutoSubsystemValues.speeds.size(); i++) {
//             ChassisSpeeds speed = AutoSubsystemValues.speeds.get(i);
//             toPrint += "new ChassisSpeeds(";
//             toPrint += speed.vxMetersPerSecond + ",";
//             toPrint += speed.vyMetersPerSecond + ",";
//             ;
//             toPrint += speed.omegaRadiansPerSecond + ")";

//             if (i != AutoSubsystemValues.speeds.size() - 1) {
//                 toPrint += ",";
//             }
//         }
//         toPrint += "));";

//         // append the intake speeds
//         toPrint += "\n\nList<Double> intaking = new LinkedList<Double>(Arrays.asList(";
//         for (int i = 0; i < AutoSubsystemValues.intaking.size(); i++) {
//             double intake = AutoSubsystemValues.intaking.get(i);
//             toPrint += intake;

//             if (i != AutoSubsystemValues.intaking.size() - 1) {
//                 toPrint += ",";
//             }
//         }
//         toPrint += "));\n";

//         // append the shooter speeds
//         toPrint += "\n\nList<Double> topSpeeds = new LinkedList<Double>(Arrays.asList(";
//         for (int i = 0; i < AutoSubsystemValues.topSpeeds.size(); i++) {
//             double shoot = AutoSubsystemValues.topSpeeds.get(i);
//             toPrint += shoot;

//             if (i != AutoSubsystemValues.topSpeeds.size() - 1) {
//                 toPrint += ",";
//             }
//         }
//         toPrint += "));\n";

//         // append the shooter speeds
//         toPrint += "\n\nList<Double> bottomSpeeds = new LinkedList<Double>(Arrays.asList(";
//         for (int i = 0; i < AutoSubsystemValues.bottomSpeeds.size(); i++) {
//             double shoot = AutoSubsystemValues.bottomSpeeds.get(i);
//             toPrint += shoot;

//             if (i != AutoSubsystemValues.bottomSpeeds.size() - 1) {
//                 toPrint += ",";
//             }
//         }
//         toPrint += "));\n";

//         // append the servo speeds
//         toPrint += "\n\nList<Double> servos = new LinkedList<Double>(Arrays.asList(";
//         for (int i = 0; i < AutoSubsystemValues.servos.size(); i++) {
//             double servo = AutoSubsystemValues.servos.get(i);
//             toPrint += servo;

//             if (i != AutoSubsystemValues.servos.size() - 1) {
//                 toPrint += ",";
//             }
//         }
//         toPrint += "));\n";

//         System.out.println("\n");
//         System.out.println("\n");
//         System.out.println(toPrint);
//         System.out.println("\n");
//         System.out.println("\n");

//         AutoSubsystemValues.servos.clear();
//         AutoSubsystemValues.speeds.clear();
//         AutoSubsystemValues.topSpeeds.clear();
//         AutoSubsystemValues.bottomSpeeds.clear();
//         AutoSubsystemValues.intaking.clear();
//     }
// }
