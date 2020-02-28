/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
public class EncoderSubsystem extends SubsystemBase {
  /**
   * Creates a new EncoderSubsystem.
   */
  private final AnalogInput manipulator_analog;
  public EncoderSubsystem() {
    //maniplator analog: 0
    manipulator_analog = new AnalogInput(4);
  }
  public double get_manipulator_pid() {
    return manipulator_analog.pidGet();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}