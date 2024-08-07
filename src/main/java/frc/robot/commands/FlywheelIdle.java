// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.FlywheelSubsystem;

public class FlywheelIdle extends Command {
  FlywheelSubsystem m_FlywheelSubsystem;

  /** Creates a new FlywheelKill. */
  public FlywheelIdle(FlywheelSubsystem flywheelSubsystem) {
    m_FlywheelSubsystem = flywheelSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(flywheelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_FlywheelSubsystem.setVelocity(Constants.Flywheel.idleRPM);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
