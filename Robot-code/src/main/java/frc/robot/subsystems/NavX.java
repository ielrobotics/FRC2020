/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
public class NavX extends SubsystemBase {
  /**
   * Creates a new NavX.
   */
  public AHRS ah;

  public NavX() {
    this.ah = new AHRS();
    while (this.ah.isCalibrating()) {
      //wait for the sensor to calibrate
    }
    this.ah.zeroYaw();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public double getAngle() {
    return this.ah.getAngle();
  }
}
