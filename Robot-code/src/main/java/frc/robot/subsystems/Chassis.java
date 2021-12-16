/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.RobotProperties;
import frc.robot.Constants.Ports;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */
  public final DifferentialDrive drive;
  public final WPI_TalonSRX front_right_talon;
  public final WPI_TalonSRX front_left_talon;
  public final PIDController left_pid;
  public final PIDController right_pid;
  public final DifferentialDriveKinematics kinematics;
  private final SpeedControllerGroup left_controller_group;
  private final SpeedControllerGroup right_controller_group;
  private final DifferentialDriveOdometry odometry;
  private final WPI_TalonSRX back_left_talon;
  private final WPI_TalonSRX back_right_talon;
  private final AHRS ah;
  /**
   * Creates a Chassis object.
   * @param frontLeft The CAN ID of the front left Talon SRX
   * @param frontRight The CAN ID of the front right Talon SRX
   * @param backLeft The CAN ID of the back left Victor SPX
   * @param backRight The CAN ID of the back right Victor SPX
   */
  public Chassis() {
    //Initializes Talon SRXes
    this.front_right_talon = new WPI_TalonSRX(Ports.PORT_Chassis_FrontRight);
    this.front_left_talon = new WPI_TalonSRX(Ports.PORT_Chassis_FrontLeft);
    this.back_left_talon = new WPI_TalonSRX(Ports.PORT_Chassis_BackLeft);
    this.back_right_talon = new WPI_TalonSRX(Ports.PORT_Chassis_BackRight);
    this.ah = new AHRS();
    ah.zeroYaw();

    //TODO: get PID values
    //currently temporarily using P=1 I=0 D=0
    left_pid = new PIDController(1, 0, 0);
    right_pid = new PIDController(1, 0, 0);
    this.front_right_talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    this.front_left_talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    right_controller_group = new SpeedControllerGroup(this.front_right_talon, this.back_right_talon);
    left_controller_group = new SpeedControllerGroup(this.front_left_talon, this.back_left_talon);

    this.drive = new DifferentialDrive(right_controller_group,left_controller_group);
    this.drive.setMaxOutput(0.7);

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()), new Pose2d());
    kinematics = new DifferentialDriveKinematics(RobotProperties.K_trackWidthMeters);
  }

  public double get_right_pid() {
    return right_pid.calculate(get_left_sensor());
  }
  public double get_left_pid() {
    return left_pid.calculate(get_left_sensor());
  }
  @Override
  public void periodic() {
    get_right_pid();
    get_left_pid();
    this.drive.feed();
    //TODO: maybe wait for it to calibrate instead? test to see if this causes problems
    if (!ah.isCalibrating()) {
      odometry.update(Rotation2d.fromDegrees(getHeading()), get_left_sensor(), get_right_sensor());
    }
  }
  public double get_right_sensor() {
    return this.front_right_talon.getSelectedSensorPosition() * RobotProperties.K_encoderToMeters;
  }
  public double get_left_sensor() {
    return this.front_left_talon.getSelectedSensorPosition() * RobotProperties.K_encoderToMeters;
  }
  private double getAngle() {
    return this.ah.getAngle();
  }
  public double getHeading() {
    return -Math.IEEEremainder(getAngle(), 360);
  }
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(front_left_talon.getSelectedSensorVelocity() * RobotProperties.K_encoderToMeters, front_right_talon.getSelectedSensorVelocity() * RobotProperties.K_encoderToMeters);
  }
  public void tankDriveVolts(double a, double b) {
    left_controller_group.setVoltage(a);
    right_controller_group.setVoltage(-b);
    drive.feed();
  }
}