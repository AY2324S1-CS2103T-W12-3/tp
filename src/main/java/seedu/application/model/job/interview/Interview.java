package seedu.application.model.job.interview;

import java.util.Objects;

import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;

/**
 * Represents an Interview in the application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    public final InterviewType interviewType;
    public final InterviewDateTime interviewDateTime;
    public final InterviewAddress interviewAddress;

    /**
     * Constructs a {@code Interview}.
     *
     * @param interviewType A valid interview type.
     * @param interviewDateTime A valid interview date and time.
     * @param interviewAddress A valid interview address.
     */
    public Interview(InterviewType interviewType, InterviewDateTime interviewDateTime,
                     InterviewAddress interviewAddress) {
        CollectionUtil.requireAllNonNull(interviewType, interviewDateTime, interviewAddress);
        this.interviewType = interviewType;
        this.interviewDateTime = interviewDateTime;
        this.interviewAddress = interviewAddress;
    }

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public InterviewDateTime getInterviewDateTime() {
        return interviewDateTime;
    }

    public InterviewAddress getInterviewAddress() {
        return interviewAddress;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview interview = (Interview) other;
        return interview.getInterviewType().equals(getInterviewType())
                && interview.getInterviewDateTime().equals(getInterviewDateTime())
                && interview.getInterviewAddress().equals(getInterviewAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewType, interviewDateTime, interviewAddress);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("interviewType", interviewType)
            .add("interviewDateTime", interviewDateTime)
            .add("interviewAddress", interviewAddress)
            .toString();
    }
}

