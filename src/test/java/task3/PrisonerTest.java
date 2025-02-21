package task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrisonerTest {
    @Test
    void testReactToScream() {
        Prisoner prisoner = new Prisoner();
        prisoner.reactToPressure(PressureType.SCREAM);
        assertEquals(EmotionalState.SCARED, prisoner.getEmotionalState());
    }

    @Test
    void testReactToSmile() {
        Prisoner prisoner = new Prisoner();
        prisoner.reactToPressure(PressureType.SMILE);
        assertEquals(EmotionalState.NEUTRAL, prisoner.getEmotionalState());
    }
}