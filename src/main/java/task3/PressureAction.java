package task3;

public class PressureAction {
    private final PressureType pressureType;
    private final EmotionalState emotionalEffect;

    public PressureAction(PressureType pressureType) {
        this.pressureType = pressureType;
        if (pressureType == PressureType.SCREAM)
            this.emotionalEffect = EmotionalState.REFRESHED;
        else this.emotionalEffect = EmotionalState.READY_FOR_MISCHIEF;
    }

    public EmotionalState getEmotionalEffect() {
        return emotionalEffect;
    }
}