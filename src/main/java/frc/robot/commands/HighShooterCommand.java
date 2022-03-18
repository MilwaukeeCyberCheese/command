package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.other.Stopwatch;

public class HighShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final ServoSubsystem m_servoSubsystem;
    private final Stopwatch timer;

    public HighShooterCommand(ShooterSubsystem m_shooterSubsystem, ServoSubsystem m_servoSubsystem) {
        this.m_shooterSubsystem = m_shooterSubsystem;
        this.m_servoSubsystem = m_servoSubsystem;
        timer = new Stopwatch();
        addRequirements(m_shooterSubsystem);
    }

    @Override
    public void execute() {

        if (!timer.isRunning()) {
            timer.start();
        }

        // this will spit the ball out nicely if the ball color does not match alliance
        // color
        // if (Robot.getColorSensor().hasBall()) {
        // if (Robot.getColorSensor().isRedBall() && DriverStation.getAlliance() !=
        // DriverStation.Alliance.Red
        // || Robot.getColorSensor().isBlueBall() && DriverStation.getAlliance() !=
        // DriverStation.Alliance.Blue) {
        // m_shooterSubsystem.drive(Constants.subsystems.shooter.TOP_WRONG_BALL_SPEED,
        // Constants.subsystems.shooter.BOTTOM_WRONG_BALL_SPEED);
        // //todo activate servo?
        // }
        // } else {
        m_shooterSubsystem.drive(Constants.subsystems.shooter.HIGH_TOP_SPEED, Constants.subsystems.shooter.HIGH_BOTTOM_SPEED);

        if (timer.getTime() > 800) {
            m_servoSubsystem.drive(0.2);
        } else {
            m_servoSubsystem.drive(0.4);
        }
        // }
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.drive(0.0, 0.0);
        Constants.controllers.servo.set(0.4);
        timer.stop();
        timer.reset();
    }
}