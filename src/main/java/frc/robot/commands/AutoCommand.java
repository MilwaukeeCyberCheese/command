package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystem;

public class AutoCommand extends CommandBase {
    private final AutoSubsystem m_autoSubsystem;

    public AutoCommand(AutoSubsystem autoSubsystem) {
        this.m_autoSubsystem = autoSubsystem;
    }

    public void setAuto(int auto) {
        m_autoSubsystem.setAuto(auto);
    }

    @Override
    public void execute() {
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * 
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_autoSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
