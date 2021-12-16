/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;
import frc.robot.Constants.RobotProperties;
import edu.wpi.first.wpilibj.VictorSP;
public class Arm extends SubsystemBase {
  /**
   * Creates a new BallContainerManagement.
   */
  private final VictorSP arm_motor;
  public Arm() {
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
    switch (arm) {
      case ARM_DE_ELEVATE:
      System.out.println("ARM_DE_ELEVATE");
        arm_motor.set(1);
      break;
      case ARM_STOP:
      System.out.println("ARM_STOP");
        arm_motor.set(0);
        
      break;
      case ARM_ELEVATE:
      System.out.println("ARM_ELEVATE");
        arm_motor.set(-1);
      break;
    }
  }
  public void set_state(arm_state state) {
    System.out.println(state);
    this.arm = state;
  }
}
