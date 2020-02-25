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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */
  public DifferentialDrive drive;
  public Chassis(int frontLeft,int frontRight,int backLeft,int backRight) {
    //3 4 sag 1 2 sol
    drive = new DifferentialDrive(new SpeedControllerGroup(new WPI_TalonSRX(frontRight),new WPI_VictorSPX(backRight)),new SpeedControllerGroup(new SpeedControllerGroup(new WPI_TalonSRX(frontLeft),new WPI_VictorSPX(backLeft))));
    drive.setMaxOutput(0.7);
  }

  @Override
  public void periodic() {
    drive.feed();
    // This method will be called once per scheduler run
  }
}