package com.kita.attributes;

import static com.kita.attributes.Email.*;
import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.kita.attributes.Email.EmailNullObject;

/**
 * But isn’t some code hard to test?
 * Yes, but only because that code has been designed to be hard to test.
 *  - Robert C. Martin
 *
 * @since   25.05.2018
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailTest {

	// @formatter:off
	private static final String VALID_MAIL_OF_JUSTUS_JONAS 	 = "Justus.Jonas" + AT_SIGN + "rockyBeach.com";
	private static final String INVALID_MAIL_OF_JUSTUS_JONAS = "Justus.JonasrockyBeach.com";
	private static final String VALID_MAIL_OF_PETER_SHAW 	 = "Peter.Shaw" + AT_SIGN + "rockyBeach.com";
	// @formatter:on

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testIsSerializable() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		@SuppressWarnings("cast")
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testIsValidWithValidValue() {
		boolean condition = Email.isValidAddress(VALID_MAIL_OF_JUSTUS_JONAS);
		assertTrue("email should be valid!", condition);
	}

	@Test
	public void testIsValidWithInvalidValue() {
		String eMailAddress = INVALID_MAIL_OF_JUSTUS_JONAS;
		boolean condition = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", condition);
	}

	@Test
	public void testIsValidWithIllegalNullValue() {
		boolean condition = Email.isValidAddress(null);
		assertFalse("email should be invalid.", condition);
	}

	@Test
	public void testIsValidWithAddressThatContainsASpace() {
		String eMailAddress = "abraham.van helsing@stoker.com";
		boolean condition = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", condition);
	}

	@Test
	public void testIsValidWithEmptyValue() {
		String eMailAddress = "";
		boolean condition = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", condition);
	}

	@Test
	public void testIsValidWithWhitespaceOnly() {
		String eMailAddress = "   ";
		boolean condition = Email.isValidAddress(eMailAddress);

		assertFalse("email '" + eMailAddress + "' should be invalid.", condition);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testNewInstance() {
		Class expected = EmailNullObject.class;

		Email sut = Email.newInstance();

		Class actual = sut.getClass();
		assertEquals("Instance creation was not correct!", expected, actual);
	}

	@Test
	public void testCreateInstanceWithValidEmailAdress() {
		String value = VALID_MAIL_OF_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@Test
	public void testCreateInstanceWithInvalidEmailAdress() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[email] has not a valid format!");

		String value = INVALID_MAIL_OF_JUSTUS_JONAS;
		Email email = Email.newInstance(value);

		assertEquals(value, email.toString());
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testCreateInstance_ForNullValue() {
		Class expected = EmailNullObject.class;

		Email sut = Email.newInstance(null);
		assertNotNull("Instance creation was not correct!", sut);

		Class actual = sut.getClass();
		assertEquals("Instance creation was not correct!", expected, actual);
	}

	@Test
	public void testCreateInstance_ForEmptyString() {
		Email sut = Email.newInstance("");

		boolean actual = sut.isEmpty();
		assertTrue("Instance creation was not correct!", actual);
	}

	@Test
	public void testSortByEmail() {
		Email email1 = Email.newInstance("Justus.Jonas@RockyBeach.com");
		Email email2 = Email.newInstance("Peter.Shaw@RockyBeach.com");

		int position = Email.sortByEmail(email1, email2);

		assertEquals("[position] not correct!", -6, position);
	}

	@Test
	public void testIsExternal_ForInternal() {
		Email sut = Email.newInstance("Forename.Surename@canda.com");

		boolean condition = sut.isExternal();

		assertFalse("[isExternal] not correct!", condition);
	}

	@Test
	public void testIsExternal_ForExternal() {
		Email sut = Email.newInstance("Forename.Surename@rockyBeach.com");

		boolean condition = sut.isExternal();

		assertTrue("[isExternal] not correct!", condition);
	}

	@Test
	public void testIsExternal_ForNullObjectPattern() {
		Email sut = Email.newInstance();

		boolean condition = sut.isExternal();

		assertTrue("[isExternal] not correct!", condition);
	}

	@Test
	public void testGetDomainPartWhenIsEmpty_False() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String expected = "rockyBeach.com";

		String condition = sut.getDomainPart();
		assertEquals("Domain part is not correct.", expected, condition);
	}

	@Test
	public void testSetLocalPartWithIllegalNullValue() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Local part must not be 'null'.");

		sut.setLocalPart(null);
	}

	@Test
	public void testSetLocalPartWithIllegalEmptyValue() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Local part [] could not be part of a valid email address.");

		sut.setLocalPart("");
	}

	@Test
	public void testSetLocalPartToValidFullName() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String newLocalPart = "bob.andrews";
		Email expected = Email.newInstance(newLocalPart + AT_SIGN + "rockyBeach.com");

		sut.setLocalPart(newLocalPart);
		assertEquals("Local part of email has not been set correctly.", expected, sut);
	}

	@Test
	public void testSetLocalPartToValidSingleName() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		String newLocalPart = "kent";
		Email expected = Email.newInstance(newLocalPart + AT_SIGN + sut.getDomainPart());

		sut.setLocalPart(newLocalPart);
		assertEquals("Local part of email has not been set correctly.", expected, sut);
	}

	@Test
	public void testCreateFromLocalAndDomainPartWithValidValues() {
		String localPart = "martin.fowler";
		String domainPart = "refactoring.com";
		Email expected = Email.newInstance(localPart + AT_SIGN + domainPart);

		Email actual = Email.createFromLocalAndDomainPart(localPart, domainPart);
		assertEquals("Email has not been correctly composed.", expected, actual);
	}

	@Test
	public void testCreateFromLocalAndDomainPartWithInvalidLocalPart_null() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[localPart] must not be 'null'.");

		String domainPart = "refactoring.com";

		Email.createFromLocalAndDomainPart(null, domainPart);
	}

	@Test
	public void testCreateFromLocalAndDomainPartWithInvalidDomainPart_null() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[domainPart] must not be 'null'.");

		String localPart = "martin.fowler";

		Email.createFromLocalAndDomainPart(localPart, null);
	}

	@Test
	public void testClone() {
		Email sut = Email.newInstance(VALID_MAIL_OF_PETER_SHAW);
		Email clone = sut.clone();

		assertNotSame("Cloning failed.", sut, clone);
		assertEquals("Cloning failed.", sut, clone);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.isEmpty();
		assertFalse("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Email sut = Email.newInstance(null);

		boolean condition = sut.isEmpty();
		assertTrue("[condition] for isEmpty is not correct!", condition);
	}

	@Test
	public void testToString_ForNullValue() {
		Email sut = Email.newInstance();
		String expected = "";

		String actual = sut.toString();

		assertEquals("String representation not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		int hashCode = sut.hashCode();

		assertEquals(-865138838, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.equals(sut);

		assertTrue("values should be equal!", condition);
	}

	@Test
	public void testEqualsWithNull() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.equals(null);

		assertFalse("values should not be equal!", condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.equals(new String());

		assertFalse("values should not be equal!", condition);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		sut.value = null;
		Email secondSut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_OF_PETER_SHAW);

		boolean condition = sut.equals(secondName);

		assertFalse("values should not be equal!", condition);
	}

	@Test
	public void testEqualsWithSameValues() {
		Email sut = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);
		Email secondName = Email.newInstance(VALID_MAIL_OF_JUSTUS_JONAS);

		boolean condition = sut.equals(secondName);

		assertTrue("values should be equal!", condition);
	}

}