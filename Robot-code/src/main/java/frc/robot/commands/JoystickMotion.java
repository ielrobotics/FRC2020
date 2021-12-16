/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OIConstants.Controller;
import frc.robot.Constants.OIConstants.Driver;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.JoystickInterface;
import frc.robot.subsystems.Arm.arm_state;
import frc.robot.subsystems.Intake.ball_intake_state;
import frc.robot.subsystems.Elevator.elevator_states;
public class JoystickMotion extends CommandBase {
  /**
   * Creates a new JoystickMotion.
   */
  private final Chassis m_sub;
  private final JoystickInterface m_joystick;
  private final Intake m_ball;
  private final Arm m_cont;
  private final Elevator m_elev;
  public JoystickMotion(Chassis m_chassis, JoystickInterface joystick, Intake ball, Arm cont, Elevator elev) {
    this.m_sub = m_chassis;
    this.m_cont = cont;
    this.m_ball = ball;
    this.m_elev = elev;
    this.m_joystick = joystick;
    addRequirements(this.m_sub, this.m_joystick, this.m_cont, this.m_ball, this.m_elev);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
double turboamount;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //joystick turbo key
    //Turbo key
    if (this.m_joystick.chassis_joystick.getRawButton(Driver.Button_Turbo)) {
      turboamount = 1.0;
    } else {
      turboamount = 0.7;
    }
    /*

    disable turbo if unwanted
    if (joystick.getRawButtonReleased(5)) {
      turboamount = 0.7;
    }
    */
    this.m_sub.drive.setMaxOutput(turboamount);
    this.m_sub.drive.arcadeDrive(
      -this.m_joystick.chassis_joystick.getRawAxis(Driver.Axis_X), 
      -this.m_joystick.chassis_joystick.getRawAxis(Driver.Axis_Y));
    //Ball throw key (throw constantly)
    /*
    if (joystick.getRawButtonPressed(1)) {
      m_cont.lift_arm();
      m_ball.set_ball_intake(-1);
    }
    if (joystick.getRawButtonReleased(1)) {
      m_ball.set_ball_intake(0);
      m_cont.release_arm();
      m_ball.reset_ball();
    }              
    */
    if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Intake)) {
      this.m_ball.set_ball_intake(ball_intake_state.BALL_INTAKE);
    } else if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Outtake)) {
      this.m_ball.set_ball_intake(ball_intake_state.BALL_OUTTAKE);
    } else {
      this.m_ball.set_ball_intake(ball_intake_state.BALL_STOP);
    }

    if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Raise_Arm)) {
      this.m_cont.set_state(arm_state.ARM_ELEVATE);
    } else if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Lower_Arm)) {
      this.m_cont.set_state(arm_state.ARM_DE_ELEVATE);
    } else {
      this.m_cont.set_state(arm_state.ARM_STOP);
    }
    if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Lift)) {
      this.m_elev.set_elevator_state(elevator_states.ELEVATOR_ESCALATE);
    } else if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Pull)) {
      this.m_elev.set_elevator_state(elevator_states.ELEVATOR_DE_ESCALATE);
    } else {
      this.m_elev.set_elevator_state(elevator_states.ELEVATOR_STOPPED);
    }
    if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Lift_Green)) {
      this.m_elev.set_elevator_green_state(elevator_states.ELEVATOR_ESCALATE);
    } else if (this.m_joystick.control_joystick.getRawButton(Controller.Button_Pull_Green)) {
      this.m_elev.set_elevator_green_state(elevator_states.ELEVATOR_DE_ESCALATE);
    } else {
      this.m_elev.set_elevator_green_state(elevator_states.ELEVATOR_STOPPED);
    }
    /*
    if (!joystick.getRawButton(1) && joystick.getRawButton(2)) {
      m_ball.set_ball_intake(1);
    } else if (!joystick.getRawButton(1)) {
      m_ball.set_ball_intake(0);
    }
    */
    //m_cont.container_lift_motor.set(joystick.getRawAxis(1) * 0.3 - 0.1);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ball.set_ball_intake(ball_intake_state.BALL_STOP);
    m_cont.set_state(arm_state.ARM_STOP);
    m_elev.set_elevator_state(elevator_states.ELEVATOR_STOPPED);
    m_elev.set_elevator_green_state(elevator_states.ELEVATOR_STOPPED);
  }
  // Returns true when the command should end. test commit
  @Override
  public boolean isFinished() {
    return false;
  }
}