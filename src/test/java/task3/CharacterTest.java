package task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Простетный Вогон Джельц улыбнулся очень медленно. ...
Он только что побаловал себя освежающей серией воплей на своих пленников,
и теперь чувствовал себя отдохнувшим и готовым к небольшой гнусности.
*/
class CharacterTest {
    @Test
    void testSmile() {
        Character vogon = new Character("Вогон");
        vogon.smile(SmileState.SLOW);
        assertEquals(SmileState.SLOW, vogon.getSmileState());
    }

    @Test
    void testPressureOnPrisoners() {
        Character vogon = new Character("Вогон");
        Prisoner[] prisoners = { new Prisoner(), new Prisoner() };
        vogon.pressureOnPrisoners(prisoners, PressureType.SCREAM);
        assertEquals(EmotionalState.REFRESHED, vogon.getEmotionalState());
        for (Prisoner prisoner : prisoners)
            assertEquals(EmotionalState.SCARED, prisoner.getEmotionalState());
    }

    @Test
    void testSmileToPrisoners() {
        Character vogon = new Character("Вогон");
        Prisoner[] prisoners = { new Prisoner(), new Prisoner() };
        vogon.pressureOnPrisoners(prisoners, PressureType.SMILE);
        assertNotEquals(EmotionalState.REFRESHED, vogon.getEmotionalState());
        for (Prisoner prisoner : prisoners)
            assertNotEquals(EmotionalState.SCARED, prisoner.getEmotionalState());
    }

    @Test
    void testFeelReadyForMischief() {
        Character vogon = new Character("Вогон");

        vogon.setEmotionalState(EmotionalState.REFRESHED);
        vogon.feelReadyForMischief();
        assertEquals(EmotionalState.READY_FOR_MISCHIEF, vogon.getEmotionalState());

        vogon.setEmotionalState(EmotionalState.CALM);
        vogon.feelReadyForMischief();
        assertEquals(EmotionalState.CALM, vogon.getEmotionalState());
    }
}