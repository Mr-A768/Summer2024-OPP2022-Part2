// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVPhysicsSim;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.DIOSim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class IndexerSubsystem extends SubsystemBase {

  public static REVPhysicsSim revPhysicsSim = new REVPhysicsSim();
  public static CANSparkMax canSparkMax = new CANSparkMax(1, MotorType.kBrushless);

  public static DIOSim beamBreak = new DIOSim(0); // Sets up a simulated DIO Port to act as a beam break

  public static Trigger beamStatus = new Trigger(() -> beamBreak.getValue()); // Exposes the beam break as a trigger to block controller inputs while the beam is tripped
  
  /** Creates a new SparkMaxSimSubsystem. */
  public IndexerSubsystem() {
    REVPhysicsSim.getInstance().addSparkMax(canSparkMax, DCMotor.getNEO(0));
    beamBreak.setInitialized(true);
    beamBreak.setValue(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    REVPhysicsSim.getInstance().run();
  }

  public void kill(){
    canSparkMax.getPIDController().setReference(0, ControlType.kVelocity);
    REVPhysicsSim.getInstance().run();
  }

  public void run(){
    canSparkMax.getPIDController().setReference(1000, ControlType.kVelocity);
    REVPhysicsSim.getInstance().run();
  }

  public void resetBeamBreak() {
    beamBreak.setValue(false);
  }

}
