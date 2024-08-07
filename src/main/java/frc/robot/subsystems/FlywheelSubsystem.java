// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FlywheelSubsystem extends SubsystemBase {

  public static REVPhysicsSim revPhysicsSim = new REVPhysicsSim();
  public static CANSparkMax canSparkMax = new CANSparkMax(2, MotorType.kBrushless);

  /** Creates a new FlywheelSubsystem. */
  public FlywheelSubsystem() {
    REVPhysicsSim.getInstance().addSparkMax(canSparkMax, DCMotor.getNEO(0));
    setVelocity(Constants.Flywheel.idleRPM);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    REVPhysicsSim.getInstance().run();
  }

  public void setVelocity(double rpm){
    canSparkMax.getPIDController().setReference(rpm, ControlType.kVelocity);
    REVPhysicsSim.getInstance().run();
  }

  public double getVelocity() {
    return canSparkMax.getEncoder().getVelocity();
  }

  public boolean isFlywheelAtSpeed() {
    return getVelocity() >= Constants.Flywheel.firingRPM;
  }
}
