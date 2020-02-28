/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
public class EncoderSubsystem extends SubsystemBase {
  /**
   * Creates a new EncoderSubsystem.
   */
  private final Encoder right_encoder;
  private final Encoder left_encoder;
  private final AnalogInput manipulator_analog;
  public EncoderSubsystem() {
    //drive encoder 1: right motor, 0, 1
    //drive encoder 2: left motor, 2, 3
    //maniplator analog: 4
    right_encoder = new Encoder(0, 1);
    left_encoder = new Encoder(2, 3);
    manipulator_analog = new AnalogInput(4);
  }
  public void reset_drive_encoders() {
    left_encoder.reset();
    right_encoder.reset();
  }
  public double get_forward_distance() {
    return (left_encoder.getDistance() + right_encoder.getDistance()) / 2;
  }
  public double get_left_distance() {
    return left_encoder.getDistance();
  }
  public double get_right_distance() {
    return right_encoder.getDistance();
  }
  public double get_manipulator_pid() {
    return manipulator_analog.pidGet();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}