package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PdfBook;
import seedu.address.model.ReadOnlyPdfBook;
import seedu.address.model.pdf.Pdf;

/**
 * An Immutable PdfBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate pdf(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPdfBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyPdfBook source) {
        persons.addAll(source.getPdfList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PdfBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PdfBook toModelType() throws IllegalValueException {
        PdfBook addressBook = new PdfBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Pdf pdf = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPdf(pdf)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPdf(pdf);
        }
        return addressBook;
    }

}
