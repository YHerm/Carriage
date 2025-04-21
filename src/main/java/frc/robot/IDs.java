package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;
import frc.robot.subsystems.mecanum.MotorID;
import frc.utils.battery.PowerDistributionDeviceID;

public class IDs {

	public static final PowerDistributionDeviceID POWER_DISTRIBUTION = new PowerDistributionDeviceID(1, PowerDistribution.ModuleType.kCTRE);

	public static class VictorSPXIDs {

		public static final MotorID FRONT_LEFT = new MotorID(0, false);
		public static final MotorID BACK_LEFT = new MotorID(1, false);
		public static final MotorID FRONT_RIGHT = new MotorID(2, false);
		public static final MotorID BACK_RIGHT = new MotorID(3, false);

	}

	public static class TalonFXIDs {
	}

	public static class CANCoderIDs {
	}

	public static class Pigeon2IDs {
	}

	public static class CANdleIDs {
	}

	public static class SparkMAXIDs {
	}

	public static class DigitalInputsIDs {
	}

}
