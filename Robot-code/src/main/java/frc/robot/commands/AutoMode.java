/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallContainerManagement;
import frc.robot.subsystems.BallManagement;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.NavX;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoMode extends CommandBase{
  /**
   * Creates a new AutoMode.
   * There is an Automode command
   */
  private Chassis m_sub;
  /**
   * 
   * @param m_cont for ball status
   * @param m_ball for ball status
   * @param m_chassis for movement of robot
   * @param m_navx for positioning
   */
  private Encoder rightEncoder,leftEncoder;
  public AutoMode(BallContainerManagement m_cont, BallManagement m_ball, Chassis m_chassis, NavX m_navx)  {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //TODO: Add Auto Mode logic here
    super();
  }

@Override
  public void initialize() {
  }


  @Override
  public void execute() {
    // Driving robot
    m_sub.drive.arcadeDrive(1, 0.5);

    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}