/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.Ports;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
public class Arm extends PIDSubsystem {
  /**
   * Creates a new BallContainerManagement.
   */
  private final VictorSP arm_motor;
  private final AnalogPotentiometer arm_analog;
  public Arm() {
    //TODO: Measure P, I and D
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
      this.arm_analog = new AnalogPotentiometer(Ports.PORT_Arm_Potentiometer, 90, 0);
      this.arm_motor = new VictorSP(Ports.PORT_Arm_Motor);
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here    
    this.arm_motor.set(output);
  }
  @Override
  public double getMeasurement() {
    return this.arm_analog.pidGet();
  }
  public void lift_arm() {
    //TODO: Get setpoints for the up and down states of the arm
    this.setSetpoint(90);
  }
  public void release_arm() {
    this.setSetpoint(0);
  }
}
