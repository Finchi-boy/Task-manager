package dev.maciejfrackiewicz.task_manager.task;

import dev.maciejfrackiewicz.task_manager.task.dto.CreateTaskRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateTaskRequestTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldFailWhenTitleIsBlank() {
        CreateTaskRequest request = new CreateTaskRequest(null, "", null, null, null);
        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailTitleIsTooLong() {
        CreateTaskRequest request = new CreateTaskRequest(null, "123451234512345123451234512345", null, null, null);
        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailDescriptionIsTooLong() {
        CreateTaskRequest request = new CreateTaskRequest(null, "test", "Lose john poor same it case do year we. Full how way even the sigh. Extremely nor furniture fat questions now provision incommode preserved. Our side fail find like now. Discovered travelling for insensible partiality unpleasing impossible she. Sudden up my excuse to suffer ladies though or. Bachelor possible marianne directly confined relation as on he.\n" +
                "\n" +
                "How promotion excellent curiosity yet attempted happiness. Gay prosperous impression had conviction. For every delay death ask style. Me mean able my by in they. Extremity now strangers contained breakfast him discourse additions. Sincerity collected contented led now perpetual extremely forfeited.\n" +
                "\n" +
                "For who thoroughly her boy estimating conviction. Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection favourable mrs can projecting own. Thirty it matter enable become admire in giving. See resolved goodness felicity shy civility domestic had but. Drawings offended yet answered jennings perceive laughing six did far.", null, null);
        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenDeadlineIsPast() {
        CreateTaskRequest request = new CreateTaskRequest(null, "test", null, null, LocalDateTime.now().minusDays(1));
        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldPass()
    {
        CreateTaskRequest request = new CreateTaskRequest(null, "passed","Short description",null,LocalDateTime.now().plusDays(10));
        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

}