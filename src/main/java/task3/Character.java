package task3;

public class Character {
    private final String name;
    private SmileState smileState;
    private EmotionalState emotionalState;

    public Character(String name) {
        this.name = name;
        this.smileState = SmileState.NO_SMILE;
        this.emotionalState = EmotionalState.NEUTRAL;
    }

    public void smile(SmileState smileState) {
        this.smileState = smileState;
    }

    public void pressureOnPrisoners(Prisoner[] prisoners, PressureType pressureType) {
        PressureAction pressureAction = new PressureAction(pressureType);
        for (Prisoner prisoner : prisoners)
            prisoner.reactToPressure(pressureType);
        this.emotionalState = pressureAction.getEmotionalEffect();
    }

    public void feelReadyForMischief() {
        if (this.emotionalState == EmotionalState.REFRESHED)
            this.emotionalState = EmotionalState.READY_FOR_MISCHIEF;
    }

    public SmileState getSmileState() {
        return smileState;
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    public void setEmotionalState(EmotionalState emotionalState) {
        this.emotionalState = emotionalState;
    }
}
