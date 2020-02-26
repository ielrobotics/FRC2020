/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  /**
   * Creates a new Elevator.
   */
  private VictorSPX elevator_motor;
  public Elevator() {
    elevator_motor = new VictorSPX(5);
  }

  @Override
  public void periodic() {
    //TODO: Check if motor outputs need to be backwards
    switch (elev_state) {
      case ELEVATOR_STOPPED:
        elevator_motor.set(ControlMode.PercentOutput, 0);
      break;
      case ELEVATOR_DE_ESCALATE:
        elevator_motor.set(ControlMode.PercentOutput, -1);
      break;
      case ELEVATOR_ESCALATE:
        elevator_motor.set(ControlMode.PercentOutput, 1);
      break;
    }
    // This method will be called once per scheduler run
  }
  public enum elevator_states {
    ELEVATOR_STOPPED,
    ELEVATOR_ESCALATE,
    ELEVATOR_DE_ESCALATE
  }
  private elevator_states elev_state;
  public void set_elevator_state(elevator_states state) {
    elev_state = state;
  }
}
