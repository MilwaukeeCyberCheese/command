package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.other.Stopwatch;

public class HighShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final Stopwatch timer;
    private double topSpeed = 0.0;
    private double bottomSpeed = 0.0;

    public HighShooterCommand(ShooterSubsystem m_shooterSubsystem) {
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
        if (topSpeed == 0 && bottomSpeed == 0) {
            if (Robot.getColorSensor().getProximity() > 350
                    && (Robot.getColorSensor().getRed() > Robot.getColorSensor().getBlue()
                            && DriverStation.getAlliance() != DriverStation.Alliance.Red
                            || Robot.getColorSensor().getBlue() > Robot.getColorSensor().getRed()
                                    && DriverStation.getAlliance() != DriverStation.Alliance.Blue)) {
                topSpeed = Constants.subsystems.shooter.TOP_WRONG_BALL_SPEED;
                bottomSpeed = Constants.subsystems.shooter.BOTTOM_WRONG_BALL_SPEED;
            } else {
                topSpeed = Constants.subsystems.shooter.HIGH_TOP_SPEED;
                bottomSpeed = Constants.subsystems.shooter.HIGH_BOTTOM_SPEED;
            }
        }

        m_shooterSubsystem.drive(topSpeed, bottomSpeed);

        if (timer.getTime() > 800) {
            Constants.controllers.servo.set(Constants.subsystems.servo.OPEN_POS);
        } else {
            Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);
        }
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        topSpeed = 0.0;
        bottomSpeed = 0.0;
        m_shooterSubsystem.drive(0.0, 0.0);
        Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);
        timer.stop();
        timer.reset();
    }
}