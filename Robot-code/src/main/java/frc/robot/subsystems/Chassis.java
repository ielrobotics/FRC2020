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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */
  public DifferentialDrive drive;
  public Chassis() {
    //2 3 sag 0 1 sol
    drive = new DifferentialDrive(new SpeedControllerGroup(new WPI_VictorSPX(0),new WPI_VictorSPX(1)),new SpeedControllerGroup(new WPI_VictorSPX(2),new WPI_VictorSPX(3)));
    drive.setMaxOutput(0.7);
  }

  @Override
  public void periodic() {
    drive.feed();
    // This method will be called once per scheduler run
  }
}