// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IndexerSubsystem;

public class FireCommand extends Command {
  FlywheelSubsystem m_FlywheelSubsystem;
  IndexerSubsystem m_IndexerSubsystem;

  /** Creates a new FireCommand. */
  public FireCommand(FlywheelSubsystem flywheelSubsystem, IndexerSubsystem indexerSubsystem) {
    m_FlywheelSubsystem = flywheelSubsystem;
    m_IndexerSubsystem = indexerSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexerSubsystem, flywheelSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (IndexerSubsystem.beamBreak.getValue()){ // Checks if beam break is tripped
      m_FlywheelSubsystem.setVelocity(Constants.Flywheel.firingRPM); // if so, set flywheel to firing speed and continue
    } else {
      isFinished(); // if not, end the command
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_FlywheelSubsystem.isFlywheelAtSpeed()){ // waits for the flywheel to get up to speed before running the indexer forwards
      m_IndexerSubsystem.run();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IndexerSubsystem.kill(); // Stops the indexer
    m_FlywheelSubsystem.setVelocity(Constants.Flywheel.idleRPM); // Sets the flywheel back to idle speed
    m_IndexerSubsystem.resetBeamBreak(); // Sets the beam break back to false (as irl, the cargo won't be tripping the beam break)
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
