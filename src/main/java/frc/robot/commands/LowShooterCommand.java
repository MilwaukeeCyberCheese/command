package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.other.Stopwatch;

public class LowShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;
    private final Stopwatch timer;

    public LowShooterCommand(ShooterSubsystem m_shooterSubsystem) {
        this.m_shooterSubsystem = m_shooterSubsystem;
        timer = new Stopwatch();
        addRequirements(m_shooterSubsystem);
    }

    @Override
    public void execute() {

        if (!timer.isRunning()) {
            timer.start();
        }

        if (timer.getTime() > 800) {
            Constants.controllers.servo.set(Constants.subsystems.servo.OPEN_POS);
        } else {
            Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);
        }

        if (timer.getTime() > 1800) {
            m_shooterSubsystem.drive(0.0, 0.0);
        } else {
            m_shooterSubsystem.drive(Constants.subsystems.shooter.LOW_TOP_SPEED,
                    Constants.subsystems.shooter.LOW_BOTTOM_SPEED);
        }
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.drive(0.0, 0.0);
        Constants.controllers.servo.set(Constants.subsystems.servo.CLOSED_POS);
        timer.stop();
        timer.reset();
    }
}