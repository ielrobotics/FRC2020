/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.JoystickMotion;
import frc.robot.commands.MinimalAutoMode;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.JoystickInterface;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_teleopCommand;
  private JoystickInterface m_joystick;
  private Chassis m_chassis;
  private Arm m_cont;
  private Intake m_ball;
  private Elevator m_elev;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    this.m_joystick = new JoystickInterface();
    //FrontLeft = 1 FrontRight = 2 BackLeft = 3 BackRight = 4
    //1, 2 are talons
    this.m_chassis = new Chassis();
    this.m_cont = new Arm();
    this.m_ball = new Intake();
    this.m_elev = new Elevator();
  }



  
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = new ImageRecognitionMotion(m_chassis, m_comms, m_cont, m_ball);
   //TODO: update x according to robot position on field
   //x is the distance betweed the robot and the edge of the wall closest to the target zone
   //this.m_autonomousCommand = new AutoMode(this.m_cont, this.m_ball, this.m_chassis, 164.4);
   //TODO: replace minimal auto sometime
   this.m_autonomousCommand = new MinimalAutoMode(m_chassis, m_cont, m_ball);
   this.m_autonomousCommand.schedule();
    //only needed for switcheroo
    if (this.m_teleopCommand != null) {
      this.m_teleopCommand.cancel();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (this.m_autonomousCommand != null) {
      this.m_autonomousCommand.cancel();
    }
    this.m_teleopCommand = new JoystickMotion(this.m_chassis, this.m_joystick, this.m_ball, this.m_cont, this.m_elev);
    this.m_teleopCommand.schedule();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  
  }
}