package task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PressureActionTest {
    @Test
    void testScreamCreation() {
        PressureAction action = new PressureAction(PressureType.SCREAM);
        assertEquals(EmotionalState.REFRESHED, action.getEmotionalEffect());
    }

    @Test
    void testSmileCreation() {
        PressureAction action = new PressureAction(PressureType.SMILE);
        assertEquals(EmotionalState.READY_FOR_MISCHIEF, action.getEmotionalEffect());
    }
}