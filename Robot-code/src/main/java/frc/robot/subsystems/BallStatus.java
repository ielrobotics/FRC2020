/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStatus extends SubsystemBase {
  /**
   * Creates a new BallStatus.
   */
  public BallStatus() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  //TODO: Implement ball throwing
  public void ballThrow() {
    if (slotFull()) return;
  }
  //TODO: Check if ball slot is full
  public Boolean slotFull() {
    return false;
  }
}
