package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystem;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;

    public AutoCommand(AutoSubsystem autoSubsystem) {
        this.m_autoSubsystem = autoSubsystem;
    }
    
    @Override
    public void execute() {
        m_autoSubsystem.startStopwatch();
    }

     /**
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_autoSubsystem.stopStopwatch();
        m_autoSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
