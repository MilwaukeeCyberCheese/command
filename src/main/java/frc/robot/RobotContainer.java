// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LowShooterCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ServoCommand;
import frc.robot.commands.HighShooterCommand;
import frc.robot.other.FilteredController;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static boolean readAuto = false;

  // input controllers
  private static final XboxController m_controller = new XboxController(0);
  public static final FilteredController filteredController = new FilteredController(m_controller);

  // the robot's subsystems and commands are defined here...
  public static final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  public static final DriveCommand m_driveCommand = new DriveCommand(
      m_driveSubsystem,
      () -> (modifyAxis(filteredController.getYLeft(.2)) * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND)
          * Constants.subsystems.drive.strafe,
      () -> (-modifyAxis(filteredController.getXLeft(.2)) * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND)
          * Constants.subsystems.drive.strafe,
      () -> (modifyAxis(filteredController.getXRight(.2)) * -DriveSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
          * Constants.subsystems.drive.turnRate);

  public static final AutoSubsystem m_autoSubsystem = new AutoSubsystem();
  private static final AutoCommand m_autoCommand = new AutoCommand(m_autoSubsystem);

  private static final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private static final IntakeCommand m_intakeCommand = new IntakeCommand(m_intakeSubsystem);
  private static final OuttakeCommand m_outtakeCommand = new OuttakeCommand(m_intakeSubsystem);
  // private static final OuttakeSubsystem m_outtakeSubsystem = new OuttakeSubsystem();
  // private static final OuttakeCommand m_outtakeCommand = new OuttakeCommand(m_intakeSubsystem);

  private static final ServoSubsystem m_servoSubsystem = new ServoSubsystem();
  private static final ServoCommand m_servoCommand = new ServoCommand(m_servoSubsystem);

  private static final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private static final HighShooterCommand m_highShooterCommand = new HighShooterCommand(m_shooterSubsystem, m_servoSubsystem);
  private static final LowShooterCommand m_lowShooterCommand = new LowShooterCommand(m_shooterSubsystem, m_servoSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new Button(filteredController::getLeftTriggerActive).whileHeld(m_intakeCommand);
    new Button(filteredController::getLeftBumper).whileHeld(m_outtakeCommand);
    new Button(filteredController::getRightTriggerActive).whileHeld(m_highShooterCommand);
    new Button(filteredController::getRightBumper).whileHeld(m_lowShooterCommand);
    new Button(filteredController::getAButton).whileHeld(m_servoCommand);
    // new Button(filteredController::getPOVPressed).whenActive(new Runnable() {
    //   @Override
    //   public void run() {
    //     if (!readAuto) {
    //       readAuto = true;
    //       System.out.println("Started - Begin Tracking Autonomous");
    //     } else {
    //       readAuto = false;
    //       System.out.println("Ended - Finished Tracking Autonomous");
    //       m_autoSubsystem.printSpeeds();
    //     }
    //   }
    // });
  }

  /**
   * Modifies the controller joysticks
   * @param value
   * @return
   */
  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  /**
   * Deadband for the controller joysticks
   * @param value
   * @param deadband
   * @return
   */
  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }
}
