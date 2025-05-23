package frc.robot.subsystems.swerve.states;

import frc.robot.subsystems.swerve.states.aimassist.AimAssist;
import frc.robot.subsystems.swerve.states.heading.HeadingControl;
import org.littletonrobotics.junction.Logger;

public class SwerveState {

	private static final DriveRelative DEFAULT_DRIVE_RELATIVE = DriveRelative.ALLIANCE_RELATIVE;
	private static final DriveSpeed DEFAULT_DRIVE_SPEED = DriveSpeed.NORMAL;
	private static final LoopMode DEFAULT_LOOP_MODE = LoopMode.CLOSED;
	private static final RotateAxis DEFAULT_ROTATE_AXIS = RotateAxis.MIDDLE_OF_CHASSIS;
	private static final AimAssist DEFAULT_AIM_ASSIST = AimAssist.NONE;
	private static final HeadingControl DEFAULT_HEADING_CONTROL = HeadingControl.NONE;

	public static final SwerveState DEFAULT_PATH_PLANNER = new SwerveState().withDriveRelative(DriveRelative.ROBOT_RELATIVE);
	public static final SwerveState DEFAULT_DRIVE = new SwerveState();


	private DriveRelative driveRelative;
	private DriveSpeed driveSpeed;
	private LoopMode loopMode;
	private RotateAxis rotateAxis;
	private AimAssist aimAssist;
	private HeadingControl headingControl;

	public SwerveState(SwerveState swerveState) {
		this(
			swerveState.driveRelative,
			swerveState.driveSpeed,
			swerveState.loopMode,
			swerveState.rotateAxis,
			swerveState.aimAssist,
			swerveState.headingControl
		);
	}

	private SwerveState() {
		this(DEFAULT_DRIVE_RELATIVE, DEFAULT_DRIVE_SPEED, DEFAULT_LOOP_MODE, DEFAULT_ROTATE_AXIS, DEFAULT_AIM_ASSIST, DEFAULT_HEADING_CONTROL);
	}

	private SwerveState(
		DriveRelative driveRelative,
		DriveSpeed driveSpeed,
		LoopMode loopMode,
		RotateAxis rotateAxis,
		AimAssist aimAssist,
		HeadingControl headingControl
	) {
		this.driveRelative = driveRelative;
		this.driveSpeed = driveSpeed;
		this.loopMode = loopMode;
		this.rotateAxis = rotateAxis;
		this.aimAssist = aimAssist;
		this.headingControl = headingControl;
	}

	public void log(String logPath) {
		Logger.recordOutput(logPath + "/DriveMode", driveRelative);
		Logger.recordOutput(logPath + "/DriveSpeed", driveSpeed);
		Logger.recordOutput(logPath + "/LoopMode", loopMode);
		Logger.recordOutput(logPath + "/RotateAxis", rotateAxis);
		Logger.recordOutput(logPath + "/AimAssist", aimAssist);
		Logger.recordOutput(logPath + "/HeadingControl", headingControl);
	}

	public SwerveState withDriveRelative(DriveRelative driveRelative) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.driveRelative = driveRelative;
		return swerveState;
	}

	public SwerveState withDriveSpeed(DriveSpeed driveSpeed) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.driveSpeed = driveSpeed;
		return swerveState;
	}

	public SwerveState withLoopMode(LoopMode loopMode) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.loopMode = loopMode;
		return swerveState;
	}

	public SwerveState withRotateAxis(RotateAxis rotateAxis) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.rotateAxis = rotateAxis;
		return swerveState;
	}

	public SwerveState withAimAssist(AimAssist aimAssist) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.aimAssist = aimAssist;
		return swerveState;
	}

	public SwerveState withHeadingControl(HeadingControl headingControl) {
		SwerveState swerveState = new SwerveState(this);
		swerveState.headingControl = headingControl;
		return swerveState;
	}


	public DriveRelative getDriveRelative() {
		return driveRelative;
	}

	public DriveSpeed getDriveSpeed() {
		return driveSpeed;
	}

	public LoopMode getLoopMode() {
		return loopMode;
	}

	public RotateAxis getRotateAxis() {
		return rotateAxis;
	}

	public AimAssist getAimAssist() {
		return aimAssist;
	}

	public HeadingControl getHeadingControl() {
		return headingControl;
	}

}
