package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_MULTIPLE = "All indexes must be unique"
                + " and a non-zero unsigned integer.";
    public static final String INVALID_TAGTYPE = "The tag type is invalid!";
    public static final String INVLAID_FIELD_TYPE = "The single field type is invalid!";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified any of the indexes are invalid (not non-zero unsigned integer).
     */
    public static Index[] parseIndexes(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        boolean isMultipleIndex = StringUtil.containsMultipleIndex(trimmedIndex);
        boolean isAllValidIntegers = isMultipleIndex && StringUtil.isAllNonZeroUnsignedInteger(trimmedIndex);
        boolean isValidMultipleIndex = isAllValidIntegers && StringUtil.isAllUniqueIntegers(trimmedIndex);

        if (!isMultipleIndex && !StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        } else if (isMultipleIndex && !isValidMultipleIndex) {
            throw new ParseException(MESSAGE_INVALID_INDEX_MULTIPLE);
        }

        return getIndexes(trimmedIndex);
    }

    /**
     * Transforms a string of valid one-based indexes into an array of {@code Index}.
     */
    private static Index[] getIndexes(String trimmedIndex) {
        String[] oneBasedArr = trimmedIndex.split(" ");
        Index[] indexArr = new Index[oneBasedArr.length];
        for (int i = 0; i < oneBasedArr.length; i++) {
            indexArr[i] = Index.fromOneBased(Integer.parseInt(oneBasedArr[i]));
        }
        return indexArr;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a  {@code List<String> } into a {@code List<Name>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any single list item has more than one word
     */
    public static List<Name> parseNames(List<String> list) throws ParseException {
        requireNonNull(list);
        final Set<Name> set = new HashSet<>();
        for (String value : list) {
            if (value.split(" ").length > 1) {
                throw new ParseException(FindCommandParser.MULTIPLE_WORDS);
            }
            set.add(parseName(value));
        }
        return new ArrayList<>(set);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code List<String> } into a {@code List<Phone>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any single list item has more than one word.
     */
    public static List<Phone> parsePhones(List<String> list) throws ParseException {
        requireNonNull(list);
        final Set<Phone> set = new HashSet<>();
        for (String value : list) {
            if (value.split(" ").length > 1) {
                throw new ParseException(FindCommandParser.MULTIPLE_WORDS);
            }
            set.add(parsePhone(value));
        }
        return new ArrayList<>(set);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code List<String> } into a {@code List<Address>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any single list item has more than one word.
     */
    public static List<Address> parseAddresses(List<String> list) throws ParseException {
        requireNonNull(list);
        final Set<Address> set = new HashSet<>();
        for (String value : list) {
            if (value.split(" ").length > 1) {
                throw new ParseException(FindCommandParser.MULTIPLE_WORDS);
            }
            set.add(parseAddress(value));
        }
        return new ArrayList<>(set);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code List<String> } into a {@code List<Email>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if any single list item has more than one word.
     */
    public static List<Email> parseEmails(List<String> list) throws ParseException {
        requireNonNull(list);
        final Set<Email> set = new HashSet<>();
        for (String value : list) {
            if (value.split(" ").length > 1) {
                throw new ParseException(FindCommandParser.MULTIPLE_WORDS);
            }
            set.add(parseEmail(value));
        }
        return new ArrayList<>(set);
    }


    /**
    * Parses a {@code String tag} into a {@code Tag}.
    * Leading and trailing whitespaces will be trimmed.
    *
    * @throws ParseException if the given {@code tag} is invalid.
    */
    public static Tag parseTag(String tag, String type) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toLowerCase();

        switch (type) {
        case Tag.EDUCATION:
            if (!Education.isValidTagName(trimmedTag)) {
                throw new ParseException(Education.MESSAGE_CONSTRAINTS);
            }
            return new Education(trimmedTag);
        case Tag.INTERNSHIP:
            if (!Internship.isValidTagName(trimmedTag)) {
                throw new ParseException(Internship.MESSAGE_CONSTRAINTS);
            }
            return new Internship(trimmedTag);
        case Tag.MODULE:
            if (!Module.isValidTagName(trimmedTag)) {
                throw new ParseException(Module.MESSAGE_CONSTRAINTS);
            }
            return new Module(trimmedTag);
        case Tag.CCA:
            if (!Cca.isValidTagName(trimmedTag)) {
                throw new ParseException(Cca.MESSAGE_CONSTRAINTS);
            }
            return new Cca(trimmedTag);
        default:
            throw new ParseException(INVALID_TAGTYPE);
        }
    }

    /**
    * Parses {@code Collection<String> tags} into a {@code List<Tag>}.
    */
    public static List<Tag> parseTags(Collection<String> tags, String type) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName, type));
        }
        return new ArrayList<>(tagSet);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     * Returns an empty ArrayList if the tags list is [""], this is the case that the tag list is to be cleared.
     */
    public static List<Tag> parseTagsForEdit(Collection<String> tags, String type) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        // This is the case that the tag list is meant to be cleared
        if (tags.size() == 1 && tags.contains("")) {
            return new ArrayList<>();
        }
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName, type));
        }
        return new ArrayList<>(tagSet);
    }
}
