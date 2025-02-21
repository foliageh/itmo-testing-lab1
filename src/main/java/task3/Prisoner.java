package task3;

public class Prisoner {
    private EmotionalState emotionalState;

    public Prisoner() {
        this.emotionalState = EmotionalState.CALM;
    }

    public void reactToPressure(PressureType pressureType) {
        if (pressureType == PressureType.SCREAM) {
            this.emotionalState = EmotionalState.SCARED;
            //System.out.println("Пленник напуган");
        } else {
            this.emotionalState = EmotionalState.NEUTRAL;
        }
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }
}