/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class PIDSubsytem extends PIDSubsystem {
  /**
   * Creates a new PIDSubsytem.
   */
  int P,I,D =1;
  int integral, previous_error, setpoint = 0;
  Gyro gyro;
  private double error, derivate,rcw;
  DifferentialDrive robotDrive;
  public PIDSubsytem(Gyro gyro) {
    super(
        // The PIDController used by the subsystem
        new PIDController(0, 0, 0));
        this.gyro = gyro;
  }
  public void setSetPoint(int setpoint){
    this.setpoint = setpoint;
  }
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
  }
  public void PID(){
    this.error = this.setpoint - gyro.getAngle();
    this.integral += (error*.02);
    this.derivate = (this.error - this.previous_error) / .02;
    this.rcw = P*error + I*this.integral + D*derivate;
  }
  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
  public void execute(){
    PID();
    robotDrive.arcadeDrive(0, rcw);
  }
}
