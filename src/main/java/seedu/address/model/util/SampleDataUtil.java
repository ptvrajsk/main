package seedu.address.model.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PdfBook;
import seedu.address.model.ReadOnlyPdfBook;
import seedu.address.model.pdf.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PdfBook} with sample data.
 */
public class SampleDataUtil {
    public static Pdf[] getSamplePdfs() {
        Path sampleA = Paths.get("src", "test", "data", "JsonAdaptedPdfTest", "a.pdf");
        Path sampleB = Paths.get("src","test", "data", "JsonAdaptedPdfTest", "b.pdf");
        return new Pdf[] {
            new Pdf(new Name(sampleA.getFileName().toString()), new Directory(sampleA.getParent().toAbsolutePath().toString()),
                    new Size(Long.toString(sampleA.toFile().getTotalSpace())), getTagSet("RandomTag1")),
            new Pdf(new Name(sampleB.getFileName().toString()), new Directory(sampleB.getParent().toAbsolutePath().toString()),
                    new Size(Long.toString(sampleB.toFile().getTotalSpace())), getTagSet("colleagues"))
//            new Pdf(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
//                new Address("Blk 30 Geylang Street 29, #06-40"),
//                getTagSet("friends")),
//            new Pdf(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
//                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
//                getTagSet("colleagues", "friends")),
//            new Pdf(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
//                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
//                getTagSet("neighbours")),
//            new Pdf(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
//                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
//                getTagSet("family")),
//            new Pdf(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
//                new Address("Blk 47 Tampines Street 20, #17-35"),
//                getTagSet("classmates")),
//            new Pdf(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
//                new Address("Blk 45 Aljunied Street 85, #11-31"),
//                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyPdfBook getSamplePdfBook() {
        PdfBook sampleAb = new PdfBook();
        for (Pdf samplePdf : getSamplePdfs()) {
            sampleAb.addPdf(samplePdf);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
