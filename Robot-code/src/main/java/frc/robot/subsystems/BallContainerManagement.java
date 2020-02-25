/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
public class BallContainerManagement extends PIDSubsystem {
  /**
   * Creates a new BallContainerManagement.
   */
  private VictorSP ball_motor;
  private AnalogInput input;
  public BallContainerManagement() {
    //TODO: measure P, I and D
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
      ball_motor = new VictorSP(1);
      input = new AnalogInput(0);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here    
    ball_motor.set(output);
  }
  @Override
  public double getMeasurement() {
    //TODO: check if this blows up the robot when left at this state
    return input.pidGet();
  }
  public void lift_arm() {
    //TODO: Get setpoints for the up and down states of the arm
    this.setSetpoint(1);
  }
  public void release_arm() {
    this.setSetpoint(0);
  }
}
