package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    public ShooterCommand(ShooterSubsystem m_shooterSubsystem) {
        this.m_shooterSubsystem = m_shooterSubsystem;

        addRequirements(m_shooterSubsystem);
    }
    
    @Override
    public void execute() {
        // this will spit the ball out nicely if the ball color does not match alliance color
        // if (Robot.getColorSensor().hasBall()) {
        //     if (Robot.getColorSensor().isRedBall() && DriverStation.getAlliance() != DriverStation.Alliance.Red
        //         || Robot.getColorSensor().isBlueBall() && DriverStation.getAlliance() != DriverStation.Alliance.Blue) {
        //         m_shooterSubsystem.drive(Constants.subsystems.shooter.TOP_WRONG_BALL_SPEED, Constants.subsystems.shooter.BOTTOM_WRONG_BALL_SPEED);
        //         //todo activate servo?
        //     }
        // } else {
            m_shooterSubsystem.drive(Constants.subsystems.shooter.TOP_SPEED, Constants.subsystems.shooter.BOTTOM_SPEED);
        // }
    }

     
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.drive(0.0, 0.0);
    }
}
