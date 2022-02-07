package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem m_shooterSubsystem;

    public ShooterCommand(ShooterSubsystem shooterSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
    }
    
    @Override
    public void execute() {
        
    }

     /**
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {

    }
}
