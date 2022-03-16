package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import frc.robot.commands.ServoCommand;
import frc.robot.other.Stopwatch;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SerialPort.StopBits;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final Stopwatch timer;

    public ShooterCommand(ShooterSubsystem m_shooterSubsystem) {
        this.m_shooterSubsystem = m_shooterSubsystem;
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
        m_shooterSubsystem.drive(Constants.subsystems.shooter.TOP_SPEED, Constants.subsystems.shooter.BOTTOM_SPEED);

        if (timer.getTime() > 800) {
            Constants.controllers.servo.set(0.2);
        } else {
            Constants.controllers.servo.set(0.4);
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