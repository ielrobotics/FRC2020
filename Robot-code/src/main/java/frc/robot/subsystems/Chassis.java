/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.JoystickMotion;
import edu.wpi.first.wpilibj.VictorSP;
public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */
  public DifferentialDrive drive;
  public Chassis(JoystickInterface m_joystick) {
    //5 6 sag 7 8 sol
    drive = new DifferentialDrive(new SpeedControllerGroup(new VictorSP(5), new VictorSP(6)), new SpeedControllerGroup(new VictorSP(7),new VictorSP(8)));
    drive.setMaxOutput(0.7);
    
    setDefaultCommand(new JoystickMotion(this, m_joystick));
  }

  @Override
  public void periodic() {
    drive.feed();
    // This method will be called once per scheduler run
  }
}