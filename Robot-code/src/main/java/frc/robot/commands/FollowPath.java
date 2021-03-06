/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.RobotProperties;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.ball_intake_state;

public class FollowPath extends CommandBase {
  /**
   * Creates a new FollowPathA.
   */
  private Trajectory traj;
  private final Chassis m_chassis;
  private RamseteCommand com;
  private final String path_name;
  private final Intake m_intake;
  public FollowPath(Chassis c, String name, Intake i) {
    addRequirements(c, i);
    m_intake = i;
    path_name = name;
    m_chassis = c;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    File f = new File(Filesystem.getDeployDirectory().getAbsolutePath() + "/paths/" + path_name + ".wpilib.json");
    FileReader fr;
    String s = new String();
    try {
      fr = new FileReader(f);
      try {
        fr.read(s.toCharArray());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    try {
      traj = TrajectoryUtil.deserializeTrajectory(s);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    //TODO: Trajectory may be wrong, test trajectory
    com = new RamseteCommand(traj, m_chassis::getPose, new RamseteController(RobotProperties.K_RamseteB, RobotProperties.K_RamseteZeta), new SimpleMotorFeedforward(RobotProperties.K_sVolts, RobotProperties.K_vVoltSecondsPerMeter, RobotProperties.K_aVoltSecondsSquaredPerMeter), m_chassis.kinematics, m_chassis::getSpeeds, m_chassis.left_pid, m_chassis.right_pid, m_chassis::tankDriveVolts, m_chassis);
    m_intake.set_ball_intake(ball_intake_state.BALL_INTAKE);
    com.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    com.execute();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    com.end(interrupted);
    m_intake.set_ball_intake(ball_intake_state.BALL_STOP);
    m_chassis.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return com.isFinished();
  }
}
