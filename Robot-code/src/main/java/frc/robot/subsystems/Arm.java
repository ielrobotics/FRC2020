/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
public class Arm extends SubsystemBase {
  /**
   * Creates a new BallContainerManagement.
   */
  private final VictorSP arm_motor;
  private final AnalogPotentiometer arm_analog;
  public Arm() {
    //TODO: Measure P, I and D
      this.arm_analog = new AnalogPotentiometer(Ports.PORT_Arm_Potentiometer, 90, 0);
      this.arm_motor = new VictorSP(Ports.PORT_Arm_Motor);
      arm = arm_state.ARM_STOP;
  }
  public enum arm_state {
    ARM_ELEVATE,
    ARM_STOP,
    ARM_DE_ELEVATE
  }
  private arm_state arm;
  @Override
  public void periodic() {
    //TODO: Finish arm code
    switch (arm) {
      case ARM_DE_ELEVATE:
        if (arm_analog.pidGet())
      break;
      case ARM_STOP:

      break;
      case ARM_ELEVATE:

      break;
    }
  }
  public void lift_arm() {
    //TODO: Get setpoints for the up and down states of the arm
    this.setSetpoint(0);
  }
  public void release_arm() {
    this.setSetpoint(18);
  }
}
