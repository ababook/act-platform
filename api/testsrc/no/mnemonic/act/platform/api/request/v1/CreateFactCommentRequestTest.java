package no.mnemonic.act.platform.api.request.v1;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateFactCommentRequestTest extends AbstractRequestTest {

  @Test
  public void testDecodeRequest() throws Exception {
    UUID fact = UUID.randomUUID();
    UUID replyTo = UUID.randomUUID();
    String json = String.format("{ fact : '%s', comment : 'comment', replyTo : '%s' }", fact, replyTo);

    CreateFactCommentRequest request = getMapper().readValue(json, CreateFactCommentRequest.class);
    assertEquals(fact, request.getFact());
    assertEquals("comment", request.getComment());
    assertEquals(replyTo, request.getReplyTo());
  }

  @Test
  public void testRequestValidationFailsOnNotNull() {
    Set<ConstraintViolation<CreateFactCommentRequest>> violations = getValidator().validate(new CreateFactCommentRequest());
    assertEquals(2, violations.size());
    assertPropertyInvalid(violations, "fact");
    assertPropertyInvalid(violations, "comment");
  }

  @Test
  public void testRequestValidationFailsOnSize() {
    Set<ConstraintViolation<CreateFactCommentRequest>> violations = getValidator().validate(new CreateFactCommentRequest()
            .setFact(UUID.randomUUID())
            .setComment(""));
    assertEquals(1, violations.size());
    assertPropertyInvalid(violations, "comment");
  }

  @Test
  public void testRequestValidationSucceeds() {
    assertTrue(getValidator().validate(new CreateFactCommentRequest()
            .setFact(UUID.randomUUID())
            .setComment("comment")
    ).isEmpty());
  }

}
