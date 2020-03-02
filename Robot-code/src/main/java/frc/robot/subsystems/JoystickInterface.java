/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JoystickInterface extends SubsystemBase {
  /**
   * Creates a new JoystickInterface.
   */
  public final Joystick joystick;
  public JoystickInterface() {
    this.joystick = new Joystick(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
