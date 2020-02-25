/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AutoRotating extends SubsystemBase {
  /**
   * Creates a new AutoRotating.
   */
  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX frontRight;
  private WPI_VictorSPX backLeft;
  private WPI_VictorSPX backRight;
  private Chassis chassis;

  public AutoRotating(Chassis chassis,WPI_TalonSRX frontleft,WPI_TalonSRX frontRight,WPI_VictorSPX backleft,WPI_VictorSPX backright){
    this.chassis=chassis;
    this.frontLeft=frontleft;
    this.frontRight=frontRight;
    this.backLeft=backleft;
    this.backRight=backright;
    this.chassis=new Chassis(new WPI_TalonSRX(1),new WPI_TalonSRX(2),new WPI_VictorSPX(3),new WPI_VictorSPX(4));

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
