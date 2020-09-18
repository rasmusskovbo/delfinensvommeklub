package data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getAge_is29() {
        Member member = new Member(LocalDate.of(1990, 07, 02), "Test", true);

        assertEquals(29, member.getAge());
    }

    @Test
    void isSenior() {
        Member member = new Member(LocalDate.of(1990, 07, 02), "Test", true);

        assertEquals(true, member.isSenior());
    }
}