/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallContainerManagement extends SubsystemBase {
  /**
   * Creates a new BallContainerManagement.
   */
  private final VictorSP container_lift_motor;
  public BallContainerManagement() {
    container_lift_motor = new VictorSP(00);
  }

  @Override
  public void periodic() {
    container_lift_motor.feed();
    // This method will be called once per scheduler run
  }
  public void lift_arm() {
    //TODO: lift arm
  }
  public void release_arm() {
    //TODO: release arm
  }
}
