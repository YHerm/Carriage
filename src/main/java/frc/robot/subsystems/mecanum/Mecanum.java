package frc.robot.subsystems.mecanum;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.IDs;
import frc.utils.math.ToleranceMath;

public class Mecanum {

	private static final double DEADBAND = 0.02;

	private final PWMVictorSPX frontLeft;
	private final PWMVictorSPX frontRight;
	private final PWMVictorSPX backLeft;
	private final PWMVictorSPX backRight;

	private final MecanumDrive mecanumDrive;

	public Mecanum() {
		this.frontLeft = new PWMVictorSPX(IDs.VictorSPXIDs.FRONT_LEFT.channel());
		frontLeft.setInverted(IDs.VictorSPXIDs.FRONT_LEFT.inverted());

		this.backLeft = new PWMVictorSPX(IDs.VictorSPXIDs.BACK_LEFT.channel());
		backLeft.setInverted(IDs.VictorSPXIDs.BACK_LEFT.inverted());

		this.frontRight = new PWMVictorSPX(IDs.VictorSPXIDs.FRONT_RIGHT.channel());
		frontRight.setInverted(IDs.VictorSPXIDs.FRONT_RIGHT.inverted());

		this.backRight = new PWMVictorSPX(IDs.VictorSPXIDs.BACK_RIGHT.channel());
		backRight.setInverted(IDs.VictorSPXIDs.BACK_RIGHT.inverted());

		this.mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
	}

	public void drive(double xPower, double yPower, double rotationPower) {
		xPower = ToleranceMath.applyDeadband(xPower, DEADBAND);
		yPower = ToleranceMath.applyDeadband(yPower, DEADBAND);
		rotationPower = ToleranceMath.applyDeadband(rotationPower, DEADBAND);

		mecanumDrive.driveCartesian(xPower, yPower, rotationPower);
	}

	public void stop() {
		mecanumDrive.stopMotor();
	}

}
